package zhou.oom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 * @since 2023/4/18
 * description:  模拟内存溢出并使用性能分析软件排查问题
 */
public class OomMain {
    //   jvm设置参数 -Xmx10M -XX:MaxDirectMemorySize=10M -Xloggc:gc.log
    private static final int _10MB = 10 * 1024 * 1024;

    public static void main(String[] args) throws Exception {
        List<ByteBuffer> list = new ArrayList<>();
//        // 分配 20MB
//        list.add(ByteBuffer.allocateDirect(_10MB));
//        list.add(ByteBuffer.allocateDirect(_10MB));
        while (true){
            list.add(ByteBuffer.allocateDirect(10 * 1024));
        }
    }
}
