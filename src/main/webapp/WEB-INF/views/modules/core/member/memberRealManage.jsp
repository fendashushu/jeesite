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
        function lockOrUnlock(loginName,status) {
            $.post("${ctx}/core/member/member/lockOrUnlock",{"loginName":loginName,"status":status},function (data) {
                var result = data.result;
                var msg = data.msg;
                if(result){
                    alert(msg);
                    window.location.reload();
                }else{
                    alert(msg);
                }
            })
        }

        function resetPassword(loginName) {
            if(confirm("是否要重置"+loginName+"的密码？")){
                $.post("${ctx}/core/member/member/resetPassword",{"loginName":loginName},function (data) {
                    var result = data.result;
                    var msg = data.msg;
                    if(result){
                        alert(msg);
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
		<li class="active"><a>会员列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="member"
               action="${ctx}/core/member/member/realMemberManager"
               method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <c:if test="${my == true}">
            <input id="name" name="name" type="hidden" value="${name}"/>
        </c:if>
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
				<th>会员级别</th>
				<th>联系电话</th>
				<th>身份证号</th>
				<th>推荐人</th>
				<th>接点人</th>
				<th>服务中心</th>
				<th>累计奖金</th>
				<th>剩余奖金</th>
				<th>报单时间</th>
				<th>确认时间</th>
                <c:if test="${my == false}">
				<th>操作</th>
                </c:if>
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
                        ${member.mobile}
                </td>
                <td>
                        ${member.idcard}
                </td>
                <td>
                    <c:choose>
                        <c:when test="${member.referee=='0'}">顶层会员</c:when>
                        <c:otherwise>${member.referee}</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${member.contact=='0'}">顶层会员</c:when>
                        <c:otherwise>${member.contact}</c:otherwise>
                    </c:choose>
                </td>
                <td>
                        ${member.store}
                </td>
                <td>
                        ${member.bonusTotal}
                </td>
                <td>
                        ${member.bonusCurrent}
                </td>
				<td>
					<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
                    <fmt:formatDate value="${member.activateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
                <c:if test="${my == false}">
                <td>
                    <a href="javaScript:void(0)" onclick="lockOrUnlock('${member.loginName}','${member.status}')">
                    <c:choose>
                        <c:when test="${member.status=='0'}">
                            解锁
                        </c:when>
                        <c:when test="${member.status=='1'}">
                            锁定
                        </c:when>
                    </c:choose>
                    </a>
                    <a href="${ctx}/core/member/member/realMemberManager?name=${member.loginName}">我的推荐</a>
                    <a href="javaScript:void(0)" onclick="resetPassword('${member.loginName}')">重置密码</a>
                </td>
                </c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>