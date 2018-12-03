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
				<th style="width: 20%;text-align: right">本日新增会员：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null || map.newMembers==null }">0</c:when>
                        <c:otherwise>${map.newMembers}</c:otherwise>
                    </c:choose>
                </th>
				<th style="width: 20%;text-align: right">本日新增收入：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null|| map.newBonus==null }">0</c:when>
                        <c:otherwise>${map.newBonus}</c:otherwise>
                    </c:choose>
                </th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">本月新增会员：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null|| map.monthMembers==null }">0</c:when>
                        <c:otherwise>${map.monthMembers}</c:otherwise>
                    </c:choose>
                </th>
				<th style="width: 20%;text-align: right">本月新增收入：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null|| map.monthBonus==null }">0</c:when>
                        <c:otherwise>${map.monthBonus}</c:otherwise>
                    </c:choose>
                </th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">本年新增会员：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null|| map.yearMembers==null }">0</c:when>
                        <c:otherwise>${map.yearMembers}</c:otherwise>
                    </c:choose>
                </th>
				<th style="width: 20%;text-align: right">本年新增收入：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null|| map.yearBonus==null }">0</c:when>
                        <c:otherwise>${map.yearBonus}</c:otherwise>
                    </c:choose>
                </th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">待激活会员：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null|| map.noMember==null }">0</c:when>
                        <c:otherwise>${map.noMember}</c:otherwise>
                    </c:choose>
                    &nbsp;&nbsp;<a href="${ctx}/core/member/member/activate">现在处理</a></th>
				<th style="width: 20%;text-align: right">已激活会员：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null|| map.yesMember==null }">0</c:when>
                        <c:otherwise>${map.yesMember}</c:otherwise>
                    </c:choose>
                </th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">待处理订单：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null|| map.orders==null }">0</c:when>
                        <c:otherwise>${map.orders}</c:otherwise>
                    </c:choose>
                    &nbsp;&nbsp;<a href="${ctx}/core/orders/orders/deliverGoodsList">现在处理</a></th>
				<th style="width: 20%;text-align: right">待处理充值：</th>
				<th style="width: 30%;text-align: left">
                    <c:choose>
                        <c:when test="${map == null|| map.recharges==null }">0</c:when>
                        <c:otherwise>${map.recharges}</c:otherwise>
                    </c:choose>
                    &nbsp;&nbsp;<a href="${ctx}/core/recharge/pvRecharge/confirm">现在处理</a></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>