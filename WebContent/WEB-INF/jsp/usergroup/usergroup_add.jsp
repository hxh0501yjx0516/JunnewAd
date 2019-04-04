<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加用户组</title> 
<script type="text/javascript" src="js/dailyFlow.js"></script>

</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addUserGroup" method="post" action="${pageContext.request.contextPath }/usergroup.do?action=add&flag=save&aa=bb" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>组名称：</label>
					<label><input name="usergroupname" class="required" type="text" size="30"/></label>
				    
				</p>
				<div class="divider"></div>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="usergroupstatus" value="0" checked="checked"/>正常</label>
					<label><input type="radio" name="usergroupstatus" value="1"/>锁定</label>
				</p>
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
</html>
