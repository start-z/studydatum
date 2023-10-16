package custom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 * @since 2023/10/11
 * description: vx公众号基类 一对多的关系
 */
public class VxOfficialAccount implements VxOfficialAccountBase {

    protected List<VxUser> users = new ArrayList<>();

    private String msg;

    /**
     * 订阅
     *
     * @param user 订阅的用户
     */
    @Override
    public void subscribe(VxUser user) {
        users.add(user);
    }

    /**
     * 取消订阅
     *
     * @param user 取消订阅的用户
     */
    @Override
    public void unsubscribe(VxUser user) {
        users.remove(user);
    }

    /**
     * 通知所有的订阅者并更新
     *
     * @param msg 发布的信息
     */
    @Override
    public void noticeAllUser(String msg) {
        users.forEach(user -> user.update(msg));
    }

    /**
     * 当微信号状态发生改变的时候通知所有人更新
     *
     * @param msg 相关消息
     */
    void setMsg(String msg) {
        this.msg = msg;
        noticeAllUser(msg);
    }
}
