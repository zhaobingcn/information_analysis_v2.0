/**
 * Created by lsy on 2017/2/24.
 */
// var id2 = $("#author-2-id").val()
// var window2 = 'second-institution-interest'
function loadInterestSecondInstitution(id, window) {
    var $loadWindow = document.getElementById(window);
    var myChart = echarts.init($loadWindow);
     $.ajax({
     url : "/analysis/ComparisonOfExpert/expertInterests",
     type : "get",
     dataType : "json",
     data : {
     "id":id
     },
     success : function (finalData) {
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
            data:relData
        }]
    };
    myChart.setOption(option);
      }
    })
}