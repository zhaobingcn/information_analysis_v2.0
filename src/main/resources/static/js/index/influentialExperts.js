/**
 * Created by zhzy on 2016/11/26.
 */
function loadExpertsData() {
var myChart = echarts.init(document.getElementById("10-hot-authors"));
    $.ajax({
        url:"/analysis/pages/influentialExperts",
        dataType:"json",
        type:"get",
        data:{"limit": 9},
        success: function (experts){
            expertsName = [];
            expertsValue = [];
            for(var i=8; i>=0; i--){
                expertsName[8-i] = experts.data[i].name;
                expertsValue[8-i] = experts.data[i].value;
            }
            $tableList = $("#table-ten-authors").empty();
            for(var i=8; i>=0; i--){
                $tableList.append("<tr><td>" + expertsName[i] + "</td><td>" + expertsValue[i] + "</td></tr>")
            }
            var option = {
                title: {
                    text: '权威专家',
                    subtext: '数据截止2016年'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    data: expertsName
                },
                series: [
                    {
                        name: '论文贡献值',
                        type: 'bar',
                        data: expertsValue
                    },

                ]
            };
            myChart.setOption(option);
        }
    });

}
loadExpertsData();