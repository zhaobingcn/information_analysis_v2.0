function loadWordsCloudData() {
    var myChart = echarts.init(document.getElementById("echarts-word-cloud"));
    myChart.showLoading();
    $.ajax({
        url:"/pages/wordsCloud",
        dataType:"json",
        type:"get",
        data:{"limit": 150},
        success: function (words) {
            myChart.hideLoading();
            $wordList = $("#list-influential-words").empty();
            for(var i=0; i<10; i++){
                $wordList.append(
                "<a href=\"#\" class=\"list-group-item\">" +
                    "<i class=\"fa fa-star fa-fw\"></i>" + words.data[i].name +
                    "<span class=\"pull-right text-muted small\">" +
                    "<em>" + words.data[i].value + "次" +
                    "</em>" +
                    "</span>" +
                "</a>"
                );
            }
            var option = {
                tooltip: {},
                series: [ {
                    type: 'wordCloud',
                    gridSize: 2,
                    sizeRange: [12, 50],
                    rotationRange: [0, 0],
                    shape: 'circle',
                    width: 600,
                    height: 400,
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
                    data: words.data
                } ]
            };
            myChart.setOption(option);
        }
    });
};
loadWordsCloudData();