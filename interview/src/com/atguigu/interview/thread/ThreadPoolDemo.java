package com.atguigu.interview.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @create 2019-08-05 8:25
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5线程
        ExecutorService pool1 = Executors.newSingleThreadExecutor();//一池一线程

        try{
            for (int i = 1; i <=100 ; i++) {
                pool1.execute(() ->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //注意一定要关闭连接
            pool1.shutdown();
        }

    }
}

