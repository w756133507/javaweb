package com.boot.youzan.youzan.as;

import com.alibaba.fastjson.JSON;
import com.boot.youzan.youzan.Db.GetToken;
import com.boot.youzan.youzan.Db.JDBCBatch;
import com.boot.youzan.youzan.dto.MemMemberYouZan;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;

import com.youzan.cloud.open.sdk.core.client.auth.Token;

import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanScrmCustomerCreate;
import com.youzan.cloud.open.sdk.gen.v3_0_0.model.*;
import com.youzan.cloud.open.sdk.gen.v3_1_2.api.YouzanScrmCustomerSearch;
import com.youzan.cloud.open.sdk.gen.v3_1_2.model.YouzanScrmCustomerSearchParams;
import com.youzan.cloud.open.sdk.gen.v3_1_2.model.YouzanScrmCustomerSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;


/***** @description: 客戶信息(注意引入的版本)
 　　* @author 王泽辉
 　　* @date 2020/8/31 12:02
 　　*/
@Slf4j
public class HxYouZanDataImport {
    private final static Logger logger = LoggerFactory.getLogger(HxYouZanDataImport.class);

    public static void main(String[] args) {
        //  getCustomerList();
        //  importCustomerNewList();
        //  importCustomerHistoryList();
        importCustomerNewList();
        //bachCreateCustomer();



    }

    private static Token token;
    private static DefaultYZClient yzClient;

    static {
        yzClient = new DefaultYZClient();
        token = new Token(Objects.requireNonNull(GetToken.getToken(yzClient)).getAccessToken());
    }

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
    private static ExecutorService executor = new ThreadPoolExecutor(10, 20, 2000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);

    private static void bachCreateCustomer() {
        List<MemMemberYouZan> list = JDBCBatch.getMemMemberInfo();
        System.out.println("源集合数量：" + list.size());
        //Collections.synchronizedList()包装
        long start = System.currentTimeMillis();
        for (MemMemberYouZan data : list) {
            executor.submit(() -> createCustomer(data));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(2000, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("时间:" + (end - start) + "ms");
    }

    private static void createCustomer(MemMemberYouZan sync) {
        try {
            YouzanScrmCustomerCreate youzanScrmCustomerCreate = new YouzanScrmCustomerCreate();
            //创建参数对象,并设置参数
            YouzanScrmCustomerCreateParams youzanScrmCustomerCreateParams = new YouzanScrmCustomerCreateParams();
            //客户电话
            youzanScrmCustomerCreateParams.setMobile(sync.getMemberMobile());
            YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsCustomercreate customerParams = new YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsCustomercreate();
            //客户姓名
            customerParams.setName(sync.getMemberNm());
            //生日
            customerParams.setBirthday(sync.getMemberBirthday());
            //性别，0：未知；1：男；2：女
            customerParams.setGender("00002".equals(sync.getMemberSex()) ? Short.parseShort("2") : Short.parseShort("1"));
            //备注
            customerParams.setRemark(sync.getMemberCd());
            youzanScrmCustomerCreateParams.setCustomerCreate(customerParams);
            customerParams.setAscriptionKdtId(Integer.parseInt(Content.GRANT_ID));
            youzanScrmCustomerCreate.setAPIParams(youzanScrmCustomerCreateParams);
            YouzanScrmCustomerCreateResult response = yzClient.invoke(youzanScrmCustomerCreate, token, YouzanScrmCustomerCreateResult.class);

            if (!response.getSuccess()) {
                logger.info("有赞调用接口失败：{}", JSON.toJSONString(response));
                //客户已存在不记录
                if (response.getCode() != 143001027) {
                    sync.setRemark(JSON.toJSONString(response));
                    JDBCBatch.insertMemMemberErrorInfo(sync);
                }
            } else {
                YouzanScrmCustomerCreateResult.YouzanScrmCustomerCreateResultData result = response.getData();
                sync.setYouzanAccountId(result.getAccountId());
                //成功的记录下来
                JDBCBatch.insertMemMemberImportSuccessInfo(sync);
                logger.info("新增会员同步到有赞接口调用成功：{}", JSON.toJSONString(response));
            }
        } catch (SDKException e) {
            logger.info("新增会员同步到有赞接口出现异常：", e);
            sync.setRemark(JSON.toJSONString(e.getMessage()));
            JDBCBatch.insertMemMemberErrorInfo(sync);
        }
    }

    /**
     * @desc 导入最新数据(currentDate表示时间节点以后的所有数据)
     * @author 王泽辉
     * @date 2020/11/20 14:13
     */
    public static void importCustomerNewList() {
        try {
            //2019-09-20 00:00:00
            //2020-11-19 09:22:49
            YouzanScrmCustomerSearch youzanScrmCustomerSearch = new YouzanScrmCustomerSearch();
            //默认要导的数量
            long totalNum = 148742;
            //默认要导的时间
            long currentDate = 1605748969L;
            Long max = JDBCBatch.getMaxCredateAt();
            if (max != null && max != 0) {
                currentDate = max + 1;
                totalNum = getTotalNum(currentDate, "createdAtStart");
            }
            int pageSize = 50;
            int pageMax = 200;
            long num = totalNum >= pageMax * pageSize ? (totalNum / pageSize / pageMax) : 0;
            //每页50条，每200页分批入库一次
            for (int j = 0; j <= num; j++) {
                max = JDBCBatch.getMaxCredateAt();
                if (max != null && max != 0) {
                    currentDate = max + 1;
                    totalNum = getTotalNum(currentDate, "createdAtStart");
                }
                System.out.println("剩余要导入的数据：" + totalNum);
                List<YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultRecordlist> batchList = new ArrayList<>();
                for (int i = 0; i < pageMax; i++) {
                    YouzanScrmCustomerSearchParams youzanScrmCustomerSearchParams = new YouzanScrmCustomerSearchParams();
                    youzanScrmCustomerSearchParams.setPage(i + 1);
                    youzanScrmCustomerSearchParams.setPageSize(pageSize);
                    youzanScrmCustomerSearchParams.setHasMobile(true);
                    //youzanScrmCustomerSearchParams.setCreatedAtStart(1568908800L);
                    youzanScrmCustomerSearchParams.setCreatedAtStart(currentDate);
                    youzanScrmCustomerSearch.setAPIParams(youzanScrmCustomerSearchParams);
                    YouzanScrmCustomerSearchResult result = yzClient.invoke(youzanScrmCustomerSearch, token, YouzanScrmCustomerSearchResult.class);
                    YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultData customerSearch = result.getData();
                    List<YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultRecordlist> recordList = customerSearch.getRecordList();
                    if (recordList == null || recordList.size() == 0) {
                        break;
                    }
                    System.out.println("第" + (i + 1) + "页：");
                    System.out.print(JSON.toJSONString(recordList));
                    batchList.addAll(recordList);
                    if (i % 50 == 0) {
                        Thread.sleep(1000);
                    }
                }
                JDBCBatch.insertBath(batchList);
            }

        } catch (Exception e) {
            logger.error("异常：", e);
        }
    }

    public static void importCustomerHistoryList() {
        try {
            System.out.println(token.getAccessToken());
            //2019-09-20 00:00:00
            //2020-11-19 09:22:49
            YouzanScrmCustomerSearch youzanScrmCustomerSearch = new YouzanScrmCustomerSearch();
            long totalNum = 148742;
            long currentDate = 1605748969L;
            Long min = JDBCBatch.getMinCredateAt();
            if (min != null && min != 0) {
                currentDate = min - 1;
                totalNum = getTotalNum(currentDate, "createdAtEnd");
            }
            int pageSize = 50;
            int pageMax = 200;
            long num = totalNum >= pageMax * pageSize ? (totalNum / pageSize / pageMax) : 0;
            //每页50条，每200页分批入库一次
            for (int j = 0; j <= num; j++) {
                min = JDBCBatch.getMinCredateAt();
                if (min != null && min != 0) {
                    currentDate = min - 1;
                    totalNum = getTotalNum(currentDate, "createdAtEnd");
                }
                System.out.println("剩余要导入的数据：" + totalNum);
                List<YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultRecordlist> batchList = new ArrayList<>();
                for (int i = 0; i < pageMax; i++) {
                    YouzanScrmCustomerSearchParams youzanScrmCustomerSearchParams = new YouzanScrmCustomerSearchParams();
                    youzanScrmCustomerSearchParams.setPage(i + 1);
                    youzanScrmCustomerSearchParams.setPageSize(pageSize);
                    youzanScrmCustomerSearchParams.setHasMobile(true);
                    //youzanScrmCustomerSearchParams.setCreatedAtStart(1568908800L);
                    youzanScrmCustomerSearchParams.setCreatedAtEnd(currentDate);
                    youzanScrmCustomerSearch.setAPIParams(youzanScrmCustomerSearchParams);
                    YouzanScrmCustomerSearchResult result = yzClient.invoke(youzanScrmCustomerSearch, token, YouzanScrmCustomerSearchResult.class);
                    YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultData customerSearch = result.getData();
                    List<YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultRecordlist> recordList = customerSearch.getRecordList();
                    if (recordList == null || recordList.size() == 0) {
                        break;
                    }
                    System.out.println("第" + (i + 1) + "页：");
                    System.out.print(JSON.toJSONString(recordList));
                    batchList.addAll(recordList);
                    if (i % 50 == 0) {
                        Thread.sleep(1000);
                    }
                }
                JDBCBatch.insertBath(batchList);
            }

        } catch (Exception e) {
            logger.error("异常：", e);
        }
    }

    private static long getTotalNum(long createdAt, String type) {
        try {
            YouzanScrmCustomerSearch youzanScrmCustomerSearch = new YouzanScrmCustomerSearch();
            YouzanScrmCustomerSearchParams youzanScrmCustomerSearchParams = new YouzanScrmCustomerSearchParams();
            youzanScrmCustomerSearchParams.setPage(1);
            youzanScrmCustomerSearchParams.setPageSize(50);
            youzanScrmCustomerSearchParams.setHasMobile(true);
            if ("createdAtEnd".equals(type)) {
                youzanScrmCustomerSearchParams.setCreatedAtEnd(createdAt);
            } else {
                youzanScrmCustomerSearchParams.setCreatedAtStart(createdAt);
            }
            youzanScrmCustomerSearch.setAPIParams(youzanScrmCustomerSearchParams);
            YouzanScrmCustomerSearchResult result = yzClient.invoke(youzanScrmCustomerSearch, token, YouzanScrmCustomerSearchResult.class);
            YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultData customerSearch = result.getData();
            return customerSearch.getTotal();
        } catch (SDKException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * @Description 获取会员列表信息
     * @Author 王泽辉
     * @Date 2020/8/30 13:53
     */
    public static void getCustomerList() {
        try {
            //2019-09-20 00:00:00
            //2020-11-18 19:38:11
            YouzanScrmCustomerSearch youzanScrmCustomerSearch = new YouzanScrmCustomerSearch();
            //创建参数对象,并设置参数18188086660
            YouzanScrmCustomerSearchParams youzanScrmCustomerSearchParams = new YouzanScrmCustomerSearchParams();
            youzanScrmCustomerSearchParams.setPage(200);
            youzanScrmCustomerSearchParams.setPageSize(50);
            youzanScrmCustomerSearchParams.setHasMobile(true);
            //youzanScrmCustomerSearchParams.setCreatedAtStart(1568908800L);
            youzanScrmCustomerSearchParams.setCreatedAtEnd(1605748969L);
            youzanScrmCustomerSearch.setAPIParams(youzanScrmCustomerSearchParams);
            YouzanScrmCustomerSearchResult result = yzClient.invoke(youzanScrmCustomerSearch, token, YouzanScrmCustomerSearchResult.class);
            System.out.println(JSON.toJSONString(result));
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }
}
