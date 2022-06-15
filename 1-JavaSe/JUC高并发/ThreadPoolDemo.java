import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhouhelong
 * @creat 2022-06-15 21:10
 * @description:
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        Cached();
    }

    /**
     * 一池多线程
     */
    public static void Fixed() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "正在办理业务");
            });
        }
        service.shutdown();//将线程放回线程池
    }

    /**
     * 一池一线程
     */
    public static void Single() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "正在办理业务");
            });
        }
        service.shutdown();//将线程放回线程池
    }
    /**
     * 一池可扩容线程
     */
    public static void Cached() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "正在办理业务");
            });
        }
        service.shutdown();//将线程放回线程池
    }

}
