/**
 * @author zhou
 * @since 2023/10/23
 * description: 设计模式-解释器模式
 */
public class InterpreterPattern {
    public interface Expression {
        void count(int a, int b);
    }

    /**
     * 加法解释器
     */
    public class Addition implements Expression {

        @Override
        public void count(int a, int b) {
            System.out.println(a + b);
        }
    }

    /**
     * 减法解释器
     */
    public class Subtraction implements Expression {

        @Override
        public void count(int a, int b) {
            System.out.println(a - b);
        }
    }
}
