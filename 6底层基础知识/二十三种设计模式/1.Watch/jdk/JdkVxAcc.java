package jdk;

import java.util.Observable;

/**
 * @author zhou
 * @since 2023/10/11
 * description: 实现源码主题基类
 */
public class JdkVxAcc extends Observable {
    private String msg;


    public String getMsg() {
        return msg;
    }


    /**
     * 主题更新
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
        setChanged();
        notifyObservers();
    }
}
