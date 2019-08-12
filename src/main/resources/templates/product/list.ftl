<html>
    <#include "../common/head.ftl">
    <body>
    <div id = "wrapper" class="toggled">
        <#--边栏-->
        <#include "../common/nav.ftl">
        <#--主要内容-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>商品ID</th>
                                <th>名称</th>
                                <th>图片</th>
                                <th>单价</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>折扣</th>
                                <th>类目</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="3">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                    <#list productInfo.content as ProductInfo>
                    <tr>
                        <td>${ProductInfo.productId}<br></td>
                        <td>${ProductInfo.productName}</td>
                        <td><img height="100" width="100" src="${ProductInfo.productIcon!''}" alt=""></td>
                        <td>${ProductInfo.productPrice}</td>
                        <td>${ProductInfo.productStock}</td>
                        <td>${ProductInfo.productDescription}</td>
                        <td>
                            <#if ProductInfo.productDiscount??>
                                ${ProductInfo.productDiscount * 10} 折
                                <#else >
                                    ${"无"}
                            </#if>
                        </td>
                        <td>${ProductInfo.categoryType}</td>
                        <td>${ProductInfo.createTime}</td>
                        <td>${ProductInfo.updateTime}</td>
                        <td>
                            <a href="/seller/product/index?productId=${ProductInfo.productId}">修改</a>
                        </td>
                        <#if ProductInfo.productStatusEnum().getMessage() == "上架">
                            <td><a href="/seller/product/off_sale?productId=${ProductInfo.productId}">下架</a></td>
                        <#else >
                            <td><a href="/seller/product/on_sale?productId=${ProductInfo.productId}">上架</a></td>
                        </#if>
                    </tr>
                    </#list>
                            </tbody>
                        </table>


                    </div>
                <#--分页-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/seller/product/list?page=${currentPage - 1}&size=${size}">上一页</a><li>
                    </#if>
                    <#list 1..productInfo.getTotalPages() as index>
                        <#if currentPage == index>
                        <li class="disabled"><a href="#">${index}</a><li>
                        <#else >
                        <li><a href="/seller/product/list?page=${index}&size=${size}">${index}</a><li>
                        </#if>
                    </#list>
                    <#if currentPage gte productInfo.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/seller/product/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>