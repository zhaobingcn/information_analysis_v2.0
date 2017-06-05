/**
 * Created by zhzy on 2017/6/2.
 */

    function postTopExperts() {
    document.getElementById('display-expert-graph').style.display='block';

    var keyword1 = $("#keyword1").val();
    if(keyword1 == null){
        keyword1 = "";
    }
    var keyword2 = $("#keyword2").val();
    if(keyword2 == null){
        keyword2 = "";
    }
    var keyword3 = $("#keyword3").val();
    if(keyword3 == null){
        keyword3 = "";
    }
    var keyword4 = $("#keyword4").val();
    if(keyword4 == null){
        keyword4 = "";
    }
    var iname1 = $("#iname1").val();
    if(iname1 == null){
        iname1 = "";
    }
    var iname2 = $("#iname2").val();
    if(iname2 == null){
        iname2 = "";
    }
    var table = $("#show-experts-detail").empty();
    var select = $("#method-select").val();
    var url;
    if(select == 1){
        url = "/recommendOfExpert/getTopExpertsByPageRank";
    }else{
        url = "/recommendOfExpert/getTopExpertsByAchievement";
    }
        $.ajax(
            {url : url,
                data : {"keyword1": keyword1,
                        "keyword2": keyword2,
                        "keyword3": keyword3,
                        "keyword4": keyword4,
                        "iname1": iname1,
                        "iname2": iname2
                        },
                dataType: "json",
                async : false,
                type: "post",
                success: function (data) {
                    // alert(data);
                    if(data.length != 0) {
                        for (var i = 0; i < 9; i++) {
                            $("<tr class=\"info\">" +
                                "<td>" + (i+1) + "</td>" +
                                "<td>" +data[i].author.name+ "</td>" +
                                "<td>" +data[i].author.institution+ "</td>" +
                                "<td>" +
                                "<a href=\"" +  "/detailOfExpert/?name=" +data[i].author.name+ "&&institution=" +data[i].author.institution+ "\">" +
                                "<div class=\"\">" +
                                "<span class=\"pull-left\"><i class=\"fa fa-arrow-circle-right\"></i></span>" +
                                "<div class=\"clearfix\"></div>" +
                                "</div>" +
                                "</a>" +
                                "</td>" +
                                "</tr>").appendTo(table);
                        }
                    }

                    var myChart = echarts.init(document.getElementById("professor-list"));

                    var xaxis = [];
                    var yaxis = [];
                    for(var i=0; i< 9; i++){
                        yaxis[8-i] = data[i].author.name;
                        xaxis[8-i] = data[i].times;
                    }
                    var text;
                    if(select == 1){
                        text = "按社交影响力推荐";
                    }else{
                        text = "按专家研究成果推荐";
                    }
                    option = {
                        title :{
                            text : text,
                            x : 'center'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        yAxis: [{
                            type: 'category',
                            data: yaxis,
                            axisTick: {
                                alignWithLabel: true
                            }
                        }],
                        xAxis: [{
                            type: 'value'
                        }],
                        backgroundColor: '#ffffff',
                        series: [{
                            name: 'Top 10',
                            type: 'bar',
                            data: xaxis,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'insideRight',
                                    textStyle: {
                                        color: 'white' //color of value
                                    }
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                        offset: 0,
                                        color: 'lightBlue' // 0% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: '#3398DB' // 100% 处的颜色
                                    }], false)
                                }
                            }
                        }]
                    };
                    myChart.setOption(option);
                }
            }
        )
    }
