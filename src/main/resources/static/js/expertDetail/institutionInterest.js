/**
 * Created by lsy on 2017/2/23.
 */
function loadInterestInstitution() {
    var $loadWindow = document.getElementById('institution-research-point');
    var myChart = echarts.init($loadWindow);
    $.ajax({
        url : "/table/institutionPoint",
        type : "get",
        dataType : "json",
        data : {
            "limit": 30,
            "institutionId":1
        },
        success : function (finalData) {

            var data = new Array();
            for(var i = 0;i < finalData.length;i++){
                var a = {"name":finalData[i].keyword.name,
                          "value":finalData[i].times,
                          "id":finalData[i].keyword.id
                          };
                data.push(a);
            }


            var option = {
                tooltip: {},
                series: [ {
                    type: 'wordCloud',
                    gridSize: 2,
                    sizeRange: [12, 50],
                    rotationRange: [0, 0],
                    shape: 'circle',
                    width: $loadWindow.clientWidth,
                    height: $loadWindow.clientHeight,
                    textStyle: {
                        normal: {
                            color: function () {
                                return 'rgb(' + [
                                        Math.round(Math.random() * 160),
                                        Math.round(Math.random() * 160),
                                        Math.round(Math.random() * 160)
                                    ].join(',') + ')';
                            }
                        },
                        emphasis: {
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    data: data
                    /*[ {
                        "name" : "FPGA",
                        "value" : 554
                    }, {
                        "name" : "应用",
                        "value" : 475
                    }, {
                        "name" : "可靠性",
                        "value" : 406
                    }, {
                        "name" : "仿真",
                        "value" : 385
                    }, {
                        "name" : "合成",
                        "value" : 316
                    }, {
                        "name" : "设计",
                        "value" : 304
                    }, {
                        "name" : "数值模拟",
                        "value" : 286
                    }, {
                        "name" : "管理",
                        "value" : 269
                    }, {
                        "name" : "物理化学",
                        "value" : 254
                    }, {
                        "name" : "飞行试验",
                        "value" : 249
                    }, {
                        "name" : "结构设计",
                        "value" : 223
                    }, {
                        "name" : "雷达",
                        "value" : 215
                    }, {
                        "name" : "问题",
                        "value" : 209
                    }, {
                        "name" : "对策",
                        "value" : 205
                    }, {
                        "name" : "有限元",
                        "value" : 199
                    }, {
                        "name" : "核电厂",
                        "value" : 198
                    }, {
                        "name" : "DSP",
                        "value" : 190
                    }, {
                        "name" : "地质特征",
                        "value" : 184
                    }, {
                        "name" : "核电站",
                        "value" : 180
                    }, {
                        "name" : "发展",
                        "value" : 176
                    }, {
                        "name" : "分析",
                        "value" : 173
                    }, {
                        "name" : "企业",
                        "value" : 167
                    }, {
                        "name" : "有机化学",
                        "value" : 162
                    }, {
                        "name" : "控制",
                        "value" : 154
                    }, {
                        "name" : "单片机",
                        "value" : 153
                    }, {
                        "name" : "标准",
                        "value" : 152
                    }, {
                        "name" : "项目管理",
                        "value" : 152
                    }, {
                        "name" : "发展趋势",
                        "value" : 152
                    }, {
                        "name" : "有限元分析",
                        "value" : 150
                    }, {
                        "name" : "优化",
                        "value" : 147
                    }, {
                        "name" : "电磁兼容",
                        "value" : 145
                    }, {
                        "name" : "遗传算法",
                        "value" : 141
                    }, {
                        "name" : "测量",
                        "value" : 140
                    }, {
                        "name" : "@@",
                        "value" : 139
                    }, {
                        "name" : "固体推进剂",
                        "value" : 139
                    }, {
                        "name" : "传感器",
                        "value" : 139
                    }, {
                        "name" : "数据采集",
                        "value" : 138
                    }, {
                        "name" : "性能",
                        "value" : 138
                    }, {
                        "name" : "措施",
                        "value" : 137
                    }, {
                        "name" : "无人机",
                        "value" : 136
                    }, {
                        "name" : "力学性能",
                        "value" : 133
                    }, {
                        "name" : "测试",
                        "value" : 132
                    }, {
                        "name" : "宽带",
                        "value" : 131
                    }, {
                        "name" : "优化设计",
                        "value" : 131
                    }, {
                        "name" : "航天器",
                        "value" : 131
                    }, {
                        "name" : "稳定性",
                        "value" : 130
                    }, {
                        "name" : "PLC",
                        "value" : 128
                    }, {
                        "name" : "质量管理",
                        "value" : 127
                    }, {
                        "name" : "GPS",
                        "value" : 127
                    }, {
                        "name" : "不确定度",
                        "value" : 127
                    }, {
                        "name" : "信息化",
                        "value" : 126
                    }, {
                        "name" : "热设计",
                        "value" : 123
                    }, {
                        "name" : "质量控制",
                        "value" : 120
                    }, {
                        "name" : "安全",
                        "value" : 119
                    }, {
                        "name" : "爆炸力学",
                        "value" : 119
                    }, {
                        "name" : "现状",
                        "value" : 116
                    }, {
                        "name" : "相位噪声",
                        "value" : 114
                    }, {
                        "name" : "标准化",
                        "value" : 114
                    }, {
                        "name" : "核电",
                        "value" : 114
                    }, {
                        "name" : "铀",
                        "value" : 111
                    }, {
                        "name" : "卫星通信",
                        "value" : 110
                    }, {
                        "name" : "numerical simulation",
                        "value" : 103
                    }, {
                        "name" : "控制系统",
                        "value" : 101
                    }, {
                        "name" : "校准",
                        "value" : 101
                    }, {
                        "name" : "抗干扰",
                        "value" : 101
                    }, {
                        "name" : "创新",
                        "value" : 100
                    }, {
                        "name" : "数据库",
                        "value" : 100
                    }, {
                        "name" : "复合材料",
                        "value" : 100
                    }, {
                        "name" : "研究",
                        "value" : 99
                    }, {
                        "name" : "天线",
                        "value" : 98
                    }, {
                        "name" : "科研院所",
                        "value" : 98
                    }, {
                        "name" : "现场可编程门阵列",
                        "value" : 98
                    }, {
                        "name" : "建议",
                        "value" : 95
                    }, {
                        "name" : "热分解",
                        "value" : 94
                    }, {
                        "name" : "信号处理",
                        "value" : 94
                    }, {
                        "name" : "工艺",
                        "value" : 94
                    }, {
                        "name" : "柴油机",
                        "value" : 93
                    }, {
                        "name" : "方法",
                        "value" : 93
                    }, {
                        "name" : "振动",
                        "value" : 92
                    }, {
                        "name" : "结构",
                        "value" : 92
                    }, {
                        "name" : "燃烧性能",
                        "value" : 92
                    }, {
                        "name" : "安全性",
                        "value" : 92
                    }, {
                        "name" : "故障诊断",
                        "value" : 91
                    }, {
                        "name" : "试验",
                        "value" : 91
                    }, {
                        "name" : "锁相环",
                        "value" : 91
                    }, {
                        "name" : "数据处理",
                        "value" : 90
                    }, {
                        "name" : "技术",
                        "value" : 90
                    }, {
                        "name" : "质量",
                        "value" : 90
                    }, {
                        "name" : "砂岩型铀矿",
                        "value" : 90
                    }, {
                        "name" : "找矿方向",
                        "value" : 90
                    }, {
                        "name" : "滤波器",
                        "value" : 89
                    }, {
                        "name" : "岩土工程",
                        "value" : 89
                    }, {
                        "name" : "LabVIEW",
                        "value" : 88
                    }, {
                        "name" : "铀矿",
                        "value" : 86
                    }, {
                        "name" : "航空发动机",
                        "value" : 84
                    }, {
                        "name" : "physical chemistry",
                        "value" : 84
                    }, {
                        "name" : "嵌入式系统",
                        "value" : 83
                    }, {
                        "name" : "检测",
                        "value" : 83
                    }, {
                        "name" : "synthesis",
                        "value" : 83
                    }, {
                        "name" : "温度",
                        "value" : 82
                    }, {
                        "name" : "CAN总线",
                        "value" : 82
                    }, {
                        "name" : "卫星",
                        "value" : 81
                    }, {
                        "name" : "无线传感器网络",
                        "value" : 81
                    }, {
                        "name" : "flight test",
                        "value" : 81
                    }, {
                        "name" : "有限元法",
                        "value" : 81
                    }, {
                        "name" : "锂离子电池",
                        "value" : 81
                    }, {
                        "name" : "数字化",
                        "value" : 78
                    }, {
                        "name" : "DDS",
                        "value" : 77
                    }, {
                        "name" : "船舶",
                        "value" : 77
                    }, {
                        "name" : "建模",
                        "value" : 77
                    }, {
                        "name" : "VxWorks",
                        "value" : 76
                    }, {
                        "name" : "水文地质",
                        "value" : 76
                    }, {
                        "name" : "事业单位",
                        "value" : 75
                    }, {
                        "name" : "关键技术",
                        "value" : 74
                    }, {
                        "name" : "毫米波",
                        "value" : 74
                    }, {
                        "name" : "合成孔径雷达",
                        "value" : 74
                    }, {
                        "name" : "信息安全",
                        "value" : 73
                    }, {
                        "name" : "内部控制",
                        "value" : 72
                    }, {
                        "name" : "定位",
                        "value" : 71
                    }, {
                        "name" : "网络安全",
                        "value" : 71
                    }, {
                        "name" : "网络",
                        "value" : 71
                    }, {
                        "name" : "影响因素",
                        "value" : 71
                    }, {
                        "name" : "找矿标志",
                        "value" : 71
                    }, {
                        "name" : "故障",
                        "value" : 71
                    }, {
                        "name" : "软件测试",
                        "value" : 71
                    }, {
                        "name" : "误差分析",
                        "value" : 71
                    }, {
                        "name" : "simulation",
                        "value" : 70
                    }, {
                        "name" : "腐蚀",
                        "value" : 70
                    }, {
                        "name" : "成矿条件",
                        "value" : 70
                    }, {
                        "name" : "目标跟踪",
                        "value" : 70
                    }, {
                        "name" : "虚拟仪器",
                        "value" : 70
                    }, {
                        "name" : "模块化",
                        "value" : 69
                    }, {
                        "name" : "模态分析",
                        "value" : 69
                    }, {
                        "name" : "电子设备",
                        "value" : 69
                    }, {
                        "name" : "国有企业",
                        "value" : 69
                    }, {
                        "name" : "理论",
                        "value" : 69
                    }, {
                        "name" : "云计算",
                        "value" : 69
                    }, {
                        "name" : "蒸汽发生器",
                        "value" : 68
                    }, {
                        "name" : "小型化",
                        "value" : 68
                    }, {
                        "name" : "铝合金",
                        "value" : 68
                    }, {
                        "name" : "干扰",
                        "value" : 68
                    }, {
                        "name" : "神经网络",
                        "value" : 68
                    }, {
                        "name" : "数字信号处理器",
                        "value" : 68
                    }, {
                        "name" : "灵敏度",
                        "value" : 67
                    }, {
                        "name" : "应用化学",
                        "value" : 67
                    }, {
                        "name" : "MATLAB",
                        "value" : 67
                    }, {
                        "name" : "FFT",
                        "value" : 66
                    }, {
                        "name" : "动力学",
                        "value" : 66
                    }, {
                        "name" : "找矿前景",
                        "value" : 66
                    }, {
                        "name" : "物联网",
                        "value" : 66
                    } ]*/
                }]
            };
            myChart.setOption(option);
        }
    })
}

loadInterestInstitution();