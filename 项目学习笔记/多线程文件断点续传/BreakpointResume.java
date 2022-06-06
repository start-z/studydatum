import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouhelong
 * @creat 2022-06-06 17:20
 * @description:
 */
public class BreakpointResume {

    //断点续传
    public static void main(String[] args) {
        File file = new File("C:\\Users\\一号线\\Desktop\\新建文件夹\\demio.txt");
        String outPath = "C:\\Users\\一号线\\Desktop\\新建文件夹\\hello.txt";
        try {
            FileInputStream inputStream = new FileInputStream(file);
            breakpointResume(inputStream, outPath, 5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void breakpointResume(InputStream input, String outPath) {
        breakpointResume(input, outPath, 5);
    }

    ;

    public static void breakpointResume(InputStream input, String outPath, int threadSum) {
        threadSum = threadSum == 0 ? 0 : 5;
        FileInputStream fileInputStream = (FileInputStream) input;
        //断点续传实体文件类
        try {
            //下载文件的目录
            File outFile = new File(outPath);
            //下载的文件目录
            RandomAccessFile outAccessFile = new RandomAccessFile(outFile, "rw");
            //记录日志
            String parent = outFile.getParent();
            File logFile = new File(parent + "/xxxx.log");
            //线程安全map
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(14);
            String[] thredNowNums = null;
            if (logFile.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(logFile));
                String readLine = fileReader.readLine();
                if (readLine != null) {
                    thredNowNums = readLine.split(",");
                    fileReader.close();
                }
            }
            //文件长度
            long length = fileInputStream.getChannel().size();
            int num = Integer.parseInt(String.valueOf(length)) / threadSum;
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < threadSum; i++) {
                final int k = i;
                String[] finalThredNowNums = thredNowNums;
                Thread rw = new Thread(() -> {
                    //设置读取文件缓冲区
                    byte[] bytes = new byte[5];
                    int readSum = 0;
                    int read = -1;
                    while (true) {
                        try {
                            read = fileInputStream.read(bytes);
                            //文件读取完成
                            if (read != -1) {
                                concurrentHashMap.put(k, read);

                                outAccessFile.seek(Long.parseLong(finalThredNowNums == null ? String.valueOf(k * num) : finalThredNowNums[k]));
                                readSum += read;
                                outAccessFile.write(bytes, 0, readSum);
                                RandomAccessFile logAccessFile = new RandomAccessFile(logFile, "rw");
                                // 将 map 中的数据进行写入文件中
                                logAccessFile.seek(0); // 直接覆盖全部文件
                                StringJoiner joiner = new StringJoiner(",");
                                concurrentHashMap.forEach((key, val) -> joiner.add(String.valueOf(val)));
                                logAccessFile.write(joiner.toString().getBytes(StandardCharsets.UTF_8));
                                logAccessFile.close();
                            } else {
                                break;
                            }
                            if (readSum > num) {
                                break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                threads.add(rw);
                rw.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("当前文件失败");
        }
    }
}

