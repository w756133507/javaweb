package com.boot.youzan.youzan.Db;

import com.boot.youzan.youzan.dto.MemMemberDto;
import com.boot.youzan.youzan.dto.MemMemberYouZan;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.youzan.cloud.open.sdk.gen.v3_1_2.model.YouzanScrmCustomerSearchResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
*@Description TODO
*@Author 王泽辉
*@Date 2020/11/18 20:15
*/
public class JDBCBatch {
//    public static void main(String[] args) throws Exception {
//        Connection connection = JDBCUtils.getConnection();
//        //设置自动提交关闭
//        connection.setAutoCommit(false);
//        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mem_member_youzan_t(member_nm,member_sex,member_birthday,member_mobile,youzan_account_id) VALUES (?,?,?,?,?)");
//        Date memberBirthday=new java.sql.Date(System.currentTimeMillis());
//        for (int i = 1; i <= 5000; i++) {
//            preparedStatement.setString(1, "张三" + i);
//            preparedStatement.setString(2, "0001");
//            preparedStatement.setDate(3,memberBirthday);
//            preparedStatement.setString(4, "123" + i);
//            preparedStatement.setString(5, "123" + i);
//            preparedStatement.addBatch();
//            if (i % 1000 == 0) {
//                preparedStatement.executeBatch();
//                connection.commit();
//                preparedStatement.clearBatch();
//            }
//        }
//       // preparedStatement.executeUpdate();
//        connection.commit();
//        preparedStatement.clearBatch();
//    }
    public static void insert(YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultRecordlist data){
        try {
            Connection connection = JDBCUtils.getConnection();
            //设置自动提交关闭
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mem_member_youzan_t(member_nm,member_sex,member_birthday,member_mobile,youzan_account_id,created_at) VALUES (?,?,?,?,?,?)");
                Date memberBirthday=new java.sql.Date(System.currentTimeMillis());
                preparedStatement.setString(1, data.getName()==null?data.getShowName():data.getName());
                preparedStatement.setString(2, "2".equals(data.getGender())?"0002":"0001");
                preparedStatement.setDate(3,memberBirthday);
                preparedStatement.setString(4, data.getMobile());
                preparedStatement.setLong(5, data.getYzUid());
                preparedStatement.setLong(6, data.getCreatedAt());
                preparedStatement.addBatch();
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
        }catch (Exception e){
          e.printStackTrace();
        }finally {
        }
    }
    public static void insertBath(List<YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultRecordlist> list){
        try {
            Connection connection = JDBCUtils.getConnection();
            //设置自动提交关闭
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mem_member_youzan_t(member_nm,member_sex,member_birthday,member_mobile,youzan_account_id,created_at) VALUES (?,?,?,?,?,?)");
                Date memberBirthday=new java.sql.Date(System.currentTimeMillis());
                System.out.println("准备入库数量："+list.size());
                for (int i = 0; i < list.size(); i++){
                    YouzanScrmCustomerSearchResult.YouzanScrmCustomerSearchResultRecordlist data=list.get(i);
                    preparedStatement.setString(1, data.getName()==null?data.getShowName():data.getName());
                    preparedStatement.setString(2, data.getGender()==null||data.getGender()==2?"0002":"0001");
                    preparedStatement.setDate(3,memberBirthday);
                    preparedStatement.setString(4, data.getMobile());
                    preparedStatement.setLong(5, data.getYzUid());
                    preparedStatement.setLong(6, data.getCreatedAt());
                    preparedStatement.addBatch();
                    if (i % 1000 == 0) {
                        preparedStatement.executeBatch();
                        preparedStatement.clearBatch();
                    }
                }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
          e.printStackTrace();
        }finally {
        }
    }
    public static Long getMinCredateAt(){
        try {
            Connection connection = JDBCUtils.getConnection();
            //设置自动提交关闭
            PreparedStatement preparedStatement = connection.prepareStatement("select min(created_at) as created_at from mem_member_youzan_t");
            ResultSet rs=preparedStatement.executeQuery();
            rs.first();
            return rs.getLong("created_at");
        }catch (Exception e){
          e.printStackTrace();
        }
        return null;
    }
    public static Long getMaxCredateAt(){
        try {
            Connection connection = JDBCUtils.getConnection();
            //设置自动提交关闭
            PreparedStatement preparedStatement = connection.prepareStatement("select max(created_at) as created_at from mem_member_youzan_t");
            ResultSet rs=preparedStatement.executeQuery();
            rs.first();
            return rs.getLong("created_at");
        }catch (Exception e){
          e.printStackTrace();
        }
        return null;
    }

    public static List<MemMemberYouZan> getMemMemberInfo(){
        List<MemMemberYouZan> list=new ArrayList<>();
        try {
            Connection connection = JDBCUtils.getConnection();
            //设置自动提交关闭
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT member_cd, member_nm, member_sex, member_birthday, member_mobile\n" +
                    "FROM mem_member_t a\n" +
                    "WHERE a.channel_type = 'M1'\n" +
                    "\tAND (a.member_mobile IS NOT NULL OR a.member_mobile!='')\n" +
                    "\tAND (a.member_mobile REGEXP \"^[1][3,4,5,6,7,8,9][0-9]{9}$\" )\n" +
                    "\tAND NOT EXISTS (\n" +
                    "\t\tSELECT 1\n" +
                    "\t\tFROM mem_member_youzan_t b\n" +
                    "\t\tWHERE a.member_mobile = b.member_mobile\n" +
                    "\t)\n" +
                    "\tAND NOT EXISTS (\n" +
                    "\t\tSELECT 1\n" +
                    "\t\tFROM mem_member_youzan_success_t b\n" +
                    "\t\tWHERE a.member_cd = b.member_cd\n" +
                    "\t)\n" +
                    "\tAND member_id>2163566");
            ResultSet rs=preparedStatement.executeQuery();

            while(rs.next()){
                MemMemberYouZan memMemberYouZan=new MemMemberYouZan();
                memMemberYouZan.setMemberCd(rs.getString("member_cd"));
                memMemberYouZan.setMemberNm(rs.getString("member_nm"));
                memMemberYouZan.setMemberSex(rs.getString("member_sex"));
                memMemberYouZan.setMemberBirthday(rs.getString("member_birthday"));
                memMemberYouZan.setMemberMobile(rs.getString("member_mobile"));
                list.add(memMemberYouZan);
            }
        }catch (Exception e){
          e.printStackTrace();
        }
        return list;
    }
    public static void insertMemMemberErrorInfo(MemMemberYouZan data){
        try {
            Connection connection = JDBCUtils.getConnection();
            //设置自动提交关闭
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mem_member_youzan_error_t(member_nm,member_sex,member_birthday,member_mobile,member_cd,remark) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, data.getMemberNm());
            preparedStatement.setString(2, data.getMemberSex());
            preparedStatement.setString(3,data.getMemberBirthday());
            preparedStatement.setString(4, data.getMemberMobile());
            preparedStatement.setString(5, data.getMemberCd());
            preparedStatement.setString(6, data.getRemark());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void insertMemMemberImportSuccessInfo(MemMemberYouZan  data){
        try {
            Connection connection = JDBCUtils.getConnection();
            //设置自动提交关闭
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mem_member_youzan_success_t(member_cd,youzan_account_id) VALUES (?,?)");
            preparedStatement.setString(1, data.getMemberCd());
            preparedStatement.setString(2, data.getYouzanAccountId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
    private static ExecutorService executor = new ThreadPoolExecutor(10,20,200L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);
    public static void main(String[] args) {
        List<MemMemberYouZan> list=getMemMemberInfo();
        System.out.println("源集合数量："+list.size());
        /**
         * Collections.synchronizedList()包装
         */
        List<MemMemberDto> newCollList = Collections.synchronizedList(new ArrayList<>());
        long start = System.currentTimeMillis();
        for (MemMemberYouZan data : list){
            executor.submit(()->{
                MemMemberDto memMemberDto=new MemMemberDto();
                memMemberDto.setMemberCd(data.getMemberCd());
                newCollList.add(memMemberDto);
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(6, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("时间:"+(end-start)+"ms");
        System.out.println("newCollList新集合数量:"+newCollList.size());
    }


}
