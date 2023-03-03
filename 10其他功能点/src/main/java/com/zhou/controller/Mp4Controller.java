package com.zhou.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zhouhelong
 * @creat 2023-03-02 19:49
 * @description:
 */
@Controller
@RequestMapping("/profile")
@Slf4j
public class Mp4Controller {

    @GetMapping("/toMp4ByFileSteam1/{path}")
    public void toMp4ByFileSteam(@PathVariable String path, HttpServletResponse response) {

        try {
            File file = new File(path);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName().replace(" ", "_"));
            InputStream iStream = new FileInputStream(file);
            IOUtils.copy(iStream, response.getOutputStream());
            response.flushBuffer();
        } catch (java.nio.file.NoSuchFileException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping(value = "/toMp4ByFileSteam")
    public void aloneVideoPlay(HttpServletRequest request, HttpServletResponse response) {
        String path = "D:\\alldev\\Project\\hist-culture\\files\\mp4\\20230301213322.mp4";
        BufferedInputStream bis = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                long p = 0L;
                long toLength = 0L;
                long contentLength = 0L;
                int rangeSwitch = 0;
                long fileLength;
                String rangBytes = "";
                fileLength = file.length();

                // 得到文件的长度
                InputStream ins = new FileInputStream(file);
                bis = new BufferedInputStream(ins);

                //告诉bs端进行断点续传
                response.reset();
                response.setHeader("Accept-Ranges", "bytes");

                // 客户端下载指定字节
                String range = request.getHeader("Range");
                if (range != null && range.trim().length() > 0 && !"null".equals(range)) {
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                    rangBytes = range.replaceAll("bytes=", "");
                    // 得到rangge请求位置然后判断读取位置
                    if (rangBytes.endsWith("-")) {
                        rangeSwitch = 1;
                        p = Long.parseLong(rangBytes.substring(0, rangBytes.indexOf("-")));
                        contentLength = fileLength - p;
                    } else {
                        rangeSwitch = 2;
                        String temp1 = rangBytes.substring(0, rangBytes.indexOf("-"));
                        String temp2 = rangBytes.substring(rangBytes.indexOf("-") + 1);
                        p = Long.parseLong(temp1);
                        toLength = Long.parseLong(temp2);
                        contentLength = toLength - p + 1;
                    }
                } else {
                    contentLength = fileLength;
                }

                // 如果设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。
                // Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
                response.setHeader("Content-Length", Long.toString(contentLength));

                // 断点开始
                // 响应的格式是:
                // Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
                if (rangeSwitch == 1) {
                    String contentRange = new StringBuffer("bytes ")
                            .append(p)
                            .append("-")
                            .append(fileLength - 1)
                            .append("/")
                            .append(fileLength)
                            .toString();
                    response.setHeader("Content-Range", contentRange);
                    long skip = bis.skip(p);
                } else if (rangeSwitch == 2) {
                    String contentRange = range.replace("=", " ") + "/" + fileLength;
                    response.setHeader("Content-Range", contentRange);
                    long skip = bis.skip(p);
                } else {
                    String contentRange = new StringBuffer("bytes ")
                            .append("0-")
                            .append(fileLength - 1)
                            .append("/")
                            .append(fileLength)
                            .toString();
                    response.setHeader("Content-Range", contentRange);
                }

                String fileName = file.getName();
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

                OutputStream out = response.getOutputStream();
                int n = 0;
                long readLength = 0;
                int bsize = 1024;
                byte[] bytes = new byte[bsize];
                if (rangeSwitch == 2) {
                    while (readLength <= contentLength - bsize) {
                        n = bis.read(bytes);
                        readLength += n;
                        out.write(bytes, 0, n);
                    }
                    if (readLength <= contentLength) {
                        n = bis.read(bytes, 0, (int) (contentLength - readLength));
                        out.write(bytes, 0, n);
                    }
                } else {
                    while ((n = bis.read(bytes)) != -1) {
                        out.write(bytes, 0, n);
                    }
                }
                out.flush();
                out.close();
                bis.close();
            }
        } catch (Exception e) {
            log.error("流媒体播放异常,{0}", e);
        }
    }
}
