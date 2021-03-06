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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a >首页</a></li>
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 20%;text-align: right">会员名称：</th>
				<th style="width: 30%;text-align: left">${member.loginName}</th>
				<th style="width: 20%;text-align: right">会员姓名：</th>
				<th style="width: 30%;text-align: left">${member.name}</th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">会员等级：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${member.memberlevel=='0'}">会员</c:when>
                        <c:when test="${member.memberlevel=='1'}">一级</c:when>
                        <c:when test="${member.memberlevel=='2'}">二级</c:when>
                        <c:when test="${member.memberlevel=='3'}">三级</c:when>
                    </c:choose>
                </th>
				<th style="width: 20%;text-align: right">服务中心：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${member.isstore=='0'}">否</c:when>
                        <c:when test="${member.isstore=='1'}">是</c:when>
                    </c:choose>
                </th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">奖金累计：</th>
				<th style="width: 30%;text-align: left">${bonusTotal.bonusTotal}</th>
				<th style="width: 20%;text-align: right">奖金总额：</th>
				<th style="width: 30%;text-align: left">${bonusTotal.bonusCurrent}</th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">转账支出：</th>
				<th style="width: 30%;text-align: left">${map.outAmount}</th>
				<th style="width: 20%;text-align: right">转账收入：</th>
				<th style="width: 30%;text-align: left">${map.inAmount}</th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">本周奖金：</th>
				<th style="width: 30%;text-align: left">${zhouBonus.bonus}</th>
				<th style="width: 20%;text-align: right">上周奖金：</th>
				<th style="width: 30%;text-align: left">
                    ${empty preZhouBonus ? "0.00" : preZhouBonus.bonus}
                    <%--<c:choose>
                        <c:when test=" ${preZhouBonus is null}">0</c:when>
                        <c:otherwise>${preZhouBonus.bonus}</c:otherwise>
                    </c:choose>--%>
                </th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>