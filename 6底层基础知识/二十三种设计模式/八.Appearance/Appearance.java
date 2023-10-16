/**
 * @author zhou
 * @since 2023/10/12
 * description: 外观模式-我买了一台电视机，我想要使用他还需要打开音响、使用遥控板、操作电视机等等，如果我们频繁的创建相关的实体类在调用的话是比较麻烦的，我们可以写一个汇总类，然后在这个类中调用相关的操作。
 */
public class Appearance {
    private Televistion televistion;
    private Sound sound;

    public void open() {
        televistion.open();
        sound.open();
    }

    public interface Electron {
        void open();

        void close();
    }

    public class Televistion implements Electron {
        @Override
        public void open() {
            System.out.println("电视机打开");
        }

        @Override
        public void close() {
            System.out.println("电视机关闭");
        }
    }

    public class Sound implements Electron {
        @Override
        public void open() {
            System.out.println("音响打开");
        }

        @Override
        public void close() {
            System.out.println("音响关闭");
        }
    }
}
