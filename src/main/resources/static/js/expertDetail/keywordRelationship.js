/**
 * Created by lsy on 2017/3/14.
 */
function loadKeywordRelationship() {
    var myChart = echarts.init(document.getElementById("keyword-relationship"));
    myChart.showLoading();
    $.ajax({
        url : "/keywordDetail/keywordsRelationship",
        type: "get",
        dataType : "json",
        data:{
            "id": id,
            "depath" : depath
        },
        success : function (json) {

        var option = {
            animationDurationUpdate: 1500,
            animationEasingUpdate: 'quinticInOut',
            series : [
                {
                    type: 'graph',
                    layout: 'none',
                    // progressiveThreshold: 700,
                    //有关各点的信息
                    data: json.nodes.map(function (node, idx) {
                        return {
                            x: Math.random()*1000,
                            y: Math.random()*1000,
                            id: idx,
                            name: node.name,
                            symbolSize: node.value,
                            itemStyle: {
                                normal: {
                                    color: node.color
                                }
                            }
                        };
                    }),
                    //有关各边的信息
                    edges: json.edges.map(function (edge) {
                        return {
                            source: edge.source,
                            target: edge.target
                        };
                    }),
                    //显示标签于右侧
                    label: {
                        emphasis: {
                            position: 'right',
                            show: true
                        }
                    },
                    roam: true,
                    focusNodeAdjacency: true,
                    lineStyle: {
                        normal: {
                            width: 0.5,
                            curveness: 0.3,
                            opacity: 0.7
                        }
                    }
                }
            ]
        };
            myChart.setOption(option);
    }});
}

loadKeywordRelationship();