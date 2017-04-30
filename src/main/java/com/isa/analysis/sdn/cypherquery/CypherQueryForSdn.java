package com.isa.analysis.sdn.cypherquery;

/**
 * Created by zhzy on 2016/12/31.
 */
public class CypherQueryForSdn {

    String a = "MATCH (k:Keyword) WHERE id(k) = 42 CALL apoc.path.subgraphAll(k, {maxLevel:2, relationshipFilter:\"similar\", bfs:true, filterStartNode:false, limit:1000}) YIELD relationships with relationships as rel\n" +
            "return extract(r in rel | startNode(r)), extract(r in rel | endNode(r))";


}
