<html>
    <#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
        <#include "../common/nav.ftl">
<#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/seller/product/save" enctype="multipart/form-data">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control"
                                   value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label style="color: crimson">价格</label>
                            <input name="productPrice" type="number" class="form-control" min="0.00" step="0.01"
                                   value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="number" class="form-control"
                                   value="${(productInfo.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control"
                                   value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label style="color: crimson">折扣(示例)5折就写5</label>
                            <input name="productDiscount" type="number" class="form-control" min="0.00" step="0.01"
                                   value="${(productInfo.productDiscount)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">
                            <input name="productIcon" type="text" class="form-control"
                                   value="${(productInfo.productIcon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="img_input" style="color: crimson">上传图片(新增商品的时)</label>
                            <input type="file" id="img_input" name="file" accept="image/*">
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="CategoryType" class="form-control">
                                    <#list productCategory as category>
                                        <option value="${category.categoryType}"
                                        <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                selected
                                        </#if>
                                        >${category.categoryName}
                                        </option>
                                    </#list>
                            </select>
                        </div>
                        <input hidden name="productId" value="${(productInfo.productId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                    <script type="text/javascript">

                        $("#img_input").on("change", function (e) {
                            var file = e.target.files[0]; //获取图片资源
                            // 只选择图片文件
                            if (!file.type.match('image.*')) {
                                return false;
                            }
                            var reader = new FileReader();
                            reader.readAsDataURL(file); // 读取文件
                            // 渲染文件
                            reader.onload = function (arg) {

                                var img = '<img class="preview" src="' + arg.target.result + '" alt="preview"/>';
                                $(".preview_box").empty().append(img);
                            }
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
</html>