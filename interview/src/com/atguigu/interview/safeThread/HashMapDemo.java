package com.atguigu.interview.safeThread;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @create 2019-06-30 15:15
 */
public class HashMapDemo {
    public static void main(String[] args) {

        Map map = new ConcurrentHashMap();
        for (int i = 1; i <= 10 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }
}
