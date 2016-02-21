package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Benjamin Winterberg
 */
public class Executors1 {

    public static void main(String[] args) {
        test1(2);
//        test1(7);
    }

    private static void test1(long seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
                String name = Thread.currentThread().getName();
                System.out.println("task finished: " + name);
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        });
        stop(executor);
    }

    static void stop(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown(); //waits for currently running tasks to finish 没有马上结束
            System.out.println("waits for currently running tasks to finish");
            
//            try {
//				Thread.sleep(2500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
            
            //executor.awaitTermination(5, TimeUnit.SECONDS); //等待
            
            System.out.println("waiting finish");

            
        }
//        catch (InterruptedException e) {
//            System.err.println("termination interrupted");
//        }
        finally 
        {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();  //立即结束
            System.out.println("shutdown finished");
        }
    }
}
