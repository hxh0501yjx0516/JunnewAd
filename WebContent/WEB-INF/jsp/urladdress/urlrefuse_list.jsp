<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>域名黑名单</title> 
<script type="text/javascript">
function urlToExcel(){
	var qryurl = document.urlForm.qryurl.value;
	var qrywebmaster = document.urlForm.qrywebmaster.value;
	location.href = "${pageContext.request.contextPath}/urladdress.do?action=urlToExcel&qryurl="+qryurl+"&qrywebmaster="+qrywebmaster;
}
</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/urladdress.do?action=refuseList&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<!--【可选】查询条件-->
	<input type="hidden" name="qryurl" value="${qryurl }" />
	
  </form>
<body>
<div class="page">
<div class="pageHeader">
		<form name="urlForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/urladdress.do?action=refuseList" method="post">
		
		<div class="searchBar" >
			<ul class="searchContent" >
				<li>
					<label>域名地址关键字：</label>
					<input type="text" name="qryurl" value="${qryurl }"/>
				</li>
					<li>
					<div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div>
					<div class="buttonActive">
						<div class="buttonContent">
							<a href="${pageContext.request.contextPath }/urladdress.do?action=refuseList" target="navTab" title="域名黑名单" rel="域名黑名单"><button>重置</button></a>
						</div>
					</div>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="urlToExcel();">导出</button>
						</div>
					</div>
					</li>
			</ul>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/urladdress.do?action=refuseAdd" target="navTab" rel="添加黑名单"><span>添加黑名单</span></a></li>
			</ul>
		</div>
	
	<form method="post" action="${pageContext.request.contextPath }/urladdress.do?action=refuseList" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		
		<table class="table" layouth="113">
			<thead>
				<tr>
					<th style="width:60px;">编号</th>
					<th style="width:150px;">域名名称</th>
					<th style="width:200px;">域名地址</th>
					<th style="width:100px;">操作人</th>					
					<th style="width:200px;">日期</th>
					<th style="width:200px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="urlrefuse" >
					<tr> 
						<td>${urlrefuse.urlId}</td>
						<td>${urlrefuse.urlName}</td>
						<td>${urlrefuse.url}</td>
						<td>${urlrefuse.userName}</td>
						<td>${urlrefuse.addTime}</td>
						<td>
						<a name="editbutton" title="修改域名" class="btnEdit" href="${pageContext.request.contextPath }/urladdress.do?action=refuseEdit&urlid=${urlrefuse.urlId}&flag=edit" target="navTab" rel="editReadyBox"><span style="color:blue">修改</span></a>
						<a name="deletebutton" title="确定要删除吗？" class="btnDel" href="${pageContext.request.contextPath }/urladdress.do?action=refuseDel&urlid=${urlrefuse.urlId}" target="navTabTodo" ><span style="color:blue">删除</span></a>
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