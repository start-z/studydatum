import com.sun.xml.internal.ws.util.StringUtils;

import java.io.FileReader;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author zhouhelong
 * @creat 2022-06-06 17:20
 * @description:
 */
public class BreakpointResume {

    //断点续传
    public static void main(String[] args) {
        BreakpointResume();
    }


    public static void BreakpointResume() {
        String srcfilePath = "C:\\Users\\一号线\\Desktop\\新建文件夹\\demio.txt";
        String  outFilePath="";
        //断点续传实体文件类
        try {
            RandomAccessFile srcFile = new RandomAccessFile(srcfilePath, "r");
            RandomAccessFile outFile = new RandomAccessFile(srcfilePath, "rw");
            FileReader reader = new FileReader(".log");
            reader.
            long length = accessFile.length();
            System.out.println("文件字节长度为" + length);
            byte[] bytes = new byte[1024 * 8];
            while (true){
                if (accessFile.read()!=-1){
                    System.out.println(accessFile.read());
                    accessFile.read(bytes);
                }else {
                    break;
                }
            }
            String s = new String(bytes);
            System.out.println(s);

        } catch (Exception e) {
            System.out.println("我出错了");
        }
    }
}

