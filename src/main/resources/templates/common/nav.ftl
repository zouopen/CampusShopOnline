<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
            ${shopInfo.shopName}
            </a>
        </li>
        <li>
            <a href="/seller/order/shoplist"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/seller/product/list">列表</a></li>
                <li><a href="/seller/product/index">新增</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/seller/category/list">列表</a></li>
                <li><a href="/seller/category/index">新增</a></li>
            </ul>
        </li>

        <li>
            <#if shopInfo.shopStatus == 0>
                <a href="/close"><i class="fa fa-fw fa-list-alt"></i>打烊</a>
            <#else >
                <a href="/open"><i class="fa fa-fw fa-list-alt"></i>开张</a>
            </#if>
        </li>
        <li>
            <a href="/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>