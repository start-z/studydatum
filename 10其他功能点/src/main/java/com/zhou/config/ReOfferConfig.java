package com.zhou.config;

import jdk.nashorn.internal.ir.LexicalContext;

public class ReOfferConfig {
    public static String longBaoYinCondition = "{t-1}日涨停;{t}日振幅小于15%;{t}日开盘涨幅大于-1%;{t}日成交量大于3000万;{t}日开盘价大于收盘价;{t}日竞价金额大于2000万;{t}日收盘价<{t-1}日收盘价;（{t}日成交量/{t-1}日成交量)<4;{t}日换手率>15%;不是st股、创业板股票、北交所股票、科创板股票;";
    public static String playingCondition = "{t-1}日涨停且2<{t-1}日连续涨停天数<6,{t-1}日14点30分前涨停,2%<{t}日竞价涨幅<5%,不是st股、创业板股票、北交所股票、科创板股票";
    public static String oneEnterTwoCondition = "{t-1}日涨停且{t-1}日连续涨停天数=1,2%<{t}日9点25分涨幅<5%,{t}日竞价金额大于3000万,10<股价<50,不是st股、创业板股票、北交所股票、科创板股票";
    public static String newDaBanCondition = "{t-1}日的涨停;{t-1}日的连续涨停天数>0日且{t-1}日的连续涨停天数<6日;{t-1}日14点30分前涨停;{t}日9点25分的涨跌幅>2%且{t}日9点25分的涨跌幅<7%;{t}日的竞价金额大于3000万元;{t}日的收盘价>4元且{t}日的收盘价<50元;股票简称不包含st且上市板块不包含创业板且股票市场类型不包含北交所且上市板块不包含科创板;";
    public static String lowPacketCondition = "{t-1}日首板;{t-1}日9点25分的涨跌幅<7%;3%<{t}日振幅且{t}日为阴线;{t}日个股热度排名小于500;-0.02<({t-1}日收盘价/{t}日收盘价)-1<0.07;{t}日9点25分的涨跌幅<5%;股价小于30;不是st股、创业板股票、北交所股票、科创板股票;";

}
