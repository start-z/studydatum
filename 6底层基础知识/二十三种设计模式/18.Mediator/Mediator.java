/**
 * @author zhou
 * @since 2023/10/23
 * description: 中介者模式
 * 用一个中介对象来封装一系列的对象交互
 */
public class Mediator {
    public static void main(String[] args) {
        Mediator media = new Mediator();
        media.test();
    }

    public void test() {
        User user = new User("1", "我是测试类");
        user.shoMessage();
    }

    /**
     * 中介类
     */
    public static class Intermediary {

        public static void showMessage(User user, String message) {
            System.out.println("当前发送消息的人" + user.getName() + ",消息是" + message);
        }

    }

    public class User {
        private String name;
        private String message;

        public User(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public void shoMessage() {
            Intermediary.showMessage(this, message);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
