package com.atguigu.interview.vol;

/**
 * @author Administrator
 * @create 2019-07-29 8:16
 *
 * DCL双端检索模式 不一定线程安全 因为 有指令重排
 */
public class SingletonDemo {

    //volatile禁止指令重排 保证多线程间的语义一致性
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){

    }

    //如果使用synchronized多线程情况下可以控制数据的一致性，但是并发性差 锁太中
    //使用DCL double check lock 双端检索机制 进来前后判断一次 加两个锁 企业认可的
    public static  SingletonDemo getInstance(){
        //就是在加锁前后进行判断
        //在多线程下 为了性能和效果 它会进行指令重排
        //可能出现异常的机率很低 但是还是存在问题
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    System.out.println(Thread.currentThread().getName()+"\t 构造方法SingletonDemo....");
                    instance = new SingletonDemo();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {

        //并发条件下
        for (int i = 1; i <= 10 ; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }

    }

}


