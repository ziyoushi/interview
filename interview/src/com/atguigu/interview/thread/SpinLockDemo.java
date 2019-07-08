package com.atguigu.interview.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Administrator
 * @create 2019-06-30 16:48
 *
 * 写一个自旋锁
 *
 */
public class SpinLockDemo {

    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){

        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in ");
        while (!atomicReference.compareAndSet(null,thread)){

        }

    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoke myUnlock");
    }

}

class SplinLockDemoTest{

    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{

            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnlock();

        },"A").start();

        try { TimeUnit.SECONDS.sleep( 1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnlock();
        },"B").start();

    }
}
