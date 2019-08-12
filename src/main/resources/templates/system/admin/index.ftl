<!DOCTYPE html>
<html>
<head>
  <#include "../macro/base.ftl"/>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>茂名职业技术学院电商管理平台</title>
  <@style/>
</head>
<body class="sidebar-mini ajax-template skin-blue fixed">
	<div class="wrapper">
		<@header/>
		<@menu/>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header-navtabs">
			<div class="tabs-page">
				<ul class="tabs-list clearfix" id="navTabs">
					<li class="active"><span>我的主页</span></li>
				</ul>
				<a href="javascript:void(0);" class="prev fa fa-step-backward"></a>
				<a href="javascript:void(0);" class="next fa fa-step-forward"></a> 
			</div>
			<!--邮件按钮-->
			<div class="context-menu" id="contextMenu">
				<ul class="ct-nav">
					<li rel="reload">刷新标签页</li>
					<li rel="closeCurrent">关闭标签页</li>
					<li rel="closeOther">关闭其他标签页</li>
					<li rel="closeAll">关闭全部标签页</li>
				</ul>
			</div>
		</section>
		<!-- Main content -->
		<section class="content" id="content">
			<div class="tabs-panel">
				<h1>欢迎使用茂名职业技术学院电商管理平台</h1>
			</div>
		</section>
		</div>
        <div class="col-md-12 column">
            <div class="modal fade" id="Mymodal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">新的申请</h4>
                        </div>
                        <div class="modal-body">有新人想加入啦,请前往电商管理中店铺审核中查看！</div>
                        <div class="modal-footer">
                            <button type="button" onclick="javascript:document.getElementById('notice').pause()"
                                    class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="button" onclick="location.reload()" class="btn btn-primary">知道啦</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<@footer/>
		<@setting/>
	</div>
	<@jsFile/>
</body>
<script>
    var websocket = null;
    if ('WebSocket' in  window){
        websocket = new WebSocket('ws://localhost:8081/mobilesocket');
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
</html>
