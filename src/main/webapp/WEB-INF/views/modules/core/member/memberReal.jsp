<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
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
		<li class="active">会员列表</li>
	</ul>
	<form:form id="searchForm" modelAttribute="member" action="${ctx}/core/member/member/realMember" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
            <li><label>会员编号：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
            <li><label>推荐人：</label><form:input path="referee" htmlEscape="false" maxlength="50" class="input-medium"/></li>
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
				<th>会员资格</th>
				<th>推荐人</th>
				<th>联系电话</th>
				<th>QQ号码</th>
				<th>服务中心</th>
				<th>报单时间</th>
				<th>确认时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member">
			<tr>
                <td>
                        ${member.loginName}
                </td>
                <td>
                        ${member.name}
                </td>
                <td>
                        <c:choose>
                            <c:when test="${member.memberlevel=='0'}">会员</c:when>
                            <c:when test="${member.memberlevel=='1'}">一级</c:when>
                            <c:when test="${member.memberlevel=='2'}">二级</c:when>
                            <c:when test="${member.memberlevel=='3'}">三级</c:when>
                        </c:choose>
                </td>
                <td>
                        ${member.referee}
                </td>
                <td>
                        ${member.mobile}
                </td>
                <td>
                        ${member.qq}
                </td>
                <td>
                        ${member.store}
                </td>
				<td>
					<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
                    <fmt:formatDate value="${member.activateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>