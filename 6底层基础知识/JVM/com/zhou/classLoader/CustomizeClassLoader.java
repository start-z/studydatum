package zhou.classLoader;

/**
 * @author zhou
 * @since 2023/4/18
 * description:
 */
public class CustomizeClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println(name);
        return super.loadClass(name);
    }

    class Hello {
        private String  name = "hello";

        public static void main(String[] args) {
            ClassLoader loader = this.getClass().getClassLoader();
            loader.equals(CustomizeClassLoader.GET)
        }

    }
}
