/**
 * @author zhou
 * @since 2023/10/12
 * description: 懒汉类单例模式
 */
public class HungerSingleon {
    public static HungerSingleon hungerSingleon = new HungerSingleon();

    public HungerSingleon() {
    }

    public static HungerSingleon creatHungerSigleon() {
        return hungerSingleon;
    }
}
