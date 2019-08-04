package com.atguigu.interview.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @create 2019-08-04 11:47
 * 高并发版本的线程通信
 * 使用volatile CAS BlockQueue 原子引用 atomicInteger
 */
public class ProCusBlockDemo {
    public static void main(String[] args) throws Exception {

        MyResource resource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程开始启动");
            System.out.println();
            System.out.println();
            System.out.println();
            try {
                resource.pro();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Pro").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程开始启动");
            System.out.println();
            System.out.println();
            System.out.println();
            try {
                resource.cus();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"Cus").start();

        try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println();
        System.out.println();
        System.out.println();
        resource.stop();
    }
}

class MyResource {
    private volatile boolean flag = true;
    AtomicInteger atomicInteger = new AtomicInteger();
     BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        //日志排查
        System.out.println(blockingQueue.getClass().getName());
    }

    public void pro() throws Exception {

        String data = null;
        boolean retValue;
        while (flag) {

            //多线程版的i++
            data = atomicInteger.incrementAndGet() + "";

            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);

            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            try { TimeUnit.SECONDS.sleep( 1); } catch (InterruptedException e) { e.printStackTrace(); }

        }
        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停,flag=false生产线程结束");
    }

    public void cus() throws Exception {

        String result = null;
        while (flag) {

            //消费
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if (null == result || result.equalsIgnoreCase("") ){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 超时2L");
                return;
            }

            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");

        }

    }

    public void stop() throws Exception {
        this.flag = false;
    }
}