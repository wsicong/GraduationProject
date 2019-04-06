/**
 * 兴趣列表
 */
var pageCurr;
$(function () {
    layui.use('table', function () {
        var table = layui.table
            , form = layui.form;

        tableIns = table.render({
            elem: '#hobbyList'
            , url: '/hobby/getHobbyList'
            , method: 'post'//默认：get请求
            , cellMinWidth: 80//全局定义所有常规单元格的最小宽度（默认：60）
            , page: true//开启分页
            , text: {
                none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
            },
            request: {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'limit' //每页数据量的参数名，默认：limit
            }, response: {
                statusName: 'code' //数据状态的字段名称，默认：code
                , statusCode: 200 //成功的状态码，默认：0
                , countName: 'totals' //数据总数的字段名称，默认：count
                , dataName: 'list' //数据列表的字段名称，默认：data
            }
            , cols: [[
                {type: 'numbers', title: '序号'}
                , {field: 'id', title: 'ID', width: 80, unresize: true, sort: true}
                , {field: 'hobbyName', title: '兴趣名称'}
                , {field: 'hobbyInfo', title: '简介'}
                , {field: 'enable', title: '是否启用', width: 95, templet: '#enableTpl'}
                , {field: 'createBy', title: '创建者', hide: true}
                , {field: 'createTime', title: '创建时间', sort: true}
                , {field: 'updateBy', title: '更新者', hide: true}
                , {field: 'updateTime', title: '更新时间', sort: true}
                , {field: 'remark', title: '备注'}
                , {fixed: 'right', title: '操作', width: 140, align: 'center', toolbar: '#optBar'}
            ]]
            , done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                //console.log(curr);
                //得到数据总量
                //console.log(count);
                pageCurr = curr;
            }
        });

        //监听是否启用兴趣分类
        form.on('swith(isEnableTpl)', function (obj) {
            var data = obj.data;
            setHobbyEnable(obj, this.value, this.name, obj.elem.checked);
        });

        //监听工具条，编辑和删除操作
        table.on('tool(hobbyTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                delHobby(data, data.id, data.hobbyName);
            } else if (obj.event === 'edit') {
                editHobby(data, data.id);
            }
        });

        //监听提交
        form.on('submit(hobbySubmit)', function (data) {
            // TODO 校验
            formSubmit(data);
            return false;
        });
    });
    //搜索框
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#insertTimeStart'
        });
        laydate.render({
            elem: '#insertTimeEnd'
        });
        //TODO 数据校验
        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            //重新加载table
            load(data);
            return false;
        });
    });
});

//设置是否启用该兴趣分类
function setHobbyEnable(obj, id, hobbyName, checked) {
    var isEnable = checked ? 1 : 0;
    var hobbyIsEnable = checked ? '启用' : '禁用';
    //是否启用
    layer.confirm('您确定要吧兴趣：' + hobbyName + '设置为' + hobbyIsEnable + '状态吗？', {
        btn: ['确认', '取消']
    }, function () {
        $.post("/hobby/setHobbyEnable", {"id": id, "enable": isEnable}, function (data) {
            if (isLogin(data)) {
                if (data == "ok") {
                    //回调弹框
                    layer.alert("操作成功！", function () {
                        layer.closeAll();
                        //加载load方法
                        load(obj);
                    });
                } else {
                    layer.alert(data, function () {
                        layer.closeAll();
                        //加载load方法
                        load(obj);//自定义
                    });
                }
            }
        });
    }, function () {
        layer.closeAll();
        //加载load方法
        load(obj);
    });
}

//提交表单
function formSubmit(obj) {
    var currentUser = $('#currentUser').html();
    submitAjax(obj, currentUser);
}

//发送请求
function submitAjax(obj, currentUser) {
    $.ajax({
        type: 'POST',
        data: $('#hobbyForm').serialize(),
        url: '/hobby/setHobby',
        success: function (data) {
            //判断是否登录
            if (isLogin(data)) {
                if (data == 'ok') {
                    layer.alert('操作成功', function () {
                        //关闭所有弹出层
                        layer.closeAll();
                        cleanHobby();
                        //重新加载table
                        load(obj);
                    });
                } else {
                    layer.alert(data, function () {
                        layer.closeAll();
                        load(obj);
                    });
                }
            }
        },
        error: function () {
            layer.alert('操作请求有误，请稍后再试', function () {
                //关闭所有弹出层
                layer.closeAll();
                //重新加载table
                load(obj);
            })
        }
    });
}

//清除数据
function cleanHobby() {
    /*$('#hobbyName').val('');
    $('#hobbyInfo').val('');
    $('#enable').removeAttr('checked');*/
    /*$('#enable').val('');*/
    $('#hobbyForm')[0].reset();
    layui.form.render();
}

function addHobby() {
    openHobby(null, '新增兴趣分类');
    //重新渲染下form表单 否则复选框无效
    layui.form.render('checkbox');
}

//打开弹出层
function openHobby(id, title) {
    if (id == null || id == "") {
        $("#id").val("");
    }
    layer.open({
        type: 1,
        title: title,
        fixed: false,
        resize: false,
        shadeClose: true,
        skin: 'layui-layer-molv',
        content: $('#setHobby'),
        end: function () {
            cleanHobby();
        }
    });
}

function delHobby(obj, id, hobbyName) {
    if (null != id) {
        layer.confirm('您确定要删除兴趣分类：' + hobbyName + '吗？', {
            btn: ['确定', '取消']
        }, function () {
            $.post("/hobby/delete", {"id": id}, function (data) {
                if (isLogin(data)) {
                    if (data == "ok") {
                        //回调弹框
                        layer.alert("删除成功！", function () {
                            layer.closeAll();
                            //加载load方法
                            load(obj);//自定义
                        });
                    } else {
                        layer.alert(data, function () {
                            layer.closeAll();
                            //加载load方法
                            load(obj);//自定义
                        });
                    }
                }
            });
        }, function () {
            layer.closeAll();
        });
    }
}

function editHobby(obj, id) {
    $.get("/hobby/getHobby", {"id": id}, function (data) {
        if (isLogin(data)) {
            if (data.msg == 'ok' && data.hobby != null) {
                $("#id").val(data.hobby.id == null ? '' : data.hobby.id);
                $("#hobbyName").val(data.hobby.hobbyName == null ? '' : data.hobby.hobbyName);
                $("#hobbyInfo").val(data.hobby.hobbyInfo == null ? '' : data.hobby.hobbyInfo);
                /*$("#enable").checked(data.hobby.enable == true ? true : false);*/
                openHobby(id, '编辑兴趣分类');
                /*layui.form.render();*/
                /*layui.form.render('checkbox');*/
            } else {
                //弹出错误提示
                layer.alert(data.msg, function () {
                    layer.closeAll();
                });
            }
        }
    });
}

function load(obj) {
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}


