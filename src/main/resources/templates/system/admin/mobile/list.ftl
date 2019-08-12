<div<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title" style="color: #dc2600">店铺审核</h3>
            </div>
            <div class="box-body">
                <div class="clearfix">
                    <div class="col-md-4">
                        <div class="input-group date ">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" class="form-control pull-right" id="securityTime" placeholder="选择时间...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                            <input type="text" class="form-control" id="securityPremise" placeholder="根据账号搜索...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                            <input type="text" class="form-control" id="roleId" placeholder="根据角色搜索...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <button type="submit" onclick="securityReload();" class="btn btn-primary">搜索</button>
                    </div>
                </div>
                <table id="mobile_tab" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>序号</th>
                        <th>店铺归属人</th>
                        <th>店铺名字</th>
                        <th>店铺联系电话</th>
                        <th>店铺将要归属的类目编号</th>
                        <th>店铺描述</th>
                        <th>申请时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var mobile_tab;
    $(function () {
        //初始化时间选择器
        $('#securityTime').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true
        });
        //初始化表格
        var No = 0;
        mobile_tab = $('#mobile_tab').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": false, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "adminlte/plugins/datatables/language.json"},
            "ajax": {
                "url": "/examine/page",
                "type": "post",
                "data": function(d) {

                }
            },
            "columns": [
                {"data": null},
                {"data": "shopFounder"},
                {"data": "shopName"},
                {"data": "shopPhone"},
                {"data": "shopCategoryType"},
                {"data": "shopDescription"},
                {"data": "createTime"},
                {"data": null}
            ],
            "columnDefs": [
                {
                    targets: 0,
                    data: null,
                    render: function (data) {
                        No = No + 1;
                        return No;
                    }
                },
                {
                    "targets": -1,
                    "data": null,
                    "render": function (data) {
                        var btn = "";
                        btn = '<a class="btn btn-xs btn-info" callback="securityReload();" data-body="请认真审核资料再确认" target="ajaxTodo"  href="/examine/success?id='+data.auditId +'">通过</a> &nbsp;'
                                +'<a class="btn btn-xs btn-pinterest" callback="securityReload();" data-body="确认要未通过吗？未通过将要被删除！" target="ajaxTodo" href="/examine/UnSuccess?id='+ data.auditId + '">未通过</a>';

                        return btn;
                    }
                }]
        }).on('preXhr.dt', function (e, settings, data) {
            No = 0;
        });

        $("#securitySeek").on("click", function () {
            reloadTable(mobile_tab, "#securityTime", "#securityPremise");
            securityReload();
        });
    });
// 刷新
    function securityReload() {
        reloadTable(mobile_tab);
        console.log(mobile_tab);
    }

    function securityToListAjax() {
        list_ajax = mobile_tab;
        console.log(list_ajax);
    }
</script>
