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
        keChong.showKeCongLock();
    }


    public static class KeChong {
        private Object o = new Object();
        private Lock lock = new ReentrantLock();

        public void showKeCongLock() {
            lock.lock();
            try {

                new Thread(() -> {
                    try {
                        lock.lock();
                        System.out.println("我是外层");
                        try {
                            lock.lock();
                            System.out.println("我是中层");

                            try {
                                lock.lock();
                                System.out.println("我是内层");
                            } finally {
                                lock.unlock();
                            }
                        } finally {
                            lock.unlock();
                        }

                    } finally {
                        lock.unlock();
                    }
                }).start();
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
