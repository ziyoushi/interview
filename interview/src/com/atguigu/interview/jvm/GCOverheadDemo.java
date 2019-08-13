package com.atguigu.interview.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @create 2019-08-13 8:33
 * 演示 OOM：GC OverHead limit exceeded
 * 98%的内存用来GC 但是GC效果还很差
 *
 * GC回收时间过长会抛出OutOfMemoryError
 * 超过98%的时间用来做GC回收不到2%的堆内存，连续多次GC都只回收不到2%的极端情况
 *
 * jvm参数配置
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=10m
 *
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class GCOverheadDemo {
    public static void main(String[] args) {

        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true){

                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("***************"+i);
            e.printStackTrace();
            throw e;
        }
    }
}
