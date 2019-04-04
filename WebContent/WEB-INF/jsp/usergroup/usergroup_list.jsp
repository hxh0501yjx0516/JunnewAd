<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>用户组</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/plan.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
<body>
<div class="page">
<div class="pageHeader">
		
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/usergroup.do?action=list" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
	<div class="panelBar">
			<ul class="toolBar">
			<li><a class="add" href="${pageContext.request.contextPath }/usergroup.do?action=add" target="navTab" rel="addUserGroup"><span>添加用户组</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="${resource == ''?113:88}">
			<thead>
				<tr>
					<th style="width:40px;">编号</th>
					<th style="width:500px;">组名称</th>
					<th style="width:100px;">组状态</th>	
					<th style="width:200px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="usergroup" >
					<tr> 
						<td>${usergroup.id}</td>
						<td>${usergroup.userGroupName}</td>
						<td>
						<c:if test="${usergroup.userGroupStatus eq 0}">
						<a name="editStatebutton" href="${pageContext.request.contextPath }/usergroup.do?action=list&flag=editStatus&id=${usergroup.id}"  target="navTabTodo"><font color="blue">正常</font></a>
						</c:if>
						<c:if test="${usergroup.userGroupStatus eq 1}">
						<a name="editStatebutton" href="${pageContext.request.contextPath }/usergroup.do?action=list&flag=editStatus&id=${usergroup.id}"  target="navTabTodo"><font color="orange">锁定</font></a>
						</c:if>
						</td>
						<td>
						<a name="editbutton" title="修改用户组" class="btnEdit" href="${pageContext.request.contextPath }/usergroup.do?action=edit&flag=edit&id=${usergroup.id}" target="navTab" rel="editUserGroup"><span style="color:blue">修改用户组</span></a>
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