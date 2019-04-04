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
	

	
<title>添加信息</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/resource.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/resource.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/resource.do?action=addAction&flag=ainit" target="navTab" rel="res"><span>添加资源</span></a></li>
				
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					
					<th style="width:70px;">编号</th>
					<th style="width:120px;">资源名称</th>
					<th style="width:200px;">资源URL</th>
					<th style="width:100px;">资源模块</th>
					<th style="width:100px;">显示次序</th>
					<th style="width:100px;">类型</th>
					<th style="width:80px;">父编号</th>
					<th style="width:100px;">操 作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l" varStatus="status">
						<c:choose>
							<c:when test="${l.resourceType == 0}">
								<tr style="background-color: #EEF4F5;"> 
							</c:when>
							<c:when test="${l.resourceType == 2}">
								<tr > 
							</c:when>
						</c:choose>
						<td>${l.resourceId}</td>
						<td>${l.resourceName}</td>
						<td>${l.resourceUrl}</td>
						<td>${l.resourceModual}</td>
						<td>${l.displayorder}</td>
						<td>
						<c:choose>
							<c:when test="${l.resourceType == 0}">
								<span style="color: blue;">主菜单</span>
							</c:when>
							<c:when test="${l.resourceType == 2}">
								<span style="color: orange;">子菜单</span>
							</c:when>
						</c:choose>
						</td>
						<td>${l.resourcePid}</td>
						
						<td>
						<a name="editbutton" class="edit" href="${pageContext.request.contextPath }/resource.do?action=editAction&flag=einit&value=${l.resourceId }" target="navTab"><span style="color:blue">修改资源</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<!-- <a name="delbutton" class="delete" href="${pageContext.request.contextPath }/resource.do?action=delAction&flag=del&value=${l.resourceId }" target="navTabTodo" title="确定要删除吗?"><span style="color:blue">删除资源</span></a>
						 -->
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<c:if test="${numPerPage==20}"><option value="20" selected>20</option></c:if>
					<c:if test="${numPerPage!=20}"><option value="20" >20</option></c:if>
					<c:if test="${numPerPage==50}"><option value="50" selected>50</option></c:if>
					<c:if test="${numPerPage!=50}"><option value="50" >50</option></c:if>		
					<c:if test="${numPerPage==100}"><option value="100" selected>100</option></c:if>
					<c:if test="${numPerPage!=100}"><option value="100" >100</option></c:if>
					<c:if test="${numPerPage==200}"><option value="200" selected>200</option></c:if>
					<c:if test="${numPerPage!=200}"><option value="200" >200</option></c:if>
				</select>
				<span>条，共${totalCount}条</span>
			</div>
			<!-- 分页 -->
			<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage }" pageNumShown="10" currentPage="${pageNum }"></div>

		</div>
		
	</form>
	</div>
</div>
</body>
 </html>