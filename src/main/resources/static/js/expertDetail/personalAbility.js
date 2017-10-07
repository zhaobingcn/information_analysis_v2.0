/**
 * Created by hexu on 2016/12/7.
 */

function loadAbilityPersonal() {
    var myChart = echarts.init(document.getElementById("person-ability"));
    var authorName = $("#authorsName").text();
    var authorInstitution = $("#authorsInstitution").val();
    $.ajax({
        url: "/analysis/detailOfExpert/abilityOfExpert",
        type: "get",
        dataType: "json",
        data:{
            "name": authorName,
            "institution": authorInstitution
        },
        success: function (abilityData) {
            var option = {
                title: {
                    text: '专家能力雷达图'
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
                    name: '专家能力度量',
                    type: 'radar',
                    // areaStyle: {normal: {}},
                    data : [
                        {
                            value : abilityData.data,
                            name : '专家能力'
                        }
                    ]
                }]
            };
            myChart.setOption(option);
        }
    })
}

loadAbilityPersonal();