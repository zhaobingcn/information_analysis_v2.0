package com.isa.analysis.restapi;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URLDecoder;

@Repository
public class RestQuery {

    /**
     * httpPost
     * @param url  路径
     * @param jsonParam 参数
     * @return
     */
    public JSONObject httpPost(String url, JSONObject jsonParam){
        return httpPost(url, jsonParam, false);
    }

    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
        /**
         * post请求返回结果
         */
        JSONObject jsonResult = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                /**
                 * 解决中文乱码问题
                 */
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = new JSONObject(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }


    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public JSONObject httpGet(String url){
        /**
         * get请求返回结果
         */
        JSONObject jsonResult = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            /**
             * 发送get请求
             */
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = new JSONObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}