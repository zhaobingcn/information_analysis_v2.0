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
    JSONObject getWorkTogetherPaths(String name, String institution, int depath);

    /**
     * 查询图中作者合作关系的子图,可以定义深度,使用id作为参数
     */

    JSONObject getWorkTogetherPaths(Long id, int depath);

    /**
     * 查询途中关键词关系的子图，可以定义深度，使用id作为参数
     * @param id
     * @param depath
     * @return
     */
    JSONObject getSimilarPaths(Long id, int depath);

    /**
     * 使用关键词名字作为参数
     * @param name
     * @param depath
     * @return
     */
    JSONObject getSimilarPaths(String name, int depath);

    /**
     * 生成rest 请求的参数格式
     * @param query
     * @param parameters
     * @param resultDataContents
     * @return
     */
    JSONObject generateStatements(String query, Map<String, Object> parameters, String[] resultDataContents);

    /**
     * @param id
     * @param type all, in, out, relationshiptype
     * @return
     */
    Long getDegreeOfNode(Long id, String type);


}
