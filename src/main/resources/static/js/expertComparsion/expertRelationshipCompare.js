/**
 * Created by lsy on 2017/2/28.
 */
function loadRelationshipExpertCompare(id, depath, window) {
    var $loadWindow = document.getElementById(window);
    var myChart = echarts.init($loadWindow);
    myChart.showLoading();

    $.ajax({
        url : "/analysis/ComparisonOfExpert/cooperateOfAuthor",
        type: "get",
        dataType : "json",
        data:{
            "id": id,
            "depath" : depath
        },
        success : function (graph) {
            myChart.hideLoading();
            graph.nodes.forEach(function (node) {
                node.itemStyle = null;
                node.value = node.value;
                node.symbolSize = Math.sqrt(node.value)*7;
                node.label = {
                    normal: {
                        show: node.symbolSize > 5
                    }
                };
                node.category = node.category;
            });
            var legendData = [];
            var categoriesLength = graph.categories.length;
            for(var i=0; i<categoriesLength; i++){
                legendData[i] = graph.categories[i].name;
            }
            option = {
                title: {
                    text: '作者合作关系图',
                    top: 'bottom',
                    left: 'right'
                },
                tooltip: {},
                legend: {
                    data: legendData
                },
                animationDurationUpdate: 1500,
                animationEasingUpdate: 'quinticInOut',
                series : [
                    {
                        name: '专家',
                        type: 'graph',
                        layout: 'force',
                        circular: {
                            rotateLabel: true
                        },
                        data: graph.nodes.map(function (node, idx) {
                            node.id = idx;
                            return node;
                        }),
                        links: graph.links,
                        categories: graph.categories,
                        roam: true,
                        label: {
                            normal: {
                                position: 'top',
                                formatter: '{b}'
                            }
                        },
                        lineStyle: {
                            normal: {
                                color: 'source',
                                curveness: 0.0
                            }
                        },
                        edgeSymbol: ['arrow', 'arrow'],
                        force: {
                            repulsion: 100,
                            edgeLength: 100
                        }
                    }
                ]
            };
            myChart.setOption(option);
        }
    });
}
