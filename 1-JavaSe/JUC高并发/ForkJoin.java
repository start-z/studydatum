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
