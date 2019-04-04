<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
 <html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>修改白名单</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addAdSize" method="post" action="${pageContext.request.contextPath }/urlallow.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input name="uid" value="${urlAllow.urlId}" type="hidden"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>名称：</label>
					<label style="float: left;padding-right:0px;"><input name="urlName" class="required" type="text" value="${urlAllow.urlName}"/></label>
				</p>
				<div class="divider"></div>
				<p>
					<label>地址：</label>
					<label style="float: left;padding-right:0px;"><input name="urlAddress" class="required" type="text" value="${urlAllow.url}"/></label>
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
