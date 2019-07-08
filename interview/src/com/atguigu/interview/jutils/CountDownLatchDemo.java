package com.atguigu.interview.jutils;

import com.atguigu.interview.enums.CountDownLatchEnum;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @create 2019-07-01 8:22
 *
 * 秦灭 六国 一统华夏
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);


        for (int i = 1; i <= 6 ; i++) {
            final int tempInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国 被灭了");
                countDownLatch.countDown();

            }, CountDownLatchEnum.forEachEnum(tempInt).getRetMsg()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 秦 一统 华夏");


    }
}
