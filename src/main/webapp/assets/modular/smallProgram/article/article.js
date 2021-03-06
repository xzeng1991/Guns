layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--角色管理
     */
    var Article = {
        tableId: "articleTable",    //表格id
        condition: {
            title: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Article.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true},
            {field: 'title', sort: true, title: '文章标题'},
            {field: 'brife', sort: true, title: '文章简介'},
            {field: 'author', sort: true, title: '作者'},
            {field: 'collectNum', sort: true, title: '作者'},
            {field: 'readNum', sort: true, title: '阅读数'},
            {field: 'commentNum', sort: true, title: '评论数'},
            {field: 'upNum', sort: true, title: '点赞数'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Article.search = function () {
        var queryData = {};
        queryData['title'] = $("#title").val();
        table.reload(Article.tableId, {where: queryData});
    };

    /**
     * 弹出添加文章页面
     */
    Article.openAddArticle = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area: ['890px', '660px'],
            title: '添加文章',
            content: Feng.ctxPath + '/article/article_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Article.tableId);
            }
        });
    };



    /**
     * 点击编辑角色
     *
     * @param data 点击按钮时候的行数据
     */
    Article.onEditArticle = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area: ['990px', '960px'],
            title: '修改文章',
            content: Feng.ctxPath + '/article/article_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Role.tableId);
            }
        });
    };

    /**
     * 点击删除角色
     *
     * @param data 点击按钮时候的行数据
     */
    Article.onDeleteArticle = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/article/remove", function () {
                Feng.success("删除成功!");
                table.reload(Article.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除文章 " + data.title + "?", operation);
    };



    // 渲染表格
     table.render({
        elem: '#' + Article.tableId,
        url: Feng.ctxPath + '/article/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Article.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Article.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Article.openAddArticle();
    });



    // 工具条点击事件
    table.on('tool(' + Article.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            Article.onEditArticle(data);
        } else if (layEvent === 'delete') {
            Article.onDeleteArticle(data);
        } 
    });
});
