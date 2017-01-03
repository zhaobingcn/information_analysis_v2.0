package com.isa.analysis.restapi.httprepository;

import org.neo4j.ogm.json.JSONObject;

import java.util.Map;

/**
 * Created by hexu on 2017/1/3.
 */
public interface RestApiRepository {

    /**
     * 查询图中作者合作关系的子图,可以定义深度
     * @return 返回子图所有路径
     */
    JSONObject generateWorkTogetherGraph(String name, String institution, int depath);

    /**
     * 生成rest 请求的参数格式
     * @param query
     * @param parameters
     * @param resultDataContents
     * @return
     */
    JSONObject generateStatements(String query, Map<String, Object> parameters, String[] resultDataContents);
}
