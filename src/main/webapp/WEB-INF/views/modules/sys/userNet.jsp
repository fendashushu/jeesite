<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            closeLoading();
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

        function search(){
            var loginName = $("#nickname").val();
            window.location.href="${ctx}/sys/user/net?loginName="+loginName;
        }
    </script>
    <style type="text/css">
        .table-bordered th, .table-bordered td {
            text-align: center !important;
        }
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="javaScript:void(0)">网图结构</a></li>
</ul><br/>
<sys:message content="${message}"/>
<table width="100%" height="600px" cellpadding="3" cellspacing="1" border="0" align="center">
    <form name="form1" method="post" action="?"></form>
    <tbody><tr>
        <td height="22" colspan="12" class="ziti">查询会员：
            <input type="text" name="nickname" id="nickname">
            <input name="submin" type="submit" class="button_blue" id="submin" onclick="search()" style="margin-bottom: 10px;" value="查  询">
            <input  class="button_blue" onclick="history.go(-1)" type="button" value="返回上一级" style="margin-bottom: 10px;">
        </td>
        <td style="background:#f7f0f0" align="center" class="ziti">会员</td>
        <td style="background:#ffb3a7" align="center" class="ziti">银卡</td>
        <td style="background:#a4e2c6" align="center" class="ziti">金卡</td>
        <td style="background:#eedeb0" align="center" class="ziti">钻卡</td>
        <td style="background:#00DB00" align="center" class="ziti">已激活</td>
        <td style="background:#FFFF00" align="center" class="ziti">未激活</td>
    </tr>
    <tr>
            <style>
        <c:choose>
        <c:when test="${m1.memberlevel eq '0'}">
            .back{
            background:#f7f0f0 !important;
            }
        </c:when>
        <c:when test="${m1.memberlevel eq '1'}">
            .back{
            background:#ffb3a7 !important;
            }
        </c:when>
        <c:when test="${m1.memberlevel eq '2'}">
            .back{
            background:#a4e2c6 !important;
            }
        </c:when>
        <c:when test="${m1.memberlevel eq '3'}">
            .back{
            background:#eedeb0 !important;
            }
        </c:when>
        <c:otherwise>
            .back{
                background:#00DB00 !important;
            }
        </c:otherwise>
        </c:choose>
        <c:choose>
        <c:when test="${m1.activate eq '0'}">
            .status{
                background-color: #FFFF00 !important;
            }
        </c:when>
        <c:otherwise>
        .status{
            background-color: #00DB00 !important;
        }
        </c:otherwise>
        </c:choose>
            </style>

        <td height="100" colspan="4" align="center">
            <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 10%;text-align: center !important;">
                <tbody><tr>
                    <td colspan="4" class="status" align="center"><font color="black"><font color="black">${m1.loginName}</font></font></td>
                </tr>
                <tr>
                    <td class="back" colspan="4" align="center">${m1.name}</td>
                </tr>
                <tr>
                    <td class="back" colspan="4" align="center">
                        <c:choose>
                            <c:when test="${m1.referee == '0'}">顶层会员</c:when>
                            <c:otherwise>${m1.referee}</c:otherwise>
                        </c:choose>

                    </td>
                </tr>
                <tr>
                    <td class="back" colspan="4" align="center">总计</td>
                </tr>
                <tr>
                    <td class="back" align="center">${m1.apvTotal}</td>
                    <td class="back" align="center">${m1.bpvTotal}</td>
                </tr>
                <tr>
                    <td class="back" colspan="4" align="center">结余</td>
                </tr>
                <tr>
                    <td class="back" align="center">${m1.apv}</td>
                    <td class="back" align="center">${m1.bpv}</td>
                </tr>
                </tbody></table></td>
    </tr>
    <tr>
        <td height="36" colspan="4" align="center" style="font-size: 0">
            <img src="${ctxStatic}/images/t_tree_bottom_l.gif" style="height: 30px;">
            <img src="${ctxStatic}/images/t_tree_line.gif"  style="width: 25%; height: 30px;">
            <img src="${ctxStatic}/images/t_tree_top.gif" style="height: 30px;">
            <img src="${ctxStatic}/images/t_tree_line.gif"   style="width:  25%; height: 30px;">
            <img src="${ctxStatic}/images/t_tree_bottom_r.gif" style="height: 30px;">
        </td>
    </tr>
    <tr>
            <style>
                <c:choose>
                <c:when test="${ma.memberlevel eq '0'}">
                .backa{
                    background:#f7f0f0 !important;
                }
                </c:when>
                <c:when test="${ma.memberlevel eq '1'}">
                .backa{
                    background:#ffb3a7 !important;
                }
                </c:when>
                <c:when test="${ma.memberlevel eq '2'}">
                .backa{
                    background:#a4e2c6 !important;
                }
                </c:when>
                <c:when test="${ma.memberlevel eq '3'}">
                .backa{
                    background:#eedeb0 !important;
                }
                </c:when>
                <c:otherwise>
                .backa{
                    background:#00DB00 !important;
                }
                </c:otherwise>
                </c:choose>
                <c:choose>
                <c:when test="${ma.activate eq '0'}">
                .statusa{
                    background-color: #FFFF00 !important;
                }
                </c:when>
                <c:otherwise>
                .statusa{
                    background-color: #00DB00 !important;
                }
                </c:otherwise>
                </c:choose>
            </style>
        <c:choose>
            <c:when test="${empty ma}">
                <td width="50%" colspan="2" align="center">
                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 20%;text-align: center !important;">
                        <tbody><tr>
                            <td style="background: #d8d1d1;" colspan="4" align="center">
                                <c:choose>
                                    <c:when test="${m1.memberlevel=='0' || isstore=='0'}">
                                        注册
                                    </c:when>
                                    <c:when test="${m1.activate=='0'}">
                                        注册
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${ctx}/sys/user/form?store=${m1.loginName}&contact=${m1.loginName}&area=A">注册</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color:#d8d1d1;" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">0</td>
                            <td style="background-color:#d8d1d1; width: 50%;" align="center">0</td>
                        </tr>
                        </tbody></table></td>
            </c:when>
            <c:otherwise>
                <td width="50%" colspan="2" align="center">
                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 20%;text-align: center !important;">
                        <tbody><tr>
                            <td class="statusa" style="font-color:black;" colspan="4" align="center">
                            <font color="black"><a href="${ctx}/sys/user/net?loginName=${ma.loginName}"><font color="black">${ma.loginName}</font></a></font>
                            </td>
                        </tr>
                        <tr>
                            <td class="backa"  colspan="4" align="center">${ma.name}</td>
                        </tr>
                        <tr>
                            <td class="backa" colspan="4" align="center">${ma.referee} </td>
                        </tr>
                        <tr>
                            <td class="backa" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td class="backa" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${ma.apvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${ma.apvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                            <td class="backa" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${ma.bpvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${ma.bpvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="backa" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td class="backa" align="center" style="width: 50%;">${ma.apv}</td>
                            <td class="backa" align="center" style="width: 50%;">${ma.bpv}</td>
                        </tr>
                        </tbody></table></td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${empty mb}">
                <td width="50%" colspan="2" align="center">

                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 20%;text-align: center !important;">
                        <tbody><tr>
                            <td style="background: #d8d1d1;" colspan="4" align="center">
                                <c:choose>
                                    <c:when test="${m1.memberlevel=='0' || isstore=='0'}">
                                        注册
                                    </c:when>
                                    <c:when test="${m1.activate=='0'}">
                                        注册
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${ctx}/sys/user/form?store=${m1.loginName}&contact=${m1.loginName}&area=B">注册</a>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color:#d8d1d1;" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">0</td>
                            <td style="background-color:#d8d1d1; width: 50%;" align="center">0</td>
                        </tr>
                        </tbody></table></td>
            </c:when>
            <c:otherwise>
                <td width="50%" colspan="2" align="center">
                    <style>
                        <c:choose>
                        <c:when test="${mb.memberlevel eq '0'}">
                        .backb{
                            background:#f7f0f0 !important;
                        }
                        </c:when>
                        <c:when test="${mb.memberlevel eq '1'}">
                        .backb{
                            background:#ffb3a7 !important;
                        }
                        </c:when>
                        <c:when test="${mb.memberlevel eq '2'}">
                        .backb{
                            background:#a4e2c6 !important;
                        }
                        </c:when>
                        <c:when test="${mb.memberlevel eq '3'}">
                        .backb{
                            background:#eedeb0 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .backb{
                            background:#00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                        <c:choose>
                        <c:when test="${mb.activate eq '0'}">
                        .statusb{
                            background-color: #FFFF00 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .statusb{
                            background-color: #00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                    </style>
                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 20%;text-align: center !important;">
                        <tbody><tr>
                            <td class="statusb" colspan="4" align="center"><a href="${ctx}/sys/user/net?loginName=${mb.loginName}"><font color="black">${mb.loginName}</font></a></td>
                        </tr>
                        <tr>
                            <td class="backb" colspan="4" align="center">${mb.name}</td>
                        </tr>
                        <tr>
                            <td class="backb" colspan="4" align="center">${mb.referee}</td>
                        </tr>
                        <tr>
                            <td class="backb" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td class="backb" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${mb.apvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${mb.apvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                            <td class="backb" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${mb.bpvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${mb.bpvTotal}</c:otherwise>
                                </c:choose>
                             </td>
                        </tr>
                        <tr>
                            <td class="backb" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td class="backb" align="center" style="width: 50%;">${mb.apv}</td>
                            <td class="backb" align="center" style="width: 50%;">${mb.bpv}</td>
                        </tr>
                        </tbody></table></td>
            </c:otherwise>
        </c:choose>
    </tr>
    <tr>
        <td height="36" colspan="2" number="10" align="center" style="font-size: 0">
            <img src="${ctxStatic}/images/t_tree_bottom_l.gif" alt=""  style="height: 30px;">
            <img src="${ctxStatic}/images/t_tree_line.gif" alt="" style="height: 30px;width: 25%;">
            <img src="${ctxStatic}/images/t_tree_top.gif" alt="" style="height: 30px;">
            <img src="${ctxStatic}/images/t_tree_line.gif" alt="" style="height: 30px;width: 25%;">
            <img src="${ctxStatic}/images/t_tree_bottom_r.gif" alt="" style="height: 30px;">
        </td>
        <td colspan="2" number="10" align="center" style="font-size: 0">
            <img src="${ctxStatic}/images/t_tree_bottom_l.gif" alt=""  style="height: 30px;">
            <img src="${ctxStatic}/images/t_tree_line.gif" alt="" style="height: 30px;width: 25%;">
            <img src="${ctxStatic}/images/t_tree_top.gif" alt="" style="height: 30px;">
            <img src="${ctxStatic}/images/t_tree_line.gif" alt="" style="height: 30px;width: 25%;">
            <img src="${ctxStatic}/images/t_tree_bottom_r.gif" alt="" style="height: 30px;">
        </td>
    </tr>
    <tr>
        <c:choose>
            <c:when test="${empty maa}">
                <td align="center" valign="top">
                    <table width="120" height="80" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 33%;text-align: center !important;">
                        <tbody><tr>
                            <td style="background: #d8d1d1;" colspan="4" align="center">
                                <c:choose>
                                    <c:when test="${empty ma  || isstore=='0'}">
                                       注册
                                    </c:when>
                                    <c:when test="${ma.activate=='0'}">
                                        注册
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${ctx}/sys/user/form?store=${m1.loginName}&contact=${ma.loginName}&area=A">注册</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color:#d8d1d1;" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">0</td>
                            <td style="background-color:#d8d1d1; width: 50%;" align="center">0</td>
                        </tr>
                        </tbody></table></td>
            </c:when>
            <c:otherwise>
                <td align="center" valign="top">
                    <style>
                        <c:choose>
                        <c:when test="${maa.memberlevel eq '0'}">
                        .backaa{
                            background:#f7f0f0 !important;
                        }
                        </c:when>
                        <c:when test="${maa.memberlevel eq '1'}">
                        .backaa{
                            background:#ffb3a7 !important;
                        }
                        </c:when>
                        <c:when test="${maa.memberlevel eq '2'}">
                        .backaa{
                            background:#a4e2c6 !important;
                        }
                        </c:when>
                        <c:when test="${maa.memberlevel eq '3'}">
                        .backaa{
                            background:#eedeb0 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .backaa{
                            background:#00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                        <c:choose>
                        <c:when test="${maa.activate eq '0'}">
                        .statusaa{
                            background-color: #FFFF00 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .statusaa{
                            background-color: #00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                    </style>
                    <table width="120" height="80" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 33%;text-align: center !important;">
                        <tbody><tr>
                            <td class="statusaa" colspan="4" align="center"><a href="${ctx}/sys/user/net?loginName=${maa.loginName}"><font color="black">${maa.loginName}</font></a></td>
                        </tr>
                        <tr>
                            <td class="backaa" colspan="4" align="center">${maa.name}</td>
                        </tr>
                        <tr>
                            <td class="backaa" colspan="4" align="center">${maa.referee}</td>
                        </tr>

                        <tr>
                            <td class="backaa" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td class="backaa" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${maa.apvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${maa.apvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                            <td class="backaa" align="center" style="width: 50%;">
                                <c:choose>
                                <c:when test="${maa.bpvTotal==0}">&#12288;0&#12288;</c:when>
                                <c:otherwise>${maa.bpvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="backaa" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td class="backaa" align="center" style="width: 50%;">${maa.apv}</td>
                            <td class="backaa" align="center" style="width: 50%;">${maa.bpv}</td>
                        </tr>
                        </tbody></table></td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${empty mab}">
                <td align="center" valign="top">
                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 33%;text-align: center !important;">
                        <tbody><tr>
                            <td style="background: #d8d1d1;" colspan="4" align="center">
                                <c:choose>
                                    <c:when test="${empty ma  || isstore=='0'}">
                                        注册
                                    </c:when>
                                    <c:when test="${ma.activate=='0'}">
                                        注册
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${ma.memberlevel=='0'}">
                                                注册
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${ctx}/sys/user/form?store=${m1.loginName}&contact=${ma.loginName}&area=B">注册</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color:#d8d1d1;" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">0</td>
                            <td style="background-color:#d8d1d1; width: 50%;" align="center">0</td>
                        </tr>
                        </tbody></table></td>
            </c:when>
            <c:otherwise>
                <td align="center" valign="top">
                    <style>
                        <c:choose>
                        <c:when test="${mab.memberlevel eq '0'}">
                        .backab{
                            background:#f7f0f0 !important;
                        }
                        </c:when>
                        <c:when test="${mab.memberlevel eq '1'}">
                        .backab{
                            background:#ffb3a7 !important;
                        }
                        </c:when>
                        <c:when test="${mab.memberlevel eq '2'}">
                        .backab{
                            background:#a4e2c6 !important;
                        }
                        </c:when>
                        <c:when test="${mab.memberlevel eq '3'}">
                        .backab{
                            background:#eedeb0 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .backab{
                            background:#00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                        <c:choose>
                        <c:when test="${mab.activate eq '0'}">
                        .statusab{
                            background-color: #FFFF00 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .statusab{
                            background-color: #00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                    </style>
                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 33%;text-align: center !important;">
                        <tbody><tr>
                            <td class="statusab" colspan="4" align="center"><a href="${ctx}/sys/user/net?loginName=${mab.loginName}"><font color="black">${mab.loginName}</font></a></td>
                        </tr>
                        <tr>
                            <td class="backab" colspan="4" align="center">${mab.name}</td>
                        </tr>
                        <tr>
                            <td class="backab" colspan="4" align="center">${mab.referee} </td>
                        </tr>
                        <tr>
                            <td class="backab" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td class="backab" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${mab.apvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${mab.apvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                            <td class="backab" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${mab.bpvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${mab.bpvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="backab" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td class="backbb" align="center" style="width: 50%;">${mab.apv}</td>
                            <td class="backbb" align="center" style="width: 50%;">${mab.bpv}</td>
                        </tr>
                        </tbody></table></td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${empty mba}">
                <td align="center" valign="top">
                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 33%;text-align: center !important;">
                        <tbody><tr>
                            <td style="background: #d8d1d1;" colspan="4" align="center">
                                <c:choose>
                                    <c:when test="${empty mb  || isstore=='0'}">
                                        注册
                                    </c:when>
                                    <c:when test="${mb.activate=='0'}">
                                        注册
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${ctx}/sys/user/form?store=${m1.loginName}&contact=${mb.loginName}&area=A">注册</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color:#d8d1d1;" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">0</td>
                            <td style="background-color:#d8d1d1; width: 50%;" align="center">0</td>
                        </tr>
                        </tbody></table></td>
            </c:when>
            <c:otherwise>
                <td align="center" valign="top">
                    <style>
                        <c:choose>
                        <c:when test="${mba.memberlevel eq '0'}">
                        .backba{
                            background:#f7f0f0 !important;
                        }
                        </c:when>
                        <c:when test="${mba.memberlevel eq '1'}">
                        .backba{
                            background:#ffb3a7 !important;
                        }
                        </c:when>
                        <c:when test="${mba.memberlevel eq '2'}">
                        .backba{
                            background:#a4e2c6 !important;
                        }
                        </c:when>
                        <c:when test="${mba.memberlevel eq '3'}">
                        .backba{
                            background:#eedeb0 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .backba{
                            background:#00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                        <c:choose>
                        <c:when test="${mba.activate eq '0'}">
                        .statusba{
                            background-color: #FFFF00 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .statusba{
                            background-color: #00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                    </style>
                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 33%;text-align: center !important;">
                        <tbody><tr>
                            <td class="statusba" colspan="4" align="center"><a href="${ctx}/sys/user/net?loginName=${mba.loginName}"><font color="black">${mba.loginName}</font></a></td>
                        </tr>
                        <tr>
                            <td class="backba" colspan="4" align="center">${mba.name}</td>
                        </tr>
                        <tr>
                            <td class="backba" colspan="4" align="center">${mba.referee}</td>
                        </tr>
                        <tr>
                            <td class="backba" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td class="backba" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${mba.apvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${mba.apvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                            <td class="backba" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${mba.bpvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${mba.bpvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="backba"colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td class="backba" align="center" style="width: 50%;">${mba.apv}</td>
                            <td class="backba" align="center" style="width: 50%;">${mba.bpv}</td>
                        </tr>
                        </tbody></table></td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${empty mbb}">
                <td align="center" valign="top">
                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 33%;text-align: center !important;">
                        <tbody><tr>
                            <td style="background: #d8d1d1;" colspan="4" align="center">
                                <c:choose>
                                    <c:when test="${empty mb  || isstore=='0'}">
                                        注册
                                    </c:when>
                                    <c:when test="${mb.activate=='0'}">
                                        注册
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${mb.memberlevel=='0'}">
                                                注册
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${ctx}/sys/user/form?store=${m1.loginName}&contact=${mb.loginName}&area=B">注册</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">&#12288;0&#12288;</td>
                        </tr>
                        <tr>
                            <td style="background-color:#d8d1d1;" colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td style="background-color: #d8d1d1;width: 50%;" align="center">0</td>
                            <td style="background-color:#d8d1d1;width: 50%; " align="center">0</td>
                        </tr>
                        </tbody></table></td>
            </c:when>
            <c:otherwise>
                <td align="center" valign="top">
                    <style>
                        <c:choose>
                        <c:when test="${mbb.memberlevel eq '0'}">
                        .backbb{
                            background:#f7f0f0 !important;
                        }
                        </c:when>
                        <c:when test="${mbb.memberlevel eq '1'}">
                        .backbb{
                            background:#ffb3a7 !important;
                        }
                        </c:when>
                        <c:when test="${mbb.memberlevel eq '2'}">
                        .backbb{
                            background:#a4e2c6 !important;
                        }
                        </c:when>
                        <c:when test="${mbb.memberlevel eq '3'}">
                        .backbb{
                            background:#eedeb0 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .backbb{
                            background:#00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                        <c:choose>
                        <c:when test="${mbb.activate eq '0'}">
                        .statusbb{
                            background-color: #FFFF00 !important;
                        }
                        </c:when>
                        <c:otherwise>
                        .statusbb{
                            background-color: #00DB00 !important;
                        }
                        </c:otherwise>
                        </c:choose>
                    </style>
                    <table width="120" cellpadding="3" cellspacing="1" border="0" class="table table-striped table-bordered table-condensed tree_table" style="width: 33%;text-align: center !important;">
                        <tbody><tr>
                            <td class="statusbb" colspan="4" align="center"><a href="${ctx}/sys/user/net?loginName=${mbb.loginName}"><font color="black">${mbb.loginName}</font></a></td>
                        </tr>
                        <tr>
                            <td class="backbb" colspan="4" align="center">${mbb.name}</td>
                        </tr>
                        <tr>
                            <td class="backbb" colspan="4" align="center">${mbb.referee}</td>
                        </tr>
                        <tr>
                            <td class="backbb" colspan="4" align="center">总计</td>
                        </tr>
                        <tr>
                            <td class="backbb" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${mbb.apvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${mbb.apvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                            <td class="backbb" align="center" style="width: 50%;">
                                <c:choose>
                                    <c:when test="${mbb.bpvTotal==0}">&#12288;0&#12288;</c:when>
                                    <c:otherwise>${mbb.bpvTotal}</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="backbb"colspan="4" align="center">结余</td>
                        </tr>
                        <tr>
                            <td class="backbb" align="center" style="width: 50%;">${mbb.apv}</td>
                            <td class="backbb" align="center" style="width: 50%;">${mbb.bpv}</td>
                        </tr>
                        </tbody></table></td>
            </c:otherwise>
        </c:choose>
    </tr>
    </tbody></table>
</body>
</html>