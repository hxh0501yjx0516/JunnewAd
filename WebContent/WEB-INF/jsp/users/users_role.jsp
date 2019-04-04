<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/users.do?action=userRoleAction&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="userid" value="${userid }"/>
			<div class="pageFormContent" layoutH="200">
				<c:forEach items="${listur}" var="l">
					<p>
					 <c:choose>
					<c:when test="${l.flag==0}">
						<label><input type="checkbox" id="${l.roleId }" name="chk" value="${l.roleId }" checked/>&nbsp;&nbsp;${l.roleName }</label>					
					</c:when>
					
					<c:otherwise>
						<label><input type="checkbox" id="${l.roleId }" name="chk" value="${l.roleId }" />&nbsp;&nbsp;${l.roleName }</label>					
					</c:otherwise>
					</c:choose>
					</p>
			</c:forEach>
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

