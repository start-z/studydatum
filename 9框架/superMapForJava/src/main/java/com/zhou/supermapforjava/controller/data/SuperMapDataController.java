package com.zhou.supermapforjava.controller.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.supermap.data.*;
import com.vividsolutions.jts.io.ParseException;
import com.zhou.supermapforjava.utils.R;
import com.zhou.supermapforjava.utils.ShapeUtil;
import com.zhou.supermapforjava.utils.SuperMapDataUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author zhou
 * @since 2023/3/30
 * description: com.supermap.data软件包下面实施模块
 */
@Api(value = "/data", tags = {" com.supermap.data软件包下面实施模块"})
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
    @ApiOperation(value = "投影转换  CoordSysTranslator 投影转换一般有三种工作方式：地理（经纬度）坐标和投影坐标之间的转换使用 forward() 方法、投影坐标和地理（经纬度）坐标之间的转换使用inverse() 方法 、两种投影坐标系之间的转换使用convert() 方法。 <p> 注意：当前版本不支持光栅数据的投影转换。即在同一数据源中，投影转换只转换矢量数据部分。地理坐标系（Geographic coordinate system）也称为地理坐标系统，是以经纬度为地图的存储单位的。很明显，地理坐标系是球面坐标系统。如果将地球上的数字化信息存放到球面坐标系统上，就需要有这样的椭球体具有如下特点：可以量化计算的，具有长半轴（Semimajor Axis），短半轴（Semiminor Axis），偏心率（Flattening），中央子午线（prime meridian）及大地基准面（datum）。投影坐标系统（Projection coordinate system）实质上便是平面坐标系统，其地图单位通常为米。将球面坐标转化为平面坐标的过程便称为投影。所以每一个投影坐标系统都必定会有地理坐标系统（Geographic Coordinate System）参数。 因此就存在着投影坐标之间的转换以及投影坐标系之间的转换。 <p> 在进行投影转换时，对文本对象（GeoText）投影转换后，文本对象的字高和角度会相应地转换，如果用户不需要这样的改变，需要对转换后的文本对象修正其字高和角度。", notes = "投影转换  CoordSysTranslator 投影转换一般有三种工作方式：地理（经纬度）坐标和投影坐标之间的转换使用 forward() 方法、投影坐标和地理（经纬度）坐标之间的转换使用inverse() 方法 、两种投影坐标系之间的转换使用convert() 方法。 <p> 注意：当前版本不支持光栅数据的投影转换。即在同一数据源中，投影转换只转换矢量数据部分。地理坐标系（Geographic coordinate system）也称为地理坐标系统，是以经纬度为地图的存储单位的。很明显，地理坐标系是球面坐标系统。如果将地球上的数字化信息存放到球面坐标系统上，就需要有这样的椭球体具有如下特点：可以量化计算的，具有长半轴（Semimajor Axis），短半轴（Semiminor Axis），偏心率（Flattening），中央子午线（prime meridian）及大地基准面（datum）。投影坐标系统（Projection coordinate system）实质上便是平面坐标系统，其地图单位通常为米。将球面坐标转化为平面坐标的过程便称为投影。所以每一个投影坐标系统都必定会有地理坐标系统（Geographic Coordinate System）参数。 因此就存在着投影坐标之间的转换以及投影坐标系之间的转换。 <p> 在进行投影转换时，对文本对象（GeoText）投影转换后，文本对象的字高和角度会相应地转换，如果用户不需要这样的改变，需要对转换后的文本对象修正其字高和角度。", httpMethod = "GET")
    @GetMapping("/coordSysTranslator")
    public String coordSysTranslator() {
        Point2D point2D = new Point2D(100.23, 2.01);
        Point2Ds point2Ds = new Point2Ds(new Point2D[]{point2D});
        PrjCoordSys prjCoordSys = new PrjCoordSys(PrjCoordSysType.PCS_WGS_1984_UTM_1S);
        boolean forward = CoordSysTranslator.forward(point2Ds, prjCoordSys);
        return JSONObject.toJSONString(prjCoordSys);
    }

    @GetMapping("/queryGeometry")
    public Object getWebRestData(String url) {
        Workspace workspace = new Workspace();
        DatasourceConnectionInfo datasourceconnection = new DatasourceConnectionInfo();
        // 设置 SQL 数据源连接需要的参数
        Datasource datasource = SuperMapDataUtils.builder().connecttionDb(workspace, datasourceconnection);
        //功能实现一 --查询行政区划(adcode)为410100的面数据
        DatasetVector datasetVector = (DatasetVector) datasource.getDatasets().get("T1");
        QueryParameter parameter = new QueryParameter();
        parameter.setAttributeFilter("smlibtileid=1");
//        parameter.setAttributeFilter("adcode=410100");
        parameter.setCursorType(CursorType.STATIC);
        Recordset query = datasetVector.query(parameter);
        JSONArray res = new JSONArray();
        while (!query.isEOF()) {
            Feature feature = query.getFeature();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("smid", String.valueOf(feature.getID()));
            hashMap.put("geomerty", Toolkit.GeometryToGeoJson(feature.getGeometry()));
            res.add(hashMap);
            query.moveNext();
        }

        SuperMapDataUtils.builder().dispose(workspace, datasourceconnection);
        query.dispose();
        return res;

    }

    @PostMapping("/shpInsertDb")
    @ApiOperation(("shp文件数据入库、计算面积(api练习)"))
    public R shpInsertDb(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        JSONObject res = new JSONObject();
        // io拷贝
        File temporartFile = new File("C:\\Users\\BJB-314\\Desktop\\湖南省\\hunan.shp");
        IOUtils.copy(file.getInputStream(), new FileOutputStream(temporartFile));
        JSONArray array = ShapeUtil.readShpFile(temporartFile.getAbsolutePath());
        temporartFile.delete();

        // 创建连接
        Workspace workspace = new Workspace();
        DatasourceConnectionInfo info = new DatasourceConnectionInfo();
        Datasource datasource = SuperMapDataUtils.builder().connecttionDb(workspace, info);

        //1数据入库(字段添加)
//        this.shpInsertDb(datasource, array);

        //2删除数据集
//        this.deleteDataDb(datasource, "T1", "smid=13");

        //3计算面积
//        String area = this.getArea(array);
//        res.put("area",area);



        SuperMapDataUtils.builder().dispose(workspace, info);

        return R.data(res);
    }

    /**
     * 根据wktjson数据获取面数据
     * @param array
     */
    private String getArea(JSONArray array) {
        BigDecimal init = new BigDecimal("0.00");
        for (int i = 0; i < array.size(); i++) {
            String theGeom = array.getJSONObject(i).getString("the_geom");
            GeoRegion geoRegion = (GeoRegion)Toolkit.WKTToGeometry(theGeom);
            init = init.add(BigDecimal.valueOf(geoRegion.getArea()));
        }
        return init.toString();
    }

    /**
     * 数据入库
     *
     * @param datasource 数据集
     * @param array      入库的数据
     */
    public void shpInsertDb(Datasource datasource, JSONArray array) {
        DatasetVectorInfo hunan = new DatasetVectorInfo("hunan", DatasetType.REGION);
        DatasetVector datasetVector = datasource.getDatasets().create(hunan);
        datasetVector.getFieldInfos().add(new FieldInfo("ceshi", FieldType.INT32));
        Recordset recordset2 = datasetVector.getRecordset(false, CursorType.DYNAMIC);
        for (int i = 0; i < array.size(); i++) {
            HashMap<String, Object> map = new HashMap<>(16);
            map.put("ceshi", i);
            String theGeom = array.getJSONObject(i).getString("the_geom");
            Geometry geometry = Toolkit.WKTToGeometry(theGeom);
            recordset2.addNew(geometry, map);
            recordset2.update();
        }
        hunan.dispose();
        recordset2.dispose();
    }
    /**
     * 删除数据集
     */
    public void deleteDataDb(Datasource datasource, String layerName, String condition) {
        DatasetVector datasetVector1 = (DatasetVector) datasource.getDatasets().get(layerName);
        Recordset recordset1 = datasetVector1.query(condition, CursorType.DYNAMIC);
        recordset1.deleteAll();
        recordset1.update();
        recordset1.dispose();
        recordset1.dispose();
    }


}
