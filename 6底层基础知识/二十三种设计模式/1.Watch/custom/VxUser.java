package custom;

/**
 * @author zhou
 * @since 2023/10/11
 * description:  公众号用户基类
 */
public class VxUser implements VxUserBase {
    /**
     * 初始化时订阅相关公众号
     *
     * @param vxOfficialAccount 相关公众号类
     */
    public VxUser(VxOfficialAccount vxOfficialAccount) {
        vxOfficialAccount.subscribe(this);
    }

    @Override
    public void update(String msg) {
        System.out.println("修改相关数据," + msg);
    }
}
