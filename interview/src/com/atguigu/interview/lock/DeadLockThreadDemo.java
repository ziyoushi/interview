package com.atguigu.interview.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @create 2019-08-06 8:24
 */
public class DeadLockThreadDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLock(lockA,lockB),"AAA").start();
        new Thread(new HoldLock(lockB,lockA),"BBB").start();

    }
}

class HoldLock implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {

        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockA+"\t 想拥有"+lockB);

            try { TimeUnit.SECONDS.sleep( 1); } catch (InterruptedException e) { e.printStackTrace(); }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockB+"\t 想拥有"+lockA);
            }
        }
    }
}
