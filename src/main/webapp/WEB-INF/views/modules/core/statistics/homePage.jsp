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
				<th style="width: 30%;text-align: left">${map.newMembers}</th>
				<th style="width: 20%;text-align: right">本日新增收入：</th>
				<th style="width: 30%;text-align: left">${map.newBonus}</th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">本月新增会员：</th>
				<th style="width: 30%;text-align: left">${map.monthMembers}</th>
				<th style="width: 20%;text-align: right">本月新增收入：</th>
				<th style="width: 30%;text-align: left">${map.monthBonus}</th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">本年新增会员：</th>
				<th style="width: 30%;text-align: left">${map.yearMembers}</th>
				<th style="width: 20%;text-align: right">本年新增收入：</th>
				<th style="width: 30%;text-align: left">${map.yearBonus}</th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">待激活会员：</th>
				<th style="width: 30%;text-align: left">${map.noMember}&nbsp;&nbsp;<a href="${ctx}/core/member/member/activate">现在处理</a></th>
				<th style="width: 20%;text-align: right">已激活会员：</th>
				<th style="width: 30%;text-align: left">${map.yesMember}</th>
			</tr>
			<tr>
				<th style="width: 20%;text-align: right">待处理订单：</th>
				<th style="width: 30%;text-align: left">${map.orders}&nbsp;&nbsp;<a href="${ctx}/core/orders/orders/deliverGoodsList">现在处理</a></th>
				<th style="width: 20%;text-align: right">待处理充值：</th>
				<th style="width: 30%;text-align: left">${map.recharges}&nbsp;&nbsp;<a href="${ctx}/core/recharge/pvRecharge/confirm">现在处理</a></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>