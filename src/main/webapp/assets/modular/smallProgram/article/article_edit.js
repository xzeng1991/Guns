layui.use(['layer','upload','layedit', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var layedit = layui.layedit;
    var layer = layui.layer;
    var upload = layui.upload;
    // 让当前iframe弹层高度适应
    admin.iframeAuto();
   
    // 渲染时间选择框
    /*laydate.render({
        elem: '#createTime'
    });*/
    //获取用户信息
    var ajax = new $ax(Feng.ctxPath + "/article/getArticleInfo?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('articleEditForm', result.data);
    //$("#articleEditForm textarea[name=content]").val(result.data.content);
    var editIndex = layedit.build('content', {
        height: 535
        //,uploadImage: { url: "../../json/newsImg.json"}
    });
    
    layedit.setContent(editIndex,result.data.content);
    
    upload.render({
        elem: '#avatarFile',
        url: Feng.ctxPath + '/article/upload/',
        auto: false,
        //,multiple: true,
        bindAction: '#uploadAvatar',
        done: function(res){
        	var avatar=res.data;
            $("#articleEditForm input[name=avatar]").val(avatar);
        }
      });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
    	data.field.content = layedit.getContent(editIndex);
        var ajax = new $ax(Feng.ctxPath + "/article/edit", function (data) {
            Feng.success("修改成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改成功！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});