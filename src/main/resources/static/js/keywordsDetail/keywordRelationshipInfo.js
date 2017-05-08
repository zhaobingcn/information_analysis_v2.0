/**
 * Created by lsy on 2017/4/11.
 */
function loadKeywordInfo(id , depath) {
    var myChart = echarts.init(document.getElementById('keyword-relationship-info'));
    myChart.showLoading();

    $.ajax({
        url : "/keywordDetail/keywordsRelationship",
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
            node.symbolSize = Math.pow(node.value, 1/3)*4;
            node.label = {
                normal: {
                    show: node.symbolSize > 5
                }
            };
            node.category = node.category;
        });
        var option = {
            title: {

            },
            tooltip: {},
            legend: [{
                // selectedMode: 'single',
                show: false,
                data: graph.categories.map(function (a) {
                    return a.name;
                })
            }],
            // animation: true,
            animationDuration: 3000,
            animationEasingUpdate: 'quinticInOut',
            series : [
                {
                    name: '关键词关联网络',
                    type: 'graph',
                    layout: 'force',
                    data: graph.nodes.map(function (node, idx) {
                        node.id = idx;
                        return node;
                    }),
                    links: graph.links,
                    categories: graph.categories,
                    roam: true,
                    label: {
                        normal: {
                            position: 'right'
                        }
                    },
                    force: {
                        repulsion: 100
                    },
                    lineStyle: {
                        normal: {
                            color: 'source',
                            curveness: 0.0,
                        }
                    },
                    // edgeSymbol: ['arrow', 'arrow']
                }
            ]
        };

        myChart.setOption(option);
    }})
}