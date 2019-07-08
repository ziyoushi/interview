package com.atguigu.interview.safeThread;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Administrator
 * @create 2019-06-30 15:01
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList();

        for (int i = 1; i <= 30 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }

}
