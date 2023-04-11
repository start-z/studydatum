package com.zhou.supermapforjava.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


/**
 * @描述：响应对象实体类
 * @作者：彭辉
 * @时间：2019/1/13 12:05
 */
public class HttpRespons {

    String urlString;

    int defaultPort;

    String file;

    String host;

    String path;

    int port;

    String protocol;

    String query;

    String ref;

    String userInfo;

    String contentEncoding;

    StringBuffer content;

    String contentType;

    int code;

    String message;

    String method;

    int connectTimeout;

    int readTimeout;

    byte[] bytay;

    boolean isText;

    Map<String,List<String>> headerFields;

    public String getContent() {
        return getContentBuffer().toString();
    }

    public StringBuffer getContentBuffer() {
        if (content == null) {
            try {
                ByteArrayInputStream byteIn = new ByteArrayInputStream(bytay);
                InputStreamReader input = new InputStreamReader(byteIn, "utf8");
                BufferedReader reader = new BufferedReader(input);
                content = new StringBuffer();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\r\n");
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }




    public String getUrlString() {
        return urlString;
    }

    public int getDefaultPort() {
        return defaultPort;
    }

    public String getFile() {
        return file;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getQuery() {
        return query;
    }

    public String getRef() {
        return ref;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public String getContentType() {
        return contentType;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMethod() {
        return method;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public byte[] getBytay() {
        return bytay;
    }

    public boolean isText() {
        return isText;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public void setHeaderFields(Map<String, List<String>> headerFields) {
        this.headerFields = headerFields;
    }
}
