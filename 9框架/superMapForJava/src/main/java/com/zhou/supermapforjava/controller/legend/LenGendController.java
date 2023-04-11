package com.zhou.supermapforjava.controller.legend;

import com.zhou.supermapforjava.utils.HttpRequester;
import com.zhou.supermapforjava.utils.HttpRespons;
import com.zhou.supermapforjava.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author zhou
 * @since 2023/4/7
 * description:
 */
@Api(value = "lenGend", tags = {"图例控制器"})
@RestController
@RequestMapping("lenGend")
public class LenGendController {

    @Autowired
    private HttpRequester httpRequester;

    @ApiOperation(value = "根据url获取图例json", notes = "", httpMethod = "GET")
    @GetMapping("/getLayerLenGendJson")
    public R getLayerLenGend() throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("f", "json");
        HttpRespons response = httpRequester.sendGet("http://localhost:6080/arcgis/rest/services/SampleWorldCities/MapServer/legend", params);
        return R.success(response.getContent());
    }


}
