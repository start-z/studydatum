/**
 * @author zhou
 * @since 2023/10/11
 * description: 简单工厂模式
 */
public class EasyFactory {

    /**
     * 根据传入的类型返回参数
     *
     * @param type 类型
     */
    public String createBean(Integer type) {
        String value = "";
        switch (type) {
            case 1:
                value = "1";
                break;
            case 2:
                value = "2";
                break;
            default:
                break;
        }
        return value;
    }
}
