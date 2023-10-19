/**
 * @author zhou
 * @since 2023/10/16
 * description: 设计模式-状态模式
 * 自动售卖机，有4种状态已投币、未投币、商品售完、商品售出。有四种动作投币、退币、转动手柄、出货。(由于需求可能会经常变动 所以采用状态模式进行设计)
 */
public class StateMode {

    /**
     * 定义动作基类-new
     */
    public interface BaseAction {
        void coined();

        void coinReturn();
    }

    /**
     * 售卖机新代码。-new
     */
    public class VendingMachineNew {
        // 已投币
        private final BaseAction coined = new Coined(this);
        // 未投币
        private final int unCoinedStatus = 1;
        // 售出
        private final int soedStatus = 2;
        // 售完
        private final int beSoldOutStatus = 3;
        private BaseAction currrentAction = coined;

        /**
         * 投币
         */
        public void coined() {
            currrentAction.coined();
        }

        /**
         * 退币
         */
        public void coinReturn() {
            currrentAction.coinReturn();
        }

    }

    /**
     * 投币状态专用管理-new - 后续状态代码不在编写 知其意即可。
     */
    public class Coined implements BaseAction {
        private VendingMachineNew vendingMachine;

        Coined(VendingMachineNew vendingMachine) {
            this.vendingMachine = vendingMachine;
        }

        @Override
        public void coined() {
            System.out.println("当前售卖机已经投过币了");

        }

        @Override
        public void coinReturn() {
            System.out.println("退币成功");
            // 这里应该是投币状态的实现类  懒得写了
            vendingMachine.currrentAction = null;
        }
    }

    /**
     * 这样设计以后以后增加动作或者状态的时候需要修改原有代码的switch分支特别麻烦。-old
     */
    public class VendingMachineOld {
        // 已投币
        private final int coinedStatus = 0;
        // 未投币
        private final int unCoinedStatus = 1;
        // 售出
        private final int soedStatus = 2;
        // 售完
        private final int beSoldOutStatus = 3;
        private int currrentStatus = unCoinedStatus;

        /**
         * 投币
         */
        public void coined() {
            switch (currrentStatus) {
                case coinedStatus:
                    System.out.println("你已经投过币了");
                    break;
                case unCoinedStatus:
                    System.out.println("你已投币成功");
                    this.currrentStatus = coinedStatus;
                    break;
                case soedStatus:
                    System.out.println("本商品已经售出");
                    break;
                case beSoldOutStatus:
                    System.out.println("本商品已经售完");
                    break;
                default:
                    break;
            }
        }

        /**
         * 退币
         */
        public void coinReturn() {
            switch (currrentStatus) {
                case coinedStatus:
                    System.out.println("退币成功");
                    this.currrentStatus = unCoinedStatus;
                    break;
                case unCoinedStatus:
                    System.out.println("当前售货机没有投币");
                    this.currrentStatus = coinedStatus;
                    break;
                case soedStatus:
                case beSoldOutStatus:
                    System.out.println("本商品已经售完-正在退币中");
                    this.currrentStatus = unCoinedStatus;
                    break;
                default:
                    break;
            }
        }

    }
}
