<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();%>  
    <%@ include file="/util/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">

<title>角色资源</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/resource.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageHeader">
	</div>
	<div class="pageContent">
	<form method="post" id="addre" action="${pageContext.request.contextPath }/role.do?action=roleResAction&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<input type="hidden" name="roleid" value="${roleid }"/>
			<div class="pageFormContent" layoutH="56">
			<c:forEach items="${menu}" var="m">
					
					<c:if test="${m.pid == 0}">
						<p>
						 <c:choose>
						<c:when test="${m.flag==0}">
							<label><input type="checkbox" id="${m.resId }" name="chk" value="${m.resId }" checked/>&nbsp;&nbsp;<font style="color:red">${m.resName }</font></label>					
						</c:when>
						
						<c:otherwise>
							<label><input type="checkbox" id="${m.resId }" name="chk" value="${m.resId }" />&nbsp;&nbsp;<font style="color:red">${m.resName }</font></label>					
						</c:otherwise>
						</c:choose>
						</p>					
					</c:if>
					<c:forEach items="${listur}" var="l">
						<c:if test="${m.id == l.pid}">
						<p>
						 <c:choose>
						<c:when test="${l.flag==0}">
							<label><input type="checkbox" id="${l.resId }" name="chk" value="${l.resId }" checked/>&nbsp;&nbsp;${l.resName }</label>					
						</c:when>
						
						<c:otherwise>
							<label><input type="checkbox" id="${l.resId }" name="chk" value="${l.resId }" />&nbsp;&nbsp;${l.resName }</label>					
						</c:otherwise>
						</c:choose>
						</p>
						</c:if>
					</c:forEach>	
							
			<div class="divider"></div>
			</c:forEach>
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
 </html>