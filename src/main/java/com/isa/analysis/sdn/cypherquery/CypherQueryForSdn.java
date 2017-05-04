package com.isa.analysis.sdn.cypherquery;

/**
 * Created by zhzy on 2016/12/31.
 */
public class CypherQueryForSdn {

//    String a = "MATCH (k:Keyword) WHERE id(k) = 42 CALL apoc.path.subgraphAll(k, {maxLevel:2, relationshipFilter:\"similar\", bfs:true, filterStartNode:false, limit:1000}) YIELD relationships with relationships as rel\n" +
//            "return extract(r in rel | startNode(r)), extract(r in rel | endNode(r))";
//
//    String a = "match (k:Keyword)<-[:involve]-(p:Paper) where k.name=\"FPGA\" AND toInt(p.date)>=200701 AND toInt(p.date)<200801 with p \n" +
//            "match (p)<-[:publish]-(a:Author) return count (distinct(a))";
//
//    String a = "match (k:Keyword)<-[:involve]-(p:Paper) where k.name=\"FPGA\" AND toInt(p.date)>=200701 AND toInt(p.date)<200801 return count(distinct(p))";
//
//
//    String a = "match (k:Keyword)<-[:involve]-(p:Paper) where k.name=\"FPGA\" AND toInt(p.date)>=200701 AND toInt(p.date)<200801 with p \n" +
//            "match (p)<-[:publish]-(a:Author)-[:works_in]->(i:Institution) return count(distinct(i))";
}
