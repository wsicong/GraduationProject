/**
 * 兴趣分类列表
 */
var pageCurr;
$(function () {
    layui.use('table', function () {
        var table = layui.table
            , form = layui.form;

        tableIns = table.render({
            elem: '#hobbyClassList'
            , url: '/hobbyClass/getHobbyClassList'
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
                /*, {field: 'id', title: 'ID', width: 80, unresize: true, sort: true, hide: true}*/
                , {field: 'className', title: '班级名称'}
                /*, {field: 'hobbyTypeId', title: '兴趣类别id', hide: true}*/
                , {field: 'hobbyTypeName', title: '兴趣类别'}
                , {field: 'studentDescribe', title: '招生说明'}
                , {field: 'studentAge', title: '招生年龄', width: 95}
                , {field: 'enrollNum', title: '招生人数', width: 95}
                , {field: 'enrolledNum', title: '报名人数', width: 95}
                , {field: 'classTime', title: '上课时间', templet: '#classTimeTpl', width: 195}
                /*, {field: 'classStartDate', title: '开始日期', hide: true}
                , {field: 'classEndDate', title: '结束日期', hide: true}
                , {field: 'classWeeks', title: '上课星期', hide: true}
                , {field: 'classStartTime', title: '开始时间', hide: true}
                , {field: 'classEndTime', title: '结束时间', hide: true}*/
                , {field: 'costAndHour', title: '收费/课时', templet: '#costAndHourTpl', width: 135}
                /*, {field: 'classCost', title: '收费', hide: true}
                , {field: 'classHour', title: '课时', hide: true}*/
                , {field: 'status', title: '状态', templet: '#statusTpl'}
                , {field: 'enable', title: '是否启用', width: 95, templet: '#enableTpl'}
                /*, {field: 'createBy', title: '创建者', hide: true}
                , {field: 'createTime', title: '创建时间', width: 160, sort: true, hide: true}
                , {field: 'updateBy', title: '更新者', hide: true}
                , {field: 'updateTime', title: '更新时间', width: 160, sort: true, hide: true}
                , {field: 'remark', title: '备注', hide: true}*/
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
                //加载兴趣分类下拉框
                pageCurr = curr;
            }
        });

        //监听是否启用兴趣分类
        form.on('switch(isEnableTpl)', function (obj) {
            var data = obj.data;
            setHobbyClassEnable(obj, this.value, this.name, obj.elem.checked);
        });

        //监听工具条，编辑和删除操作
        table.on('tool(hobbyClassTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                delHobbyClass(data, data.id, data.className);
            } else if (obj.event === 'edit') {
                editHobbyClass(data, data.id);
            }
        });

        //监听提交
        form.on('submit(hobbyClassSubmit)', function (data) {
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

        //搜索插入日期
        laydate.render({
            elem: '#insertTimeStart'
            , type: 'datetime'
        });
        laydate.render({
            elem: '#insertTimeEnd'
            , type: 'datetime'
        });

        //添加、修改日期
        //上课日期
        laydate.render({
            elem: '#classStartDateStr'
        });
        laydate.render({
            elem: '#classEndDateStr'
        });

        //上课时间
        laydate.render({
            elem: '#classStartTimeStr'
            , type: 'time'
        });
        laydate.render({
            elem: '#classEndTimeStr'
            , type: 'time'
        });


        //TODO 数据校验
        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            //重新加载table
            load(data);
            return false;
        });
    });

    initHobbySelect();
});

//设置是否启用该兴趣分类
function setHobbyClassEnable(obj, id, hobbyClassName, checked) {
    var isEnable = checked ? 1 : 0;
    var hobbyClassEnable = checked ? '启用' : '禁用';
    //是否启用
    layer.confirm('您确定要把班级：' + hobbyClassName + '设置为' + hobbyClassEnable + '状态吗？', {
        btn: ['确认', '取消']
        , yes: function () {
            $.post("/hobbyClass/setHobbyClassEnable", {"id": id, "isEnable": isEnable}, function (data) {
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
        }, btn2: function () {
            layer.closeAll();
            //加载load方法
            load(obj);
        }, cancel: function () {
            layer.closeAll();
            //加载load方法
            load(obj);
        }
    });
}

//提交表单
function formSubmit(obj) {
    var currentUser = $('#currentUser').html();
    submitAjax(obj, currentUser);
}

//发送请求,新增/修改
function submitAjax(obj, currentUser) {
    var classStartDateStr = $("#classStartDateStr").val();
    var classEndDateStr = $("#classEndDateStr").val();
    var classStartTimeStr = $("#classStartTimeStr").val();
    var classEndTimeStr = $("#classEndTimeStr").val();
    var studentAgeMin = $("#studentAgeMin").val();
    var studentAgeMax = $("#studentAgeMax").val();
    var studentAgeRange = studentAgeMin + '-' + studentAgeMax;
    console.log(classStartDateStr + "--------" + classEndDateStr + "--------" + classStartTimeStr + "--------" + classEndTimeStr);
    var formData = $('#hobbyClassForm').serialize();
    $.ajax({
        type: 'POST',
        data: formData + '&classStartDateStr=' + classStartDateStr
            + '&classEndDateStr=' + classEndDateStr
            + '&classStartTimeStr=' + classStartTimeStr
            + '&classEndTimeStr=' + classEndTimeStr
            + '&studentAgeRange=' + studentAgeRange,
        url: '/hobbyClass/setHobbyClass',
        success: function (data) {
            //判断是否登录
            if (isLogin(data)) {
                if (data == 'ok') {
                    layer.alert('操作成功', function () {
                        //关闭所有弹出层
                        layer.closeAll();
                        cleanHobbyClass();
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
function cleanHobbyClass() {
    /*$('#hobbyName').val('');
    $('#hobbyInfo').val('');
    $('#enable').removeAttr('checked');*/
    /*$('#enable').val('');*/
    $('#hobbyClassForm')[0].reset();
    layui.form.render();
}


//异步获取兴趣下拉列表
function initHobbySelect() {
    $.get('/hobbyType/getHobbyTypes', function (data) {
        var list = data;//返回的list
        if (null != list) {
            var hobby = document.getElementById("hobbyTypeId");//hobbyTypeId为select定义的id
            for (var p in list) {
                var option = document.createElement('option');//创建添加option属性
                option.setAttribute('value', list[p].id);//给option的value添加值
                option.innerText = list[p].hobbyTypeName;//打印option对应的纯文本
                hobby.appendChild(option);//给select添加option子标签
            }
            /*openHobbyClass(null, '新增兴趣分类');*/
            layui.form.render('select');//刷新select，显示数据
        }
        /*else {
                    //弹出错误提示
                    layer.alert("获取兴趣数据有误，请您稍后再试", function () {
                        layer.closeAll();
                    });
                }*/
    });
}

//添加兴趣
function addHobbyClass() {
    /*$.get('/hobby/getHobbies',function (data) {
        var list=data;//返回的list
        if (null!=list){
            var hobby=document.getElementById("hobbyTypeId");//hobbyTypeId为select定义的id
            for (var p in list){
                var option=document.createElement('option');//创建添加option属性
                option.setAttribute('value',list[p].id);//给option的value添加值
                option.innerText=list[p].hobbyName;//打印option对应的纯文本
                hobby.appendChild(option);//给select添加option子标签
            }*/
    openHobbyClass(null, '新增班级');
    /*layui.form.render('select');//刷新select，显示数据
}else {
    //弹出错误提示
    layer.alert("获取兴趣数据有误，请您稍后再试", function () {
        layer.closeAll();
    });
}
});*/
}

//打开弹出层
function openHobbyClass(id, title) {
    if (id == null || id == "") {
        $("#id").val("");
    }
    layer.open({
        type: 1,
        title: title,
        area: ['1050px', '700px'],
        fixed: false,
        resize: false,
        shadeClose: true,
        skin: 'layui-layer-molv',
        content: $('#setHobbyClass'),
        end: function () {
            cleanHobbyClass();
        }
    });
}

function delHobbyClass(obj, id, className) {
    if (null != id) {
        layer.confirm('您确定要删除班级：' + className + '吗？', {
            btn: ['确定', '取消']
        }, function () {
            $.post("/hobbyClass/delete", {"id": id}, function (data) {
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

function editHobbyClass(obj, id) {
    $.get("/hobbyClass/getHobbyClass", {"id": id}, function (data) {
        /*console.log("时间："+DateUtils.formatterDate(data.hobbyClass.classStartDate));*/
        if (isLogin(data)) {
            if (data.msg == 'ok' && data.hobbyClass != null) {
                $("#id").val(data.hobbyClass.id == null ? '' : data.hobbyClass.id);
                $("#className").val(data.hobbyClass.className == null ? '' : data.hobbyClass.className);
                $("#studentDescribe").val(data.hobbyClass.studentDescribe == null ? '' : data.hobbyClass.studentDescribe);
                $("#studentAge").val(data.hobbyClass.studentAge == null ? '' : data.hobbyClass.studentAge);
                $("#enrollNum").val(data.hobbyClass.enrollNum == null ? '' : data.hobbyClass.enrollNum);
                $("#classStartDateStr").val(data.hobbyClass.classStartDate == null ? '' : DateUtils.formatterDate(data.hobbyClass.classStartDate));
                $("#classEndDateStr").val(data.hobbyClass.classEndDate == null ? '' : DateUtils.formatterDate(data.hobbyClass.classEndDate));
                $("#studentAgeMin").val(data.hobbyClass.studentAge == null ? '' : (data.hobbyClass.studentAge.split('-'))[0]);
                $("#studentAgeMax").val(data.hobbyClass.studentAge == null ? '' : (data.hobbyClass.studentAge.split('-'))[1]);
                $("#classStartTimeStr").val(data.hobbyClass.classStartTime == null ? '' : DateUtils.formatterTime(data.hobbyClass.classStartTime));
                $("#classEndTimeStr").val(data.hobbyClass.classEndTime == null ? '' : DateUtils.formatterTime(data.hobbyClass.classEndTime));
                $("#classCost").val(data.hobbyClass.classCost == null ? '' : data.hobbyClass.classCost);
                $("#classHour").val(data.hobbyClass.classHour == null ? '' : data.hobbyClass.classHour);
                $("#hobbyTypeId").val(data.hobbyClass.hobbyTypeId == null ? '' : data.hobbyClass.hobbyTypeId);
                /*$("#hobbyClassName").val(data.hobbyClass.hobbyClassName == null ? '' : data.hobbyClass.hobbyClassName);
                $("#hobbyClassInfo").val(data.hobbyClass.hobbyClassInfo == null ? '' : data.hobbyClass.hobbyClassInfo);*/
                /*$("#studyMaterials").val(data.hobbyClass.studyMaterials == null ? '' : data.hobbyClass.studyMaterials);*/

                if (null != data.hobbyClass.classWeeks && data.hobbyClass.classWeeks != null) {
                    var classWeeks = data.hobbyClass.classWeeks.split(',');
                    for (var p in classWeeks) {
                        if (1 == classWeeks[p]) {
                            $("#Monday").prop("checked", true);
                        } else if (2 == classWeeks[p]) {
                            $("#Tuesday").prop("checked", true);
                        } else if (3 == classWeeks[p]) {
                            $("#Wednesday").prop("checked", true);
                        } else if (4 == classWeeks[p]) {
                            $("#Thursday").prop("checked", true);
                        } else if (5 == classWeeks[p]) {
                            $("#Friday").prop("checked", true);
                        } else if (6 == classWeeks[p]) {
                            $("#Saturday").prop("checked", true);
                        } else if (7 == classWeeks[p]) {
                            $("#Sunday").prop("checked", true);
                        }
                    }
                }

                if (data.hobbyClass.enable) {
                    $("#enable").prop("checked", true);
                }
                /*$("#enable").check((data.hobby.enable == true) ? true : false);*/
                openHobbyClass(id, '编辑兴趣分类');
                layui.form.render();
                layui.form.render('checkbox');
                layui.form.render('select');
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


