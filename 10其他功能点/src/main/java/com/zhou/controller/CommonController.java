package com.zhou.controller;

import com.zhou.lister.event.UserEvent;
import com.zhou.pojo.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhouhelong
 * @creat 2022-07-27 17:54
 * @description:
 */
@RestController
public class CommonController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/lister")
    public void listterEventTest() {
        User user = new User();
        user.setId("1");
        user.setName("zhou");
        applicationEventPublisher.publishEvent(new UserEvent(user));

    }

    @GetMapping(value = "downloadImage", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public void bsImageDownload(@RequestParam String url, HttpServletResponse response) throws Exception {
        ServletOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            URL imageUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) imageUrl.openConnection();
//            response.addHeader("Content-Disposition", "attachment;filename="+new String( "中文".getBytes("utf-8"), "ISO8859-1" )+"文件类型");
            response.addHeader("Content-Disposition", "attachment;filename=" + "1.JPG");
            inputStream = urlConnection.getInputStream();
            outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            response.flushBuffer();
        } finally {
            inputStream.close();
            outputStream.close();
        }

    }
}
