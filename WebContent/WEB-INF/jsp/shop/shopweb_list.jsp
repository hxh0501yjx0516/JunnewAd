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
	

	
<title>合作站点</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath}/shopweb.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
	<input type="hidden" name="shopwebname" value="${shopwebname}">
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/shopweb.do?action=list" method="post">
		
		<div class="searchBar" >
			<ul class="searchContent">
				<li >
					<label>合作站点：</label>
					<input type="text" name="shopwebname" value="${shopwebname}"/>
				</li>
				<li>
				<div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div>
				<div class="buttonActive" ><div class="buttonContent"><a href="${pageContext.request.contextPath }/shopweb.do?action=list" target="navTab" rel="子栏目列表" title="合作站点"><button type="button">重置</button></a></div></div>
				</li>
			</ul>
		</div>
		</form>
	</div>

	<div class="pageContent">
	
	<form method="post" action="${pageContext.request.contextPath}/shopweb.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath}/shopweb.do?action=add" target="navTab" title="添加站点" rel="add"><span>添加合作站点</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="114">
			<thead>
				<tr>
					<th style="width:120px;">编号</th>
					<th style="width:100px;">站点名称</th>
					<th style="width:100px;">域名</th>
					<th style="width:100px;">联系人</th>
					<th style="width:100px;">电话</th>
					<th style="width:100px;">QQ/MSN</th>
					<th style="width:100px;">状态</th>
					<th style="width:100px;">添加时间</th>
					<th style="width:100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l">
					<tr> 						
						<td>${l.shopWebId }</td>
						<td>${l.shopWebName }</td>
						<td>${l.shopWebUrl }</td>
						<td>${l.shopWebContact }</td>
						<td>${l.shopWebTel }</td>
						<td>${l.shopWebQq }</td>
						<td>${l.shopWebStatus eq 0?"<span style=' color:blue'>正常</span>":"<span style=' color:orange'>锁定</span>"}</td>
						<td>
						<fmt:parseDate value="${l.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />
						</td>			
						<td>
						<a name="editbutton" title="编辑站点" class="btnEdit" href="${pageContext.request.contextPath}/shopweb.do?action=edit&shopwebid=${l.shopWebId}" target="navTab" rel="editShopWeb"><span style="color:blue">修改</span></a>
						<a name="editbutton" href="${pageContext.request.contextPath}/shop.do?action=getcode&shopwebid=${l.shopWebId}" target="dialog" rel="getShopCodeDialog"><span style="color:blue">获取投放</span></a>
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