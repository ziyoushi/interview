package com.atguigu.interview.thread;

/**
 * @author Administrator
 * @create 2019-06-30 16:01
 *
 * 验证synchronized为可重入锁
 * 生活的case：主人已经进入大门，他再使用厨房时，虽然
 * 厨房也有锁，但是进入厨房不需要再使用其他的锁
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(()->{
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{

            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
        System.out.println();
        System.out.println();
        System.out.println();

        //验证ReentrantLock为可重入锁
        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");

        t3.start();
        t4.start();

    }
}

class Phone implements Runnable{

    public synchronized void sendSms() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoke sendSms");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t ........invoke sendEmail");
    }

    @Override
    public void run() {
        get();
    }

    private void get() {
        System.out.println(Thread.currentThread().getName()+"\t invoke get");
        set();
    }

    private void set() {
        System.out.println(Thread.currentThread().getName()+"\t *************invoke set");
    }
}
