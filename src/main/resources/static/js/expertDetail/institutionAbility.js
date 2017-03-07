/**
 * Created by lsy on 2017/2/18.
 */

function loadAbilityInstitution() {
   var myChart = echarts.init(document.getElementById("institution-ability"));
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
                    text: '科研机构能力雷达图'
                },
                tooltip: {},
                radar: {
                    // shape: 'circle',
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
                            value : [15,130,1,45,190,177],
                            name : '科研机构能力'
                        }
                    ]
                }]
            };
           myChart.setOption(option);
       // }
    // })
}

loadAbilityInstitution();