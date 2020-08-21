package com;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/***
����һ����֧
**/
public class Test {


    /**
     * ʵ����һ�����У������е�����Ϊ10
     */
    private static BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        ScheduledExecutorService product = Executors.newScheduledThreadPool(1);
        Random random = new Random();
        product.scheduleAtFixedRate(() -> {
            int value = random.nextInt(101);
            try{
                blockingQueue.offer(value);  //offer()�������������е�β������ֵ 
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }, 0, 200, TimeUnit.MILLISECONDS);  //ÿ100����ִ���߳�

        new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(2000);
					 Thread.sleep(2000);
                    System.out.println("��ʼȡֵ2");
                    List<Integer> list = new LinkedList<>();
                    blockingQueue.drainTo(list);  //drainTo()�������е�ֵȫ���Ӷ������Ƴ�������ֵ����Ӧ����
                    list.forEach(System.out::println);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
