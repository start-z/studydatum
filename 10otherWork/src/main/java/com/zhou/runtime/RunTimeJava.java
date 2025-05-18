package com.zhou.runtime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhou
 * @since 2023/7/24
 * description:
 */
public class RunTimeJava {


    public static void main(String[] args) throws IOException, InterruptedException, IOException {

        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec("python --version");
        InputStream inputStream = exec.getInputStream();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes))!=-1){
            stream.write(bytes,0,len);
        }
        stream.flush();
        String s =  stream.toString("utf-8");
        System.out.println(exec.waitFor() + s);

    }
}
