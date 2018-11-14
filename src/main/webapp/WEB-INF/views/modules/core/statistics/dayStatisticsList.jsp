<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统计管理</title>
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
		<li class="active"><a href="${ctx}/core/statistics/dayStatistics/">统计列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="dayStatistics" action="${ctx}/core/statistics/dayStatistics/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
            <li><label>开始日期：</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
                                           value="<fmt:formatDate value="${dayStatistics.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
            <li><label>结束日期：</label><input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
                                           value="<fmt:formatDate value="${dayStatistics.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>统计日期</th>
				<th>新增会员数</th>
				<th>会员总数</th>
				<th>新增业绩</th>
				<th>业绩总数</th>
				<th>奖金发放</th>
				<th>奖金发放总数</th>
				<th>拨比</th>
				<th>总波比</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dayStatistics">
			<tr>
				<td>
					<fmt:formatDate value="${dayStatistics.dataDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${dayStatistics.newMembers}
				</td>
				<td>
					${dayStatistics.allMembers}
				</td>
				<td>
					${dayStatistics.newBonus}
				</td>
				<td>
					${dayStatistics.allBonus}
				</td>
				<td>
					${dayStatistics.outBonus}
				</td>
				<td>
					${dayStatistics.outAllBonus}
				</td>
				<td>
					${dayStatistics.bobi}%
				</td>
				<td>
					${dayStatistics.allBobi}%
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>