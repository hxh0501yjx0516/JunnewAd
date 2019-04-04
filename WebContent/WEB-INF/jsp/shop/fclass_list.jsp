<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();
%>  
<%@ include file="/util/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
	

	
<title>父栏目列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath}/shopclass.do?action=fClassList&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageContent">
	
	<form method="post" action="${pageContext.request.contextPath}/shopclass.do?action=fClassList" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath}/shopclass.do?action=fClassAdd" target="navTab" rel="fClassList"><span>添加父栏目</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="114">
			<thead>
				<tr>
					<th style="width:120px;">父栏目编号</th>
					<th style="width:100px;">父栏目名称</th>
					<th style="width:100px;">添加时间</th>
					<th style="width:100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l">
					<tr> 						
						<td>${l.shopFclassId }</td>
						<td>${l.shopFclassName }</td>
						<td>
						<fmt:parseDate value="${l.addTime }" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date"  pattern="yyyy-MM-dd"/>
						</td>		
						<td>
						<a name="editbutton" title="编辑父栏目" class="btnEdit" href="${pageContext.request.contextPath }/shopclass.do?action=fClassEdit&shopfclassid=${l.shopFclassId}" target="navTab" rel="editFClassList"><span style="color:blue">修改</span></a>
						</td>	
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<%@ include file="/util/pager.jsp" %>
			</div>
		</div>
		
	</form>
	</div>
</div>
</body>
 </html>