package com.zhou.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author zhou
 * @since 2024/9/23
 * description:
 */
public class WzryUtils {
    private  static String configUrl = "https://pvp.qq.com/web201605/js/herolist.json";
    private  static String videoUrl = "https://smoba.ws.tcdnos.com/Banner/img/Video/skin/%s.mp4?mkey=lego_ztc&f=00&sche_type=7&cip=183.221.19.242&proto=https&tx_domain=image.smoba.qq.com&tx_id=6c9382a8c8";
    private  static   Map<String,JSONObject> allHeros = new HashMap<>();

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        getConfig();
        Api api = new Api();
        boolean error = true;
        while (error) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请你输入想要下载的英雄mv或者语音");
            String heroName = scanner.nextLine();
            JSONObject jsonObject = allHeros.get(heroName);
            String heroId = jsonObject.getString("ename");
            if (jsonObject == null) {
                System.out.println("当前没有这个英雄");
                error = false;
                return;
            }
            System.out.println("下载mv输入1;下载语音输入2");
            int isMv = scanner.nextInt();
            if (isMv == 1) {
                System.out.println("请你选择这个英雄的皮肤");
                String[] skin_names = jsonObject.getString("skin_name").split("\\|");
                for (int i = 0; i < skin_names.length; i++) {
                    System.out.println(i + ":" + skin_names[i]);
                }
                System.out.println("请选择" + heroName + "的皮肤");
                int skin = scanner.nextInt();
                System.out.println("你的英雄mv地址为" + String.format(videoUrl, "30" + heroId + skin));
            }else if(isMv == 2){
                System.out.println("正在查询中");
                //获取到语音集合
                api.getVoicesByHeroId(heroId);


            }
        }
    }

    public  void downloadMv(){

    }

    public  static   void getConfig() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        String entityJSON = HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET, configUrl, "", new HashMap<>());
        JSONArray result =  Objects.requireNonNull(JSONObject.parseArray(entityJSON));
        // 使用 Stream 将 JSONObject 转换为 Map
        for (int i = 0; i < result.size(); i++) {
            JSONObject item = (JSONObject) result.get(i);
            allHeros.put(item.getString("cname"), item);
        }
    }
}
