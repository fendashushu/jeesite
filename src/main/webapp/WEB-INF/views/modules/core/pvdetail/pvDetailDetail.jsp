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
	<form:form id="searchForm" modelAttribute="pvDetail" action="${ctx}/core/pvdetail/pvDetail/detail" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="createDate" name="createDate" type="hidden" value="${createDate}"/>
		<%--<ul class="ul-form">
            <li><label>会员编号：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                <input class="btn btn-primary" type="button" onclick="history.go(-1)" value="返回"/>
            </li>
			<li class="clearfix"></li>
		</ul>--%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日期</th>
				<th>会员编号</th>
				<th>奖金</th>
				<th>互助基金</th>
                <th>可提现金额</th>
                <th>备注</th>
                <th>相关会员</th>
                <c:if test="${flag != 'detail'}">
                    <th>奖金明细</th>
                </c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pvDetail">
			<tr>
				<td>
					<fmt:formatDate value="${pvDetail.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pvDetail.loginName}
				</td>
				<td>
					${pvDetail.pvTotal}
				</td>
				<td>
					${pvDetail.pvDues}
				</td>
				<td>
					${pvDetail.pvSheng}
				</td>
				<td>
					${pvDetail.note}
				</td>
				<td>
					${pvDetail.zhuceName}
				</td>
                <c:if test="${flag != 'detail'}">
                <td>
                    <a href="${ctx}/core/pvdetail/pvDetail/detail?name=${pvDetail.loginName}&flag=detail">查看</a>
                </td>
                </c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>