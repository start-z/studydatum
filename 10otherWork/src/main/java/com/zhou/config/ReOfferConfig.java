package com.zhou.config;

import jdk.nashorn.internal.ir.LexicalContext;

public class ReOfferConfig {
    public static String longBaoYinCondition = "{t-1}涨停;{t}振幅小于15%;{t}开盘涨幅大于-1%;{t}成交量大于3000万;{t}开盘价大于收盘价;{t}竞价金额大于2000万;{t}收盘价<{t-1}收盘价;（{t}成交量/{t-1}成交量)<4;{t}换手率>15%;不是st股、创业板股票、北交所股票、科创板股票;";
    public static String playingCondition = "{t-1}涨停且2<{t-1}连续涨停天数<6,{t-1}14点30分前涨停,2%<{t}竞价涨幅<5%,不是st股、创业板股票、北交所股票、科创板股票";
    public static String oneEnterTwoCondition = "{t-1}涨停且{t-1}连续涨停天数=1,2%<{t}9点25分涨幅<5%,{t}竞价金额大于3000万,10<股价<50,不是st股、创业板股票、北交所股票、科创板股票";
    public static String newDaBanCondition = "{t-1}的涨停;{t-1}的连续涨停天数>0且{t-1}的连续涨停天数<6;{t-1}14点30分前涨停;{t}9点25分的涨跌幅>2%且{t}9点25分的涨跌幅<7%;{t}的竞价金额大于3000万元;{t}的收盘价>4元且{t}的收盘价<50元;股票简称不包含st且上市板块不包含创业板且股票市场类型不包含北交所且上市板块不包含科创板;";
    public static String lowPacketCondition = "{t-1}首板;{t-1}9点25分的涨跌幅<7%;{t}个股热度排名小于500;3%<{t}振幅且{t}为阴线;-0.02<({t-1}收盘价/{t}收盘价)-1<0.07;-2%<{t}9点25分的涨跌幅<5%;股价小于30;不是st股、创业板股票、北交所股票、科创板股票;";

}
