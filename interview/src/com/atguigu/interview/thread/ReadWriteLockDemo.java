package com.atguigu.interview.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Administrator
 * @create 2019-07-26 12:58
 * 读写锁
 * volatile 三大特性 可见性 不支持原子性 禁止指令重拍
 * 线程操作资源类
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        //5个线程读 5个线程来写
        for (int i = 1; i <= 5 ; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5 ; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}

//资源类
class MyCache{
    //手写缓存 volatile这个必用 可见性
    private volatile Map<String,Object> map = new HashMap<>();
    //传统的lock细粒度需要加深 不足以满足 所以使用读写锁
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //写
    public void put(String str,Object obj){
        lock.writeLock().lock();
        try {

            System.out.println(Thread.currentThread().getName()+"\t 正在写 "+str);
            map.put(str,obj);
            try { TimeUnit.MICROSECONDS.sleep( 400); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t 写完成 ");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }

    }

    //读
    public void get(String string){

        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 开始读 "+string);
            try { TimeUnit.MICROSECONDS.sleep( 400); } catch (InterruptedException e) { e.printStackTrace(); }
            Object result = map.get(string);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成 "+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }

    }

}
