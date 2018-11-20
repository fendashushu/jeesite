<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品信息管理</title>
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
		<li><a href="${ctx}/core/goods/goods/">商品列表</a></li>
		<li class="active"><a href="${ctx}/core/goods/goods/form?id=${goods.id}">商品<shiro:hasPermission name="core:goods:goods:edit">${not empty goods.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="core:goods:goods:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="goods" action="${ctx}/core/goods/goods/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="goodsName" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品类型：</label>
			<div class="controls">
				<form:select path="goodsType" class="input-xlarge required">
					<form:options items="${fns:getDictList('goodsType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">零售价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">店铺价：</label>
			<div class="controls">
				<form:input path="vipPrice" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存：</label>
			<div class="controls">
				<form:input path="inventory" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品图片：</label>
            <div class="controls">
                <form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                <sys:ckfinder input="image" type="images" uploadPath="/image" selectMultiple="false" maxWidth="100" maxHeight="100"/>
            </div>
		</div>
		<div class="control-group">
			<label class="control-label">商品描述：</label>
			<div class="controls">
				<form:textarea path="goodsDesc" htmlEscape="false" rows="4" maxlength="5000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="xh" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否必点：</label>
			<div class="controls">
				<form:radiobuttons path="isMust" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="core:goods:goods:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>