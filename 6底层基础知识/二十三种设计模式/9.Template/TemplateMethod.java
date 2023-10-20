/**
 * @author zhou
 * @since 2023/10/16
 * description: 设计模式-模板方法
 */
public abstract class TemplateMethod {

    public abstract void palyer();

    public void record() {
        System.out.println("我开启电脑");
        System.out.println("我开始上网了");
        this.palyer();
        System.out.println("我结束上网");
        System.out.println("我关闭电脑");
    }

    public class A extends TemplateMethod {

        @Override
        public void palyer() {
            System.out.println("我玩lol");
        }
    }

    public class B extends TemplateMethod {

        @Override
        public void palyer() {
            System.out.println("我玩CF");
        }
    }
}
