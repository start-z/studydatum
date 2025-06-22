package com.zhou;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demoTest {
private static  int ticket =20;//票数
    public static void main(String[] args) {
        Object o = new Object();
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                 while (ticket>0) {
                     lock.lock();
                     ticket--;
                     System.out.println(Thread.currentThread().getName() + finalI + "抢到票了，还剩" + ticket + "张票");
                     lock.unlock();
                     try {
                         Thread.sleep(2000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
            }).start();
        }
    }
}
