/**
 * Created by zhzy on 2016/11/26.
 */
function loadPieGraphOfJournalData(){
    var myChart = echarts.init(document.getElementById("hot-journal"));
    $.ajax({
        url : "/analysis/pages/influentialJournals",
        dataType: "json",
        data: {"limit": 10},
        type : "get",
        success: function (journals) {
            var legendData = [];
            for(var i=0; i<10; i++){
                legendData[i] = journals.data[i].name;
            }
            var option = {
                title : {
                    text: '热门期刊所占比例',
                    subtext: '截止2016年',
                    x:'center'
                },

                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    bottom: 'bottom',
                    orient: 'horizontal',
                    data: legendData
                },
                series: [
                    {
                        name:'文章来源',
                        type:'pie',
                        selectedMode: 'single',
                        radius: [0, '30%'],

                        label: {
                            normal: {
                                position: 'inner'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:[
                            {value:335, name:'中电科', selected:true},
                            {value:679, name:'中核工业'},
                            {value:1548, name:'中航工业'},
                            {valee:1225, name:'高校'}
                        ]
                    },
                    {
                        name:'所投期刊',
                        type:'pie',
                        radius: ['40%', '55%'],
                        data:journals.data
                    }
                ]
            };
            myChart.setOption(option);
        }
    });

}
loadPieGraphOfJournalData();

