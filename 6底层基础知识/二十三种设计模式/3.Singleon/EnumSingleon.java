/**
 * 单例模式-枚举
 *
 * @author BJB314
 */
public class EnumSingleon {
    private EnumSingleon() {
    }


    public enum SingletonEnum {
        // 实例对象
        instance;
        /**
         * 实例化bean
         */
        private EnumSingleon enumSingleon;

        SingletonEnum() {
            enumSingleon = new EnumSingleon();
        }

        public EnumSingleon getInstance() {
            return instance.enumSingleon;
        }
    }
}
