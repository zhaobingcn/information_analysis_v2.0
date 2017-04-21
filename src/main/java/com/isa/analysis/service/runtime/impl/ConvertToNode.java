package com.isa.analysis.service.runtime.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by zhzy on 17-4-19.
 */
@Component
public class ConvertToNode {
    public List<Map<String, String>> getAuthors(JSONObject object){
        List<Map<String, String>> authors = new ArrayList<>();
        if(object.getJSONObject("authors") == null){
            return authors;
        }
        JSONObject authorsObject = object.getJSONObject("authors");
        Iterator<String> authorKeys = authorsObject.keys();
        while (authorKeys.hasNext()){
            Map<String, String> author = new HashMap<>();
            String name = authorKeys.next();
            author.put("name", name);
            JSONObject ins = authorsObject.getJSONObject(name);
            String institution = "";
            institution = ins.getString("institution");
            author.put("institution", institution);
            authors.add(author);
        }
        return authors;
    }

    public List<Map<String, String>> getInsittution(JSONObject object){
        List<Map<String, String>> institutions = new ArrayList<>();
        if(object.getJSONObject("institutions") == null){
            return institutions;
        }
        JSONObject insObject = object.getJSONObject("institutions");
        Iterator<String> insKeys = insObject.keys();
        while(insKeys.hasNext()){
            Map<String, String> institution = new HashMap<>();
            String name = insKeys.next();
            institution.put("name", name);
            String location = "";
            if(insObject.get(name).getClass().equals(String.class)){
                location = insObject.getString(name);
            }
            institution.put("location", location);
            institutions.add(institution);
        }
        return institutions;
    }

    public Map<String, Object> getPaper(JSONObject object){
        Map<String, Object> paper = new HashMap<>();
        if(object.getString("title") != null){
            paper.put("title", object.getString("title"));
        }
        if(object.getString("quote") != null){
            paper.put("quote", Integer.parseInt(object.getString("quote")));
        }
        if(object.getString("link") != null){
            paper.put("link", object.getString("link"));
        }
        if(object.get("date").getClass().equals(JSONObject.class)){
            JSONObject date = object.getJSONObject("date");
            String year = date.getString("year");
            //period有字母的问题
            Integer period = 1;
            if(date.getString("period").startsWith("z") || date.getString("period").startsWith("Z")){
                period = Integer.parseInt(date.getString("period").substring(1));
            }else if(!date.getString("period").contains("_")){
                period = date.getInt("period");
            }
            String strPeriod = period.toString();
            if(period / 10 == 0){
                strPeriod = "0" + strPeriod;
            }
            String strDate = year + strPeriod;
            paper.put("date", strDate);
        }else{
            paper.put("date", "000000");
        }
        return paper;
    }

    public Map<String, String> getJournal(JSONObject object){
        Map<String, String> journal = new HashMap<>();
        if(object.get("journal").getClass().equals(String.class)){
            journal.put("name", object.getString("journal"));
        }
        return  journal;
    }

    public List<String> getInclude(JSONObject object){
        List<String> includes = new ArrayList<>();
        if (object.get("include").getClass().equals(JSONArray.class)) {
            if (object.getJSONArray("include") == null) {
                return includes;
            } else {
                JSONArray includeArray = object.getJSONArray("include");
                for (Object inc : includeArray) {
                    String include = inc.toString();
                    includes.add(include);
                }
            }
        } else if(object.get("include").getClass().equals(String.class)){
            includes.add(object.getString("include"));
        }
        return includes;
    }


    public List<String> getKeyWords(JSONObject object){
        List<String> keywords = new ArrayList<>();
        if(object.getJSONArray("keywords") != null){
            JSONArray keywordsArray = object.getJSONArray("keywords");
            for(Object key: keywordsArray){
                String keyword = key.toString();
                keywords.add(keyword);
            }
        }
        return keywords;
    }
}
