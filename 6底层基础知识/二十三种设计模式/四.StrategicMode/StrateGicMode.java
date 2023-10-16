/**
 * @author zhou
 * @since 2023/10/12
 * description: 策略模式
 */
public class StrateGicMode {


    public interface write {
        void write();
    }

    public interface change {
        void change();
    }

    public interface eat {
        void eat();
    }

    public interface run {
        void run();
    }

    public class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void run() {
        }

        public void eat() {
        }

        public void write() {
        }

        public void change() {
        }
    }

    public class newUser {
        private String name;
        private write write;
        private change change;
        private eat eat;
        private run run;

        public StrateGicMode.write getWrite() {
            return write;
        }

        public void setWrite(StrateGicMode.write write) {
            this.write = write;
        }

        public StrateGicMode.change getChange() {
            return change;
        }

        public void setChange(StrateGicMode.change change) {
            this.change = change;
        }

        public StrateGicMode.eat getEat() {
            return eat;
        }

        public void setEat(StrateGicMode.eat eat) {
            this.eat = eat;
        }

        public StrateGicMode.run getRun() {
            return run;
        }

        public void setRun(StrateGicMode.run run) {
            this.run = run;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class UserA extends User {
        @Override
        public void setName(String name) {
            System.out.println("111");
        }

        @Override
        public void run() {
            super.run();
        }

        @Override
        public void eat() {
            super.eat();
        }

        @Override
        public void write() {
            super.write();
        }

        @Override
        public void change() {
            super.change();
        }
    }
}
