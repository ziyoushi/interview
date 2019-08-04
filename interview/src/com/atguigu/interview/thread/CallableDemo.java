package com.atguigu.interview.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @create 2019-08-04 14:37
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread thread = new Thread(futureTask,"A");
        thread.start();

        Integer result1 = 100;

        //cas思想 自旋锁
        while (!futureTask.isDone()){

        }

        Integer result2 = futureTask.get();

        System.out.println(Thread.currentThread().getName()+"\t 执行完成"+(result1+result2));

    }
}

class MyThread implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        System.out.println("...come in call()");
        try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;
    }
}