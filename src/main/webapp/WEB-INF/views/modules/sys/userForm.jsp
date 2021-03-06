<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
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

		function checkUser(id) {
            var userName = $("#"+id).val();
            $.post("${ctx}/sys/user/checkUser",{"userName":userName},function(data){
                if(data != null && data != ''){
                    alert("验证通过!\r\n编号："+data.loginName+"\r\n姓名："+data.name)
                }else{
                    alert("会员不存在，请重新输入！")
                    $("#"+id).val("");
                    $("#"+id).focus();

                }
            })
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%--<li><a href="${ctx}/sys/user/list">用户列表</a></li>--%>
		<li class="active"><a href="${ctx}/sys/user/form?id=${user.id}">会员<shiro:hasPermission name="sys:user:edit">${not empty user.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
        <div class="control-group">
            <label class="control-label">服务中心编号:</label>
            <div class="controls">
                <form:input path="store" htmlEscape="false" maxlength="50" class="required"/>
                <span class="help-inline"><font color="red">*</font> </span>
                <input  class="btn btn-primary" type="button" value="验 证" onclick="checkUser('store')"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">推荐人编号:</label>
            <div class="controls">
                <form:input path="referee" htmlEscape="false" maxlength="50" class="required"/>
                <span class="help-inline"><font color="red">*</font> </span>
                <input  class="btn btn-primary" type="button" value="验 证" onclick="checkUser('referee')"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">接点人编号:</label>
            <div class="controls">
                <form:input path="contact" htmlEscape="false" maxlength="50" class="required"/>
                <span class="help-inline"><font color="red">*</font> </span>
                <input  class="btn btn-primary" type="button" value="验 证" onclick="checkUser('contact')"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">安置区域:</label>
            <div class="controls">

                <form:select path="area" class="input-xlarge" disabled="false">
                    <form:option value="${area}" label="${area}区"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">加盟资格:</label>
            <div class="controls">
                <form:select path="memberlevel" class="input-xlarge">
                   <%-- <form:option value="0" label="会员[￥500]"/>--%>
                    <form:option value="1" label="一级[￥1200]"/>
                    <form:option value="2" label="二级[￥3600]"/>
                    <form:option value="3" label="三级[￥7200]"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

<%--
		<div class="control-group">
			<label class="control-label">头像:</label>
			<div class="controls">
				<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		--%>

		<div class="control-group">
			<label class="control-label">会员编号:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
				<form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value="123456" maxlength="50" minlength="3"  class="${empty user.id?'required':''}"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="123456" maxlength="50" minlength="3" equalTo="#newPassword"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">二级密码:</label>
			<div class="controls">
				<input id="newPassword2" name="newPassword2" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认二级密码:</label>
			<div class="controls">
				<input id="confirmNewPassword2" name="confirmNewPassword2" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword2"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">身份证号:</label>
			<div class="controls">
				<form:input path="idcard" htmlEscape="false" maxlength="100" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别:</label>
			<div class="controls">
                <form:radiobuttons path="sex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货地址:</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="100" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电子邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="100" class="required"/><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">QQ号码:</label>
			<div class="controls">
				<form:input path="qq" htmlEscape="false" maxlength="100"/>
			</div>
		</div>

        <div class="control-group">
            <label class="control-label">开户银行:</label>
            <div class="controls">
                <form:select path="bank">
                    <form:options items="${fns:getDictList('bank')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">开户账号:</label>
            <div class="controls">
                <form:input path="bankno" htmlEscape="false" maxlength="100" class="required"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">开户姓名:</label>
            <div class="controls">
                <form:input path="bankname" htmlEscape="false" maxlength="100" class="required"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>


		<div class="control-group" style="display: none;">
			<label class="control-label">是否允许登录:</label>
			<div class="controls">
				<form:select path="loginFlag">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span>
			</div>
		</div>
		<div class="control-group" style="display: none">
			<label class="control-label">用户类型:</label>
			<div class="controls">
                <input name="userType" value="1">
			</div>
		</div>
		<div class="control-group" style="display: none">
			<label class="control-label">用户角色:</label>
			<div class="controls">
                <span><input id="roleIdList1" name="roleIdList" class="required" type="checkbox" checked="checked" value="3"><label for="roleIdList1">会员</label></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<%--<c:if test="${not empty user.id}">
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">最后登陆:</label>
				<div class="controls">
					<label class="lbl">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
		</c:if>--%>
		<div class="form-actions">
			<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>