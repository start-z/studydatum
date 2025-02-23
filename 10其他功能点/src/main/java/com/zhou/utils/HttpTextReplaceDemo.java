package com.zhou.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class HttpTextReplaceDemo {
    public  void getDDL() {
        String date = "2025-01";
        // 假设我们要请求的URL，获取某个指定日期的数据
        String targetUrl = "http://api.haoshenqi.top/holiday?date=" + date; // 用实际的API替换

        // 执行HTTP请求并获取返回的JSON数据
        String jsonResponse = sendHttpRequest(targetUrl);

        // 假设返回的数据包含一个"tradeDate"字段，我们需要用它来替换文本
        if (jsonResponse != null) {
            List<Holiday> holidays = JSONArray.parseArray(jsonResponse, Holiday.class);
            holidays = holidays.stream().filter(item -> item.status == 0).collect(Collectors.toList());
            // 模拟一个文本替换操作 {t-2}成交量为阳线,
            String templateText = "{t-1}涨停,{t}成交量>5000万且{t}成交量为阴线且{t}竞价金额>1000万，{t}收盘价<{t-1}收盘价，（{t}成交量/{t-1}成交量)<2.5，{t}换手率>15%,不是st股、创业板股票、北交所股票、科创板股票";
            String templateText2 = "{t-1}日涨停且2<{t-1}日连续涨停天数<6,{t-1}日14点前涨停,{t}日早盘竞价金额>2000万,2%<{t}日9点25分涨幅<5%,不是st股、创业板股票、北交所股票、科创板股票";
            for (int i = 1; i < holidays.size(); i++) {
                String updatedText = templateText.replace("{t}", holidays.get(i).getDate()).replace("{t-1}", holidays.get(i - 1).getDate());
//                System.out.println("龙包阴条件:");
//                System.out.println(updatedText);
                System.out.println("打板条件:");
                String updatedText2 = templateText2.replace("{t}", holidays.get(i).getDate()).replace("{t-1}", holidays.get(i - 1).getDate());
                System.out.println(updatedText2);
                System.out.println("----------");
            }
        }
    }

    public String sendHttpRequest(String targetUrl) {
        StringBuilder result = new StringBuilder();

        try {
            // 创建URL对象
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置HTTP请求方法
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            // 获取响应码
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("HTTP Request failed with response code: " + responseCode);
                return null;
            }

            // 读取响应内容
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public  static  class Holiday {
        private String date;
        private int year;
        private int month;
        private int day;
        private int status;

    }
}

