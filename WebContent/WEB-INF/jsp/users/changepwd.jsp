<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <body>

<div class="page">
	<div class="pageContent">
	
	<form method="post" action="${pageContext.request.contextPath}/users.do?action=setPwd&flag=save" class="pageForm" onsubmit="return validateCallback(this)">
		<div class="pageFormContent" layoutH="58">

			<div class="unit">
				<label>旧密码：</label>
				<input type="password" name="oldpwd" size="30" minlength="6" maxlength="20" class="required"/><font style="color:red">至少6个字符</font>				
			</div>
			<div class="unit">
				<label>新密码：</label>
				<input type="password" id="newpwd" name="newpwd" size="30" minlength="6" maxlength="20" class="required  alphanumeric" title="6~20字符,至少1个字母、数字、特殊字符组合"/><font style="color:red">6~20字符,至少1个字母、数字、特殊字符组合</font>	
			</div>
			<div class="unit">
				<label>重复输入新密码：</label>
				<input type="password" name="rnewpwd" size="30" class="required alphanumeric" title="6~20字符,至少1个字母、数字、特殊字符组合"/><font style="color:red">6~20字符,至少1个字母、数字、特殊字符组合</font>	
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
	</div>
</div>
</body>
