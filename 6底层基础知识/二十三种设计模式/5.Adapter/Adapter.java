/**
 * @author zhou
 * @since 2023/10/12
 * description: 适配器模式
 */
public class Adapter {
    /**
     * 手机基类
     */
    public class Moblie {
        private String type = "TypecC";
    }

    /**
     * 耳机基类
     */
    public class Headset {
        private String type = "CircularHole";
    }

    /**
     * 转换器
     */
    public class TypecCoverCircularHole {
        public void typecCoverCircularHole(Headset headset, Moblie moblie) {
            moblie.type = headset.type;
        }
    }
}
