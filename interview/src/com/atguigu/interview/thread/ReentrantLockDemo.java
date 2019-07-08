package com.atguigu.interview.thread;

/**
 * @author Administrator
 * @create 2019-06-30 16:01
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

    }
}

class Phone{

    public synchronized void sendSms() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoke sendSms");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t ........invoke sendEmail");
    }

}
