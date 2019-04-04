<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
 <html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加白(黑)名单</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addAdSize" method="post" action="${pageContext.request.contextPath }/loginconfine.do?action=add&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>IP地址：</label>
					<label style="float: left;padding-right:0px;"><input name="qryIp" class="required" type="text" /></label>
				</p>
					<p>
					<label>描述：</label>
					<label style="float: left;padding-right:0px;"><input name="qryLoginName" type="text" /></label>
				</p>
				<div class="divider"></div>
				<p>
					<label>类型：</label>
					<input type="radio" name="qryType" value="0"  checked="checked"/>黑名单
					<input type="radio" name="qryType" value="1"/>白名单
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
