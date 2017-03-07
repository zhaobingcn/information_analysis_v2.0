/**
 * Created by lsy on 2017/2/21.
 */

function loadAchievementsInstitutionData() {
    var myChart = echarts.init(document.getElementById('institution-achievements'));
    /*var authorName = $("#authorsName").text();
    var authorInstitution = $("#authorsInstitution").val();
    $.ajax({
        url : "/detailOfExpert/authorsAchievement",
        dataType : "json",
        type : "get",
        data : {"name" : authorName,
            "institution" : authorInstitution
        },
        success : function (achievement) {*/
            var option = {
                title: {
                    text: '研究成果汇总',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    top: 'bottom',
                    data:['论文发表量','专利申请量','项目承接量']
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
                    type: 'value',
                    name: '按年份排列'
                },
                series: [
                    {
                        name:'论文发表量',
                        type:'line',
                        data:[123,234,345,456,345,467,345,89,344,678,456],
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        }
                    },
                    {
                        name:'专利申请量',
                        type:'line',
                        data:[12,23,34,45,35,45,33,56,76,45,56],
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        }
                    },
                    {
                        name:'项目承接量',
                        type:'line',
                        data:[12,24,35,45,35,67,45,89,44,68,46],
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
        //}
    //})
}

loadAchievementsInstitutionData();