/**
 * Created by lsy on 2017/2/24.
 */
function loadStaffSecondInstitution() {
    var myChart = echarts.init(document.getElementById("second-institutional-staff"));
    var option = {
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['院士','教授','高级工程师','XXX学者','其他']
        },
        series : [
            {
                // name: '访问来源',
                type: 'pie',
                radius : '65%',
                center: ['55%', '50%'],
                data:[
                    {value:335, name:'院士'},
                    {value:310, name:'教授'},
                    {value:234, name:'高级工程师'},
                    {value:135, name:'XXX学者'},
                    {value:555, name:'其他'}
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
loadStaffSecondInstitution();