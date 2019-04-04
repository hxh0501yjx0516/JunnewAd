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
	

	
<title>内容列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath}/shop.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="shopname" value="${shopname }">
	<input type="hidden" name="shopfclassid" value="${shopfclassid }">
	<input type="hidden" name="shopsclassid" value="${shopsclassid }">
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/shop.do?action=list" method="post">
		
		<div class="searchBar" >
			<ul class="searchContent">
				<li >
					<label>标题：</label>
					<input type="text" name="shopname" value="${shopname}"/>
				</li>
				<li >
					<label>父栏目：</label>
					<select name="shopfclassid">
					<option value="">==请选择==</option>
					<c:forEach items="${shopFclassList}" var="l">
					<option value="${l.shopFclassId }" ${shopfclassid eq l.shopFclassId?"selected":""} >┝${l.shopFclassName }</option>
					</c:forEach>
					</select>
				</li>
				<li >
					<label>子栏目：</label>
					<select name="shopsclassid">
					<option value="">==请选择==</option>
					<c:forEach items="${shopSclassList}" var="l">
					<option value="${l.shopSclassId }" ${shopsclassid eq l.shopSclassId?"selected":""} >┝${l.shopSclassName }</option>
					</c:forEach>
					</select>

				</li>
				<li>
				<div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div>
				<div class="buttonActive" ><div class="buttonContent"><a href="${pageContext.request.contextPath }/shop.do?action=list"  target="navTab" rel="内容列表" title="内容列表"><button type="button">重置</button></a></div></div>
				</li>
			</ul>
		</div>
		</form>
	</div>

	<div class="pageContent">
	
	<form method="post" action="${pageContext.request.contextPath}/shop.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath}/shop.do?action=add" target="navTab" title="添加内容" rel="addShopContent"><span>添加内容</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="114">
			<thead>
				<tr>
					<th style="width:80px;">编号</th>
					<th style="width:150px;">标题</th>
					<th style="width:80px;">链接地址</th>
					<th style="width:80px;">原价</th>
					<th style="width:80px;">现价</th>
					<th style="width:80px;">图片</th>
					<th style="width:80px;">备注</th>
					<th style="width:80px;">添加时间</th>
					<th style="width:80px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l">
					<tr> 						
						<td>${l.shopId}</td>
						<td>${l.shopName }</td>
						<td><a title="${l.shopUrl}">${fn:substring(l.shopUrl, 0, 40)}</a></td>
						<td style=" text-decoration: line-through; color:red">
						￥<fmt:formatNumber type="curreny" pattern="#,###.##" value="${l.shopPrice }" />
						</td>
						<td style=" color:blue">
						￥<fmt:formatNumber type="curreny" pattern="#,###.##" value="${l.shopNewPrice }" />
						</td>
						<td>${l.shopImg }</td>
						<td>${l.shopRemarks }</td>
						<td>
						<fmt:parseDate value="${l.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />
						</td>			
						<td>
						<a name="editbutton" title="编辑内容" class="btnEdit" href="${pageContext.request.contextPath}/shop.do?action=edit&shopid=${l.shopId}" target="navTab" rel="editShop"><span style="color:blue">修改</span></a>
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