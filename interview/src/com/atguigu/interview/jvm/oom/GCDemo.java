package com.atguigu.interview.jvm.oom;

import java.util.Random;

/**
 * @author Administrator
 * @create 2019-08-15 11:13
 *
 * 使用串行GC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
 *
 * 使用并行GC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC
 * Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated
 * and will likely be removed in a future release
 * 默认不推荐在老年代使用 Serial old
 *
 * 使用并行回收GC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
 * 跟老年代的UseParallelOldGC可以互相激活 即老年代配置UseParallelOldGC新生代自动激活UseParallelGC
 *
 * 老年代使用CMS
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
 * 新生代会自动配置 UserParNewGC
 * -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
 * GC (CMS Initial Mark) 停其他线程
 * CMS-concurrent-mark-start 并行
 * remark 停停其他线程
 * concurrent mark-sweep 并行
 *
 *
 *
 */
public class GCDemo {
    public static void main(String[] args) {

        System.out.println("******GCHello ");

        try {

            String str = "atguigu";

            while (true){
                str += str + new Random().nextInt(88888888) + new Random().nextInt(9999999);
                str.intern();
            }

        }catch (Throwable e){
            e.printStackTrace();
        }

    }
}
