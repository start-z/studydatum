package com.zhou.supermapforjava.controller;

import com.zhou.supermapforjava.service.ISuperMapForJavaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou
 * @since 2023/3/29
 * description:
 */
@Api(value = "gis", tags = {"gis测试地图类"})
@RestController
@RequestMapping("gis")
public class SuperMapController {

    private static final String SMWU_WORK_FILE = "F:\\zhou\\supermapidesktopData\\SampleData\\China\\China100\\China.smwu";

    @Autowired
    private ISuperMapForJavaService superMapForJavaService;

    @ApiOperation(value = "", notes = "", httpMethod = "GET")
    @GetMapping("")
    public String test() {
        return "hello";
    }

    /**
     * 空间查询
     *
     * @return 空间数据集
     */
    @ApiOperation(value = "空间查询", notes = "空间查询", httpMethod = "GET")
    @GetMapping("/doQuery")
    public Object doQuery() {
        return superMapForJavaService.doSearchQuery(SMWU_WORK_FILE);
    }

}
