package custom;

/**
 * @author zhou
 * @since 2023/10/11
 * description: 用户自定义的公众号服务测试类
 */
public class userWatch {
    public static void main(String[] args) {
        VxOfficialAccount account = new VxOfficialAccount();
        VxUser vxUser1 = new VxUser(account);
        VxUser vxUser2 = new VxUser(account);
        account.setMsg("我通知了所有人");
    }
}
