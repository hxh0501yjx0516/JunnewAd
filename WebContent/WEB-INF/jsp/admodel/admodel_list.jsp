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
	

	
<title>广告模式列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/admodel.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/admodel.do?action=list" method="post">
		<input type="hidden" id="selectedId_demo"/>
		<table>
		<tr>
		<td>	<ul class="searchContent">
				<li>
					<label>模板名称：</label>
					<input type="text" name="modelName" value="${modelName}" size="10"/>	
					<label>模板文件：</label>
					<input type="text" name="modelJs" value="${modelJs}" size="10"/>		
				</li>
			</ul>	</td><td>	<ul>
					<li>
					<div class="buttonActive">
					<div class="buttonContent"><button type="submit">检索</button></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/admodel.do?action=list" target="navTab" rel="admodelList"><button>重置</button></a></div>
					</div>
					</li>
				</ul>	</td>
		</tr>
		</table>
	
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/admodel.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/admodel.do?action=add" target="navTab" rel="admodel"><span>添加模板</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="114">
			<thead>
				<tr>
					
					<th style="width:120px;">编号</th>
					<th style="width:100px;">模板名称</th>
					<th style="width:100px;">模板文件</th>	
					<th style="width:100px;">类型</th>				
					<th style="width:100px;">状态</th>
					<th style="width:120px">操作</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l" >
					<tr> 						
						<td>${l.modelId}</td>
						<td>${l.modelName}</td>
						<td>${l.modelJs}</td>			
						<td>
						<c:choose>
						<c:when test="${l.modelFlag == 0}">
						纯点击
						</c:when>
						<c:when test="${l.modelFlag == 1}">
						弹窗广告
						</c:when>
						<c:when test="${l.modelFlag == 2}">
						创意广告
						</c:when>
						</c:choose>
						</td>				
						<td>
						<c:if test="${0==l.modelStatus}"><a href="${pageContext.request.contextPath}/admodel.do?action=editAction&flag=modistate&state=1&modelId=${l.modelId}" target="navTabTodo"><font color="blue">正常</font></a></c:if>						
						<c:if test="${1==l.modelStatus}"><a href="${pageContext.request.contextPath}/admodel.do?action=editAction&flag=modistate&state=0&modelId=${l.modelId}" target="navTabTodo"><font color="red">暂停</font></a></c:if>						
						
						</td> 
						<td>
						<a name="editbutton" title="编辑模板" class="btnEdit" href="${pageContext.request.contextPath}/admodel.do?action=editAction&modelId=${l.modelId}" target="navTab" rel="editAdModelList"><span style="color:blue">编辑模板</span></a>
						<!--
						<a name="delbutton" class="delete" href="${pageContext.request.contextPath}/admodel.do?action=delAction&value=${l.modelId }" target="navTabTodo" title="确定要删除吗?"><span style="color:blue">删除</span></a>
						 -->
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