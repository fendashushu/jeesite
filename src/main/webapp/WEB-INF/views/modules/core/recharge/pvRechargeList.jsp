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
				<th>会员姓名</th>
				<th>申请金额</th>
				<th>货币类型</th>
				<th>申请时间</th>
				<th>审核状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pvRecharge">
			<tr>
				<td>
					${pvRecharge.loginName}
				</td>
				<td>
					${pvRecharge.name}
				</td>
				<td>
					${pvRecharge.amount}
				</td>
				<td>
					电子币
				</td>
				<td>
					<fmt:formatDate value="${pvRecharge.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
                    <c:choose>
                        <c:when test="${pvRecharge.status=='1'}">
                            已发放
                        </c:when>
                        <c:otherwise>
                            未发放
                        </c:otherwise>
                    </c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>