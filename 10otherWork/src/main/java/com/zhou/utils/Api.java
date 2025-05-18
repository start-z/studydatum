package com.zhou.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Api {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final String baseUrl = "https://pvp.qq.com/zlkdatasys/yuzhouzhan/";

    private String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public String getHeroList() throws IOException {
        return HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET,baseUrl + "list/heroList.json?t=" + getTimestamp(),null,null);
    }

    public String getHeroDetail(String id) throws IOException {
        return HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET,baseUrl + "hero/detail/" + id + ".json?t=" ,null,null);
    }

    public String getAnimationList() throws IOException {
        return HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET,baseUrl + "video/list.json?t=" ,null,null);
    }

    public String getCartoonList() throws IOException {
        return HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET,baseUrl + "cartoonlist.json?t=" ,null,null);
    }

    public String getHeroStory(String id) throws IOException {
        return HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET,baseUrl + "storyhero/story" + id + ".json?t=" ,null,null);
    }

    public String getSkinList() throws IOException {
        return HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET,baseUrl + "pfjs.json?t=" ,null,null);
    }

    public String getHomeData() throws IOException {
        return HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET,baseUrl + "index/home.json?t=" ,null,null);
    }

    public String getRegionDetail(String sUrl) throws IOException {
        return HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET,sUrl + "?t=" ,null,null);
    }

    public String getVoicesByHeroId(String sId) throws IOException {
        return HttpClientUtils.sendRequest(HttpClientUtils.HttpMethod.GET,baseUrl + "herovoice/" + sId + ".json?t=" ,null,null);
    }

    private String makeGetRequest(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity());
        }
    }
}
