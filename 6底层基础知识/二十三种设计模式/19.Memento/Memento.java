import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 * @since 2023/10/23
 * description: 设计模式-备忘录模式
 */
public class Memento {
    public static void main(String[] args) {
        Memento memento = new Memento();
        memento.test();
    }

    public void test() {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }

    ;

    /**
     * 当前对象的状态
     */
    public class MementoState {
        private String state;

        public MementoState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    /**
     * 创建存储对象
     */
    public class Originator {
        private String state;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public MementoState saveStateToMemento() {
            return new MementoState(state);
        }

        public void getStateFromMemento(MementoState Memento) {
            state = Memento.getState();
        }
    }

    /**
     * 保存历史记录
     */
    public class CareTaker {
        private List<MementoState> mementoList = new ArrayList<MementoState>();

        public void add(MementoState state) {
            mementoList.add(state);
        }

        public MementoState get(int index) {
            return mementoList.get(index);
        }
    }
}
