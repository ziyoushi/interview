package com.atguigu.interview.juc;

import com.atguigu.interview.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @create 2019-07-28 15:35
 * 生活的case 班长锁门
 * 历史的case 秦灭六国 一统华夏
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        //秦灭六国 一统华夏
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                countDownLatch.countDown();
            }, CountryEnum.for_countryEnum(i).getRetMessage()).start();
        }

        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"\t 秦灭六国 一统华夏");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"\t 班长统一锁门");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
