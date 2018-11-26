<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function submitForm() {
            loading('正在提交，请稍等...');
            //form.submit();
            $.post('${ctx}/core/orders/orders/deliver',$("#inputForm").serialize(),function(data){
                if(data.result){
                    closeLoading();
                    alert(data.msg);
                    window.parent.window.location.href="${ctx}/core/orders/orders/deliverGoodsList";
                    window.parent.window.jBox.close();
                }else{
                    closeLoading();
                    alert(data.msg);
                }
            })
        }
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="orders" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">物流公司：</label>
			<div class="controls">
				<form:input path="expressCompany" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物流单号：</label>
			<div class="controls">
				<form:input path="expressNum" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="core:orders:orders:edit"><input id="btnSubmit" class="btn btn-primary" onclick="submitForm()" type="button" value="确 认"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>