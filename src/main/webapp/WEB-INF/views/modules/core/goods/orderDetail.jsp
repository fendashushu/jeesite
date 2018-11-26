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
            $.post('${ctx}/core/orders/orders/buy',$("#inputForm").serialize(),function(data){
                if(data.result){
                    closeLoading();
                    alert(data.msg);
                    window.parent.window.location.href="${ctx}/core/orders/orders/myOrders";
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
	<form:form id="inputForm" modelAttribute="orders"  method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">购买数量：</label>
            <input type="hidden" value="${goods.id}" name="goodsId" id="goodsId" />
            <input type="hidden" value="${goods.price}" name="goodsPrice" id="goodsPrice" />
            <input type="hidden" value="${goods.vipPrice}" name="vipPrice" id="vipPrice" />
			<div class="controls">
				<form:input path="goodsCount" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人姓名：</label>
			<div class="controls">
				<form:input path="takeName" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="takePhone" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货地址：</label>
			<div class="controls">
				<form:input path="takeAddress" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="core:goods:goods:edit"><input id="btnSubmit" class="btn btn-primary" onclick="submitForm()" type="button" value="确 认"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>