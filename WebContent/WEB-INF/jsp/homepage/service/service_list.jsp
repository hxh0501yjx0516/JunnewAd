<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>客服列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/service.do?action=list&flag=pager" >
 	<input type="hidden" name="pageNum" value="1" /><!-- 【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!-- 【可选】每页显示多少条-->
	<!--	【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/service.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/service.do?action=add" target="navTab" rel="添加客服"><span>添加客服</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					<th style="width:80px;" >编号</th>
					<th style="width:120px;">昵称</th>
					<th style="width:100px;">类别</th>					
					<th style="width:100px;">QQ</th>
					<th style="width:100px;">状态</th>
					<th style="width:100px;">预览</th>
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="service" >
					<tr> 
						<td>${service.id}</td>
						<td>${service.name}</td>
						<td>
						<c:choose >
						<c:when test="${service.typeId == 0}">媒介</c:when>
						<c:when test="${service.typeId == 1}">销售</c:when>
						<c:when test="${service.typeId == 2}">洽谈</c:when>
						<c:when test="${service.typeId == 3}">客服</c:when>
						<c:when test="${service.typeId == 4}">技术</c:when>
						</c:choose>
						</td>
						<td>${service.qq}</td>
						<td>
						<c:choose >
						<c:when test="${service.status == 0}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/service.do?action=edit&sid=${service.id}&state=1&flag=editState" target="navTabTodo" ><span style="color:blue">显示</span></a>
						</c:when>
						<c:when test="${service.status == 1}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/service.do?action=edit&sid=${service.id}&state=0&flag=editState" target="navTabTodo" ><span style="color:orange">隐藏</span></a>
						</c:when>
						</c:choose>
						</td>
						<td>${customer.customerUrl}
						<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${service.qq}&site=qq&menu=yes">
						<img border="0" src="http://wpa.qq.com/pa?p=2:${service.qq}:46" alt="点击这里给我发消息" title="点击这里给我发消息 " align="absmiddle">
						</a>
						</td>
						<td>
						<a name="editbutton" title="修改客服" class="btnEdit" href="${pageContext.request.contextPath }/service.do?action=edit&sid=${service.id}&flag=edit" target="navTab" rel="editCutomer"><span style="color:blue">修改客服</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
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