/**
 * Created by lsy on 2017/2/28.
 */
function loadCitedPaperExpertCompare () {
    var myChart = echarts.init(document.getElementById('expert-cited-paper-comparison'));
    var option = {
        title: {
            text: '专家论文引用量比较',
            x: 'center'
        },
        tooltip: {
            //formatter: 'Group {a}: ({c})'
            trigger: 'axis'
        },
        legend: {
            //gridIndex: 0,
            top: 'bottom',
            left: 'center',
            data:['专家1','专家2']
        },
        xAxis: {
            data: ['2006','2007','2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016']
        },
        yAxis: {
            type: 'value',
            name: '按年份排列'
        },
        series: [
            {
                name:'专家1',
                type:'line',
                data:[12,23,34,45,35,45,33,56,76,45,56]
                /*markPoint: {
                 data: [
                 {type: 'max', name: '最大值'},
                 {type: 'min', name: '最小值'}
                 ]
                 }*/
            },
            {
                name:'专家2',
                type:'line',
                data:[123,234,345,456,345,467,345,89,344,278,456]
                /*markPoint: {
                 data: [
                 {type: 'max', name: '最大值'},
                 {type: 'min', name: '最小值'}
                 ]
                 }*/

            }
        ]
    };
    myChart.setOption(option);
}
loadCitedPaperExpertCompare();