/**
 * Created by lsy on 2017/3/14.
 */
function loadKeywordRelationship() {
    alert("进入函数了")
    var myChart = echarts.init(document.getElementById("keyword-relationship"));
    myChart.showLoading();
    $.ajax({
        url : "/keywordDetail/keywordsRelationship",
        type: "get",
        dataType : "json",
        data:{
            "id": 28,
            "depath" : 2
        },
        success : function (json) {
            // alert(json.nodes[0].toString());
        myChart.hideLoading();
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
                        // alert(idx);
                        return {
                            x: Math.random()*250,
                            y: Math.random()*100,
                            id: idx,
                            name: node.name,
                            symbolSize: Math.sqrt(node.value)*2,
                            itemStyle: {
                                normal: {
                                    color: "#" +(Math.random()*0Xffffff<<0).toString(16)
                                }
                            }
                        };
                    }),
                    //有关各边的信息
                    edges: json.links.map(function (edge) {
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