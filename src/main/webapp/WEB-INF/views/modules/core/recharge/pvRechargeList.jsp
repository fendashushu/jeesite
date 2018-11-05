<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>充值管理</title>
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
		<li class="active"><a href="${ctx}/core/recharge/pvRecharge/">充值列表</a></li>
		<shiro:hasPermission name="core:recharge:pvRecharge:edit"><li><a href="${ctx}/core/recharge/pvRecharge/form">充值添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pvRecharge" action="${ctx}/core/recharge/pvRecharge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员编号</th>
				<th>会员</th>
				<th>金额</th>
				<th>货币类型</th>
				<th>发放状态;0:未发放；1：已发放</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="core:recharge:pvRecharge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pvRecharge">
			<tr>
				<td><a href="${ctx}/core/recharge/pvRecharge/form?id=${pvRecharge.id}">
					${pvRecharge.loginName}
				</a></td>
				<td>
					${pvRecharge.name}
				</td>
				<td>
					${pvRecharge.amount}
				</td>
				<td>
					${pvRecharge.amountType}
				</td>
				<td>
					${pvRecharge.status}
				</td>
				<td>
					<fmt:formatDate value="${pvRecharge.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pvRecharge.remarks}
				</td>
				<shiro:hasPermission name="core:recharge:pvRecharge:edit"><td>
    				<a href="${ctx}/core/recharge/pvRecharge/form?id=${pvRecharge.id}">修改</a>
					<a href="${ctx}/core/recharge/pvRecharge/delete?id=${pvRecharge.id}" onclick="return confirmx('确认要删除该充值吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>