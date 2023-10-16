/**
 * 单例模式-饿汉式
 *
 * @author BJB314
 */
public class HungrySingleton {

	private static HungrySingleton instance = null;

	static {
		instance = new HungrySingleton();
	}

	private HungrySingleton() {
	}

	public static HungrySingleton getInstance() {
		return instance;
	}
}
