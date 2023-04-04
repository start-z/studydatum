package com.zhou.supermapforjava.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.supermap.data.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou
 * @since 2023/3/30
 * description: com.supermap.data软件包下面实施模块
 */
@RestController
@RequestMapping("/data")
public class SuperMapDataController {

    /**
     * 投影转换  CoordSysTranslator
     * 投影转换一般有三种工作方式：地理（经纬度）坐标和投影坐标之间的转换使用 forward() 方法、投影坐标和地理（经纬度）坐标之间的转换使用inverse() 方法 、两种投影坐标系之间的转换使用convert() 方法。
     * <p>
     * 注意：当前版本不支持光栅数据的投影转换。即在同一数据源中，投影转换只转换矢量数据部分。地理坐标系（Geographic coordinate system）也称为地理坐标系统，是以经纬度为地图的存储单位的。很明显，地理坐标系是球面坐标系统。如果将地球上的数字化信息存放到球面坐标系统上，就需要有这样的椭球体具有如下特点：可以量化计算的，具有长半轴（Semimajor Axis），短半轴（Semiminor Axis），偏心率（Flattening），中央子午线（prime meridian）及大地基准面（datum）。投影坐标系统（Projection coordinate system）实质上便是平面坐标系统，其地图单位通常为米。将球面坐标转化为平面坐标的过程便称为投影。所以每一个投影坐标系统都必定会有地理坐标系统（Geographic Coordinate System）参数。 因此就存在着投影坐标之间的转换以及投影坐标系之间的转换。
     * <p>
     * 在进行投影转换时，对文本对象（GeoText）投影转换后，文本对象的字高和角度会相应地转换，如果用户不需要这样的改变，需要对转换后的文本对象修正其字高和角度。
     */
    @GetMapping("/coordSysTranslator")
    public String coordSysTranslator() {
        Point2D point2D = new Point2D(100.23, 2.01);
        Point2Ds point2Ds = new Point2Ds(new Point2D[]{point2D});
        PrjCoordSys prjCoordSys = new PrjCoordSys(PrjCoordSysType.PCS_WGS_1984_UTM_1S);
        boolean forward = CoordSysTranslator.forward(point2Ds, prjCoordSys);
        return JSONObject.toJSONString(prjCoordSys);
    }


}
