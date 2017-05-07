/**
 * Created by lsy on 2017/2/28.
 */
function loadPaperExpertCompare (id1, id2) {
    var myChart = echarts.init(document.getElementById('expert-paper-comparison'));
    $.ajax({
        url : "/ComparisonOfExpert/achievements",
        type: "get",
        dataType : "json",
        data:{
            "id1": id1,
            "id2" : id2
        },
        success : function (data) {
            var option = {
                title: {
                    text: '专家论文发表量与引用量比较',
                    left: 'center'
                },
                grid:[
                    {x:'7%',y:'17%',width: '38%'},
                    {x2:'7%',y:'17%',width: '38%'}
                ],
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    top: 'bottom',
                    left: 'center',
                    data:['专家1','专家2']
                },
                xAxis: [
                    {
                        gridIndex: 0,
                        name:'年',
                        data: ['2006','2007','2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016']
                    },
                    {
                        gridIndex: 1,
                        name:'年',
                        data: ['2006','2007','2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016']
                    }],
                yAxis: [
                    {
                        gridIndex: 0,
                        type: 'value',
                        name: '论文发表量'
                    },
                    {
                        gridIndex: 1,
                        type: 'value',
                        name: '论文引用量'
                    }],
                series: [
                    {
                        name:'专家1',
                        type:'line',
                        xAxisIndex: 0,
                        yAxisIndex: 0,
                        data: data.author1PublishInYear
                    },
                    {
                        name:'专家2',
                        type:'line',
                        xAxisIndex: 0,
                        yAxisIndex: 0,
                        data:data.author2PublishInYear
                    },
                    {
                        name:'专家1',
                        type:'line',
                        xAxisIndex: 1,
                        yAxisIndex: 1,
                        data: data.author1QuoteInYear
                    },
                    {
                        name:'专家2',
                        type:'line',
                        xAxisIndex: 1,
                        yAxisIndex: 1,
                        data: data.author2QuoteInYear
                    }
                ]
            };
            myChart.setOption(option);
        }
        });
}
