/**
 * Created by hexu on 2016/12/7.
 */

function loadAchievementData () {
    var myChart = echarts.init(document.getElementById('publish-quote-times'));
    var authorName = $("#authorsName").text();
    var authorInstitution = $("#authorsInstitution").val();
    $.ajax({
        url : "/detailOfExpert/authorsAchievement",
        dataType : "json",
        type : "get",
        data : {"name" : authorName,
                "institution" : authorInstitution
        },
        success : function (achievement) {
            var option = {
                title: {
                    text: '研究成果汇总',
                    subtext: '按年份排列'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['发文量','被引用量']
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none'
                        },
                        dataView: {readOnly: false},
                        magicType: {type: ['line', 'bar']},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                xAxis:  {
                    type: 'category',
                    boundaryGap: false,
                    data: ['2006','2007','2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name:'发文量',
                        type:'line',
                        data:achievement.publish,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        }
                    },
                    {
                        name:'被引用量',
                        type:'line',
                        data:achievement.quote,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        }

                    }
                ]
            };
            myChart.setOption(option);
        }
    })
};

loadAchievementData();
