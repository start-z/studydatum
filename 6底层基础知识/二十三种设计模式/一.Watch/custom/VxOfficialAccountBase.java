package custom;

/**
 * @author zhou
 * @since 2023/10/11
 * description: 公众号积累
 */
public interface VxOfficialAccountBase {

    /**
     * 订阅
     *
     * @param user 订阅一个用户
     */
    public void subscribe(VxUser user);

    /**
     * 取消订阅
     *
     * @param user 取消一个订阅用户
     */
    public void unsubscribe(VxUser user);

    /**
     * 通知所有的人并更新
     *
     * @param msg 发布的信息
     */
    public void noticeAllUser(String msg);

}
