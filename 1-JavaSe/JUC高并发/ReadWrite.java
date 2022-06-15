import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhouhelong
 * @description: 读写锁
 * @deprecated 2022-06-15 16:52
 */
public class ReadWrite {
    public static void main(String[] args) {
        demotion();
    }

    /**
     * 读写锁降级
     */
    public  static void demotion() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        int i = 1;
        //读
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
    }


}
