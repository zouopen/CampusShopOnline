<html>
<#--头部-->
    <#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
        <#include "../common/nav.ftl">
<#--主要内容    -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                    <#list shopList.content as orderDTO>

                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.OrderMasterStatusEnum().getMessage()}</td>
                        <td>${orderDTO.PayStatusEnum().message}</td>
                        <td>${orderDTO.createTime}</td>
                        <td><a href="/seller/order/shopdetail?orderId=${orderDTO.orderId}">详情</a></td>
                        <td>
                            <#if orderDTO.OrderMasterStatusEnum().getMessage() != "已取消" && orderDTO.OrderMasterStatusEnum().message != "完结">
                                <a href="/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                </div>
            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if curettage lte 1 >
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else >
                        <li><a href="/seller/order/shoplist?page=${curettage - 1}&size=10">上一页</a></li>
                    </#if>

                    <#list 1..shopList.getTotalPages() as index>
                        <#if curettage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else >
                            <li><a href="/seller/order/shoplist?page=${index}">${index}</a></li>
                        </#if>
                    </#list>
                    <#if curettage gte shopList.getTotalPages() >
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/seller/order/shoplist?page=${curettage + 1}&size=${size}">下一页</a></li>
                    </#if>

                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12 column">
        <div class="modal fade" id="Mymodal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">新订单</h4>
                    </div>
                    <div class="modal-body">官人,你有新订单了</div>
                    <div class="modal-footer">
                        <button type="button" onclick="javascript:document.getElementById('notice').pause()"
                                class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" onclick="location.reload()" class="btn btn-primary">查看新的订单</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
</div>
<script>
    var websocket = null;
    if ('WebSocket' in  window){
        websocket = new WebSocket('ws://localhost:8081/webSocket');
    }else{
        alert('该浏览器不支持WebSocket');
    }
    websocket.onopen = function (event) {
        console.log('建立连接')
    };
    websocket.onclose = function (ev) {
        console.log('连接关闭')
    };
    websocket.onmessage = function (ev) {
        console.log('收到消息:' + ev.data);
         $('#Mymodal').modal('show');
        // websocket.send()
    };
    websocket.onerror = function (ev) {
        alert('WebSocket通信错误')
    };
    window.onbeforeunload = function (ev) {
        websocket.close();
    }
</script>
</body>
</html>