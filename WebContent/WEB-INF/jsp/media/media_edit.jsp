<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/media.do?action=editAction&flag=editsave&userid=${media.userId }" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class=searchBar>
			<br><br>
			&nbsp;&nbsp;&nbsp;&nbsp;<b>资料编辑:</b>
			</div>
			<div class="pageFormContent" layoutH="300">
				<p>
					<label>用户名：</label>
					<input name="name" class="required" type="text" size="30" value="${media.username }" />
				</p>
				<p>
					<label>真实姓名：</label>
					<input name="realname" class="required" type="text" size="30" value="${media.realname }" />
				</p>
				
				<p>
					<label>联系电话：</label>
					<input name="tel" type="text" size="30" value="${media.tel }" />
				</p>
				<p>
					<label>所在部门：</label>
					<input name="dep" type="text" size="30" value="${media.dept }" />
				</p>
				<p>
					<label>职 务：</label>
					<input name="title" type="text" size="30" value="${media.title }" />
				</p>
				<p>
					<label>状 态：</label>
					<select id="state" name="state">
						<c:if test="${0==media.state}"><option value="0" selected>正 常</option></c:if>
						<c:if test="${0!=media.state}"><option value="0" >正 常</option></c:if>
						<c:if test="${1==media.state}"><option value="1" selected>暂 停</option></c:if>
						<c:if test="${1!=media.state}"><option value="1" >暂 停</option></c:if>
						
					</select>
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

