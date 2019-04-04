<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>客服列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/adsize.do?action=list&flag=pager" >
 	<input type="hidden" name="pageNum" value="1" /><!-- 【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!-- 【可选】每页显示多少条-->
	<!--	【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/adsize.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/adsize.do?action=add" target="navTab" rel="添加尺寸"><span>添加尺寸</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					<th style="width:80px;" >编号</th>
					<th style="width:120px;">尺寸(像素)</th>
					<th style="width:100px;">状态</th>					
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="adSize" >
					<tr> 
						<td>${adSize.adSizeId}</td>
						<td>${adSize.adWidth}×${adSize.adHeight}</td>
						<td>
						<c:choose >
						<c:when test="${adSize.adSizeStatus == 0}">
						<a name="editStatebutton"  href="${pageContext.request.contextPath }/adsize.do?action=edit&sid=${adSize.adSizeId}&flag=editState&state=1" target="navTabTodo" ><span style="color:blue">显示</span></a></c:when>
						<c:when test="${adSize.adSizeStatus == 1}">
						<a name="editStatebutton"  href="${pageContext.request.contextPath }/adsize.do?action=edit&sid=${adSize.adSizeId}&flag=editState&state=0" target="navTabTodo" ><span style="color:orange">隐藏</span>
						</c:when>
						</c:choose>
						</td>
						<td>
						<a name="editbutton" title="修改尺寸" class="btnEdit" href="${pageContext.request.contextPath }/adsize.do?action=edit&sid=${adSize.adSizeId}&flag=edit" target="navTab" rel="editAdSize"><span style="color:blue">修改尺寸</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
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