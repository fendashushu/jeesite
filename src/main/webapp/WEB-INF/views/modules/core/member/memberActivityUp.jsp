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
        function upMember(loginName,level,text) {
            $.ajaxSetup({async:false})
		    if(confirm("是否要升级"+loginName+"为"+text+"级会员？")){
                $.post('${ctx}/core/member/member/activityUping',{'loginName':loginName,'level':level},function (data) {
                    var result = data.result;
                    var msg = data.msg;
                    if(result){
                        alert("升级成功！");
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
		<li class="active"><a>会员列表</a></li>
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
	<div id="messageBox" class="alert alert-success hide" style="display: block;">${msg}</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员编号</th>
				<th>会员姓名</th>
				<th>会员资格</th>
				<th>推荐人</th>
				<th>联系电话</th>
				<th>报单时间</th>
				<shiro:hasPermission name="core:member:member:edit"><th>升级</th></shiro:hasPermission>
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
					<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="core:member:member:edit"><td>
                    升级为&nbsp;&nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${member.memberlevel=='0'}">
                            <a href="" onclick="upMember('${member.loginName}','1','一')">一级</a>&nbsp;&nbsp;&nbsp;
                            <a href="" onclick="upMember('${member.loginName}','2','二')">二级</a>&nbsp;&nbsp;&nbsp;
                            <a href="" onclick="upMember('${member.loginName}','3','三')">三级</a>
                        </c:when>
                        <c:when test="${member.memberlevel=='1'}">
                            <a href="" onclick="upMember('${member.loginName}','2','二')">二级</a>&nbsp;&nbsp;&nbsp;
                            <a href="" onclick="upMember('${member.loginName}','3','三')">三级</a>
                        </c:when>
                        <c:when test="${member.memberlevel=='2'}">
                            <a href="" onclick="upMember('${member.loginName}','3','三')">三级</a>
                        </c:when>
                    </c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>