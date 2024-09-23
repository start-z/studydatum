package com.zhou.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DouYinGetM3u8 {
    public static void main(String[] args) throws Exception {
        String url = "https://live.douyin.com/207304008882?cover_type=1&enter_from_merge=web_live&enter_method=web_card&game_name=%E7%8E%8B%E8%80%85%E8%8D%A3%E8%80%80&is_recommend=1&live_type=game&more_detail=all&request_id=20240923090922CFAE8D4E0FDE0BFF482D&room_id=7417624146068114202&stream_type=horizontal&title_type=1&web_live_page=game_2&web_live_tab=more";
        // 创建 HttpClient 实例
        getDouYinM3u8(url);
    }

    // 方法：解析查询参数
    public static Map<String, String> convertQueryToMap(String query){
        Map<String, String> queryPairs = new HashMap<>();
        String[] pairs = query.split("&");

        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = null;
            String value = null;

            if (idx != -1) {
                key = pair.substring(0, idx);
                value = pair.substring(idx + 1);
            }

            if (key != null) {
                try {
                    key = URLDecoder.decode(key, StandardCharsets.UTF_8.toString());
                    if (value != null) {
                        value = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
                    }
                    queryPairs.put(key, value);
                } catch (UnsupportedEncodingException e) {
                    System.out.println("分割url异常");
                }

            }
        }
        return queryPairs;
    }


    private static void getDouYinM3u8(String douyinUrl) {
        Map<String, String> searchMap = convertQueryToMap(douyinUrl);
        String room_id = searchMap.get("room_id");
        CloseableHttpClient httpClient =HttpClients.createDefault();
        try {
            // 创建 SSLContext，忽略证书验证
            SSLContext sslContext = SSLContextBuilder.create()
                    // 信任所有证书
                    .loadTrustMaterial((chain, authType) -> true)
                    .build();

            // 创建 HttpClient，并设置忽略证书验证和主机名验证
             httpClient = HttpClients.custom()
                    .setSSLContext(sslContext)
                    // 不验证主机名
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            // 构建 URI 并设置参数
            URI uri = new URIBuilder("https://webcast.amemv.com/webcast/room/reflow/info/")
                    .setParameter("type_id", "0")
                    .setParameter("live_id", "1")
                    .setParameter("room_id", room_id)
                    .setParameter("app_id", "1128")
                    .build();

            // 创建 HttpGet 请求并设置 Headers
            HttpGet request = new HttpGet(uri);
            request.addHeader("authority", "webcast.amemv.com");
            request.addHeader("cookie", "_tea_utm_cache_1128={\"utm_source\":\"copy\",\"utm_medium\":\"android\",\"utm_campaign\":\"client_share\"}");


            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(request);
            String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");

            // 输出响应内容
            System.out.println("内容是"+ jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭 HttpClient
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
