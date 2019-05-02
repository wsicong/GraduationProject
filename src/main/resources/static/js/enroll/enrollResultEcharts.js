var myChart;

$(function () {
    //搜索框
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;
        //TODO 数据校验
        //监听搜索框
        form.on('submit(searchSubmit)', function () {
            genData();
            return false;
        });
    });


    myChart = echarts.init(document.getElementById('main'));
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

    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var hobbyClassName = [];
    var enrolledNum = [];
    var enrollNum = [];

// 异步加载数据
    $.post('/hobbyClass//getHobbyClass').done(function (data) {
        console.log(data)
        for (var p in data) {
            hobbyClassName[p] = data[p].className + "(" + data[p].hobbyTypeName + ")";
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
                data: enrollNum,
                itemStyle: {
                    normal: {
                        label: {
                            show: true, //开启显示
                            position: 'top', //在上方显示
                            textStyle: { //数值样式
                                color: 'black',
                                fontSize: 16
                            }
                        }
                    }
                }
            }, {
                // 根据名字对应到相应的系列
                name: '报名人数',
                data: enrolledNum,
                itemStyle: {
                    normal: {
                        label: {
                            show: true, //开启显示
                            position: 'top', //在上方显示
                            textStyle: { //数值样式
                                color: 'black',
                                fontSize: 16
                            }
                        }
                    }
                }
            }]
        });
        myChart.hideLoading();
    });
});

function genData() {
    $.ajax({
        type: 'POST',
        data: $('#hobbyClassSearch').serialize(),
        url: '/hobbyClass//getHobbyClass',
        success: function (data) {
            var hobbyClassName = [];
            var enrolledNum = [];
            var enrollNum = [];
            for (var p in data) {
                hobbyClassName[p] = data[p].className + "(" + data[p].hobbyTypeName + ")";
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
            myChart.hideLoading();
        },
        error: function () {
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    });
}
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
