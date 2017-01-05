/**
 * Created by hexu on 2016/12/7.
 */

function loadRelationshipPersonal(depath) {
    var myChart = echarts.init(document.getElementById('author-relationship'));
    myChart.showLoading();
    var authorName = $("#authorsName").text();
    var authorInstitution = $("#authorsInstitution").val();
    $.ajax({
        url : "/detailOfExpert/cooperateOfAuthor",
        type: "get",
        dataType : "json",
        data:{
            "name" : "詹毅",
            "institution" : "电子科技集团36所",
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
                   // circular: {
                   //     rotateLabel: true
                   // },
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
                            curveness: 0.4
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

loadRelationshipPersonal(3);