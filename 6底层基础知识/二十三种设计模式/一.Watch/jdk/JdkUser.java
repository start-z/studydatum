package jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zhou
 * @since 2023/10/11
 * description: 用户实现类
 */
public class JdkUser implements Observer {

    public JdkUser(Observable observable) {
        observable.addObserver(this);
    }

    public static void main(String[] args) {
        JdkVxAcc acc = new JdkVxAcc();
        JdkUser jdkUser = new JdkUser(acc);
        acc.setMsg("123");
    }

    @Override
    public void update(Observable o, Object arg) {
        JdkVxAcc jdkVxAcc = (JdkVxAcc) o;
        System.out.println("由于公众号状态更新,我接受到了相关的信息，所以我被打印出来了");
    }
}
