/**
 * @author zhou
 * @since 2023/10/12
 * description: 线程安全懒汉模式
 */
public class SecHungerSingleon {
    public SecHungerSingleon secHungerSingleon = null;

    public SecHungerSingleon() {
    }

    /**
     * 普通懒汉式-线程不安全
     *
     * @return 普通懒汉式
     */
    public SecHungerSingleon createSecHungerSingleon() {
        if (secHungerSingleon == null) {
            secHungerSingleon = new SecHungerSingleon();
        }
        return secHungerSingleon;
    }

    /**
     * 普通懒汉式-线程安全-使用synchronized修饰方法 效率低下
     *
     * @return 普通懒汉式
     */
    public synchronized SecHungerSingleon createSecSecHungerSingleon() {
        if (secHungerSingleon == null) {
            secHungerSingleon = new SecHungerSingleon();
        }
        return secHungerSingleon;
    }

    /**
     * 普通懒汉式-线程安全-使用同步代码块
     * 但是这种方式还是不安全-当A和B都执行这段代码时，由于A实例判断没有实例bean就往下走同步代码块,而b这个时候也进来判断 就导致A和B都创建了实例
     *
     * @return 普通懒汉式
     */
    public SecHungerSingleon createSycSecHungerSingleon() {
        if (secHungerSingleon == null) {
            synchronized (SecHungerSingleon.class) {
                secHungerSingleon = new SecHungerSingleon();
            }
        }
        return secHungerSingleon;
    }

    /**
     * 普通懒汉式-线程安全-使用同步代码块
     * 但是这种方式还是不安全-当A和B都执行这段代码时，由于A实例判断没有实例bean就往下走同步代码块,而b这个时候也进来判断 就导致A和B都创建了实例
     *
     * @return 普通懒汉式
     */
    public SecHungerSingleon createSycSecTwoHungerSingleon() {
        if (secHungerSingleon == null) {
            synchronized (SecHungerSingleon.class) {
                if (secHungerSingleon == null) {
                    secHungerSingleon = new SecHungerSingleon();
                }
            }
        }
        return secHungerSingleon;
    }
}
