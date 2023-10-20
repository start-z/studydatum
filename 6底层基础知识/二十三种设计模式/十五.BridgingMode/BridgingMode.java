/**
 * @author zhou
 * @since 2023/10/20
 * description: 设计模式- 桥接模式
 */
public class BridgingMode {
    public interface DrawAPI {
        public void drawCircle(int radius, int x, int y);
    }

    public abstract class Shape {
        protected DrawAPI drawApi;

        protected Shape(DrawAPI drawApi) {
            this.drawApi = drawApi;
        }

        abstract void draw();

    }

    public class Circle extends Shape {
        private int x, y, radius;

        public Circle(int x, int y, int radius, DrawAPI drawAPI) {
            super(drawAPI);
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        @Override
        void draw() {
            drawApi.drawCircle(x, y, radius);
        }
    }

    public class RedCircle implements DrawAPI {

        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("我是红色的圆");
        }

        ;
    }

    public class BlackCircle implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("我是黑色的圆");
        }
    }
}
