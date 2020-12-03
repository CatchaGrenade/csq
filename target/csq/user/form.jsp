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
    <title>用户信息</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css"  media="all">
    <script type="text/javascript" src="../comResource/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<%
    //获得页面type
    String pageType=request.getParameter("pageType");
    //获得用户account
    String account=request.getParameter("account")==null?"":request.getParameter("account");
%>
<script type="text/javascript">
    var pageType =  "<%= pageType%>";
    var account =  "<%= account%>";
</script>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>用户信息</legend>
</fieldset>

<form class="layui-form" action="" lay-filter="userInfo-form">
    <div class="layui-form-item">
        <label class="layui-form-label">账号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id = "account" name="account" lay-verify="required" lay-reqtext="账号不能为空" autocomplete="off" placeholder="请输入账号" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-block">
            <input type="password" name="password" lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="userName" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="1" title="男" checked="">
            <input type="radio" name="sex" value="0" title="女">
        </div>
    </div>
    <div id = "subBox" class="layui-form-item" style="text-align: center">
        <div class="layui-input-block" style="margin-left: 0px">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="userInfo-sub">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../comResource/js/layui-patch.js" charset="utf-8"></script>
<script>
    layui.use('form', function() {
        var form = layui.form;
        form.render();
        switch (pageType) {
            case 'add':
                break;
            case 'edit':
                $("#account").attr("disabled","disabled");
                getFormInfo(form,"userInfo-form","/user/getUserByAccount/"+account);
                break;
            case 'check':
                $("#subBox").remove();
                getFormInfo(form,"userInfo-form","/user/getUserByAccount/"+account);
                break;
        }
        //监听提交
        form.on('submit(userInfo-sub)', function(data){
            var formJson = JSON.stringify(data.field);
                $.ajax({
                    url:"/user/addOrUpdUser"
                    ,type:"POST"
                    ,contentType:'application/json'
                    ,data:formJson
                    ,success: function(callback){
                        if(callback.code == 200){
                            parent.tableIn.reload();
                            parent.layer.closeAll();
                            parent.layer.msg('保存成功', {icon: 1});
                        }
                    }
                    ,error: function (XMLHttpRequest) {
                        layer.msg('保存失败', {icon: 5});
                    }
                });
            return false;
        });
    });
</script>
</body>
</html>
