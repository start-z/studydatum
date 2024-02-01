import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouhelong
 * @creat 2022-06-15 15:39
 * @description:
 */
public class CountDownlatchDemo {

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo();
//        countDownDemo();
//        CyclibarrierDemo();

    }

    public static void SemaphoreDemo() {
        Semaphore semaphore = new Semaphore(3);//三个车位
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                try {
                    if (semaphore.tryAcquire(1, 8, TimeUnit.SECONDS)) {
                        System.out.println(threadName + "得到了车位");//发放许可证
                        try {
                            TimeUnit.SECONDS.sleep(4);//停车4秒后离开
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        semaphore.release();//释放车位
                        System.out.println(threadName + "----------离开了车位");
                    } else {
                        System.out.println(threadName + "来到了停车场,等待8s申请停车资格发现没有直接跑路");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }

    /**
     * 栅格计数  满足指定条件后输出
     */
    public static void CyclibarrierDemo() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("已经集齐了七龙珠");
        });

        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + "得到了龙珠");
                try {
                    System.out.println(name + "到达了屏障点,等待其他人员获取龙珠");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }


    /**
     * CountDownlatch计数为0时释放线程  并且调用wait方法的不会被阻塞
     */
    public static void countDownDemo() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "离开了教室");
//                System.out.println(countDownLatch.getCount());
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长进行锁门");
    }
}
