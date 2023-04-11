package com.zhou.supermapforjava.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @描述：HTTP请求对象
 * @作者：彭辉
 * @时间：2019/1/13 12:05
 */
@Component
public class HttpRequester {

    private static Pattern regex = Pattern.compile("[\u4e00-\u9fa5]");

    public static String encode(String str) {
        Matcher m = regex.matcher(str);
        StringBuffer b = new StringBuffer();
        try {
            while (m.find()) {
                String key = m.group();
                m.appendReplacement(b, URLEncoder.encode(key, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.appendTail(b);
        return b.toString();
    }

    /**
     * @描述：发送GET请求
     * @入参：url地址
     * @作者：彭辉
     * @时间：2019/1/13 12:06
     */
    public <T> T sendGet(String url) throws IOException {
        return this.send(url, "GET", null, null);
    }

    /**
     * @描述：发送GET请求
     * @入参：URL：url地址，params：参数集合
     * @作者：彭辉
     * @时间：2019/1/13 12:06
     */
    public <T> T sendGet(String url, Map<String, String> params)
            throws IOException {
        return this.send(url, "GET", params, null);
    }

    /**
     * @描述：发送GET请求
     * @入参：URL：url地址，params：参数集合，propertys：属性集合
     * @作者：彭辉
     * @时间：2019/1/13 12:06
     */
    public <T> T sendGet(String url, Map<String, String> params,
                         Map<String, String> propertys) throws IOException {
        return this.send(url, "GET", params, propertys);
    }

    /**
     * @描述：发送POST请求
     * @入参：url地址
     * @作者：彭辉
     * @时间：2019/1/13 12:06
     */
    public <T> T sendPost(String url) throws IOException {
        return this.send(url, "POST", null, null);
    }

    /**
     * @描述：发送POST请求
     * @入参：URL：url地址，params：参数集合
     * @作者：彭辉
     * @时间：2019/1/13 12:06
     */
    public <T> T sendPost(String url, Map<String, String> params)
            throws IOException {
        return this.send(url, "POST", params, null);
    }

    /**
     * @描述：发送POST请求
     * @入参：URL：url地址，params：参数集合，propertys：属性集合
     * @作者：彭辉
     * @时间：2019/1/13 12:06
     */
    public <T> T sendPost(String url, Map<String, String> params,
                          Map<String, String> propertys) throws IOException {
        return this.send(url, "POST", params, propertys);
    }
    /**
     * @描述：发送HTTP请求
     * @入参：URL：url地址，method：请求方式，parameters：参数集合，propertys：属性集合
     * @作者：彭辉
     * @时间：2019/1/13 12:10
     */
    private synchronized <T> T send(String url, String method,
                                    Map<String, String> parameters, Map<String, String> propertys)
            throws IOException {
        HttpURLConnection urlConnection = null;
        StringBuffer param = new StringBuffer();
        try {
            //将最后的?去掉
            url = url.replaceAll("\\?$", "");
            if (method.equalsIgnoreCase("GET") && parameters != null) {
                int i = 0;
                for (String key : parameters.keySet()) {
                    if (i == 0 && !url.contains("?")) {
                        param.append("?");
                    } else {
                        param.append("&");
                    }
                    String value = parameters.get(key);
                    //if (!Common.isCheckNull(value)) {
                    if (value != null) {
                        param.append(URLEncoder.encode(key, "UTF-8"))
                                .append("=")
                                .append(URLEncoder.encode(value, "UTF-8"));
                    } else {
                        param.append(URLEncoder.encode(key, "UTF-8"))
                                .append("=");
                    }
                    i++;
                }
                url += param;
            }
            if (method.equalsIgnoreCase("POST") && parameters != null) {
                int i = 0;
                for (String key : parameters.keySet()) {
                    if (i != 0) param.append("&");
                    String value = parameters.get(key);
                    if (StringUtils.isEmpty(value)) {
                        param.append(URLEncoder.encode(key, "UTF-8")).append("=");
                    } else {
                        param.append(URLEncoder.encode(key, "UTF-8")).append("=").append(URLEncoder.encode(value, "UTF-8"));
                    }
                    i++;
                }
            }
            //将中文转码
            url = encode(url);
            URL httpUrl = new URL(url);
            urlConnection = (HttpURLConnection) httpUrl.openConnection();
            if (method.equalsIgnoreCase("GET")) {
                urlConnection.setRequestMethod("GET");// 提交模式
            } else {
                urlConnection.setRequestMethod("POST");// 提交模式
            }
            urlConnection.setRequestMethod(method);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            // 设置获取连接超时时间
            //urlConnection.setConnectTimeout(10000);
            // 设置读取返回数据超时时间
            //urlConnection.setReadTimeout(10000);
            if (propertys != null) {
                for (String key : propertys.keySet()) {
                    urlConnection.addRequestProperty(key, propertys.get(key));
                }
            }
            if (method.equalsIgnoreCase("POST") && parameters != null) {
                OutputStream opt = urlConnection.getOutputStream();
                opt.write(encode(param.toString()).getBytes());
                opt.flush();
                opt.close();
            }

            HttpRespons httpResponser = getHttpRespons();
            String ecod = "UTF-8";
            InputStream input = urlConnection.getInputStream();
            byte[] bytay = getByteArr(input);
            //判断是否为文本
            boolean isText = true;
            for (int i = 0; i < bytay.length; i++) {
                if (bytay[i] == 0) {
                    isText = false;
                    break;
                }
            }
            httpResponser.isText = isText;    //判断是否文本格式
            httpResponser.bytay = bytay;    //字节数组
            httpResponser.urlString = url;
            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
            httpResponser.file = urlConnection.getURL().getFile();
            httpResponser.host = urlConnection.getURL().getHost();
            httpResponser.path = urlConnection.getURL().getPath();
            httpResponser.port = urlConnection.getURL().getPort();
            httpResponser.protocol = urlConnection.getURL().getProtocol();
            httpResponser.query = urlConnection.getURL().getQuery();
            httpResponser.ref = urlConnection.getURL().getRef();
            httpResponser.userInfo = urlConnection.getURL().getUserInfo();

            //httpResponser.content = content;
            httpResponser.contentEncoding = ecod;
            httpResponser.code = urlConnection.getResponseCode();
            httpResponser.message = urlConnection.getResponseMessage();
            httpResponser.contentType = urlConnection.getContentType();
            httpResponser.method = urlConnection.getRequestMethod();
            httpResponser.headerFields = urlConnection.getHeaderFields();
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();
            httpResponser.readTimeout = urlConnection.getReadTimeout();
            return (T) httpResponser;
        } catch (IOException e) {
            if (urlConnection != null) {
                System.out.println("url: " + urlConnection.getURL());
            }
            throw e;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public HttpRespons getHttpRespons() {
        return new HttpRespons();
    }

    private byte[] getByteArr(InputStream input) throws IOException {
        List<byte[]> list = new ArrayList<byte[]>();
        int total = 0;
        while (true) {
            int size = 64;
            byte[] buf = new byte[size];
            int ind = input.read(buf);
            if (ind == -1) break;
            if (ind < size) {
                byte[] bufnew = new byte[ind];
                System.arraycopy(buf, 0, bufnew, 0, ind);
                list.add(bufnew);
            } else {
                list.add(buf);
            }
            total += ind;
        }
        input.close();
        byte[] bytay = new byte[total];
        for (int i = 0, n = 0; i < list.size(); i++) {
            byte[] byt = list.get(i);
            for (int j = 0; j < byt.length; j++, n++) {
                byte temp = byt[j];
                bytay[n] = temp;
            }
        }
        return bytay;
    }
}
