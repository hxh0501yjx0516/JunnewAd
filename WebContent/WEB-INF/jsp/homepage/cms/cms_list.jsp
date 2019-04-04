<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>新闻列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/cms.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qrytype" value="${qrytype }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/cms.do?action=list" method="post">
	<div class="searchBar" >
			<ul class="searchContent">
				<li>
					<label>类别：</label>
					<select  name="qrytype" class="">
					<option value="">请选择</option>
					<c:forEach items="${cmsClassList}" var="cmsClass">
						<option value="${cmsClass.cmsClassId}" ${qrytype == cmsClass.cmsClassId?"selected":""}>${cmsClass.cmsClassName}</option>
					</c:forEach>
					</select>
				</li>
					<li><div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
		</form>
		</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/cms.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/cms.do?action=add&cmsClassId=${cmsClassId}" target="navTab" rel="添加新闻"><span>添加新闻</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="113">
			<thead>
				<tr>
					<th style="width:80px;" orderField="customerId" >编号</th>
					<th style="width:120px;">标题</th>
					<th style="width:100px;">类别</th>
					<th style="width:100px;">添加时间</th>						
					<th style="width:100px;">状态</th>
					<th style="width:100px;">置顶</th>
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="cms" >
					<tr> 
						<td>${cms.cmsId}</td>
						<td>${cms.cmsName}</td>
						<td>${cms.cmsClassName}</td>
						<td>
						<fmt:parseDate value="${cms.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>
						<td>
						<c:if test="${0==cms.cmsStatus}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/cms.do?action=edit&cid=${cms.cmsId}&state=1&flag=editState" target="navTabTodo" ><span style="color:blue">正常</span></a>
						</c:if>				
						<c:if test="${1==cms.cmsStatus}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/cms.do?action=edit&cid=${cms.cmsId}&state=0&flag=editState" target="navTabTodo" ><span style="color:orange;">锁定</span></a>
						</c:if>
						</td>
						<td>
						<c:if test="${0==cms.cmsFlag}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/cms.do?action=edit&cid=${cms.cmsId}&cmsFlag=1&flag=editFlag" target="navTabTodo" ><span style="color:blue">否</span></a>
						</c:if>				
						<c:if test="${1==cms.cmsFlag}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/cms.do?action=edit&cid=${cms.cmsId}&cmsFlag=0&flag=editFlag" target="navTabTodo" ><span style="color:orange;">是</span></a>
						</c:if>
						</td>
						<td>
						<a name="editbutton" title="修改新闻" class="btnEdit" href="${pageContext.request.contextPath }/cms.do?action=edit&cid=${cms.cmsId}&flag=edit" target="navTab" rel="editCms"><span style="color:blue">修改新闻</span></a>
						<a name="deletebutton" title="删除新闻" class="btndel" href="${pageContext.request.contextPath }/cms.do?action=delete&cid=${cms.cmsId}" target="navTabTodo" rel="deleteCms"><span style="color:blue">删除新闻</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
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