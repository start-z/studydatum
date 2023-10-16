import java.util.Arrays;

/**
 * @author zhou
 * @since 2023/10/12
 * description: 命令模式-我有多个机器人，每个机器人的职责和业务都不相同，现在我需要对这些机器人发送不同的指令，并希望有个发送命令的控制中枢方便控制。
 */
public class Command {
    /**
     * 机器人基类
     */
    public interface Robot {
        void execute();
    }

    public class ControlCenter {
        public Robot[] robots;

        /**
         * 添加指定按键机器人
         *
         * @param robot    机器人
         * @param keyIndex 按键
         */
        public void addRobot(Robot robot, Integer keyIndex) {
            robots[keyIndex] = robot;
        }

        /**
         * 删除指定按键机器人
         *
         * @param robot    机器人
         * @param keyIndex 按键
         */
        public void removeRobot(Robot robot, Integer keyIndex) {
            robots[keyIndex] = null;
        }

        /**
         * 一键执行
         */
        public void execute() {
            Arrays.stream(robots).forEach(Robot::execute);
        }
    }
}
