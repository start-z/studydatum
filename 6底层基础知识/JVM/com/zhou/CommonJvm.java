package zhou;

/**
 * @author zhou
 * @since 2024/3/20
 * description:
 */
public class CommonJvm {

    public static void main(String[] args) {
//        new ReferenceCountingGC().testGC();
//        测试gc
//        for (int i = 0; i < 10000; i++) {
//            String temp = new String("Temp String " + i);
//            temp = null;
//        }
//        System.gc(); // 显式触发垃圾回收   -XX:+PrintHeapAtGC -Xms10m -Xmx10m
//        System.out.println("系统回收完成");

//     测试大对象直接进入老年代  特别注意当对象大于老年代的空间大小时，是不会分配到老年代的 -XX:+UseSerialGC -XX:+PrintHeapAtGC -Xms20m -Xmx20m -XX:PretenureSizeThreshold=3145728
//        int  _1MB =1024 * 1024;
//        byte[] bytes;
//         bytes = new byte[_1MB * 10];
//         System.gc();

//    Thread.getAllStackTraces().forEach((key,value)-> System.out.println(value.));

    }




    /**
     * jdk7以前，intern()方法会把首次遇到的字符串实例复制到永久代的字符串常量池
     * 中存储, StringBuilder是放在堆内存上面 所以jdk7以前的版本编译都是false
     * jdk7以后 常量池在堆上，随意str1比较会为true,而str2为假的原因是java是英文字母。
     * 在builder构建的时候已经出现在了常量池中,而intern方法是返回值,而不是对象的引用，所以值！=引用。
     */
    public  void  jdk7New(){
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

    /**
     * testGC()方法执行后，objA和objB会不会被GC呢？
     * @author zzm
     */
    public static class ReferenceCountingGC {
        public Object instance = null;
        private static final int _1MB = 1024 * 1024;
        /**
         * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否有回收过
         */
        private byte[] bigSize = new byte[2 * _1MB];
        public void testGC() {
            CommonJvm.ReferenceCountingGC objA = new CommonJvm.ReferenceCountingGC();
            CommonJvm.ReferenceCountingGC objB = new CommonJvm.ReferenceCountingGC();
            objA.instance = objB;
            objB.instance = objA;
            objA = null;
            objB = null;
            // 假设在这行发生GC，objA和objB是否能被回收？
            System.gc();
        }
    }
}
