package com.atguigu.interview.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @create 2019-06-30 12:30
 *
 * 比较并交换
 *
 */
public class CasDemo {

    private volatile int num = 10;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2019));


        System.out.println(atomicInteger.compareAndSet(5, 2014));

        atomicInteger.getAndIncrement();
//        atomicInteger.compareAndSet()

//        atomicInteger.getIntVolatile();
        new Thread();

        Lock lock = new ReentrantLock();

        try {
            //定时锁
            lock.tryLock(200, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AtomicInteger a = new AtomicInteger();

        AtomicReference atomicReference = new AtomicReference();

        A a1 = new A();

        AtomicStampedReference stampedReference = new AtomicStampedReference(a1,1);

        stampedReference.compareAndSet(null,null,1,1);

        //默认非公平锁 false
        ReentrantLock lock1 = new ReentrantLock(true);


    }

}

class A{

}