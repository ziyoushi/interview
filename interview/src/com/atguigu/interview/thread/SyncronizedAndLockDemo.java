package com.atguigu.interview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @create 2019-08-04 11:01
 * <p>
 * 举例说明lock比synchronized好
 * lock可以精确唤醒线程
 * synchronize要么随机唤醒一个线程，要么全部唤醒
 * <p>
 * 练习：线程A打印5次 线程B打印10次 线程C打印15次
 * 严格按照A->B->C的顺序执行十轮
 */
public class SyncronizedAndLockDemo {
    public static void main(String[] args) {
        //线程操作资源类
        ShareResource resource = new ShareResource();

        for (int i = 1; i <= 10 ; i++) {
            new Thread(()->{
                resource.print5();
            },"A").start();
        }

        for (int i = 1; i <= 10 ; i++) {
            new Thread(()->{
                resource.print10();
            },"B").start();
        }

        for (int i = 1; i <= 10 ; i++) {
            new Thread(()->{
                resource.print15();
            },"C").start();
        }

    }
}

class ShareResource {

    private Integer num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {

        lock.lock();
        try {
            //判断 干活 修改标志位
            //使用while进行判断 防止虚假唤醒
            while (num != 1) {
                condition1.await();
            }

            //干活 打印5次
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            num = 2;

            //通知
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void print10() {
        lock.lock();
        try {
            //判断 干活 修改标志位
            //使用while进行判断 防止虚假唤醒
            while (num != 2) {
                condition2.await();
            }

            //干活 打印5次
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            num = 3;
            //交互
            condition3.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void print15() {
        lock.lock();
        try {

            //判断 干活 修改标志位
            //使用while进行判断 防止虚假唤醒
            while (num != 3) {
                condition3.await();
            }

            //干活 打印5次
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            num = 1;
            //交互
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}