package com.atguigu.interview.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @create 2019-07-28 22:56
 *
 * 传统的生产者 消费者模式
 * 心法
 * 1、 线程  操作(方法) 资源类
 * 2、判断 干活 通知
 * 防止虚假唤醒 --》在进行判断时 不能使用if 防止其他线程被虚假唤醒
 *
 */
public class ProduceCustomerDemo {
    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        //进行5轮 消费 生产
        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.increament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.decreament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}

class ShareData{

    private Integer num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //生产
    public void increament() throws Exception{

        lock.lock();
        try {
            //判断
            while (num != 0){
                condition.await();
            }
            //操作
            num++;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //消费
    public void decreament () throws Exception{

        lock.lock();
        try {
            //判断
            while (num == 0){
                condition.await();
            }
            //操作
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}