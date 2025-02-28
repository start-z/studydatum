package com.zhou.config;

import jdk.nashorn.internal.ir.LexicalContext;

public class ReOfferConfig {
    public static String longBaoYinCondition = "{t-1}日涨停,{t}日成交量>5000万且{t}日成交量为阴线且{t}日竞价金额>5000万，{t}日收盘价<{t-1}日收盘价，（{t}日成交量/{t-1}日成交量)<2.5，{t}日换手率>15%,不是st股、创业板股票、北交所股票、科创板股票";
    public static String playingCondition = "{t-1}日涨停且2<{t-1}日连续涨停天数<6,{t-1}日14点30分前涨停,2%<{t}日竞价涨幅<5%,不是st股、创业板股票、北交所股票、科创板股票";
    public static String oneEnterTwoCondition = "{t-1}日涨停且{t-1}日连续涨停天数=1,2%<{t}日9点25分涨幅<5%,{t}日竞价金额>3000万,10<股价<50,不是st股、创业板股票、北交所股票、科创板股票";
    public static String newDaBanCondition = "{t-1}日涨停且0<{t-1}日连续涨停天数<6,2%<{t}日9点25分涨幅<5%,{t}日竞价金额>3000万,4<股价<50,不是st股、创业板股票、北交所股票、科创板股票";
}
