<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
            closeLoading();
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
		function checkNum(obj) {
            var val = $(obj).val();
            if(isNaN(val)){
                alert("只能填写数字！");
                return false;
            }
            return true;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>会员设置</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="memberSetting" action="${ctx}/core/setting/memberSetting/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
        <table id="contentTable" class="table table-striped table-bordered table-condensed">
            <thead>
                <tr>
                    <th style="width: 9%">级别</th>
                    <th style="width: 9%">名称</th>
                    <th style="width: 9%">奖金级别</th>
                    <th style="width: 9%">加盟资格</th>
                    <th style="width: 9%">直推奖（%）</th>
                    <th style="width: 9%">合作奖（%）</th>
                    <th style="width: 9%">管理奖（%）</th>
                    <th style="width: 9%">推店奖（%）</th>
                    <th style="width: 9%">报单奖（%）</th>
                    <th style="width: 9%">活动升级</th>
                    <th style="width: 9%">周封顶</th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td style="width: 9%;"><input name="name0" value="${memberSetting.name0}" style="width: 100%"></td>
                <td style="width: 9%;"><input name="pv0" value="${memberSetting.pv0}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="jion0" value="${memberSetting.jion0}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="zhitui0" value="${memberSetting.zhitui0}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="hezuo0" value="${memberSetting.hezuo0}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="guanli0" value="${memberSetting.guanli0}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="tuidian0" value="${memberSetting.tuidian0}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="baodan" value="${memberSetting.baodan}" style="width: 100%" onblur="checkNum(this)"></td>
                <td></td>
                <td style="width: 9%;"><input name="zhou0" value="${memberSetting.zhou0}" style="width: 100%" onblur="checkNum(this)"></td>
            </tr>
            <tr>
                <td>2</td>
                <td style="width: 9%;"><input name="name1" value="${memberSetting.name1}" style="width: 100%"></td>
                <td style="width: 9%;"><input name="pv1" value="${memberSetting.pv1}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="jion1" value="${memberSetting.jion1}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="zhitui1" value="${memberSetting.zhitui1}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="hezuo1" value="${memberSetting.hezuo1}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="guanli1" value="${memberSetting.guanli1}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="tuidian1" value="${memberSetting.tuidian1}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="baodan" value="${memberSetting.baodan}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="oneToTwo" value="${memberSetting.oneToTwo}" style="width: 45%;float: left;" onblur="checkNum(this)"><p style="width: 45%;float: left;">(一升二)</p></td>
                <td style="width: 9%;"><input name="zhou1" value="${memberSetting.zhou1}" style="width: 100%" onblur="checkNum(this)"></td>
            </tr>
            <tr>
                <td>3</td>
                <td style="width: 9%;"><input name="name2" value="${memberSetting.name2}" style="width: 100%"></td>
                <td style="width: 9%;"><input name="pv2" value="${memberSetting.pv2}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="jion2" value="${memberSetting.jion2}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="zhitui2" value="${memberSetting.zhitui2}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="hezuo2" value="${memberSetting.hezuo2}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="guanli2" value="${memberSetting.guanli2}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="tuidian2" value="${memberSetting.tuidian2}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="baodan" value="${memberSetting.baodan}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="oneToThree" value="${memberSetting.oneToThree}" style="width: 45%;float: left;" onblur="checkNum(this)"><p style="width: 45%;float: left;">(一升三)</p></td>
                <td style="width: 9%;"><input name="zhou2" value="${memberSetting.zhou2}" style="width: 100%" onblur="checkNum(this)"></td>
            </tr>
            <tr>
                <td>4</td>
                <td style="width: 9%;"><input name="name3" value="${memberSetting.name3}" style="width: 100%"></td>
                <td style="width: 9%;"><input name="pv3" value="${memberSetting.pv3}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="jion3" value="${memberSetting.jion3}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="zhitui3" value="${memberSetting.zhitui3}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="hezuo3" value="${memberSetting.hezuo3}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="guanli3" value="${memberSetting.guanli3}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="tuidian3" value="${memberSetting.tuidian3}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="baodan" value="${memberSetting.baodan}" style="width: 100%" onblur="checkNum(this)"></td>
                <td style="width: 9%;"><input name="twoToThree" value="${memberSetting.twoToThree}" style="width: 45%;float: left;" onblur="checkNum(this)"><p style="width: 45%;float: left;">(二升三)</p></td>
                <td style="width: 9%;"><input name="zhou3" value="${memberSetting.zhou3}" style="width: 100%" onblur="checkNum(this)"></td>
            </tr>
            </tbody>
        </table>
		<div class="form-actions">
			<shiro:hasPermission name="core:setting:memberSetting:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>