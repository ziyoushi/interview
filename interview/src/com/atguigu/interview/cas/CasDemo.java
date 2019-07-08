package com.atguigu.interview.cas;

import java.util.concurrent.atomic.AtomicInteger;

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



    }

}
