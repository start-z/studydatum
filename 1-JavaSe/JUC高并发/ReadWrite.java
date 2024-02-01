import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhouhelong
 * @description: 读写锁
 * @deprecated 2022-06-15 16:52
 */
public class ReadWrite {
    public static void main(String[] args) {
        demotion(false);
    }

    /**
     * 读写锁降级
     */
    public  static void demotion(Boolean  isTest) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        int i = 1;

        // 锁降级  在有写锁的情况下还能加上读锁
        if (!isTest){

            //写
            writeLock.lock();
            i++;
            System.out.println("我在写");
            //读上锁
            readLock.lock();
            System.out.println(i);
            //释放写锁
            writeLock.unlock();
            //释放读锁
            readLock.unlock();
        }else {
            //读  可以看到执行这里由于没有获取到解锁-读锁所以导致线程死锁 没有输出i
            readLock.lock();
            i++;
            System.out.println("我在读");
            //写上锁
            writeLock.lock();
            System.out.println(i);
            //释放写锁
            writeLock.unlock();
            //释放读锁
            readLock.unlock();
        }

    }


}
