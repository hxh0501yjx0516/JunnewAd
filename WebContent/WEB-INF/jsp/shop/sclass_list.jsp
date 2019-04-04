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
	

	
<title>子栏目列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath}/shopclass.do?action=sClassList&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
	<input type="hidden" name="shopsclassname" value="${shopsclassname }">
	<input type="hidden" name="shopfclassid" value="${shopfclassid }">
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/shopclass.do?action=sClassList" method="post">
		
		<div class="searchBar" >
			<ul class="searchContent">
				<li >
					<label>子栏目名称：</label>
					<input type="text" name="shopsclassname" value="${shopsclassname}"/>
				</li>
				<li>
				<lable>父栏目：</lable>
				<select name="shopfclassid">
				<option value="">==请选择==</option>
				<c:forEach items="${shopFclassList}" var="l">	
					<option value="${l.shopFclassId}" ${l.shopFclassId eq shopfclassid?"selected":""}>┹${l.shopFclassName }</option>
				</c:forEach>
				</select>
				</li>
				<li>
				<div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div>
				<div class="buttonActive" ><div class="buttonContent"><a href="${pageContext.request.contextPath }/shopclass.do?action=sClassList" target="navTab" rel="子栏目列表" title="子栏目列表"><button type="button">重置</button></a></div></div>
				</li>
			</ul>
		</div>
		</form>
	</div>
	
	<div class="pageContent">
	
	<form method="post" action="${pageContext.request.contextPath}/shopclass.do?action=sClassList" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath}/shopclass.do?action=sClassAdd" target="navTab" rel="sClassList"><span>添加子栏目</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="114">
			<thead>
				<tr>
					<th style="width:120px;">子栏目编号</th>
					<th style="width:100px;">子栏目名称</th>
					<th style="width:100px;">所属父栏目</th>
					<th style="width:100px;">添加时间</th>
					<th style="width:100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${shopsclasslist}" var="l">
					<tr> 						
						<td>${l.shopSclassId }</td>
						<td>${l.shopSclassName }</td>
						<td>${l.shopFclassName }</td>
						<td>
						<fmt:parseDate value="${l.addTime }" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date"  pattern="yyyy-MM-dd"/>
						</td>		
						<td>
						<a name="editbutton" title="编辑子栏目" class="btnEdit" href="${pageContext.request.contextPath}/shopclass.do?action=sClassEdit&shopsclassid=${l.shopSclassId}" target="navTab" rel="sClassEdit"><span style="color:blue">修改</span></a>
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