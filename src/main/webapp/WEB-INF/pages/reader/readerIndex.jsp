<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>读者管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
    <script src="${pageContext.request.contextPath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
</head>

<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="demoTable">
            <div class="layui-form-item layui-form">
                图书卡号：
                <div class="layui-inline">
                    <input class="layui-input" name="readerNumber" id="readerNumber" autocomplete="off">
                </div>
                用户名：
                <div class="layui-inline">
                    <input class="layui-input" name="username" id="username" autocomplete="off">
                </div>
                电话：
                <div class="layui-inline">
                    <input class="layui-input" name="tel" id="tel" autocomplete="off">
                </div>

                <button class="layui-btn" data-type="reload">搜索</button>
            </div>
        </div>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">添加</button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete">删除
                </button>
            </div>
        </script>

        <!-- 表单，查询出的数据在这里显示 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="update">修改</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>

<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;


        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/readerAll',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                //{field: 'id', width: 100, title: 'ID', sort: true},
                {field: 'readerNumber', width: 150, title: '读者卡号'},
                {field: 'username', width: 100, title: '用户名'},
                {field: 'realName', width: 100, title: '真实姓名'},
                {field: 'sex', width: 80, title: '性别'},
                {field: 'tel', width: 150, title: '电话'},
                {
                    templet: "<div>{{layui.util.toDateString(d.registerDate,'yyyy-MM-dd HH:mm:ss')}}</div>",
                    width: 200,
                    title: '办卡时间'
                },
                {templet: '<div>{{d.email}}</div>', width: 150, title: '邮箱'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line',
            id: 'testReload'
        });

        var $ = layui.$, active = {
            reload: function () {
                var readerNumber = $('#readerNumber').val();
                var username = $('#username').val();
                var tel = $('#tel').val();
                console.log(name)
                table.reload('testReload', {
                    page: {
                        curr: 1
                    }
                    , where: {
                        readerNumber: readerNumber,
                        username: username,
                        tel: tel
                    }
                }, 'data');
            }
        };

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'update') {
                var index = layer.open({
                    title: '修改读者信息',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/queryReaderInfoById?id=' + data.id,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {
                layer.confirm('确定是否删除', function (index) {
                    deleteInfoByIds(data.id, index);
                    layer.close(index);
                });
            }
        });

        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        /**
         * 获取选中记录的 ID 信息
         */
        function getCheackId(data) {
            var arr = new Array();
            for (var i = 0; i < data.length; i++) {
                arr.push(data[i].id);
            }
            //拼接id,变成一个字符串
            return arr.join(",");
        };


        /**
         * 提交删除
         */
        function deleteInfoByIds(ids, index) {
            $.ajax({
                url: "deleteReader",
                type: "POST",
                data: {ids: ids},
                success: function (result) {
                    if (result.code === 0) {
                        layer.msg('删除成功', {
                            icon: 6,
                            time: 500
                        }, function () {
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        });
                    } else {
                        layer.msg("删除失败");
                    }
                }
            })
        };

        /**
         * toolbar栏监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {
                var index = layer.open({
                    title: '添加图书',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/readerAdd',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {
                var checkStatus = table.checkStatus(obj.config.id);
                var data = checkStatus.data;
                if (data.length === 0) {
                    layer.msg("请选择要删除的记录信息");
                } else {
                    var ids = getCheackId(data);
                    layer.confirm('确定是否删除', function (index) {
                        deleteInfoByIds(ids, index);
                        layer.close(index);
                    });
                }
            }
        });

    });
</script>

</body>
</html>
