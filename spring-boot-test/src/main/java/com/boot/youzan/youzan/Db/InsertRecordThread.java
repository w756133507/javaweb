package com.boot.youzan.youzan.Db;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
/**
*@Description 向list添加数据
*@Author 王泽辉
*@Date 2020/11/19 15:55
*/
public class  InsertRecordThread {
    //引入依赖包，创建线程池
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
    private static ExecutorService executor = new ThreadPoolExecutor(10,20,200L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 10000000; i >= 1; i--) {
            list.add(0);
        }
        System.out.println("源集合数量："+list.size());
        /**
         * Collections.synchronizedList()包装
         */
        List<Integer> newCollList = Collections.synchronizedList(new ArrayList<>());
        long start = System.currentTimeMillis();
        for (Integer integer : list){
            executor.submit(()->{
                newCollList.add(integer+1);
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

