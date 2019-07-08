package com.atguigu.interview.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @create 2019-07-08 19:22
 * 死锁
 */
public class DeadThreadDemo {
    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldThread(lockA,lockB),"A").start();
        new Thread(new HoldThread(lockB,lockA),"B").start();

    }
}

class HoldThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockA+"\t 尝试持有"+lockB);
            try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        synchronized (lockB){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockB+"\t 尝试持有"+lockA);
            try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
