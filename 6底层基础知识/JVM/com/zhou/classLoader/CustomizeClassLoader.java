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

}
