package com.atguigu.interview.jvm;

/**
 * @author Administrator
 * @create 2019-07-11 15:02
 * 使用jps 查看 Java程序的进程号
 * jps -l
 * 使用jinfo 查看Java程序的详细信息
 * 如何查看一个正在运行的Java程序的某个jvm参数是否开启，具体值是多少
 * jinfo -flag PrintGCDetails 进程号
 * +表示开启 -表示关闭
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("....HelloGC");

        Thread.sleep(Integer.MAX_VALUE);
    }
}
