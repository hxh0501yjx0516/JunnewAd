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
	

	
<title>数据统计</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath}/shopcount.do?action=countList&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
	<input type="hidden" name="shopwebid" value="${shopwebid }">
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form id="searchForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/shopcount.do?action=countList" method="post">
		
		<div class="searchBar" >
			<ul class="searchContent">
				<li >
					<label>合作站点：</label>
					<select name="shopwebid">
					<option value="">==请选择==</option>
					<c:forEach items="${shopWebList}" var="l">
						<option value="${l.shopWebId }" ${l.shopWebId eq shopwebid?"selected":""}>┵${l.shopWebName }</option>
					</c:forEach>
					</select>
				</li>
				<li >
					<label>父栏目：</label>
					<select name="shopfclassid">
					<option value="">==请选择==</option>
					<c:forEach items="${shopFclassList}" var="l">
					<option value="${l.shopFclassId }" ${l.shopFclassId eq shopfclassid?"selected":""} >┵${l.shopFclassName }</option>
					</c:forEach>
					</select>
				</li>
				<li >
					<label>子栏目：</label>
					<select name="shopsclassid">
					<option value="">==请选择==</option>
					<c:forEach items="${shopSclassList}" var="l">
					<option value="${l.shopSclassId }" ${l.shopSclassId eq shopsclassid?"selected":""} >┵${l.shopSclassName }</option>
					</c:forEach>
					</select>

				</li>
				<li>
				<div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div>
				<div class="buttonActive" ><div class="buttonContent"><a href="${pageContext.request.contextPath }/shopcount.do?action=countList" target="navTab" rel="数据统计" title="数据统计"><button type="button">重置</button></a></div></div>
				</li>
			</ul>
		</div>
		</form>
	</div>

	<div class="pageContent">
	
	<form method="post" action="${pageContext.request.contextPath}/shopcount.do?action=countList" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		
		<table class="table" layouth="114">
			<thead>
				<tr>
					<th style="width:120px;">编号</th>
					<th style="width:100px;">类型</th>
					<th style="width:100px;">合作站点</th>
					<th style="width:100px;">父栏目</th>
					<th style="width:100px;">子栏目</th>
					<th style="width:100px;">标题ID</th>
					<th style="width:100px;">标题</th>
					<th style="width:100px;">页面PV</th>
					<th style="width:100px;">页面UV</th>
					<th style="width:100px;">链接点击</th>
					<th style="width:100px;">链接点击</th>
					<th style="width:100px;">日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${shopCountList}" var="l">
					<tr> 						
						<td>${l.shopCountId }</td>
						<td>${l.shopCountType eq 0?"页面":"链接" }</td>	
						<td>${l.shopWebName }</td>
						<td>${l.shopFclassName }</td>
						<td>${l.shopSclassName }</td>
						<td>${l.shopId }</td>
						<td>${l.shopName }</td>
						<td>${l.shopPv }</td>
						<td>${l.shopUv }</td>
						<td><fmt:formatNumber  pattern="#,###,###" value="${l.shopHit }" /></td>
						<td><fmt:formatNumber type="number" pattern="#,###,###" value="${l.shopHit2 }" /></td>
						<td>
						<fmt:parseDate value="${l.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />
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