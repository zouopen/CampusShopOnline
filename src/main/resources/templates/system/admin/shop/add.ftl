<div class="row">
	<div class="col-md-12">
		<form id="AddShopFrom">
			<div class="modal-body">
                <div class="form-group">
                    <label id="usernameLable">账号</label>
                    <input type="text" class="form-control" name="username" placeholder="输入账号..." value=${(sellerInfo.username)!''}>
                </div>
                <div class="form-group">
                    <label id="passwordLabel">密码</label>
                    <input type="password" class="form-control" name="password" placeholder="输入密码..." value="${(sellerInfo.password)!''}">
                </div>
                <div class="form-group">
                    <label id="shopOwnerLabel">店铺归属人</label>
                    <input type="text" class="form-control" name="shopFounder" id="shopFounder" placeholder="输入归属人..." value="${(shopInfo.shopFounder)!''}">
                </div>
                <div class="form-group">
                    <label id="shopNameLabel">店铺名字</label>
                    <input type="text" class="form-control" name="shopName" id="shopName" placeholder="输入名字..." value="${(shopInfo.shopName)!''}">
                </div>
                <div class="form-group">
                    <label id="shopPhoneLabel">店铺联系号码</label>
                    <input type="number" class="form-control" name="shopPhone" placeholder="输入联系人..." value="${(shopInfo.shopPhone)!''}">
                </div>
                <div class="form-group">
                    <label id="shopDescriptionLabel">店铺描述</label>
                    <input type="text" class="form-control" name="shopDescription" placeholder="输入店铺描述..." value="${(shopInfo.shopDescription)!''}">
                </div>
                <div class="form-group">
                    <label id="shopImgLabel">店铺图片地址</label>
                    <input type="url" class="form-control" name="shopImg" value="${(shopInfo.shopImg)!''} " placeholder="输入店铺图片网址...">
                </div>
                <div class="form-group">
                    <label id="shopLabel">商家openId</label>
                    <input type="text" class="form-control" name="openid" value="${(shopInfo.openid)!''}">
                </div>
                <div class="form-group">
                    <label id="CategoryLabel">店铺归属类目</label>
                    <select name="shopCategoryType" class="form-control">
                                    <#list categoryList as category>
                                        <option value="${category.shopCategoryType}"
                                        <#if (shopInfo.shopCategoryType)?? && shopInfo.shopCategoryType == category.shopCategoryType>
                                                selected
                                        </#if>
                                        >${category.shopCategoryName}
                                        </option>
                                    </#list>
                    </select>
                </div>
                <input hidden name="createBy" value="${(sellerInfo.username)!''}">
                <input hidden name="userId" value="${(sellerInfo.id)!''}">
                <input hidden name="shopType" type="number" value="${(shopInfo.shopType)!''}" >
                <input hidden name="shopId" value="${(shopInfo.shopId)!''}">
                <div class="modal-footer">
                    <div class="pull-right">
                        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
                        <button type="button" class="btn btn-primary btn-sm" onclick="addShop();"><i class="fa fa-save"></i>保存</button>
                    </div>
                </div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
    function addShop (){
        $("span").remove(".errorClass");
        $("br").remove(".errorClass");
        var status = 1;
        if ($("#username").val() == "") {
            $("#userNoLabel").prepend('<span class="errorClass" style="color:red">*账号不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if ($("#password").val() == "") {
            $("#passwordLabel").prepend('<span class="errorClass" style="color:red">*密码不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if ($("#shopFounder").val() == ""){
            $("#shopOwnerLabel").prepend('<span class="errorClass" style="color:red">*店铺归属人不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if ($("#shopName").val() == ""){
            $("#shopNameLable").prepend('<span class="errorClass" style="color:red">*店铺名字不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if ($("#shopPhone").val() == ""){
            $("#shopPhoneLabel").prepend('<span class="errorClass" style="color:red">*店铺联系人不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if ($("#shopDescription").val() == ""){
            $("#shopDescriptionLabel").prepend('<span class="errorClass" style="color:red">*店铺描述不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if (status == 0) {
            return false;
        } else {
            $.ajax({
                url: '/shop/save',
                type: 'post',
                dataType: 'json',
                data: $("#AddShopFrom").serialize(),
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
        }
</script>