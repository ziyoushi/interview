package com.atguigu.interview.jvm;

import java.util.Random;

/**
 * @author Administrator
 * @create 2019-08-08 10:12
 * 栈管运行 
 * 堆管内存
 * test2演示 oom java heap space
 */
public class StackDemo {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        test2();
    }

    private static void test2() {

        long l = Runtime.getRuntime().maxMemory();

        Runtime.getRuntime().totalMemory();

        //cpu核数
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);

        String str = "hello world";
        while (true){
            str += str + new Random().nextInt(88888888) + new Random().nextInt(9999999);
        }

    }
}
