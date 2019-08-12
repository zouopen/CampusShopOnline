<div class="row">
	<div class="col-md-12">
		<form id="securityAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="userNoLabel">账号</label>
					<input type="text" class="form-control" name="username" id="username" placeholder="输入账号...">
				</div>
				<div class="form-group">
					<label id="passwordLabel">密码</label>
					<input type="password" class="form-control" name="password" id="password" placeholder="输入密码...">
				</div>
                <div class="form-group">
                    <label id="passwordLabel">权限</label>
                    <input type="number" class="form-control" name="roleId" disabled placeholder="默认权限为管理员">
                </div>
                <input hidden name = "createBy"value="${bean.username}">
                <input hidden name="userId">
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="securitySave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
    function securitySave (){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	if($("#username").val()==""){
		$("#userNoLabel").prepend('<span class="errorClass" style="color:red">*账号不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if($("#password").val()==""){
		$("#passwordLabel").prepend('<span class="errorClass" style="color:red">*密码不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if(status == 0){
		return false;
	}else{
		$.ajax({
            url: '/user/save',
            type: 'post',
            dataType: 'json',
            data: $("#securityAddForm").serialize(),
            success: function (data) {
                var code = data.code;
                if (code == 0){
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功","success");
                    console.log(code);
                    reloadTable(list_ajax);
                }else{
                    var message = data.message;
                    console.log(message);
                    alertMsg("更新失败:"+message,"success");
                }
            }
        });
	}
}
</script>