<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orders" action="${ctx}/core/orders/orders/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="orderId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>商品名称</th>
				<th>物流公司</th>
				<th>物流编号</th>
				<th>购买人</th>
				<th>联系电话</th>
				<th>收货地址</th>
				<th>订单时间</th>
				<th>发货时间</th>
				<th>状态</th>
				<shiro:hasPermission name="core:orders:orders:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orders">
			<tr>
				<%--<td><a href="${ctx}/core/orders/orders/form?id=${orders.id}">
					${orders.orderId}
				</a></td>--%>
				<td>
                    ${orders.orderId}
				</td>
				<td>
					${orders.goodsName}
				</td>
				<td>
					${orders.expressCompany}
				</td>
				<td>
					${orders.expressNum}
				</td>
				<td>
					${orders.loginName}
				</td>
				<td>
					${orders.takePhone}
				</td>
				<td>
					${orders.takeAddress}
				</td>
				<td>
					<fmt:formatDate value="${orders.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${orders.expressDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:choose>
                        <c:when test="${orders.status=='0'}">未发货</c:when>
                        <c:when test="${orders.status=='1'}">已发货</c:when>
                    </c:choose>
				</td>
				<shiro:hasPermission name="core:orders:orders:edit"><td>
                    <c:choose>
                        <c:when test="${orders.status=='0'}">
                            <a href="${ctx}/core/orders/orders/form?id=${orders.id}">发货</a>
                        </c:when>
                    </c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>