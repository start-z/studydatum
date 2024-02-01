import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouhelong
 * @creat 2022-06-15 12:17
 * @description:
 */
public class SyncList {
    public static void main(String[] args) {
        KeChong keChong = new KeChong();
        new Thread(keChong::showKeCongLock, "测试1").start();
        new Thread(keChong::showKeCongLock, "测试2").start();
    }


    public static class KeChong {
        private Object o = new Object();
        private Lock lock = new ReentrantLock();

        public void showKeCongLock() {
            lock.lock();
            try {
                try {
                    // 第一次上锁
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "临界点");
                    try {
                        // 第二次上锁
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + "我是中层");

                        try {
                            // 第三次上锁
                            lock.lock();
                            System.out.println(Thread.currentThread().getName() + "我是内层");
                        } finally {
                            // 第三次解锁
                            lock.unlock();
                        }
                    } finally {
                        //第二次解锁
//                        lock.unlock();
                    }

                } finally {
                    //第一次解锁
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }


        /**
         * sync可重入锁  并且会自动释放锁
         */
        public void showKeCongSync() {
            new Thread(() -> {
                synchronized (o) {
                    System.out.println("我是外层");
                    synchronized (o) {
                        System.out.println("我是中层");
                        synchronized (o) {
                            System.out.println("我是内层");
                        }
                    }


                }
            }).start();
        }


    }

    public void syncList() {
        ArrayList<String> list1 = new ArrayList<>();
        List<String> list = Collections.synchronizedList(list1);

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }
        }).start();

    }

}
