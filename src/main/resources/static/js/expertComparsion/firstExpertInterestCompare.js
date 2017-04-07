/**
 * Created by lsy on 2017/2/23.
 */
var id1 = $("#author-1-id").val()
function loadInterestFirstInstitution(id) {
    var $loadWindow = document.getElementById('first-institution-interest');
    var myChart = echarts.init($loadWindow);
    $.ajax({
    url : "/ComparisonOfExpert/expertInterests",
    type : "get",
    dataType : "json",
    data : {
    "id" : id
    },
    success : function (finalData) {
        console.info(finalData.length)
        // console.info(finalData.size())
        relData = []
        for(var i=0; i<finalData.length; i++){
            var row ={name: finalData[i].keyword.name, value:finalData[i].times}
            relData.push(row)
        }
    var option = {
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
            data:relData
        }]
    };
    myChart.setOption(option);
    myChart.on("click", function(params) {
        window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
    });
      }
    })
}
loadInterestFirstInstitution(id1)