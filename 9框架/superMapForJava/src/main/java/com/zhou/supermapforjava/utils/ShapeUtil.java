package com.zhou.supermapforjava.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ShapeUtil {
    /**
     * 读取shp
     * @param filePath
     * @return
     */
    public static JSONArray readShpFile(String filePath) {
        JSONArray features = new JSONArray();
        JSONObject fe=null;
        try {
            File file = new File(filePath);
            FileDataStore myData = FileDataStoreFinder.getDataStore(file);
            // 设置解码方式
            ((ShapefileDataStore) myData).setCharset(StandardCharsets.UTF_8);
            SimpleFeatureSource source = myData.getFeatureSource();
            SimpleFeatureCollection featureCollection=source.getFeatures();
            SimpleFeatureIterator featureIter = featureCollection.features();
            // 利用SimpleFeature的迭代器，依次遍历所有的SimpleFeature并输出其属性值
            while (featureIter.hasNext()) {
                fe = new JSONObject();
                SimpleFeature feature = featureIter.next();
                List<Property> ps= feature.getProperties().stream().collect(Collectors.toList());
                for (Property p : ps){
                    fe.put(p.getName().toString(),p.getValue());
                }
                features.add(fe);
            }
            featureIter.close();
            return features;
        } catch (Exception e) {
            log.error(e.getMessage());
            return features;
        }
    }

}
