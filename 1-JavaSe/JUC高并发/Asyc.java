/**
 * @author zhouhelong
 * @creat 2022-06-15 22:22
 * @description:
 */


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 异步调用   CompletableFuture
 */
public class Asyc {
    public static void main(String[] args) throws ClassNotFoundException {
        jarClassLoader loader = new jarClassLoader();
        Class<?> aClass = loader.loadClass("");
    }

    public static void asycVoid() {
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "cccccc");
        });
    }


    public  void loadJar(){

    }

    static class jarClassLoader extends ClassLoader{
        String jarPath = "E:\\zhou\\data\\maven\\com\\guodi\\designer\\yanshi\\designer-yanshi.jar";

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                JarFile jarFile = new JarFile(jarPath);
                JarEntry entry = jarFile.getJarEntry("BOOT-INF/classes/com/guodi/designer/DesignerApplication.class");
                if (entry == null) {
                    throw new ClassNotFoundException(name);
                }

                // 加载类字节码
                try (InputStream input = jarFile.getInputStream(entry)) {
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    byte[] data = new byte[1024];
                    int nRead;
                    while ((nRead = input.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, nRead);
                    }
                    return defineClass(name, buffer.toByteArray(), 0, buffer.toByteArray().length);
                }
            } catch (IOException e) {
                System.out.println("类没有找到");
            }
            return super.findClass(name);
        }
    }

    /**
     * 有返回值
     */
    public static void asycIsVoid() {
        try {
            System.out.println(CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "cccccc");
                return 1024;
            }).whenComplete((t, u) -> {
                System.out.println(t);
                System.out.println(u);
            }).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
