<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>积分汇总管理</title>
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
		<li class="active"><a href="${ctx}/core/pv/pvTotal/">积分汇总列表</a></li>
		<shiro:hasPermission name="core:pv:pvTotal:edit"><li><a href="${ctx}/core/pv/pvTotal/form">积分汇总添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pvTotal" action="${ctx}/core/pv/pvTotal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="core:pv:pvTotal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pvTotal">
			<tr>
				<td><a href="${ctx}/core/pv/pvTotal/form?id=${pvTotal.id}">
					<fmt:formatDate value="${pvTotal.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${pvTotal.remarks}
				</td>
				<shiro:hasPermission name="core:pv:pvTotal:edit"><td>
    				<a href="${ctx}/core/pv/pvTotal/form?id=${pvTotal.id}">修改</a>
					<a href="${ctx}/core/pv/pvTotal/delete?id=${pvTotal.id}" onclick="return confirmx('确认要删除该积分汇总吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>