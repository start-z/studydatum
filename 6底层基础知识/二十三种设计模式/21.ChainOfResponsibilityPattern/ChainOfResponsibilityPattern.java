/**
 * @author zhou
 * @since 2023/10/23
 * description: 设计模式-责任链模式
 */
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        ChainOfResponsibilityPattern chain = new ChainOfResponsibilityPattern();
        chain.test();
    }

    public void test() {
        ErrorLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        InfoLogger infoLogger = new InfoLogger(AbstractLogger.INFO);
        ConsoleLogger consoleLogger = new ConsoleLogger(AbstractLogger.CONSOLE);
        infoLogger.setNextAbstractLogger(consoleLogger);
        errorLogger.setNextAbstractLogger(infoLogger);

        errorLogger.loadMessage(AbstractLogger.INFO, "你好,我是测试日志");
    }

    public abstract static class AbstractLogger {
        private static Integer CONSOLE = 1;
        private static Integer INFO = 2;
        private static Integer ERROR = 3;
        protected Integer level;
        private AbstractLogger nextAbstractLogger;

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public AbstractLogger getNextAbstractLogger() {
            return nextAbstractLogger;
        }

        public void setNextAbstractLogger(AbstractLogger nextAbstractLogger) {
            this.nextAbstractLogger = nextAbstractLogger;
        }

        public void loadMessage(Integer level, String message) {
            if (level <= this.getLevel()) {
                write(message);
            }
            if (nextAbstractLogger != null) {
                nextAbstractLogger.loadMessage(level, message);
            }
        }

        public abstract void write(String meassage);
    }

    public class ConsoleLogger extends AbstractLogger {

        public ConsoleLogger(Integer level) {
            this.level = level;
        }

        @Override
        public void write(String massage) {
            System.out.println("console日志消息为" + massage);
        }
    }

    public class InfoLogger extends AbstractLogger {

        public InfoLogger(Integer level) {
            this.level = level;
        }

        @Override
        public void write(String massage) {
            System.out.println("Info日志消息为" + massage);
        }
    }

    public class ErrorLogger extends AbstractLogger {

        public ErrorLogger(Integer level) {
            this.level = level;
        }

        @Override
        public void write(String massage) {
            System.out.println("Error日志消息为" + massage);
        }
    }
}
