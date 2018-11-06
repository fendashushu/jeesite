<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>积分转账管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();

			$("#inputForm").validate({
				submitHandler: function(form){
                    if(!checkNum()){
                        return;
                    }
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

        function checkNum(obj) {
            var val = $("#amount").val();
            if(isNaN(val)){
                alert("请输入数字！");
                $("#amount").focus();
                $("#amount").val("");
                return false;
            }
            return true;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/core/transfer/transfer/">积分转账列表</a></li>
		<li class="active"><a href="${ctx}/core/transfer/transfer/form?id=${transfer.id}">积分转账<shiro:hasPermission name="core:transfer:transfer:edit">${not empty transfer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="core:transfer:transfer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="transfer" action="${ctx}/core/transfer/transfer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">奖金：</label>
			<div class="controls">
                <label>${bonus}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转入会员编号：</label>
			<div class="controls">
				<form:input path="toLoginName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
                <input  class="btn btn-primary" type="button" value="验 证" onclick="checkUser('toLoginName')"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">货币类型：</label>
			<div class="controls">
				<form:input path="amountType" htmlEscape="false"  maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="core:transfer:transfer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>