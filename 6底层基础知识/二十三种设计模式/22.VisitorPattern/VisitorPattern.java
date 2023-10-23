import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 * @since 2023/10/23
 * description: 设计模式-访问者模式
 * 不改变原有对象的算法或者数据 可以看到下面关于洗漱相关的步骤我们都交给了访问者的接口去定义具体的步骤
 */
public class VisitorPattern {
    public static void main(String[] args) {
        VisitorPattern pattern = new VisitorPattern();
        pattern.test();
    }

    public void test() {
        WashUp washUp = new WashUp();
        washUp.execute(new VisitorImp());
    }

    /**
     * 洗漱基类
     */
    public interface WashUpPart {
        void execute(Visitor visitor);
    }

    public interface Visitor {
        void execute(WashYourFace washYourFace);

        void execute(BrushTeeth brushTeeth);

        void execute(WashUp washUp);

    }

    /**
     * 洗脸
     */
    public class WashYourFace implements WashUpPart {
        @Override
        public void execute(Visitor visitor) {
            visitor.execute(this);
        }
    }

    /**
     * 刷牙
     */
    public class BrushTeeth implements WashUpPart {
        @Override
        public void execute(Visitor visitor) {
            visitor.execute(this);
        }
    }

    public class WashUp implements WashUpPart {
        private List<WashUpPart> partList = new ArrayList<WashUpPart>();

        public WashUp() {
            partList.add(new BrushTeeth());
            partList.add(new WashYourFace());
        }

        @Override
        public void execute(Visitor visitor) {
            partList.forEach(part -> {
                part.execute(visitor);
            });
            visitor.execute(this);

        }
    }

    public class VisitorImp implements Visitor {

        @Override
        public void execute(WashYourFace washYourFace) {
            System.out.println("自定义洗脸方式-我在洗脸");
        }

        @Override
        public void execute(BrushTeeth brushTeeth) {
            System.out.println("自定义刷牙方式-我在刷牙");
        }

        @Override
        public void execute(WashUp washUp) {
            System.out.println("自定义洗漱方式-我在洗漱");
        }
    }
}
