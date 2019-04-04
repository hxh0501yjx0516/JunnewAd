<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
 <html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加客服</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addService" method="post" action="${pageContext.request.contextPath }/service.do?action=add&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>昵称：</label>
					<input name="name"  type="text" size="30" />
				</p>
				<p>
					<label>QQ：</label>
				<input name="qq" class="required number" type="text" size="20" />
				</p>
				<p>
					<label>类型：</label>
					<select  name="typeId" class="">
						<option value="0" >媒介</option>
						<option value="1" >销售</option>
						<option value="2" >洽谈</option>
						<option value="3" >客服</option>
						<option value="4" >技术</option>
					</select>
				</p>
<!--				<div class="divider"></div>-->
				<p>
					<label>状态：</label>
					<label><input type="radio" name="state" value="0" checked="checked"/>显示</label>
					<label><input type="radio" name="state" value="1"/>隐藏</label>
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
