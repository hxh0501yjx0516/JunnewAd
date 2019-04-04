<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>白名单列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/urlallow.do?action=list&flag=pager" >
 	<input type="hidden" name="pageNum" value="1" /><!-- 【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!-- 【可选】每页显示多少条-->
	<!--	【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/urlallow.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/urlallow.do?action=add" target="navTab" rel="添加名单"><span>添加名单</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					<th style="width:80px;" >编号</th>
					<th style="width:120px;">名称</th>
					<th style="width:100px;">地址</th>		
					<th style="width:100px;">操作人</th>		
					<th style="width:100px;">添加时间</th>				
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="urlallow" >
					<tr> 
						<td>${urlallow.urlId}</td>
						<td>${urlallow.urlName}</td>
						<td>${urlallow.url}</td>
						<td>${urlallow.userName}</td>
						<td>
						<fmt:parseDate value="${urlallow.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>
						<td>
						<a name="editbutton" title="修改名单" class="btnEdit" href="${pageContext.request.contextPath }/urlallow.do?action=edit&uid=${urlallow.urlId}&flag=edit" target="navTab" rel="editAdSize"><span style="color:blue">修改</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
				<%@ include file="/util/pager.jsp" %>
		</div>
		
	</form>
	</div>
</div>
</body>
 </html>