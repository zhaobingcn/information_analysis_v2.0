/**
 * Created by hexu on 2016/12/5.
 */
var myChart = echarts.init(document.getElementById('personrelation'));
myChart.showLoading();
$.get('webkit-dep.json', function (graph) {
    myChart.hideLoading();
    graph.nodes.forEach(function (node) {
        console.info("value"+ "  " + node.value);
        console.info("name" + " " + node.name);
        console.info("category" + " " + node.category);
        node.itemStyle = null;
        node.value = node.value;
        node.symbolSize = node.value*20;
        node.label = {
            normal: {
                show: node.symbolSize > 30
            }
        };
        node.category = node.category;
    });
    option = {
        title: {
            text: 'Les Miserables',
            subtext: 'Circular layout',
            top: 'bottom',
            left: 'right'
        },
        tooltip: {},
        legend: {
            data: ['HTMLElement', 'WebGL', 'SVG','CSS','Other']
        },
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series : [
            {
                name: 'Les Miserables',
                type: 'graph',
                layout: 'force',
//                    circular: {
//                        rotateLabel: true
//                    },
                data: graph.nodes,
                links: graph.links,
                categories: graph.categories,
                roam: true,
                label: {
                    normal: {
                        position: 'inside',
                        formatter: '{a}'
                    }
                },
                lineStyle: {
                    normal: {
                        color: 'source',
                        curveness: 0.3
                    }
                },
                edgeSymbol: ['none', 'arrow'],
                force: {
                    repulsion: 100
                }
            }
        ]
    };

    myChart.setOption(option);
}, 'json');