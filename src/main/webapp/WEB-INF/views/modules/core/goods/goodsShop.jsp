<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

        function publish(id) {
            $.post("${ctx}/core/goods/goods/publish",{"id":id},function(data){
                var result = data.result;
                var msg = data.msg;
                if(result){
                    alert(msg);
                    window.location.reload();
                }else{
                    alert(msg);
                    return;
                }
            })
        }

        function makeOrder(id){
		    jBox.open("iframe:${ctx}/core/goods/goods/orderDetail?id="+id,"订单信息",800,500,{iframeScrolling: 'no',buttons: false});
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/core/goods/goods/">商品列表</a></li>
		<shiro:hasPermission name="core:goods:goods:edit"><li><a href="${ctx}/core/goods/goods/form">商品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="goods" action="${ctx}/core/goods/goods/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
            <li><label>商品名称：</label>
                <form:input path="goodsName" htmlEscape="false" maxlength="100" class="input-medium"/>
            </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品名称</th>
				<th>商品图片</th>
				<th>商品价格</th>
				<th>商品库存</th>
				<th>购买数量</th>
				<shiro:hasPermission name="core:goods:goods:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="goods">
			<tr>
				<td><a href="${ctx}/core/goods/goods/form?id=${goods.id}">
					${goods.goodsName}
				</a></td>
				<td>
                    <img src="${goods.image}" width="100" height="100">
				</td>
				<td>
					${goods.price}
				</td>
				<td>
					${goods.inventory}
				</td>
				<td>
					${goods.inventory}
				</td>
				<shiro:hasPermission name="core:goods:goods:edit"><td>
    				<a href="javaScript:void(0)" onclick="makeOrder('${goods.id}')">购买</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>