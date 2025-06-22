package com.zhou.jsoup;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class jsoupRunner {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("F:\\zhou\\studyProjects\\study\\parent\\collect\\src\\main\\resources\\application.yml"));
            for (Object o : properties.keySet()) {
                System.out.println(o.toString()+"="+properties.getProperty(o.toString()));
            }
        } catch (IOException e) {
            System.out.println("读取配置好文件失败");
        }
        String downloadPath = "F:\\zhou\\JSOUP\\IMAGE";
//        jsoupJingDong(downloadPath,20);
    }



    public  static void  jsoupZool(){
        int  p=1;
        String url="https://www.zcool.com.cn/p1/index/data?p="+p+"&ps=20&action=recommend_index_new&column=4";
        String httpGet = httpGet(url);

    }


    public static void jsoupJingDong(String downloadPath,int count) {
        int page = 1;  //京东分页页数+2
        int s = 1; //  京东参数size参数为+50
        int current = 1;
        while (current<count) {
            String url = "https://search.jd.com/Search" +
                    "?keyword=%E6%B5%8B%E8%AF%95" +
                    "&enc=utf-8&wq=%E6%B5%8B%E8%AF%95" +
                    "&pvid=f3b6d67056f54f7da7ddd6af94765912" +
                    "&page=" + page +
                    "&s=" + s +
                    "&click=1";

            Document document = null;
            try {
                document = Jsoup.connect(url).get();
            } catch (IOException e) {
                System.out.println("获取urlio异常");
            }
            System.out.println(document.title());
            for (Element img : document.select("img")) {
                String imgsrc = "https:" + img.attr("data-lazy-img");
                InputStream inputStream = null;
                try {
                    inputStream=   getinputSteamByUrl(imgsrc);
                } catch (Exception e) {
                    System.out.println("当前失败的图片地址为" + imgsrc);
                    continue;
                }
                String fileName = imgsrc.substring(imgsrc.lastIndexOf("/") + 1);
                String quoteFilePath = downloadPath + "\\" + fileName;
                try {
                    IOUtils.copy(inputStream, new FileOutputStream(new File(quoteFilePath)));
                } catch (IOException e) {
                    System.out.println("io异常");
                }
            }
            page += 2;
            s += 50;
            System.out.println("文件爬虫爬取第" + (current++) + "页完成");
        }
        System.out.println("文件爬虫爬取完成");
    }

    public static InputStream getinputSteamByUrl(String url) throws IOException {
        return new URL(url).openConnection().getInputStream();
    }


            private static String  httpGet(String url) {
                // 创建 HttpClient 客户端
                CloseableHttpClient httpClient = HttpClients.createDefault();

                // 创建 HttpGet 请求
                HttpGet httpGet = new HttpGet(url);
                // 设置长连接
                httpGet.setHeader("Connection", "keep-alive");
                // 设置代理（模拟浏览器版本）
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
                // 设置 Cookie
                httpGet.setHeader("Cookie", "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4");

                CloseableHttpResponse httpResponse = null;
                try {
                    // 请求并获得响应结果
                    httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    // 输出请求结果
              return  EntityUtils.toString(httpEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 无论如何必须关闭连接
                finally {
                    if (httpResponse != null) {
                        try {
                            httpResponse.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (httpClient != null) {
                        try {
                            httpClient.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
}
}
