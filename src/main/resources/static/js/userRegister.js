/**
 * 普通用户注册
 */
$(function () {
    layui.use(['form', 'layer'], function () {
        var form = layui.form
            , layer = layui.layer;

        //监控注册提交
        form.on("submit(register)", function () {
            register();
            return false;
        });
    });
});

//普通用户注册请求
function register() {
    $.post("/user/addOrdinaryUser", $('#userRegister').serialize(), function (data) {
        console.log(data)
        if (data == 'ok') {
            layer.msg('注册成功,即将返回登录页面。。。', {icon: 1, time: 1000, shade: 0.4}, function () {
                window.location.href = '/userLogin';
            });
        } else {
            layer.alert('提示：' + data);
        }
    });
}