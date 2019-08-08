package com.atguigu.interview.jvm;

/**
 * @author Administrator
 * @create 2019-08-08 11:33
 * 模拟栈溢出异常
 * 面试的坑 java.lang.StackOverflowError是异常还是error -->error
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        test();
    }

}
