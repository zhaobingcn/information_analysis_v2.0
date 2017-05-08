/**
 * Created by lsy on 2017/2/25.
 */
function loadProgramInstitutionCompare () {
    var myChart = echarts.init(document.getElementById('institution-program-comparison'));
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
            left: 'center',
            text: '项目承接量比较'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            top: 'bottom',
            data:['科研机构1','科研机构2']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                //dataView: {readOnly: true},
                //magicType: {type: [ 'bar']},
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
                name:'科研机构1',
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
                name:'科研机构2',
                type:'line',
                data:[123,234,345,456,345,467,345,89,344,678,456]
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
    //}
    //})
}

loadProgramInstitutionCompare();