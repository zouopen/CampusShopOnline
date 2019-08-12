<div class="row">
    <div class="col-md-12">
        <form id="shopCategoryAddForm">
            <input type="hidden" id="shopCategoryId" name="shopCategoryId" value="${shopCategory.shopCategoryId}">
            <div class="box-body">
                <div class="form-group">
                    <label id="userNoLabel">类目名字</label>
                    <input type="text" class="form-control" name="shopCategoryName" id="shopCategoryName" value="${shopCategory.shopCategoryName}" placeholder="输入账号...">
                </div>
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="shopCategorySave();" type="button" class="btn btn-primary btn-sm"><i class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function shopCategorySave (){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	if($("#shopCategoryName").val()==""){
		$("#userNoLabel").prepend('<span class="errorClass" style="color:red">类目不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if(status == 0){
		return false;
	}else{
		$.ajax({
            url: '/shopCategory/saveUpdate',
            type: 'post',
            dataType: 'json',
            data: $("#shopCategoryAddForm").serialize(),
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