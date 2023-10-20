import java.util.Hashtable;

/**
 * @author zhou
 * @since 2023/10/19
 * description:  设计模式-原型工厂-本身属于创建性工厂，做法是接口实现对对象的拷贝,可节省内存和频繁构建对象。
 */
public class PrototypePlant {
    /**
     * 克隆基类
     */
    public abstract class Shape implements Cloneable {
        protected String type;
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        abstract void draw();

        @Override
        public Object clone() {
            Object clone = null;
            try {
                clone = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return clone;
        }
    }

    /**
     * 实现基类
     */
    public class Rectangle extends Shape {
        public Rectangle() {
            this.type = "Rectangle";
        }

        @Override
        public void draw() {
            System.out.println("Inside Rectangle::draw() method.");
        }
    }

    public class Square extends Shape {

        public Square() {
            type = "Square";
        }

        @Override
        public void draw() {
            System.out.println("Inside Square::draw() method.");
        }
    }

    public class Circle extends Shape {

        public Circle() {
            type = "Circle";
        }

        @Override
        public void draw() {
            System.out.println("Inside Circle::draw() method.");
        }
    }

    /**
     * 构建缓存类
     */
    public class ShapeCache {

        private Hashtable<String, Shape> shapeMap
                = new Hashtable<String, Shape>();

        public Shape getShape(String shapeId) {
            Shape cachedShape = shapeMap.get(shapeId);
            return (Shape) cachedShape.clone();
        }

        // 对每种形状都运行数据库查询，并创建该形状
        // shapeMap.put(shapeKey, shape);
        // 例如，我们要添加三种形状
        public void loadCache() {
            Circle circle = new Circle();
            circle.setId("1");
            shapeMap.put(circle.getId(), circle);

            Square square = new Square();
            square.setId("2");
            shapeMap.put(square.getId(), square);

            Rectangle rectangle = new Rectangle();
            rectangle.setId("3");
            shapeMap.put(rectangle.getId(), rectangle);
        }
    }

    /**
     * 测试类
     */
    public class PrototypePatternDemo {
        public void test() {
            ShapeCache shapeCache = new ShapeCache();
            shapeCache.loadCache();

            Shape clonedShape = (Shape) shapeCache.getShape("1");
            System.out.println("Shape : " + clonedShape.getType());

            Shape clonedShape2 = (Shape) shapeCache.getShape("2");
            System.out.println("Shape : " + clonedShape2.getType());

            Shape clonedShape3 = (Shape) shapeCache.getShape("3");
            System.out.println("Shape : " + clonedShape3.getType());
        }
    }
}
