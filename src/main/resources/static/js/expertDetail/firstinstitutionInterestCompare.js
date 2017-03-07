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
            data:[{
                name: 'Sam S Club',
                value: 100,
                textStyle: {
                    normal: {
                        color: 'black'
                    },
                    emphasis: {
                        color: 'red'
                    }
                }
            }, {
                name: 'Macys',
                value: 6181
            }, {
                name: 'Amy Schumer',
                value: 4386
            }, {
                name: 'Jurassic World',
                value: 4055
            }, {
                name: 'Charter Communications',
                value: 2467
            }, {
                name: 'Chick Fil A',
                value: 2244
            }, {
                name: 'Planet Fitness',
                value: 1898
            }, {
                name: 'Pitch Perfect',
                value: 1484
            }, {
                name: 'Express',
                value: 1112
            }, {
                name: 'Home',
                value: 965
            }, {
                name: 'Johnny Depp',
                value: 847
            }, {
                name: 'Lena Dunham',
                value: 582
            }, {
                name: 'Lewis Hamilton',
                value: 555
            }, {
                name: 'KXAN',
                value: 550
            }, {
                name: 'Mary Ellen Mark',
                value: 462
            }, {
                name: 'Farrah Abraham',
                value: 366
            }, {
                name: 'Rita Ora',
                value: 360
            }, {
                name: 'Serena Williams',
                value: 282
            }, {
                name: 'NCAA baseball tournament',
                value: 273
            }, {
                name: 'Point Break',
                value: 265
            }]
        }]
    };
    myChart.setOption(option);
    myChart.on("click", function(params) {
        window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
    });
    //   }
    // })
}

loadInterestFirstInstitution();