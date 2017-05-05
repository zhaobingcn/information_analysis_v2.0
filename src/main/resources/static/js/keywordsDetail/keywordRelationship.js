/**
 * Created by lsy on 2017/3/14.
 */
function loadKeywordRelationship() {
    var myChart = echarts.init(document.getElementById("keyword-relationship"));
    myChart.showLoading();
    var width = document.getElementById("keyword-relationship").clientWidth;
    var height = document.getElementById("keyword-relationship").clientHeight;
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
                            x: Math.random()*width,
                            y: Math.random()*height,
                            id: idx,
                            name: node.name,
                            symbolSize: Math.pow(node.value, 1/3)*4,
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
                            width: 1.0,
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