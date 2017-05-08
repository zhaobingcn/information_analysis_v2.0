/**
 * Created by lsy on 2017/2/23.
 */
function loadInterestInstitution(id) {
    var $loadWindow = document.getElementById('institution-research-point');
    var myChart = echarts.init($loadWindow);
    $.ajax({
        url : "/InstitutionInformation/institutionInterest",
        type : "get",
        dataType : "json",
        data : {
            "limit": 30,
            "institutionId":id
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
}
