/**
 * 报名表单页面
 */
$(function () {
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;

        //加载出生日期控件
        laydate.render({
            elem: '#childBirthStr'
        });

        //监听提交
        form.on('submit(enrollSubmit)', function (data) {
            console.log('进入监听按钮事件')
            formSubmit(data);
            return false;
        });
    });
});

//提交表单
function formSubmit(obj) {
    var currentUser = $('#currentUser').html();
    submitAjax(obj, currentUser);
}

//发送请求
function submitAjax(obj, currentUser) {
    console.log('异步请求');
    $.ajax({
        type: 'POST',
        data: $('#enrollForm').serialize(),
        url: '/enrollRecord/addEnrollRecord',
        success: function (data) {
            //判断是否登录
            if (isLogin(data)) {
                if (data == 'ok') {
                    layer.alert('报名成功', function () {

                    });
                } else {
                    layer.alert(data, function () {

                    });
                }
            }
        },
        error: function () {
            layer.alert('操作请求有误，请稍后再试', function () {

            });
        }
    });
}