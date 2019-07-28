package com.atguigu.interview.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @create 2019-07-28 17:16
 * 队列 先进先出FIFO
 * 阻塞队列
 * 好处
 * 不得不阻塞的队列 怎么进行管理
 *
 * 生活的case：饭店坐满了没有座位 大堂经理 安排 客人去候客区等候
 *
 * 同步阻塞队列(SynchronousQueue) --》生产一个消费一个
 */
public class BlockQueueDemo {

    public static void main(String[] args) {

        BlockingQueue<String> blockingDeque = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t 1");
                blockingDeque.put("1");

                System.out.println(Thread.currentThread().getName()+"\t 2");
                blockingDeque.put("2");
                System.out.println(Thread.currentThread().getName()+"\t 3");
                blockingDeque.put("2");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{

            try {
                System.out.println(blockingDeque.take());
                try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace(); }

                System.out.println(blockingDeque.take());
                try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace(); }

                System.out.println(blockingDeque.take());
                try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace(); }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"B").start();

    }
}
