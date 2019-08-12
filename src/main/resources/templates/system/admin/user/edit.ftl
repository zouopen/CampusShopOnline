<div class="row">
    <div class="col-md-12">
        <form id="securityEditForm">
            <input type="hidden" id="id" name="id" value="${bean.id}">
            <div class="box-body">
                <div class="form-group">
                    <label id="userNoLabel">账号</label>
                    <input type="text" class="form-control" name="username" id="username" value="${bean.username}" placeholder="输入账号...">
                </div>
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="securityUpdateUser();" type="button" class="btn btn-primary btn-sm"><i class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function securityUpdateUser() {
        $.ajax({
            url: '/user/update',
            type: 'post',
            dataType: 'json',
            data: $("#securityEditForm").serialize(),
            success: function (data) {
                var code = data.code;
                if (code == 0){
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功","success");
                    console.log(code);
                    reloadTable(list_ajax);
                }else{
                    var message = data.message;
                    alertMsg("更新失败:"+message,"success");
                }
            }
        });
    }

</script>