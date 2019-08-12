<html>
<#include "../common/head.ftl">
<body>
<div id = wrapper class="toggled">
    <#include "../common/nav.ftl">
    <div id = page-content-wrapper>
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>下单时间</th>
                            <th>订单状态</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.getOrderId()}</td>
                            <td>${orderDTO.getCreateTime()}</td>
                            <td>${orderDTO.PayStatusEnum().getMessage()}</td>
                            <td>${orderDTO.getOrderAmount()}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            <#--订单详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>商品ID</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>折扣</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTO.orderDetailList as orderDetail>
                <tr>
                    <td>${orderDetail.productId}</td>
                    <td>${orderDetail.productName}</td>
                    <td>${orderDetail.productPrice}</td>
                    <td>${orderDetail.productQuantity}</td>
                    <td>${(orderDetail.productDiscount)!"无"}</td>
                    <td>${orderDetail.productQuantity * orderDetail.productPrice * orderDetail.productDiscount!1}</td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
            <#--操作-->
                <div class="col-md-12 column">
            <#if orderDTO.OrderMasterStatusEnum().message == "新订单">
            <a href="/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
            <a href="/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
            </#if>
            <#if orderDTO.OrderMasterStatusEnum().message == "已取消">
                <a href="/seller/order/delete?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">删除订单</a>
            </#if>
                    <a href="/seller/order/shoplist" type="button" class="btn btn-default btn-primary pull-right">返回</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>