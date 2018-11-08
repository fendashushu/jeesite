<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">申请服务中心</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="member" action="${ctx}/core/member/member/storing" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
        <c:choose>
            <c:when test="${member.isstore=='1'}">
                <div id="messageBox" class="alert alert-success hide" style="display: block;">您申请的服务中心已通过管理员审核,您的服务中心编号为：${member.loginName}。</div>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${member.memberlevel != '3'}">
                        <div id="messageBox" class="alert alert-success hide" style="display: block;">只有三级代理才能申请服务中心，请先升级。</div>
                    </c:when>
                    <c:otherwise>
                        <div id="messageBox" class="alert alert-success hide" style="display: block;">当前可用积分为${bonusTotal.bonusCurrent}。</div>
                        <div class="control-group">
                            <label class="control-label">申请会员编码：</label>
                            <div class="controls">
                                <form:input path="loginName" htmlEscape="false" maxlength="64" class="input-xlarge required" readonly="true"/>
                                <span class="help-inline"><font color="red">*</font> </span>
                            </div>
                        </div>
                        <div class="form-actions">
                            <shiro:hasPermission name="core:member:member:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
                            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>

	</form:form>
</body>
</html>