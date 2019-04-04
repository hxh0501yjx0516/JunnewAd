<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>

<html>
	<head>
		<link href="${pageContext.request.contextPath }/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/validator.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles1.css">
		<style>
		<style>
table tr {
	font-size: 10.2pt
}
</style>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.3.2.js"></script>
		<script src="${pageContext.request.contextPath }/js/formValidator_min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/js/formValidatorRegex.js" type="text/javascript"></script>
			
		<script type="text/javascript">
			$(document).ready(function(){
				$.formValidator.initConfig({formid:"addmem",onerror:function(msg){alert(msg)}});
				$("#oldpwd").formValidator({onshow:"请输入原密码",onfocus:"密码必须不小于6位",oncorrect:"密码合法"}).inputValidator({min:6,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"原密码密码不能为空,请确认"});				
				$("#pwd").formValidator({onshow:"请输入密码",onfocus:"密码必须不小于6位",oncorrect:"密码合法"}).inputValidator({min:6,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
				$("#pwd1").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致",oncorrect:"密码一致"}).inputValidator({min:6,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"pwd",operateor:"=",onerror:"2次密码不一致,请确认"});
			});	
		</script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="cpadd">
			 <tr>
              <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="10">
                <tr>
                  <td>
                  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td height="28" bgcolor="e2e2e2" class="STYLE2"><table width="50%" border="0" align="left" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="20" align="left">&nbsp;</td>
                            <td width="25" align="left"><img src="images/xtpt_index_dian.gif" width="23" height="23" /></td>
                            <td align="left">
                            <span class="STYLE5">修改密码</span>
                            </td>
                          </tr>
                        </table>
                    </td>
                   </tr>
                   <tr>
						<td bgcolor="#FFFFFF" class="STYLE1">
							<form action="<html:rewrite action="/usersmanager"/>?action=setPwd&resourceId=${resourceId }" id="addmem" name="addmem"
								method="post">
								<input type="hidden" name="userid" value="${user.userId }"/>
								<table width="100%"  border="0" cellpadding="0" align="center" class="cpadd">
									<tr align="left">
										<td  align="right">
											用户名：
										</td>
										<td  align="left" style="color:#003377;font-size:11.5pt">
											<input id="username" name="username" type="hidden"
												value="${user.username}" readonly/>
												<label id="username">${user.username}</label>
											<span id="usernameTip"></span>
										</td>
									</tr>
									<c:if test="${flag == 'left'}">
									<tr  bgcolor="#F5F5F5">
										<td  align="right">
											原密码：
										</td>
										<td  align="left">
											<input id="oldpwd" name="oldpwd" type="password" value="" style="width:150px"/>
											<span id="oldpwdTip"></span>											
										</td>
									</tr>
									</c:if>
									<tr  >
										<td  align="right">
											新密码：
										</td>
										<td  align="left">
											<input id="pwd" name="pwd" type="password" value="${pwd }" style="width:150px"/>
											<span id="pwdTip"></span>
										</td>
									</tr>
									<tr bgcolor="#F5F5F5">
										<td width="43%" align="right"  >
											重复密码：
										</td>
										<td width="57%"  align="left">
											<input id="pwd1" name="pwd1" type="password" value="${newpwd }" style="width:150px"/>
											<span id="pwd1Tip"></span>
										</td>
									</tr>
									<tr ><td align="center" colspan="2"><font color="red">${mes }</font></td></tr>
									<tr >
										<td colspan=2 align=center>
											<input name="button" type="submit" value=" 修 改 " class="btn_2k3" style="width:80px"/>
											&nbsp;&nbsp;&nbsp;
											<input name="button" type="reset" value=" 重 填 " class="btn_2k3" style="width:80px"/>
											<c:if test="${flag != 'left'}">
											&nbsp;&nbsp;&nbsp;
											<input name="button" type="button" value=" 返 回 "
												onclick="javascript:history.back(-1)"  class="btn_2k3" style="width:80px"/>
											</c:if>
										</td>
									</tr>
									
								</table>
							</form>
						</td>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>