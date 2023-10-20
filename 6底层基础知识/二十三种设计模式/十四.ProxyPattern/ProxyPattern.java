/**
 * @author zhou
 * @since 2023/10/20
 * description: 设计模式-代理模式
 */
public class ProxyPattern {

    public interface image {
        void display();
    }

    public class RealImage implements image {

        @Override
        public void display() {
            System.out.println("我显示了");
        }
    }

    public class ProxyImage implements image {
        /**
         * 实现代理
         */
        @Override
        public void display() {
            RealImage image = new RealImage();
            image.display();
        }
    }


}
