package com.zhou.supermapforjava.service.impl;

import com.supermap.data.*;
import com.zhou.supermapforjava.service.ISuperMapForJavaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author zhou
 * @since 2023/3/30
 * description:
 */
@Slf4j
@Service("superMapForJavaService")
public class ISuperMapForJavaServiceImpl implements ISuperMapForJavaService {


    /**
     * 空间查询
     *
     * @param SMWU_WORK_FILE 本地文件名
     * @return
     */
    @Override
    public Object doSearchQuery(String SMWU_WORK_FILE) {
        Workspace workspace = null;
        WorkspaceConnectionInfo connectionInfo = null;
        ArrayList<Object> searchList = null;
        QueryParameter parameter = null;
        try {
            workspace = new Workspace();
            connectionInfo = new WorkspaceConnectionInfo();
            connectionInfo.setType(WorkspaceType.SMWU);
            connectionInfo.setServer(SMWU_WORK_FILE);
            if (!workspace.open(connectionInfo)) {
                return "打开工作空间失败";
            }
            parameter = new QueryParameter();
            parameter.setAttributeFilter("20000");
            searchList = new ArrayList<>();
            for (int i = 0; i < workspace.getDatasources().getCount(); i++) {
                Datasource datasource = workspace.getDatasources().get(i);
                Datasets datasets = datasource.getDatasets();
                for (int j = 0; j < datasets.getCount(); j++) {
                    DatasetVector dataset = (DatasetVector) datasets.get(j);
                    Recordset query = dataset.query(parameter);
                    //将查询到的记录集放入集合中
                    if (query.getRecordCount() > 0) {
                        searchList.add(query.getFieldInfos());
                    }
                }
            }
        } catch (Exception e) {
            log.error("查询数据失败,{0}", e);
        } finally {
            parameter.dispose();
            connectionInfo.dispose();
            workspace.dispose();
        }
        return searchList;
    }

}
