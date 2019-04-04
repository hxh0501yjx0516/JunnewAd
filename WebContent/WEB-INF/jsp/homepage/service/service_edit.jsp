<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
 <html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑客服</title> 
</head>
 <body>
    <div class="page">
	<div class="pageContent">
		<form name="editService" method="post" action="${pageContext.request.contextPath }/service.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="sid" value="${service.id}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>昵称：</label>
					<input name="name"  type="text" size="30" value="${service.name}"/>
				</p>
				<p>
					<label>QQ：</label>
				<input name="qq" class="required number" type="text" size="20" value="${service.qq}"/>
				</p>
				<p>
					<label>类型：</label>
					<select  name="typeId" class="">
						<option value="0" ${service.typeId == 0?"selected":""}>媒介</option>
						<option value="1" ${service.typeId == 1?"selected":""}>销售</option>
						<option value="2" ${service.typeId == 2?"selected":""}>洽谈</option>
						<option value="3" ${service.typeId == 3?"selected":""}>客服</option>
						<option value="4" ${service.typeId == 4?"selected":""}>技术</option>
					</select>
				</p>
<!--				<div class="divider"></div>-->
				<p>
					<label>状态：</label>
					<label><input type="radio" name="state" value="0" ${service.status == 0?"checked":""}/>显示</label>
					<label><input type="radio" name="state" value="1" ${service.status == 1?"checked":""}/>隐藏</label>
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
