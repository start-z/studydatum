/**
 * @author zhou
 * @since 2023/10/12
 * description: 内部类
 */
public class InnerClassSingleon {
    public InnerClassSingleon() {
    }

    public InnerClassSingleon getInstance() {
        return InnerClassSingleonHander.innerClassSingleon;
    }

    private static class InnerClassSingleonHander {
        private static InnerClassSingleon innerClassSingleon = new InnerClassSingleon();
    }
}
