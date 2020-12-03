<%--
  Created by IntelliJ IDEA.
  User: Chensq
  Date: 2020/11/26
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css"  media="all">
    <script type="text/javascript" src="../comResource/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<div class="searchBox" style="margin: 10px 10px 0px;">
    账号：
    <div class="layui-inline">
        <input class="layui-input" name="account" id="filter-account" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>
<table class="layui-table" id="table_user" lay-filter="table_user_listener"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">
            <i class="layui-icon">&#xe608;</i> 添加
        </button>
        <button class="layui-btn layui-btn-sm" lay-event="edit">
            <i class="layui-icon">&#xe6b2;</i> 编辑
        </button>
        <button class="layui-btn layui-btn-sm" lay-event="check">
            <i class="layui-icon">&#xe63c;</i> 查看
        </button>
        <button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">
            <i class="layui-icon">&#xe616;</i> 删除
        </button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="otherOp">其他操作</a>
</script>

<script type="text/html" id="switchTpl">
    <div class="layui-input-block" style="margin-left: 0px">
        <select name="sex">
            <option value="0" {{ d.sex == '0' ? 'selected' : '' }}>女</option>
            <option value="1" {{ d.sex == '1' ? 'selected' : '' }}>男</option>
        </select>
    </div>
</script>

<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../comResource/js/layui-patch.js" charset="utf-8"></script>
<script>
    var tableIn;
    layui.use('table', function() {
        let table = layui.table;
        tableIn = table.render({
            elem: '#table_user'
            ,id: 'userTable'
            ,url: '/user/getAllUser'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print']
            ,page: {
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                ,groups: 3 //只显示 3 个连续页码
            }
            ,response: {statusCode: 200}
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'account', title: 'ID', sort: true}
                ,{field:'password', title: '密码'}
                ,{field:'userName', title: '名字', sort: true}
                ,{field:'sex', title: '性别', templet: '#switchTpl'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
        });

        var $ = layui.$, active = {
            reload: function(){
                let account = $('#filter-account');
                table.reload('userTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        account: account.val()
                    }
                }, 'data');
            }
        };

        $('.searchBox .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        //头部工具栏事件
        table.on('toolbar(table_user_listener)', function(obj){
            let checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    addInfo("新增用户","../user/form.jsp","","800px","400px");
                    break;
                case 'edit':
                    editInfo(checkStatus,"account","编辑用户","../user/form.jsp","","800px","400px");
                    break;
                case 'check':
                    checkInfo(checkStatus,"account","查看用户","../user/form.jsp","","800px","400px");
                    break;
                case 'delete':
                    delInfo(tableIn,checkStatus,"account","/user/delUserByAccount/");
                    break
            }
        });

        //监听行工具事件
        table.on('tool(table_user_listener)', function(obj){
            switch (obj.event) {
                case "otherOp":
                    layer.msg("这是其他操作，可以自定义你想做的操作", {icon: 4});
                    break;
            }
        });
    });
</script>
</body>
</html>
