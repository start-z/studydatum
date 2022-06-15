/**
 * @author zhouhelong
 * @creat 2022-06-15 22:22
 * @description:
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步调用   CompletableFuture
 */
public class Asyc {
    public static void main(String[] args) {
        asycIsVoid();
    }

    public static void asycVoid() {
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "cccccc");
        });
    }

    /**
     * 有返回值
     */
    public static void asycIsVoid() {
        try {
            System.out.println(CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "cccccc");
                return 1024;
            }).whenComplete((t, u) -> {
                System.out.println(t);
                System.out.println(u);
            }).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
