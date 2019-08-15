package com.atguigu.interview.jvm.oom;

/**
 * @author Administrator
 * @create 2019-08-15 9:39
 * oom:unable to create new native thread
 * 在高并发请求服务器时出现
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {

        for (int i =0 ;;i++){

            System.out.println("i*************"+i);

            new Thread(()->{

                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            },""+i).start();
        }
    }
}
