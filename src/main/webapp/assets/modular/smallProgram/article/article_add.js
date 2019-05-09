/**
 * 角色详情对话框
 */
var RoleInfoDlg = {
    data: {
        pid: "",
        pName: ""
    }
};

layui.use(['layer','layedit', 'form', 'laydate', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var laydate = layui.laydate;
    var layedit = layui.layedit;
	  
    layedit.build('content'); //建立编辑器
    
    // 渲染时间选择框
    laydate.render({
        elem: '#createTime'
    });
    // 让当前iframe弹层高度适应
    admin.iframeAuto();
  
    

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/role/add", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});