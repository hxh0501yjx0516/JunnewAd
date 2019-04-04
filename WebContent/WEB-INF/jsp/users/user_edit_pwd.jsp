<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/users.do?action=editPwd&flag=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="userid" value="${users.userId }"/>
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>登录名称：</label>
					<input name="name" type="text" size="30" value="${users.username }" alt="" readonly/>
				</p>
				<p >
					<label style="">新密码：</label>
					<input name="pwd" class="required alphanumeric" title="6~20字符,至少1个字母、数字、特殊字符组合" type="password" size="30" minlength="6" maxlength="20" />
					<label style="width: 100%"><font style="color:red">6~20字符,至少1个字母、数字、特殊字符组合</font></label>
				</p>
				<p>
					<label>重复密码：</label>
					<input name="pwd2" class="required alphanumeric" title="6~20字符,至少1个字母、数字、特殊字符组合" type="password" size="30" minlength="6" maxlength="20" />
					<label style="width: 100%"><font style="color:red">6~20字符,至少1个字母、数字、特殊字符组合</font></label>
				</p>
			<div class="divider"></div>
			</div>
			<div class="formBar">
				<ul>					
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
  </body>
  
