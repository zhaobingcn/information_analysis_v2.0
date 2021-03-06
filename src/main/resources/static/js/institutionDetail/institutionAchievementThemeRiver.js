/**
 * Created by Administrator on 2017/3/2.
 */
function loadThemeRiver() {
    var myChart = echarts.init(document.getElementById("themeriver"));
    /*var seriesData = [
        ['2006', 22, '论文发表量'],
        ['2007', 0, '论文发表量'],
        ['2008', 0, '论文发表量'],
        ['2009', 45, '论文发表量'],
        ['2010', 60, '论文发表量'],
        ['2011', 31, '论文发表量'],
        ['2012', 30, '论文发表量'],
        ['2013', 41, '论文发表量'],
        ['2014', 25, '论文发表量'],
        ['2015', 28, '论文发表量'],
        ['2016', 5, '论文发表量'],
        ['2017', 51, '论文发表量'],
        ['2006', 22, '专利申请量'],
        ['2007', 2, '专利申请量'],
        ['2008', 10, '专利申请量'],
        ['2009', 45, '专利申请量'],
        ['2010', 60, '专利申请量'],
        ['2011', 31, '专利申请量'],
        ['2012', 30, '专利申请量'],
        ['2013', 41, '专利申请量'],
        ['2014', 25, '专利申请量'],
        ['2015', 28, '专利申请量'],
        ['2016', 5, '专利申请量'],
        ['2017', 51, '专利申请量'],
        ['2006', 22, '项目承接量'],
        ['2007', 2, '项目承接量'],
        ['2008', 10, '项目承接量'],
        ['2009', 45, '项目承接量'],
        ['2010', 60, '项目承接量'],
        ['2011', 31, '项目承接量'],
        ['2012', 30, '项目承接量'],
        ['2013', 41, '项目承接量'],
        ['2015', 28, '项目承接量'],
        ['2016', 5, '项目承接量'],
        ['2017', 51, '项目承接量']
    ];
    var option = {
        title: {
            text: '科研机构成果汇总',
            subtext: '',
            x: 'center',
            y: 'top'
        },
        tooltip: {
            trigger: 'axis',
            textStyle: {
                align: 'left'
            }
        },
        legend: {
            top: 'bottom',
            data: ['论文发表量', '专利申请量', '项目承接量']
        },
        singleAxis: {
            top: '30%',
            bottom: '20%',
            type: 'value',
            scale: true
        },
        series: [{
            type: 'themeRiver',
            data: seriesData,
            //splitNumber: 30,
            label: {
                normal: {
                    show: false
                }
            }
        }]
    };
    myChart.setOption(option);*/

    $.ajax({
        url:"/analysis/InstitutionInformation/institutionResult",
        type:"get",
        dataType:"json",
        data:{"limit": 30,"institutionId":1},
        success: function (seriesData){
            var option = {
                title: {
                    text: '科研机构成果汇总',
                    subtext: '',
                    x: 'center',
                    y: 'top'
                },
                tooltip: {
                    trigger: 'axis',
                    textStyle: {
                        align: 'left'
                    }
                },
                legend: {
                    top: 'bottom',
                    data: ['论文发表量', '专利申请量', '项目承接量']
                },
                singleAxis: {
                    top: '30%',
                    bottom: '20%',
                    type: 'value',
                    scale: true
                },
                series: [{
                    type: 'themeRiver',
                    data: seriesData,
                    //splitNumber: 30,
                    label: {
                        normal: {
                            show: false
                        }
                    }
                }]
            };



            myChart.setOption(option);
        }
    });
}

loadThemeRiver();