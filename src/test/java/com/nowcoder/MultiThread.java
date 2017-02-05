package com.nowcoder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;


/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/5 下午1:20
 */
public class MultiThread {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void testThread_00() {
        for (int i = 0; i < 10; i++) {
            new Mythread(i).start();
        }


    }

    public static void testThread_01() {
        for (int i = 0; i < 10; i++) {
            final int tid = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 10; i++) {
                            sleep(1000);
                            System.out.println(String.format("T%d:%d", tid, i));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }

    private static Object obj = new Object();

    public static void testSynchronized1() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 10; i++) {
                    sleep(1000);
                    System.out.println(String.format("T3%d", i));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void testSynchronized2() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 10; i++) {
                    sleep(1000);
                    System.out.println(String.format("T4%d", i));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testSynchronized() {
        for (int i = 0; i < 10; ++i) {
            try {
                testSynchronized1();
                testSynchronized2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    static void testBlockingQueue() {

        BlockingQueue<String> q = new ArrayBlockingQueue<String>(10);
        new Thread(new Producer(q)).start();
        new Thread(new Consumer(q), "consumer1").start();
        new Thread(new Consumer(q), "consumer2").start();


    }

    static void testExecutor() throws InterruptedException {

        // 线程池 java 编程
        //
//        ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Execute " + i);
                }
            }
        });


        service.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Execute2 " + i);
                }
            }
        });


        service.shutdown();

        while (!service.isTerminated()) {
            sleep(1000);
            System.out.println("wait for termination");
        }
    }


    static void testFuture() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                return 1;
            }
        });



        service.shutdown();
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
//        testThread_00();

//        testThread_01();

//        testSynchronized();

//        testBlockingQueue();

//        testExecutor();

        testFuture();
    }


}


class Mythread extends Thread {
    private int tid;

    public Mythread(int tid) {
        this.tid = tid;
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(String.format("T%d:%d", tid, i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
}


class Producer implements Runnable {

    private BlockingQueue<String> q;

    public Producer(BlockingQueue<String> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                sleep(1000);
                q.put(String.valueOf(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
}

class Consumer implements Runnable {

    private BlockingQueue<String> q;

    public Consumer(BlockingQueue<String> q) {
        this.q = q;
    }

    @Override
    public void run() {

        try {

            while (true) {
                System.out.println(Thread.currentThread().getName() + q.take());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
