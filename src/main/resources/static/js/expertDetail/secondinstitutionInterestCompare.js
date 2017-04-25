/**
 * Created by lsy on 2017/2/24.
 */
function loadInterestSecondInstitution() {
    var $loadWindow = document.getElementById('second-institution-interest');
    var myChart = echarts.init($loadWindow);

    $.ajax({
        url : "/ComparisonofInstitutions/firstInstitutionInterest",
        type : "get",
        dataType : "json",
        data : {
            "limit": 30,
            "institutionId":117
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
                }]
            };
            myChart.setOption(option);
        }
    })

    /*var authorName = $("#authorsName").text();
     var authorInstitution = $("#authorsInstitution").val();
     $.ajax({
     url : "/detailOfExpert/InterestOfExpert",
     type : "get",
     dataType : "json",
     data : {
     "name" : authorName,
     "institution" : authorInstitution
     },
     success : function (finalData) {*/
    /*var option = {
        tooltip: {},
        toolbox: {
            left: '5%',
            feature:{
                show: true,
                dataView: {readOnly: true}
            }
        },
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
            data:[ {
                "name" : "格型滤波器",
                "value" : 1
            }, {
                "name" : "小波变换",
                "value" : 1
            }, {
                "name" : "非负矩阵分解",
                "value" : 2
            }, {
                "name" : "非线性系统辨识",
                "value" : 1
            }, {
                "name" : "characteristic parameters",
                "value" : 1
            }, {
                "name" : "α稳定分布",
                "value" : 3
            }, {
                "name" : "低信噪比",
                "value" : 1
            }, {
                "name" : "分阶",
                "value" : 1
            }, {
                "name" : "循环相关谱",
                "value" : 1
            }, {
                "name" : "相干干扰",
                "value" : 1
            }, {
                "name" : "最小二乘格型滤波算法",
                "value" : 1
            }, {
                "name" : "Variable Step-size Normalized Least Mean P norm algorithm (VS-NLMP)",
                "value" : 1
            }, {
                "name" : "最小相关约束",
                "value" : 2
            }, {
                "name" : "adaptive beamforming",
                "value" : 1
            }, {
                "name" : "特征参数",
                "value" : 1
            }, {
                "name" : "减少参数",
                "value" : 1
            }, {
                "name" : "高阶累积量",
                "value" : 2
            }, {
                "name" : "盲检测",
                "value" : 1
            }, {
                "name" : "虚拟信道矩阵",
                "value" : 1
            }, {
                "name" : "格型正交化",
                "value" : 1
            }, {
                "name" : "全解耦",
                "value" : 1
            }, {
                "name" : "array aperture",
                "value" : 1
            }, {
                "name" : "稀疏性",
                "value" : 2
            }, {
                "name" : "正交处理",
                "value" : 1
            }, {
                "name" : "盲源分离(BSS)",
                "value" : 1
            }, {
                "name" : "blind detection",
                "value" : 1
            }, {
                "name" : "Volterra filter",
                "value" : 1
            }, {
                "name" : "OSTBC",
                "value" : 1
            }, {
                "name" : "M进制正交幅度调制(MQAM)",
                "value" : 1
            }, {
                "name" : "Volterra滤波器",
                "value" : 8
            }, {
                "name" : "认知无线电",
                "value" : 1
            }, {
                "name" : "robustness",
                "value" : 1
            }, {
                "name" : "欠定盲分离",
                "value" : 2
            }, {
                "name" : "VS-NLMP算法",
                "value" : 1
            }, {
                "name" : "解相关",
                "value" : 1
            }, {
                "name" : "调制宽带转换系统",
                "value" : 1
            }, {
                "name" : "orthogonal space-time block coding (OSTBC)",
                "value" : 1
            }, {
                "name" : "变步长",
                "value" : 1
            }, {
                "name" : "信道均衡",
                "value" : 1
            }, {
                "name" : "自适应波束形成",
                "value" : 1
            }, {
                "name" : "四阶矩切片",
                "value" : 1
            }, {
                "name" : "阵列孔径",
                "value" : 1
            }, {
                "name" : "blind recognition",
                "value" : 1
            }, {
                "name" : "sigmoid函数",
                "value" : 2
            }, {
                "name" : "幅度矩",
                "value" : 1
            }, {
                "name" : "分组",
                "value" : 1
            }, {
                "name" : "二次4阶矩切片",
                "value" : 1
            }, {
                "name" : "多子带信号采样",
                "value" : 1
            }, {
                "name" : "高斯噪声",
                "value" : 1
            }, {
                "name" : "正交空时分组码",
                "value" : 3
            }, {
                "name" : "系统辨识",
                "value" : 1
            }, {
                "name" : "MIMO systems",
                "value" : 1
            }, {
                "name" : "行列式准则",
                "value" : 3
            }, {
                "name" : "能量检测",
                "value" : 1
            }, {
                "name" : "α -stable distribution noise",
                "value" : 1
            }, {
                "name" : "quadratic fourth-order moment chip",
                "value" : 1
            }, {
                "name" : "稳健性",
                "value" : 1
            }, {
                "name" : "调制识别",
                "value" : 1
            }, {
                "name" : "nonlinear system identification",
                "value" : 1
            }, {
                "name" : "信号检测与参数估计",
                "value" : 1
            }, {
                "name" : "VLMS算法",
                "value" : 1
            }, {
                "name" : "Lyapunov稳定性理论",
                "value" : 1
            }, {
                "name" : "multiple-input-multiple-output (MIMO) systems",
                "value" : 1
            }, {
                "name" : "coherent interference",
                "value" : 1
            }, {
                "name" : "盲识别",
                "value" : 2
            }, {
                "name" : "噪声对消",
                "value" : 2
            }, {
                "name" : "direct sequence-code division multiple access (DS-CDMA)",
                "value" : 1
            }, {
                "name" : "正交化",
                "value" : 1
            }, {
                "name" : "非周期性直扩信号",
                "value" : 1
            }, {
                "name" : "二阶Volterra滤波器",
                "value" : 1
            }, {
                "name" : "lower SNR",
                "value" : 1
            }, {
                "name" : "higher-order cumulants",
                "value" : 2
            }, {
                "name" : "非负矩阵分解(NMF)",
                "value" : 1
            }, {
                "name" : "宽带频谱感知",
                "value" : 1
            }, {
                "name" : "MIMO系统",
                "value" : 3
            }, {
                "name" : "α稳定分布噪声",
                "value" : 1
            }, {
                "name" : "调制分类",
                "value" : 1
            }, {
                "name" : "直接序列码分多址(DS-CDMA)信号",
                "value" : 1
            } ]
        }]
    };
    myChart.setOption(option);*/
    //   }
    // })
}

loadInterestSecondInstitution();