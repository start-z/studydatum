import java.util.HashMap;

/**
 * @author zhou
 * @since 2023/10/19
 * description: 设计模式-享元模式
 * 我们将创建一个 Shape 接口和实现了 Shape 接口的实体类 Circle。下一步是定义工厂类 ShapeFactory。
 * ShapeFactory 有一个 Circle 的 HashMap，其中键名为 Circle 对象的颜色。无论何时接收到请求，都会创建一
 * 个特定颜色的圆。ShapeFactory 检查它的 HashMap 中的 circle 对象，
 * 如果找到 Circle 对象，则返回该对象，否则将创建一个存储在 hashmap 中以备后续使用的新对象，并把该对象返回到客户端。
 */
public class EnyuanMode {

    public interface Shape {
        int id();

        String name();
    }

    public class EnyuanFactory {
        private HashMap<Integer, Shape> cache = new HashMap<Integer, Shape>();

        public Shape getShape(int id) {
            if (cache.containsKey(id)) {
                return cache.get(id);
            } else {
                Cirle cirle = new Cirle();
                cache.put(id, cirle);
                return cirle;
            }
        }
    }

    public class Cirle implements Shape {

        @Override
        public int id() {
            return 1;
        }

        @Override
        public String name() {
            return "big";
        }
    }
}
