/**
 * 新增数据
 * @param title 窗口标题
 * @param url   页面路径
 * @param filter    页面传参,传参格式"&aaa=***&bbb=***"
 * @param width 窗口宽度
 * @param height    窗口高度
 */
addInfo = function(title,url,filter,width,height){
    layer.open({
        type: 2
        ,title: title
        ,content: url+'?pageType=add'+filter
        ,area: [width, height]
    });
};

/**
 * 编辑信息
 * @param checkStatus   选择的项目
 * @param priKey    主键（作为主键，获取选择项目的详细信息）
 * @param title     窗口标题
 * @param url       路径
 * @param filter    页面传参,传参格式"&aaa=***&bbb=***"
 * @param width     窗口宽度
 * @param height    窗口高度
 */
editInfo = function(checkStatus,priKey,title,url,filter,width,height){
    let data = checkStatus.data;
    if(data.length !== 1){
        layer.msg('请选择一个项目编辑');
    }else {
        layer.open({
            type: 2
            , title: title
            , content: url+'?pageType=edit&'+priKey+'='+data[0][priKey]+filter
            , area: [width, height]
        });
    }
};

/**
 * 查看信息
 * @param checkStatus   选择的项目
 * @param priKey    主键（作为主键，获取选择项目的详细信息）
 * @param title 窗口标题
 * @param url   路径
 * @param filter    页面传参,传参格式"&aaa=***&bbb=***"
 * @param width 窗口宽度
 * @param height    窗口高度
 */
checkInfo = function(checkStatus,priKey,title,url,filter,width,height){
    let data = checkStatus.data;
    if(data.length !== 1){
        layer.msg('请选择一个项目查看');
    }else {
        layer.open({
            type: 2
            , title: title
            , content: url+'?pageType=check&'+priKey+'='+data[0][priKey]+filter
            , area: [width, height]
        });
    }
};

/**
 * 删除数据
 * @param tableIn   选择的列表
 * @param checkedItem   列表中选中的数据
 * @param priKey    主键（根据主键来删除数据）
 * @param url   删除请求路径
 */
delInfo = function(tableIn,checkedItem,priKey,url){
    let data = checkedItem.data;
    if(data.length<1){
        layer.msg('至少勾选一项删除');
        return;
    }
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '300px;'
        ,shade: 0.8
        ,btn: ['确认', '取消']
        ,content: '<div style="padding: 30px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;text-align: center">是否删除</div>'
        ,yes: function(){
            layer.closeAll();
            let idStr = "";
            for(let i = 0; i < data.length; i++){
                if(i!==0){
                    idStr +=",";
                }
                idStr += data[i][priKey];
            }
            $.ajax({
                url:url+idStr
                ,type:"DELETE"
                ,success: function(callback){
                    if(callback.code === 200){
                        tableIn.reload();
                    }
                }
                ,error: function () {
                    layer.msg('删除出错了', {icon: 5});
                }
            });
        }
    });
};

/**
 * 获取表单信息
 * @param form 初始化的form
 * @param whichForm form表示
 * @param url 请求数据路径
 */
getFormInfo = function (form,whichForm,url) {
    $.ajax({
        url:url
        ,type:"GET"
        ,success: function(callback){
            if(callback.code === 200){
                let data = callback.data;
                form.val(''+whichForm+'', data);
            }
        }
        ,error: function () {
            layer.msg('获取表单信息失败', {icon: 5});
        }
    });
};