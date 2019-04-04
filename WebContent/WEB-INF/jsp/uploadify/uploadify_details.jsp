<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>文件管理</title> 
<script type="text/javascript"></script>
</head>
<body>
	<div class="pageContent" style=" border:solid 1px #CCC;">
	<form id="pagerForm" method="post" action="${pageContext.request.contextPath }/uploadify.do?action=details&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="dirId" value="${dirId }" />
  </form>
	<form method="post" action="${pageContext.request.contextPath }/uploadify.do?action=details" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<div class="panelBar" style="margin: 5px 0px;">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/uploadify.do?action=upload&dirId=${dirId}" target="navTab" rel="文件上传"><span>上传文件</span></a></li>
				<li><a class="add" href="${pageContext.request.contextPath }/uploadify.do?action=download"><span>下载文件</span></a></li>
				<li><a class="add" href="${pageContext.request.contextPath }/uploadify.do?action=delete"  title="删除上传文件，是否确定？"  target="navTabTodo" rel="rightContent1"><span>删除文件</span></a></li>
								
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					<th style="width:200px;">名称</th>
					<th style="width:200px;">所属项目</th>			
					<th style="width:150px;">添加人</th>
					<th style="width:100px;">添加时间</th>
					<th style="width:100px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="instance" items="${fileInstanceList}" varStatus="s">
					<tr> 
					<td>${instance.name}</td>
					<td>${instance.dirName}</td>
					<td>${instance.userName}</td>
					<td>
						<fmt:parseDate value="${instance.createTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
					</td>
					<td><a href="${pageContext.request.contextPath }/uploadify.do?action=download&instanceId=${instance.id}">下载</a>&nbsp;&nbsp;
					<a title="删除上传文件，是否确定？" href="${pageContext.request.contextPath }/uploadify.do?action=delete&instanceId=${instance.id}" target="navTabTodo" rel="rightContent1">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
</div>
  </body>
</html>
