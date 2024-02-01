import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouhelong
 * @creat 2022-06-15 11:57
 * @description:
 */
public class LockCustomization {
    private static Lock lock = new ReentrantLock();
    private static Condition Condition1 = lock.newCondition();
    private static Condition Condition2 = lock.newCondition();
    private static Condition Condition3 = lock.newCondition();
    private static int flag = 1;//标志位


    public static void main(String[] args) {
        new Thread(() -> {
            try {
                print5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "aa").start();
        new Thread(() -> {
            try {
                print10();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "bb").start();
        new Thread(() -> {
            try {
                print15();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "cc").start();
    }


    public static void print5() throws InterruptedException {
        lock.lock();  //上锁
        try {
        while (flag != 1) {
            Condition1.await();
        }

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "开始打印");
            }
            flag = 2;
            Condition2.signal();
        }finally {
            lock.unlock();
        }


    }

    public static void print10() throws InterruptedException {
        lock.lock();  //上锁
        try {
            while (flag != 2) {
                Condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "开始打印");
            }

        flag = 3;
        Condition3.signal();
        } finally {
            lock.unlock();
        }
    }


    public static void print15() throws InterruptedException {
        lock.lock();  //上锁
        try {
            while (flag != 3) {
                Condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "开始打印");
            }
            flag=1;
            Condition1.await();
        } finally {
            lock.unlock();
        }
    }
}
