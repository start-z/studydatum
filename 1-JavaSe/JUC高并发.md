# 															JUC高并发

## 1什么是juc？

### 1.1JUC简介

JUC是关于线程的，存放在java.util.concurent包下。这是一个处理线程的包。是在jdk1.5开始的。

### 1.2进程与线程

- 进程：系统进行**资源分配**和**调度**的基本单位。
- 线程： 操作系统能够进行运算调度的最小单位，被包含在进程中，一个进程包含多个线程。



### 1.3线程wait和sleep方法的区别

| 方法名 | 所属类 |     锁     |
| :----: | :----: | :--------: |
|  wait  | Object |   释放锁   |
| sleep  | Thread | 不会释放锁 |



### 1.4并发与并行

- 并发：多个资源在同一时间进行.
- 并行:多个资源在一段时刻中一起进行.



### 1.5管程(Minitor)

本质上是一把锁:是一种同步机制,在同一时间只有一个线程操作数据  保证数据安全.

### 1.6用户线程和守护线程

- 用户线程:  用户自定义线程   主线程结束后  用户线程为结束 jvm不会结束
- 守护线程:  例如垃圾回收     主线程结束后  守护线程结束  jvm结束

```


    public static void main(String[] args) {
       Thread   demo= new Thread(() -> {
            //  isDaemon 表示是用户线程还是守护线程
            System.out.println(Thread.currentThread().isDaemon());
            while (true){

            }
        });
       demo.setDaemon(true);
       demo.start();
        System.out.println(Thread.currentThread().getName());
    }
```



2LOCK接口

买票案例:

```
    private static int TICKET = 30;

    public static void main(String[] args) {
        new Thread(() -> {
            while (TICKET != 0) {
                salesofTickets();
                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "AA").start();
        new Thread(() -> {
            while (TICKET != 0) {
                salesofTickets();
            }
        }, "bb").start();
        new Thread(() -> {
            while (TICKET != 0) {
                salesofTickets();
            }

        }, "cc").start();
    }


    /**
     * 案例 如下 三个售票员 卖三十张票 需要保证线程安全
     */
    public synchronized static void salesofTickets() {
        if (TICKET > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了票还剩下" + TICKET--);
        }

    }

```



### 2.1lock与synchronized的区别

| 名称         |    类型    |                 释放锁机制                 |
| ------------ | :--------: | :----------------------------------------: |
| Lock         |   属于类   |       手动释放  产生异常会自动释放锁       |
| synchronized | 属于关键字 | 自动释放  产生异常不会自动释放锁  造成死锁 |
|              |            |                                            |



## 3线程间通信

虚假唤醒问题:

wait方法导致线程的变量出现问题  具体看p10

另外wait方法在哪里沉睡醒来的时候就会在哪里醒。



## 4线程间定制化通信

案例如下：

AA打印5次  BB打印10次

```
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
            Condition1.wait();
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
                Condition2.wait();
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
                Condition3.wait();
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

```





5集合线程不安全

案例如下：

```
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author zhouhelong
 * @creat 2022-06-15 12:17
 * @description:
 */
public class SyncList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

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

```

抛出异常ConcurrentModificationException：（表示线程不安全）

```
Exception in thread "Thread-1" java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:911)
	at java.util.ArrayList$Itr.next(ArrayList.java:861)
	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
	at java.lang.String.valueOf(String.java:2994)
	at java.io.PrintStream.println(PrintStream.java:821)
	at SyncList.lambda$main$1(SyncList.java:22)
	at java.lang.Thread.run(Thread.java:748)

进程已结束,退出代码0

```

List解决办法 ：

1. 使用vector类
2. 使用 Collections.synchronizedList();
3. .copyonwriteArrayList

Hashset解决办法：

1. 使用copyonwriteArraySet

HashMap解决方法：

1. ConcurrentHashMap



## 5多线程锁

synchronized锁实现同步的基础：java中每一个对象都可以作为锁

具体形式如下：

- 对于普通方法：锁对象为this本身

- 对于静态同步方法：锁对象为class对象。

- 对于同步方法快：琐是配置的对象。



### 5.1公平锁和非公平锁

- 非公平锁：线程饿死   效率高。
- 公平锁：每个线程都能够运行  效率相对比较低。



### 5.2可重入锁（递归锁）

例如进入家里  我们只需要打开家门的锁就可以自由进入家里面的房间就叫做**可重入锁**。

synchronized和lock都是可重入锁

synchronized代码示例如下：

```
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author zhouhelong
 * @creat 2022-06-15 12:17
 * @description:
 */
public class SyncList {
    public static void main(String[] args) {
        KeChong keChong = new KeChong();
        keChong.showKeCongSync();
    }


    
    
    public static class KeChong {
        private Object o = new Object();

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

```



lock代码示例如下：

```
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

```



### 5.3死锁

两个线程及以上为了抢夺资源而导致线程互相等待叫做**死锁**。



## 6Callable接口

Callable与Runable接口区别

| 接口名称 | 是否有返回值 | 是否抛出异常 | 实现方法 |
| -------- | ------------ | ------------ | -------- |
| Callable | 有           | 抛出         | call（） |
| Runable  | 无           | 不会抛出     | run（）  |

## 7JUC辅助类

### 7.1CountDownlatch（计数器）

情景问题：

 班长需要等教室里面的6ge同学走了才锁门。

失败案例如下 ： 

```
/**
 * @author zhouhelong
 * @creat 2022-06-15 15:39
 * @description:
 */
public class CountDownlatchDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "离开了教室");
            }, String.valueOf(i)).start();
        }
        System.out.println("班长进行锁门");
    }
}

```

![image-20220615154139718](http://inis.inis1719.cn/202206151541064.png)

使用CountDownlatch成功案例如下：

```
import java.util.concurrent.CountDownLatch;

/**
 * @author zhouhelong
 * @creat 2022-06-15 15:39
 * @description:
 */
public class CountDownlatchDemo {

    public static void main(String[] args) throws InterruptedException {
        /**
         * CountDownlatch计数为0时释放线程  并且调用wait方法的不会被阻塞
         */
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

```



### 7.2Cyclibarrier（循环栅栏）

线程之间互相等待 ，直到到达统一共同点后才释放  设置一个目标值  达到目标值才会往下执行。

情景如下：

悟空收集7龙珠

代码如下：

```
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
```



### 7.3Semaphore（信号灯）

线程获取许可证     别的线程没有许可证不可进入

情景如下：

六个汽车位停三个车位

代码如下：

```
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
```



## 8JUC读写锁

- 乐观锁：通过增加版本号version  来达到事务一致性。
- 悲观锁：每个操作都进行加锁。
- 表锁：操作一条记录的时候 将整张表都锁住。
- 行锁：只锁住当前行。



在行锁中是会发生死锁的

- 读锁：共享锁
- 写锁：独占锁

都会产生死锁

读写锁：一个资源可以被多个线程访问，但是不可能同时出现读写操作。

锁降级：将写入锁降低为读锁。

![image-20220615165033671](http://inis.inis1719.cn/202206151650759.png)

代码如下：

```
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
        //写上锁
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

```

![image-20220615170042303](http://inis.inis1719.cn/202206151700585.png)



另外读锁是不能转换为写锁的代码如下：

```
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
    public static void demotion() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        int i = 1;
        //读
        readLock.lock();

        System.out.println("我在读");
        //写上锁
        writeLock.lock();
        i++;
        System.out.println(i);
        //释放写锁
        writeLock.unlock();
        //释放读锁
        readLock.unlock();
    }


}

```

结果如下：

![image-20220615170016253](http://inis.inis1719.cn/202206151700722.png)



## 9JUC阻塞队列

![image-20220615204107652](http://inis.inis1719.cn/202206152041741.png)

- 队列为空时，获取元素操作被阻塞
- 队列满时，添加元素操作被阻塞

在多线程 模式中 所谓阻塞，在某些情况下线程被挂起（阻塞），一旦条件满足 ，被挂起的线程被唤起。

BlockingQueue（阻塞队列）

- ArrayBlockingQueue  基于数组的阻塞队列 维护定长数组  数组结构组成的阻塞队列
- LinkBlockAQueue：链表组成的队列
- DelayQueue：使用优先级队列实现的延迟无界阻塞队列。
- PriorityBlockingQueue:支持优先级排序的队列
- SunchronusQueue：不存储元素的阻塞队列  即存放一个元素的队列
- LinkedBlockingQueue：链表组成的双向队列



![image-20220615205852262](http://inis.inis1719.cn/202206152058362.png)



## 10线程池(Thread  pool)

### 10.1简介

一种线程模式。线程过多会带来**调度开销**，进而影响**性能*  ，线程池维护多个线程.保证内核的充分利用和防止过分调度.

优势:

1. 降低资源浪费
2. 提高相应速度
3. 提高线程可管理性

### 10.2线程池的创建方式

类为**Executors**

#### 10.2.1一池多线程

场景如下:

银行有五个柜台,需要办理10次业务.

代码如下:

```
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
```

#### 10.2.2一池一线程

```
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
```

#### 10.2.3一池可扩容线程

```
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

```



### 10.3底层原理(ThreadPoolExecutor)

```
   /**
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     * parameters and default thread factory and rejected execution handler.
     * It may be more convenient to use one of the {@link Executors} factory
     * methods instead of this general purpose constructor.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code corePoolSize < 0}<br>
     *         {@code keepAliveTime < 0}<br>
     *         {@code maximumPoolSize <= 0}<br>
     *         {@code maximumPoolSize < corePoolSize}
     * @throws NullPointerException if {@code workQueue} is null
     */
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), defaultHandler);
    }
```



### 10.4线程池底层工作流程

![image-20220615212357807](http://inis.inis1719.cn/202206152123909.png)

步骤如下

1. 常驻线程池
2. 阻塞队列
3. 开辟线程
4. 拒绝策略(当线程数满了以后 就会使用拒绝策略进行拒绝)



拒绝策略:

- AbortPolicy默认策略:抛出异常阻止系统正常运行
- CallerRunsPalicy调用者运行:不会抛弃任务也不会抛出异常 从哪里调用就回哪里去
- DiscardOldestPolicy抛弃最久任务:  抛弃等待时间最久的任务  然后把当前任务加入队列
- DiscardPolicy:默认丢掉无法处理的任务,不处理也不返回异常.





注意事项:

不推荐使用**Executors**创建线程池.  因为提供的线程池请求都为Integer.max个请求  太多请求了



## 11JUC分支合并框架Fork/Join

Fork/Join它可以将一个大的任务拆分成多个子任务进行并行处理,最后将子任务结果合并成最后的计算结果,并进行输出。Fork/Join 框架要完成两件事情:。

- Fork:把一个复杂任务进行分拆，大事化小。
- Join:把分拆任务的结果进行合并.

代码案例如下:

```
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhouhelong
 * @creat 2022-06-15 21:53
 * @description:
 */
public class ForkJoin {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//实现拆分对象
        MyFork myFork = new MyFork(0, 100);
        //创建分支合并对象
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.submit(myFork).get());
        pool.shutdown();

    }

    /**
     * x
     * 相加两个数并且相差的值大于10   数字 1到50
     * 拆分为  1-12   13-25  25-37   37-50
     */
    public static void forkJoin() {

    }

    public static class MyFork extends RecursiveTask<Integer> {
        private static final Integer VALUE = 10;//计算10以内的运算
        private  Integer begin;
        private  Integer end;
        private  Integer result = 0;

        public MyFork(Integer begin, Integer end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - begin <= VALUE) {
                for (Integer i = begin; i <= end; i++) {
                    //直接计算
                    result = result + i;
                }
            } else {
                //使用二分查找拆分
                int middle = (end + begin) / 2;
                //拆分左边
                ForkJoinTask<Integer> fork = new MyFork(begin, middle);
                //拆分右边
                ForkJoinTask<Integer> fork1 = new MyFork(middle + 1, end);
                fork.fork();
                fork1.fork();
                result = fork.join() + fork1.join();
            }
            return result;
        }

    }
}

```



## 12异步调度

- 同步: 和另一件事物一同做事
- 异步:自己做自己的事

CompletableFuture   线程异步调用接口

**异步调用有返回值案例**

```

```

**异步调用无返回值案例**

```

```

