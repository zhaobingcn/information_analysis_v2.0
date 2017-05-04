/**
 * Created by lsy on 2017/4/11.
 */
function loadKeywordInfo(depath) {
    var myChart = echarts.init(document.getElementById('keyword-relationship-info'));
    myChart.showLoading();
    $.get('../data/les-miserables.gexf', function (xml) {
        myChart.hideLoading();

        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [];
        for (var i = 0; i < 9; i++) {
            categories[i] = {
                name: '关键词' + (i+1)
            };
        }
        //categories[0] = {};
        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.symbolSize = 10;
            node.value = node.symbolSize;
            node.category = node.attributes.modularity_class;
            // Use random x, y
            node.x = node.y = null;
            node.draggable = true;
        });
        var option = {
            title: {
                
            },
            tooltip: {},
            legend: [{
                // selectedMode: 'single',
                show: false,
                data: categories.map(function (a) {
                    return a.name;
                })
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
                            position: 'right'
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
loadKeywordInfo();