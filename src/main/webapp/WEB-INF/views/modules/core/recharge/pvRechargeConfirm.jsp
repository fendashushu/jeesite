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
        function checkAll() {
            var check = $("#all").prop('checked');
            var loginNames = "";
            if(check){
                $("#tb :checkbox").prop("checked", true);
                $("#loginNames").val("");
                $("input[name='login']:checkbox:checked").each(function() {
                    var val = $(this).val();
                    loginNames = loginNames + val + ","
                })
                $("#loginNames").val(loginNames);
            }else {
                $("#tb :checkbox").prop("checked", false);
                $("#loginNames").val("");
            }
        }

        function setLoginNames(obj) {
            var check = $(obj).prop("checked");
            var loginNames = $("#loginNames").val();
            if(check){
                loginNames = loginNames + $(obj).val() + ",";
            }
            $("#loginNames").val(loginNames);
            alert($("#loginNames").val())
        }

        function confirming(loginName,id,amount) {
            $.ajaxSetup({async:false})
            if(confirm("是否要确认"+loginName+"的充值申请？")){
                $.post('${ctx}/core/recharge/pvRecharge/confirming',{'loginName':loginName,"id":id,"amount":amount},function (data) {
                    var result = data.result;
                    var msg = data.msg;
                    if(result){
                        alert("确认成功！");
                        window.location.reload();
                    }else{
                        alert(msg);
                    }
                })
            }
        }

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>充值列表</a></li>
	</ul>
    <input type="hidden" name="loginNames" id="loginNames" value="" >
	<form:form id="searchForm" modelAttribute="pvRecharge" action="${ctx}/core/recharge/pvRecharge/confirm" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员编号：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>会员姓名：</label>
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
                <%--<th><input type="checkbox" id="all" onclick="checkAll()"> </th>--%>
				<th>会员编号</th>
				<th>会员姓名</th>
				<th>申请金额</th>
				<th>货币类型</th>
				<th>申请时间</th>
				<th>审核状态</th>
                    <shiro:hasPermission name="core:recharge:pvRecharge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="tb">
		<c:forEach items="${page.list}" var="pvRecharge">
			<tr>
                <%--<td><input type="checkbox" id="" value="${pvRecharge.loginName}" name="login" onclick="setLoginNames(this)"></td>--%>
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
                    <shiro:hasPermission name="core:recharge:pvRecharge:edit"><td>
                        <a href="" onclick="confirming('${pvRecharge.loginName}','${pvRecharge.id}',${pvRecharge.amount})">确认</a>
                    </td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>