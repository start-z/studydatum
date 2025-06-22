package com.zhou.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zhou
 * @since 2023/4/21
 * description:
 */
public class QlvUtils {

    public static void main(String[] args) {

        String inputFilePath = "E:\\QLDownload\\一念永恒\\一念永恒 第01话 4K(超高清SDR).qlv";
        String outputFilePath = "E:\\QLDownload\\一念永恒\\一念永恒 第01话 4K(超高清SDR).mp4";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "ffmpeg",
                    "-i", inputFilePath,
                    "-c:v", "libx264",
                    "-c:a", "aac",
                    "-strict", "experimental",
                    "-b:a", "192k",
                    "-movflags", "+faststart",
                    outputFilePath
            );

            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with error code " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
