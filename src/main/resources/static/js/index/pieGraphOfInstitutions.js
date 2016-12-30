/**
 * Created by zhzy on 2016/11/26.
 */
function loadPieGraphOfInstitutionData(){
    var myChart = echarts.init(document.getElementById("papers-percent-institutions"));
    var option = {
        title : {
            text: '各机构所占比例',
            subtext: '截止2016年',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['中国电科','大学机构','其他军工集团','其他科研机构']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'中国电科'},
                    {value:310, name:'大学机构'},
                    {value:234, name:'其他军工集团'},
                    {value:135, name:'其他科研机构'},
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    myChart.setOption(option);
}
loadPieGraphOfInstitutionData();