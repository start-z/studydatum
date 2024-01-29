/**
 * @author zhouhelong
 * @creat 2022-06-15 10:45
 * @description:
 */
public class JUCTest {

    private static int TICKET = 30;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (TICKET != 0) {
                    salesofTickets();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }, String.valueOf(i)).start();
        }
    }


    /**
     * 案例 如下 三个售票员 卖三十张票 需要保证线程安全
     */
    public synchronized static void salesofTickets() {
        if (TICKET > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了票还剩下" + --TICKET);
        }else{
            System.out.println("票已卖完");
        }
    }
    /**
     * 用户线程和守护线程
     */
    public void guardAndUserThread() {
        Thread demo = new Thread(() -> {
            //  isDaemon 表示是用户线程还是守护线程
            System.out.println(Thread.currentThread().isDaemon());
            while (true) {

            }
        });
        demo.setDaemon(true);
        demo.start();
        System.out.println(Thread.currentThread().getName());
    }

    ;
}
