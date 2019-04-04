<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>新闻类别列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/cmsclass.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/cmsclass.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/cmsclass.do?action=add" target="navTab" rel="添加类别"><span>添加类别</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					<th style="width:80px;">编号</th>
					<th style="width:120px;">类别名称</th>
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="cmsClass" >
					<tr> 
						<td>${cmsClass.cmsClassId}</td>
						<td>${cmsClass.cmsClassName}</td>
						<td>
						<a name="addCmsButton" title="添加新闻"  href="${pageContext.request.contextPath }/cms.do?action=add&cmsClassId=${cmsClass.cmsClassId}" target="navTab" rel="添加新闻"><span style="color:blue">添加新闻</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a name="addCmsButton" title="查看新闻"  href="${pageContext.request.contextPath }/cms.do?action=list&qrytype=${cmsClass.cmsClassId}" target="navTab" rel="新闻列表"><span style="color:blue">查看新闻</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a name="addCmsButton" title="修改新闻类别"  href="${pageContext.request.contextPath }/cmsclass.do?action=edit&flag=edit&cid=${cmsClass.cmsClassId}" target="navTab" rel="editCmsClass"><span style="color:blue">修改</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a name="addCmsButton" title="删除新闻类别"  href="${pageContext.request.contextPath }/cmsclass.do?action=delete&cid=${cmsClass.cmsClassId}" target="navTabTodo" title="确定要删除类别？"><span style="color:blue">删除</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
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