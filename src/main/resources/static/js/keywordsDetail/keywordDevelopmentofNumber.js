/**
 * Created by lsy on 2017/3/14.
 */

function loadKeywordDevelopmentofNumber(id) {
    var myChart = echarts.init(document.getElementById("keyword-development-of-num"));
    myChart.showLoading();

    $.ajax({
        url : "/keywordDetail/keywordTrend",
        type: "get",
        dataType : "json",
        data:{
            "id": id
        },
        success : function (data) {
        myChart.hideLoading();

        //圆圈边缘阴影
        var itemStyle = {
            normal: {
                opacity: 0.8,
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        };
        //总人口转换函数
        var sizeFunction = function (x) {
            // var y = Math.sqrt(x) + 1;
            // return y;
            return x;
        };
        // 提示框内容格式
        var schema = [
            {name: 'Expert', index: 0, text: '研究该方向专家人数', unit: '人'},
            {name: 'Institution', index: 1, text: '参与研究机构数', unit: '所'},
            {name: 'Papernumber', index: 2, text: '论文数', unit: ''},
            {name: 'Keyword', index: 3, text: '关键词', unit: ''}
        ];

        var option = {
            baseOption: {
                //右侧时间线
                timeline: {
                    axisType: 'category',
                    orient: 'vertical',
                    autoPlay: true,
                    inverse: true,
                    playInterval: 1000,  //时间动画变化速率
                    left: null,
                    right: 0,
                    top: 20,
                    bottom: 20,
                    width: 55,
                    height: null,
                    label: {
                        normal: {
                            textStyle: {
                                color: '#444'
                            }
                        },
                        emphasis: {
                            textStyle: {
                                color: '#000'
                            }
                        }
                    },
                    symbol: 'none',
                    lineStyle: {
                        color: '#444'
                    },
                    checkpointStyle: {
                        color: '#bbb',
                        borderColor: '#777',
                        borderWidth: 2
                    },
                    controlStyle: {
                        showNextBtn: false,
                        showPrevBtn: false,
                        normal: {
                            color: '#666',
                            borderColor: '#666'
                        },
                        emphasis: {
                            color: '#aaa',
                            borderColor: '#aaa'
                        }
                    },
                    data: []
                },

                //backgroundColor: '#404a59',

                //右下侧时间变化显示
                title: [{
                    'text': data.timeline[0],
                    textAlign: 'center',
                    left: '65%',
                    top: '60%',
                    textStyle: {
                        fontSize: 50,
                        color: 'rgba(0, 0, 0, 0.7)'
                    }
                }],
                //提示框
                tooltip: {
                    padding: 5,
                    backgroundColor: '#222',
                    borderColor: '#777',
                    borderWidth: 1,
                    formatter: function (obj) {
                        var value = obj.value;
                        return schema[3].text + '：' + value[3] + '<br>'
                            + schema[1].text + '：' + value[1] + schema[1].unit + '<br>'
                            + schema[0].text + '：' + value[0] + schema[0].unit + '<br>'
                            + schema[2].text + '：' + value[2] + '<br>';
                    }
                },
                //坐标以及图形部分轴部署位置
                grid: {
                    left: '12%',
                    right: '110'
                },
                xAxis: {
                    type: 'log',
                    name: '研究该方向专家人数',
                    max: data.maxAuthorsCount,
                    // min: 1,
                    nameGap: 25,
                    nameLocation: 'middle',
                    nameTextStyle: {
                        fontSize: 16
                    },
                    splitLine: {
                        show: false
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#444'
                        }
                    },
                    axisLabel: {
                        formatter: '{value} 人'
                    }
                },
                yAxis: {
                    type: 'value',
                    name: '参与研究机构数',
                    max: data.maxInstitutionsCount,
                    nameTextStyle: {
                        color: '#444',
                        fontSize: 16
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#444'
                        }
                    },
                    splitLine: {
                        show: false
                    },
                    axisLabel: {
                        formatter: '{value} 所'
                    }
                },
                //视觉映射效果
                visualMap: [
                    {
                        show: false,
                        dimension: 3,  //数据映射维度，即第3个列，总人口
                        categories: data.keywords,
                        calculable: true,
                        precision: 0.1,
                        textGap: 30,
                        textStyle: {
                            color: '#ccc'
                        },
                        inRange: {
                            color: (function () {
                                var colors = ['#8EA38D', '#E88B67', '#BA9782', '#57C7C8', '#6AE8C4', '#7b7c68', '#E579B2', '#F09A44', '#7774A8', '#BD7775'];
                                return colors.concat(colors);
                            })()
                        }
                    }
                ],
                series: [
                    {
                        type: 'scatter',
                        itemStyle: itemStyle,
                        data: data.series[0],
                        symbolSize: function (val) {
                            return sizeFunction(val[2]);
                        }
                    }
                ],
                //数据更新动画延迟
                animationDurationUpdate: 1000,
                //数据更新动画效果
                animationEasingUpdate: 'quinticInOut'
            },
            options: []
        };

        for (var n = 0; n < data.timeline.length; n++) {
            option.baseOption.timeline.data.push(data.timeline[n]);
            option.options.push({
                title: {
                    show: true,
                    'text': data.timeline[n] + ''
                },
                series: {
                    name: data.timeline[n],
                    type: 'scatter',
                    itemStyle: itemStyle,
                    data: data.series[n],
                    symbolSize: function (val) {
                        return sizeFunction(val[2]);
                    }
                }
            });
        }

        myChart.setOption(option);

    }});
}