<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>广告位规格</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/adboxinfo.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageContent">
	<form name="adBoxInfoList" method="post" action="${pageContext.request.contextPath }/adboxinfo.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/adboxinfo.do?action=add" target="navTab" rel="添加规格"><span>添加规格</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					<th style="width:80px;"  >编号</th>
					<th style="width:120px;">名称</th>
					<th style="width:100px;">模式</th>					
					<th style="width:100px;">尺寸</th>
					<th style="width:100px;">备注</th>
					<th style="width:100px;">状态</th>
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="adBoxInfo" >
					<tr> 
						<td>${adBoxInfo.adBoxInfoId}</td>
						<td>${adBoxInfo.adBoxInfoName}</td>
						<td>${adBoxInfo.modelName}</td>
						<td>${adBoxInfo.adSizeName}</td>
						<td>${adBoxInfo.adBoxInfoRemarks}</td>
						<td>
						<c:if test="${0==adBoxInfo.adBoxInfoStatus}">
						<a name="editStatebutton"  href="${pageContext.request.contextPath }/adboxinfo.do?action=edit&cid=${adBoxInfo.adBoxInfoId}&state=1&flag=editState" target="navTabTodo" ><span style="color:blue">正常</span></a>
						</c:if>				
						<c:if test="${1==adBoxInfo.adBoxInfoStatus}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/adboxinfo.do?action=edit&cid=${adBoxInfo.adBoxInfoId}&state=0&flag=editState" target="navTabTodo" ><span style="color:orange;">锁定</span></a>
						</c:if>	
						</td>
						<td>
						<a name="editbutton" title="修改规格" class="btnEdit" href="${pageContext.request.contextPath }/adboxinfo.do?action=edit&cid=${adBoxInfo.adBoxInfoId}&flag=edit" target="navTab" rel="editAdBoxInfo"><span style="color:blue">修改规格</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
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