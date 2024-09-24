package com.zhou.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

@Slf4j
public class HttpClientUtils {
    /**
     * 发送HTTP请求到指定URL，并绕过HTTPS证书验证。
     *
     * @param method          HTTP方法（GET、POST、PUT等）
     * @param url             请求URL
     * @param requestBodyJson 请求体（JSON格式字符串，对于非POST/PUT请求可为空）
     * @param headers         可选的额外请求头（键值对）
     * @return 响应体（JSON格式字符串），如果请求失败则返回null
     * @throws IOException 如果发生网络错误
     */
    public static String sendRequest(HttpMethod method, String url, String requestBodyJson, Map<String, String> headers) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        // 创建一个信任所有证书的SSLContext
        SSLContext sslContext = createTrustAllSSLContext();

        // 创建一个使用该SSLContext的SSLConnectionSocketFactory
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        // 构建一个包含SSL连接工厂的Registry
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory).build();

        // 创建一个支持HTTPS的PoolingHttpClientConnectionManager，使用上述Registry
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        try (CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build()) {
            HttpUriRequest request = createHttpRequest(method, url, requestBodyJson, headers);
            printHttpRequestDetails(request);
            HttpResponse response = httpClient.execute(request);

            // 打印响应状态行、响应头和响应体
            String result = printHttpResponseDetails(response);

            // 处理响应
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return result;
            } else {
                log.error("Error: Request failed with status code " + statusCode);
                return null;
            }
        } catch (IOException e) {
            log.error("An exception occurred while executing the request:");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送获取验证码的HTTP请求到指定URL，并绕过HTTPS证书验证。
     *
     * @param method          HTTP方法（GET、POST、PUT等）
     * @param url             请求URL
     * @param requestBodyJson 请求体（JSON格式字符串，对于非POST/PUT请求可为空）
     * @param headers         可选的额外请求头（键值对）
     * @param filePath        图片保存路径
     * @return 响应体（JSON格式字符串），如果请求失败则返回null
     * @throws IOException 如果发生网络错误
     */
    public static void sendImageRequest(HttpMethod method, String url, String requestBodyJson, Map<String, String> headers, String filePath, String fileType) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        // 创建一个信任所有证书的SSLContext
        SSLContext sslContext = createTrustAllSSLContext();

        // 创建一个使用该SSLContext的SSLConnectionSocketFactory
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        // 构建一个包含SSL连接工厂的Registry
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory).build();

        // 创建一个支持HTTPS的PoolingHttpClientConnectionManager，使用上述Registry
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        try (CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build()) {
            HttpUriRequest request = createHttpRequest(method, url, requestBodyJson, headers);
            printHttpRequestDetails(request);
            HttpResponse response = httpClient.execute(request);

            // 打印响应状态行、响应头和响应体
            downloadImages(response, filePath, fileType);

            // 处理响应
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                log.info("图片下载成功，下载地址为：{}", filePath);
            } else {
                log.error("Error: Request failed with status code " + statusCode);
            }
        } catch (IOException e) {
            log.error("An exception occurred while executing the request:");
            e.printStackTrace();
        }
    }


    /**
     * 打印HTTP请求的详细信息
     *
     * @param request HTTP请求对象
     */
    private static void printHttpRequestDetails(HttpUriRequest request) {
        log.info("\n----------------------------------------------- Request Details -------------------------------------------------------");

        // 打印请求方法
        log.info("Method: " + request.getMethod());

        // 打印请求URI
        log.info("URI: " + request.getURI());

        // 打印请求头
        log.info("Headers:");
        HeaderIterator headerIterator = request.headerIterator();
        while (headerIterator.hasNext()) {
            Header header = headerIterator.nextHeader();
            log.info(header.getName() + ": " + header.getValue());
        }

        // 如果是POST或PUT请求，打印请求体
        if (request instanceof HttpEntityEnclosingRequestBase) {
            HttpEntity entity = ((HttpEntityEnclosingRequestBase) request).getEntity();
            if (entity != null) {
                try {
                    String requestBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                    log.info("Request Body: " + requestBody);
                } catch (IOException e) {
                    log.error("Failed to print request body:");
                    e.printStackTrace();
                }
            } else {
                log.info("Request Body: (empty)");
            }
        }

        log.info("\n----------------------------------------------------------------------------------------------------------------\n");
    }


    /**
     * 打印HTTP响应的详细信息
     *
     * @return
     */
    private static String printHttpResponseDetails(HttpResponse response) {
        log.info("\n----------------------------------------------- Response Details -------------------------------------------------------");

        // 打印状态行
        log.info("Status Line: " + response.getStatusLine());

        // 打印响应头
        System.out.println("Headers:");
        HeaderIterator headerIterator = response.headerIterator();
        while (headerIterator.hasNext()) {
            Header header = headerIterator.nextHeader();
            log.info(header.getName() + ": " + header.getValue());
        }

        // 打印响应体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try {
                String responseBody = EntityUtils.toString(entity, "UTF-8");
//                log.info("Response Body: " + responseBody);
                log.info("\n----------------------------------------------------------------------------------------------------------------\n");
                return responseBody;
            } catch (IOException e) {
                log.error("Failed to print response body:");
                log.info("\n----------------------------------------------------------------------------------------------------------------\n");
                e.printStackTrace();
            }
        } else {
            log.info("Response Body: (empty)");
        }

        return null;
    }

    /**
     * 下载图片
     *
     * @param response HttpResponse
     * @param filePath 下载的文件路径
     * @param fileType 文件类型，如png
     */
    private static void downloadImages(HttpResponse response, String filePath, String fileType) {
        log.info("\n----------------------------------------------- Response Details -------------------------------------------------------");

        // 打印状态行
        log.info("Status Line: " + response.getStatusLine());

        // 打印响应头
        System.out.println("Headers:");
        HeaderIterator headerIterator = response.headerIterator();
        while (headerIterator.hasNext()) {
            Header header = headerIterator.nextHeader();
            log.info(header.getName() + ": " + header.getValue());
        }

        // 打印响应体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try {
                // 获取响应实体内容
                byte[] imageBytes = EntityUtils.toByteArray(response.getEntity());

                // 创建输入流
                InputStream inputStream = new ByteArrayInputStream(imageBytes);

                // 将字节数组转换为BufferedImage
                BufferedImage image = ImageIO.read(inputStream);
                File outputFile = new File(filePath);
                // 如果父路径没有则创建
                if (!outputFile.getParentFile().exists()) {
                    outputFile.getParentFile().mkdirs();
                }
                ImageIO.write(image, fileType, outputFile);
                log.info("图片下载成功");
                log.info("\n----------------------------------------------------------------------------------------------------------------\n");
            } catch (IOException e) {
                log.error("图片下载失败");
                log.info("\n----------------------------------------------------------------------------------------------------------------\n");
                e.printStackTrace();
            }
        } else {
            log.info("Response Body: (empty)");
        }
    }

    /**
     * 创建一个HTTP请求对象
     */
    private static HttpUriRequest createHttpRequest(HttpMethod method, String url, String requestBodyJson, Map<String, String> headers) throws IOException {
        HttpUriRequest request;
        switch (method) {
            case GET:
                request = new HttpGet(url);
                break;
            case POST:
                request = new HttpPost(url);
                ((HttpEntityEnclosingRequestBase) request).setEntity(new StringEntity(requestBodyJson, "UTF-8"));
                break;
            case PUT:
                request = new HttpPut(url);
                ((HttpEntityEnclosingRequestBase) request).setEntity(new StringEntity(requestBodyJson, "UTF-8"));
                break;
            case DELETE:
                request = new HttpDelete(url);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        // 设置默认请求头
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        // 添加额外请求头（如果有）
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }

        return request;
    }

    /**
     * 创建一个信任所有证书的SSLContext
     */
    private static SSLContext createTrustAllSSLContext() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, new SecureRandom());
        return sslContext;
    }

    /**
     * HTTP方法枚举
     */
    public enum HttpMethod {
        GET, POST, PUT, DELETE
    }
}
