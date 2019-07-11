package com.atguigu.interview.thread;

/**
 * @author Administrator
 * @create 2019-07-11 20:17
 */
public class T {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();

        /**
         * Exception in thread "main" java.lang.IllegalThreadStateException
         * 	at java.lang.Thread.start(Thread.java:708)
         * 	at com.atguigu.interview.thread.T.main(T.java:11)
         */

    }
}
