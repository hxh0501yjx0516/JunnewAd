<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>文件管理</title> 
<script type="text/javascript"></script>
</head>

<body >
       	<div id="layout" style="background:#e5edef;height: 100%; ">
			<div  id="sidebar" style="float:left; display:block; overflow:auto; width:430px; border:solid 1px #CCC; line-height:21px; background:#fff;height:98%;margin: 5px 0px;">
				   <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/uploadify.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
				    <ul class="tree treeFolder collapse">
				    <c:set var="parentNode" value="${treeList}" scope="request"/>
				    <c:import url="treeNode.jsp"/>
				    <c:remove var="parentNode" scope="request"/>
				    </ul>
				    </form>
			</div>
			<div id="rightContent" style="margin-left:440px;height: 100%;">
			<div class="pageContent" style=" border:solid 1px #CCC;_width:99%;">
						<form method="post" action="${pageContext.request.contextPath }/uploadify.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
							<div class="panelBar" style="margin: 5px 0px;">
								<ul class="toolBar">
									<li><a class="add" href="${pageContext.request.contextPath }/uploadify.do?action=upload" target="navTab" rel="文件上传"><span>上传文件</span></a></li>
								</ul>
							</div>
							<table class="table" layouth="45">
								<thead>
									<tr>
										<th style="width:200px;">名称</th>
										<th style="width:200px;">类型</th>
										<th style="width:150px;">修改日期</th>
										<th style="width:100px;">大小</th>
										<th style="width:100px">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="instance" items="${fileList}" varStatus="s">
									<c:if test="${instance.type == 1}">
										<tr> 
										<td>${instance.name}</td>
										<td>${instance.suffix}文件</td>
										<td>${instance.lastTime}</td>
										<td><fmt:formatNumber value="${instance.size}" pattern="#,###"/>k</td>
										</td>
										<td>
										<a href="${pageContext.request.contextPath }/uploadify.do?action=download&filePath=${instance.path}&fileName=${instance.name}">下载</a>&nbsp;&nbsp;
										<a title="删除上传文件，是否确定？" href="${pageContext.request.contextPath }/uploadify.do?action=delete&filePath=${instance.path}" target="navTabTodo" >删除</a></td>
										</tr>
									</c:if>
									</c:forEach>
								</tbody>
							</table>
						</form>
					</div>
			</div>
			</div>
  </body>
</html>
