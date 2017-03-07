/**
 * Created by lsy on 2017/3/1.
 */
function loadAbilityExpertCompare() {
    var myChart = echarts.init(document.getElementById("expert-ability-comparison"));
    /* var authorName = $("#authorsName").text();
     var authorInstitution = $("#authorsInstitution").val();
     $.ajax({
     url: "/detailOfExpert/abilityOfExpert",
     type: "get",
     dataType: "json",
     data:{
     "name": authorName,
     "institution": authorInstitution
     },
     success: function (abilityData) { */
    var option = {
        title: {
            text: '专家能力对比雷达图',
            left: '12%',
            top: '5%'
        },
        tooltip: {},
        legend: {
            top: '20%',
            left:'12%',
            itemGap: 20,
            orient:'vertical',
            data: ['专家一','专家二']
        },
        radar: {
            // shape: 'circle',
            center: ['60%', '50%'],
            indicator: [
                { name: '论文数量', max: 30},
                { name: '引用次数', max: 200},
                { name: '研究深度', max: 2},
                { name: '研究广度', max: 50},
                { name: '合作圈子', max: 200},
                { name: '研究影响力', max: 200}
            ]
        },
        series: [{
            name: '科研机构能力度量',
            type: 'radar',
            // areaStyle: {normal: {}},
            data : [
                {
                    value : [5,110,1,35,170,127],
                    name : '专家一'
                },
                {
                    value : [15,130,1,45,190,177],
                    name : '专家二'
                }
            ]
        }]
    };
    myChart.setOption(option);
    // }
    // })
}

loadAbilityExpertCompare();