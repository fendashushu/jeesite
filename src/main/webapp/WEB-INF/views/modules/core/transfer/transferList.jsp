<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>积分转账管理</title>
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
		<li class="active"><a href="${ctx}/core/transfer/transfer/">积分转账记录</a></li>
		<shiro:hasPermission name="core:transfer:transfer:edit"><li><a href="${ctx}/core/transfer/transfer/form">积分转账</a></li></shiro:hasPermission>
	</ul>
	<%--<form:form id="searchForm" modelAttribute="transfer" action="${ctx}/core/transfer/transfer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>--%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>转出会员</th>
				<th>转出会员姓名</th>
                <th>转入会员</th>
				<th>转入会员姓名</th>
				<th>转账金额</th>
				<th>转账时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="transfer">
			<tr>
				<td>
					${transfer.loginName}
				</td>
				<td>
					${transfer.name}
				</td>
				<td>
					${transfer.toLoginName}
				</td>
				<td>
					${transfer.toName}
				</td>
				<td>
					${transfer.amount}
				</td>
				<td>
					<fmt:formatDate value="${transfer.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>