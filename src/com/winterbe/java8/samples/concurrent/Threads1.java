package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author Benjamin Winterberg
 */
public class Threads1 {

    public static void main(String[] args) {
//        test1();
        test2();
//        test3();
    }

    private static void test3() {
        Runnable runnable = () -> {
            try {
                System.out.println("Foo " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void test2() {
        Runnable runnable = () -> {
            try {
                System.out.println("begin " + Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println("end " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        
        Runnable r2 = () -> {
            try {
                System.out.println("begin " + Thread.currentThread().getName());
                Thread.sleep(500);
                System.out.println("end " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        



        Thread thread = new Thread(runnable);
        thread.start();
        
        
        Thread thread2 = new Thread(runnable);
        thread2.start();
// 		输出结果随机的。1000秒结束后，谁先被唤醒谁执行
//        begin Thread-0
//        begin Thread-1
//        end Thread-1
//        end Thread-0
//
//        begin Thread-0
//        begin Thread-1
//        end Thread-0
//        end Thread-1
        
        
    }

    private static void test1() {
        Runnable r2 = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("你好 " + threadName);
        };  //线程的执行的顺序由runnable定义的顺序决定
        
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();
//        thread.start(); //第二次startu


        Thread thread2 = new Thread(r2);
        thread2.start();
        
        System.out.println("Done!");
    }
}

