package com.zhou.supermapforjava.utils;

import com.supermap.data.Datasource;
import com.supermap.data.DatasourceConnectionInfo;
import com.supermap.data.EngineType;
import com.supermap.data.Workspace;

/**
 * @author zhou
 * @since 2024/1/5
 * description:  连接超图数据源
 */
public class SuperMapDataUtils {

    private EngineType Type = EngineType.POSTGRESQL;
    private String server = "localhost:5432";
    private String database = "test-gis";
    private String username = "postgres";
    private String password = "postgres";
    private String alias = "POSTGRESQL" + System.currentTimeMillis();

    public static SuperMapDataUtils builder(){
        return new SuperMapDataUtils();
    }
    /**
     * 获取数据库型数据源(最好先通过idespo创建数据库数据源)
     * @param workspace 工作空间
     * @param datasourceconnection  数据源信息
     * @return  打开的数据源
     */
    public Datasource connecttionDb(Workspace workspace, DatasourceConnectionInfo datasourceconnection){
        // 设置 SQL 数据源连接需要的参数
        datasourceconnection.setEngineType(Type);
        datasourceconnection.setServer(server);
        datasourceconnection.setDatabase(database);
        datasourceconnection.setUser(username);
        datasourceconnection.setPassword(password);
        datasourceconnection.setAlias(alias);
        return  workspace.getDatasources().open(datasourceconnection);
    }

    /**
     * 资源统一释放
     * @param workspace 工作空间
     * @param datasourceconnection  数据源信息
     */
    public void  dispose(Workspace workspace, DatasourceConnectionInfo datasourceconnection){
        workspace.dispose();
        datasourceconnection.dispose();
    }
}
