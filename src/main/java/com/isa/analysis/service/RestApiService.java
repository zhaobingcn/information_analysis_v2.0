package com.isa.analysis.service;

import org.neo4j.ogm.json.JSONObject;

import java.util.Map;

/**
 * Created by hexu on 2017/1/3.
 */
public interface RestApiService {

    /**
     * 生成可以在echarts展示的数据
     * @param name
     * @param institution
     * @param depath
     * @return
     */
    Map<String, Object> generateWorkTogetherGraph(String name, String institution, int depath);

    Map<String, Object> generateWorkTogetherGraph(Long id, int depath);

    Map<String, Object> generateAuthorGraph(JSONObject restResult, int depath);

    Map<String, Object> generateSimilarGraph(Long id, int depath);

    Map<String, Object> generateSimilarGraph(String name, int depath);

    Map<String, Object> generateKeywordGraph(JSONObject restResult, int depath);
}
