package com.zhou.supermapforjava.controller;

import com.zhou.supermapforjava.service.ISuperMapForJavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou
 * @since 2023/3/29
 * description:
 */
@RestController
@RequestMapping("gis")
public class superMapController {

    private static final String SMWU_WORK_FILE = "F:\\zhou\\supermapidesktopData\\SampleData\\China\\China100\\China.smwu";

    @Autowired
    private ISuperMapForJavaService superMapForJavaService;

    @GetMapping("")
    public String test() {
        return "hello";
    }

    /**
     * 空间查询
     *
     * @return 空间数据集
     */
    @GetMapping("/doQuery")
    public Object doQuery() {
        return superMapForJavaService.doSearchQuery(SMWU_WORK_FILE);
    }

}
