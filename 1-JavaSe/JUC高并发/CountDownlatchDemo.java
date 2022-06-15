import java.util.concurrent.*;

/**
 * @author zhouhelong
 * @creat 2022-06-15 15:39
 * @description:
 */
public class CountDownlatchDemo {

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo();
    }

    public static void SemaphoreDemo() {
        Semaphore semaphore = new Semaphore(3);//三个车位
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();//发放许可证
                System.out.println(Thread.currentThread().getName() + "得到了车位");
                    TimeUnit.SECONDS.sleep(4);//停车4秒后离开
                    System.out.println(Thread.currentThread().getName() + "----------离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放车位
                }
            }, String.valueOf(i)).start();

        }

    }
    public static void CyclibarrierDemo() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("七龙珠");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "得到了龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
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

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "离开了教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长进行锁门");
    }
}
