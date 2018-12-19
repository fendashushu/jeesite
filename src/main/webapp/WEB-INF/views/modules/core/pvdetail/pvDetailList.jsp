<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>积分详情管理</title>
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
		<li class="active"><a href="${ctx}/core/pvdetail/pvDetail/">积分详情列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pvDetail" action="${ctx}/core/pvdetail/pvDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%--<ul class="ul-form">
            <li><label>会员编号：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>--%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日期</th>
				<th>直推奖</th>
				<th>代扣税</th>
                <th>可提现金额</th>
				<th>合作奖</th>
                <th>代扣税</th>
                <th>可提现金额</th>
				<th>管理奖</th>
                <th>代扣税</th>
                <th>可提现金额</th>
                <th>奖金总额</th>
				<shiro:hasPermission name="core:pvdetail:pvDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pvDetail">
			<tr>
				<td>
					<fmt:formatDate value="${pvDetail.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pvDetail.pvTotal1}
				</td>
				<td>
					${pvDetail.pvDues1}
				</td>
				<td>
					${pvDetail.pvSheng1}
				</td>
				<td>
					${pvDetail.pvTotal2}
				</td>
				<td>
					${pvDetail.pvDues2}
				</td>
				<td>
					${pvDetail.pvSheng2}
				</td>
				<td>
					${pvDetail.pvTotal3}
				</td>
				<td>
					${pvDetail.pvDues3}
				</td>
				<td>
					${pvDetail.pvSheng3}
				</td>
				<td>
					${pvDetail.pvZong}
				</td>

                <shiro:hasPermission name="sys:user:edit">
                <td>
                    <a href="${ctx}/core/pvdetail/pvDetail/detail?createDate=<fmt:formatDate value="${pvDetail.createDate}" pattern="yyyy-MM-dd"/>">查看</a>
                </td>
                </shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>