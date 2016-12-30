function loadInstitutionData(){
    var myChart = echarts.init(document.getElementById("10-hot-institutions"));
    $.ajax({
        url:"/pages/influentialInstitutions",
        type:"get",
        dataType:"json",
        data:{"limit": 9},
        success: function (institutions){
            institutionsName = [];
            institutionsValue = [];
            for(var i=8; i>=0; i--){
                institutionsName[8-i] = institutions.data[i].name;
                institutionsValue[8-i] = institutions.data[i].value;
            }
            $tableList = $("#table-ten-institutions").empty();
            for(var i=8; i>=0; i--){
                $tableList.append("<tr><td>" + institutionsName[i] + "</td><td>" + institutionsValue[i] + "</td></tr>")
            }
            var option = {
                title: {
                    text: '热门科研机构',
                    subtext: '数据截止2016年'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['2011年']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.0001]
                },
                yAxis: {
                    type: 'category',
                    data: institutionsName
                },
                series: [
                    {
                        name: '发表论文数量',
                        type: 'bar',
                        data: institutionsValue
                    },

                ]
            };

            myChart.setOption(option);
        }
    });

};
loadInstitutionData();