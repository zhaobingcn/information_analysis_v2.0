/**
 * Created by lsy on 2017/2/28.
 */
function loadRelationshipExpertCompare(depath) {
    var myChart = echarts.init(document.getElementById('expert-relationship-comparison'));
    //myChart.showLoading();
    $.get('../data/les-miserables.gexf', function (xml) {
        myChart.hideLoading();

        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [];
        for (var i = 0; i < 2; i++) {
            categories[i] = {
                name: '专家' + (i+1)
            };
        }
        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.symbolSize = 30;
            node.value = node.symbolSize;
            node.category = node.attributes.modularity_class;
            // Use random x, y
            node.x = node.y = null;
            node.draggable = true;
        });
        var option = {
            title: {
                text: '与其他专家的合作关系比较',
                //subtext: 'Default layout',
                //top: 'bottom',
                left: 'center'
            },
            tooltip: {},
            legend: [{
                // selectedMode: 'single',
                data: categories.map(function (a) {
                    return a.name;
                }),
                top: 'bottom'
            }],
            animation: false,
            series : [
                {
                    name: 'Les Miserables',
                    type: 'graph',
                    layout: 'force',
                    data: graph.nodes,
                    links: graph.links,
                    categories: categories,
                    roam: true,
                    label: {
                        normal: {
                            position: 'top',
                            formatter: '{b}'
                        }
                    },
                    force: {
                        repulsion: 100
                    }
                }
            ]
        };

        myChart.setOption(option);
    }, 'xml');
}
loadRelationshipExpertCompare();