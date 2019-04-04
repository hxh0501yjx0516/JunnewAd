<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/role.do?action=addAction&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="panelBar">
			<ul class="toolBar">
				<span style="font:bold">》》添加角色</span>
				<li class="line">line</li>
			</ul>
		</div>
			
			<div class="pageFormContent" layoutH="200">
			<table >
				<tr><td>
					<label>角 色：</label>
					<input name="name" class="required" type="text" size="30" value="" />
				</td></tr><tr><td>
				
					<label>状 态：</label>
					<select id="state" name="state">
						<c:if test="${1==state}"><option value="1" selected>正 常</option></c:if>
						<c:if test="${1!=state}"><option value="1" >正 常</option></c:if>
						<c:if test="${0==state}"><option value="0" selected>暂 停</option></c:if>
						<c:if test="${0!=state}"><option value="0" >暂 停</option></c:if>
						
					</select>
				</td></tr><tr><td>
				
					<label>备 注：</label>
					<textarea name="memo" style="width:250px;height:80px"/>
				</td></tr>
			</table>
			<div class="divider"></div>
			</div>
			<div class="formBar">
				<ul>					
					<!--<li><a class="buttonActive" href="#"><span>保存</span></a></li>-->
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

