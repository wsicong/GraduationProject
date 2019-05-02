$(function () {
    //搜索框
    /*layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;
        //TODO 数据校验
        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            searchData();
            return false;
        });
    });*/


    var myChart = echarts.init(document.getElementById('main'));
// 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: '兴趣班报名情况'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data: ['招生人数', '报名人数']
        },
        xAxis: {
            data: []
        },
        yAxis: {
            type: 'value',
            name: '人数',
            axisLabel: {
                formatter: '{value} 人'
            }
        },
        series: [{
            name: '招生人数',
            type: 'bar',
            data: []
        }, {
            name: '报名人数',
            type: 'bar',
            data: []
        }]
    });

// 异步加载数据
    $.post('/hobbyClass//getHobbyClass').done(function (data) {
        var hobbyClassName = new Array();
        var enrolledNum = new Array();
        var enrollNum = new Array();
        for (var p in data) {
            hobbyClassName[p] = data[p].className;
            enrolledNum[p] = data[p].enrolledNum;
            enrollNum[p] = data[p].enrollNum;
        }
        // 填入数据
        myChart.setOption({
            xAxis: {
                data: hobbyClassName
            },
            series: [{
                name: '招生人数',
                data: enrollNum
            }, {
                // 根据名字对应到相应的系列
                name: '报名人数',
                data: enrolledNum
            }]
        });
    });
});

/*
function searchData() {
    $.ajax({
        type:'POST',
        data:$('#hobbyClassSearch').serialize(),
        url:'/hobbyClass//getHobbyClass',
        success:function () {

        }
    });
}*/
