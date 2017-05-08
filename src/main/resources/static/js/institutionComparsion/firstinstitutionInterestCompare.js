/**
 * Created by lsy on 2017/2/23.
 */
function loadInterestFirstInstitution() {
    var $loadWindow = document.getElementById('first-institution-interest');
    var myChart = echarts.init($loadWindow);
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

    $.ajax({
        url : "/ComparisonofInstitutions/secondInstitutionInterest",
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
                }]
            };
            myChart.setOption(option);
        }
    })


    /*var option = {
        tooltip: {},
        toolbox: {
            left: '5%',
            feature:{
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
                "name" : "直扩信号检测",
                "value" : 1
            }, {
                "name" : "wideband detection",
                "value" : 1
            }, {
                "name" : "非周期长码直扩",
                "value" : 1
            }, {
                "name" : "码片速率",
                "value" : 1
            }, {
                "name" : "autocorrelation",
                "value" : 1
            }, {
                "name" : "non-cooperative",
                "value" : 1
            }, {
                "name" : "宽带检测",
                "value" : 1
            }, {
                "name" : "细微特征",
                "value" : 1
            }, {
                "name" : "correlation estimators",
                "value" : 1
            }, {
                "name" : "delay-product",
                "value" : 1
            }, {
                "name" : "自相关",
                "value" : 1
            }, {
                "name" : "DSSS signal interception",
                "value" : 1
            }, {
                "name" : "特征提取",
                "value" : 1
            }, {
                "name" : "个体识别",
                "value" : 1
            }, {
                "name" : "波动相关法",
                "value" : 1
            }, {
                "name" : "非合作",
                "value" : 1
            }, {
                "name" : "non-periodic long-code Direct Sequence Spread Spectrum(DSSS)",
                "value" : 1
            }, {
                "name" : "chip-rate",
                "value" : 1
            }, {
                "name" : "通信侦察",
                "value" : 1
            }, {
                "name" : "延迟相乘",
                "value" : 1
            } ]
        }]
    };
    myChart.setOption(option);*/
    myChart.on("click", function(params) {
        window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
    });
    //   }
    // })
}

loadInterestFirstInstitution();