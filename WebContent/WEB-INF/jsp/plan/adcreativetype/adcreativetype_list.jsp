<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>广告创意类型列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/adcreativetype.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/adcreativetype.do?action=list" method="post">

		<table>
		<tr>
		<td>	<ul class="searchContent">
				<li>
					<label>主类型：</label>
					<select name="adCreativeTypeMid">
					<option value="0" selected>所有主类型</option>
					<c:forEach items="${adCreativeTypeFather}" var="f">
					<option value="${f.adCreativeTypeId}" ${adCreativeTypeMid eq f.adCreativeTypeId?"selected":""}>${f.adCreativeTypeName}</option>
					</c:forEach>
				</select>
				</li>
			</ul>	</td><td>	<ul>
					<li>
					<div class="buttonActive">
					<div class="buttonContent"><button type="submit">检索</button></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/adcreativetype.do?action=list" target="navTab" rel="adcreativetypeList"><button>重置</button></a></div>
					</div>
					</li>
				</ul>	</td>
		</tr>
		</table>
	
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/adcreativetype.do?action=list" onsubmit="return navTabSearch(this);">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/adcreativetype.do?action=add" target="navTab" rel="addCreativeType"><span>添加创意类型</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="114">
			<thead>
				<tr>
					<th style="width:100px;">编号</th>
					<th style="width:300px;">创意类型名称</th>
					<th style="width:100px;">所属类型</th>
					<th style="width:100px">父编号</th>
					<th style="width:100px">菜单类型</th>
					<th style="width:100px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l" >
					<tr> 
						<td>${l.adCreativeTypeId }</td>
						<td>${l.adCreativeTypeName }</td>
						<td>${l.adCreativeTypeMname }</td>
						<td>${l.adCreativeTypeMid }</td>
						<td>${l.adCreativeTypeTid eq 0?"<font color=blue>主类型</font>":"<font color=orange>子类型</font>" }</td>
						<td>
						<a name="editbutton" class="btnEdit" title="修改类型" href="${pageContext.request.contextPath }/adcreativetype.do?action=edit&adCreativeTypeId=${l.adCreativeTypeId }&flag=edit" target="navTab" rel="editAdCreativeype"><span style="color:blue">修改创意类型</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
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