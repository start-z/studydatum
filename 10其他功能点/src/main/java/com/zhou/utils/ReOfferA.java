package com.zhou.utils;

import com.alibaba.fastjson.JSONArray;
import com.zhou.config.ReOfferConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 条件复盘
 */
@Component
public class ReOfferA {
    public void getDDL(String date) {
        Scanner scanner = new Scanner(System.in);
        // 假设我们要请求的URL，获取某个指定日期的数据
        String targetUrl = "http://api.haoshenqi.top/holiday?date=" + date; // 用实际的API替换
        System.out.println("请输入你想要的复盘条件");
        System.out.println("1:龙包阴");
        System.out.println("2:高位博龙头");
        System.out.println("3:首板");
        System.out.println("4:新打板");
        System.out.println("5:低位涨停反包");
        int anInt = scanner.nextInt();
        // 执行HTTP请求并获取返回的JSON数据
        String jsonResponse = sendHttpRequest(targetUrl);

        // 假设返回的数据包含一个"tradeDate"字段，我们需要用它来替换文本
        if (jsonResponse != null) {
            List<Holiday> holidays = JSONArray.parseArray(jsonResponse, Holiday.class);
            holidays = holidays.stream().filter(item -> item.status == 0).collect(Collectors.toList());
            switch (anInt) {
                case 1:
                    longBaoyin(holidays);
                    break;
                case 2:
                    playingBoard(holidays);
                    break;
                case 3:
                    shouban(holidays);
                    break;
                case 4:
                    newDaBan(holidays);
                    break;
                case 5:
                    lowPacket(holidays);
                    break;
                default:
                    System.out.println("请按照规则输入指令");
                    break;
            }
        }
    }

    public void newDaBan(List<Holiday> holidays) {
        System.out.println("新开发打板:");
        for (int i = 1; i < holidays.size(); i++) {
            String updatedText = ReOfferConfig.newDaBanCondition.replace("{t}", holidays.get(i).getDate()).replace("{t-1}", holidays.get(i - 1).getDate());
            System.out.println(updatedText);
        }
    }

    /**
     * 低位涨停反包
     * @param holidays
     */
    public void lowPacket(List<Holiday> holidays) {
        System.out.println("低位涨停反包:");
        for (int i = 1; i < holidays.size(); i++) {
            String updatedText = ReOfferConfig.lowPacketCondition
                    .replace("{t}", holidays.get(i).getDate())
                    .replace("{t-1}", holidays.get(i - 1).getDate());
            System.out.println(updatedText);
        }
    }

    /**
     * 龙包阴战法 复盘
     */
    public void longBaoyin(List<Holiday> holidays) {
        System.out.println("龙包阴条件:");
        for (int i = 1; i < holidays.size(); i++) {
            String updatedText = ReOfferConfig.longBaoYinCondition.replace("{t}", holidays.get(i).getDate()).replace("{t-1}", holidays.get(i - 1).getDate());
            System.out.println(updatedText);
        }
    }

    /**
     * 首板 复盘
     */
    public void shouban(List<Holiday> holidays) {
        System.out.println("一进二:");
        for (int i = 1; i < holidays.size(); i++) {
            String updatedText = ReOfferConfig.oneEnterTwoCondition.replace("{t}", holidays.get(i).getDate()).replace("{t-1}", holidays.get(i - 1).getDate());
            System.out.println(updatedText);
        }
    }

    /**
     * 打板
     *
     * @param holidays
     */
    public void playingBoard(List<Holiday> holidays) {
        System.out.println("打板条件:");
        for (int i = 1; i < holidays.size(); i++) {
            String updatedText = ReOfferConfig.playingCondition.replace("{t}", holidays.get(i).getDate()).replace("{t-1}", holidays.get(i - 1).getDate());
            System.out.println(updatedText);
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
    public static class Holiday {
        private String date;
        private int year;
        private int month;
        private int day;
        private int status;

        public String getDate() {
            return year + "年" + month + "月" + day;
        }
    }

}

