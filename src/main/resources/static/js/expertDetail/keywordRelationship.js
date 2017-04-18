/**
 * Created by lsy on 2017/3/14.
 */
function loadKeywordRelationship() {
    var myChart = echarts.init(document.getElementById("keyword-relationship"));
    myChart.showLoading();
    $.getJSON('../data/dataofrelationship.json', function (json) {
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
                    data: json.nodes.map(function (node) {
                        return {
                            x: node.x,
                            y: node.y,
                            id: node.id,
                            name: node.label,
                            symbolSize: node.size,
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
                            source: edge.sourceID,
                            target: edge.targetID
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
    });
}

loadKeywordRelationship();