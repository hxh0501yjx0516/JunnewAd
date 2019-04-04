<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>白(黑)名单列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/loginconfine.do?action=list&flag=pager" >
 	<input type="hidden" name="pageNum" value="1" /><!-- 【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!-- 【可选】每页显示多少条-->
	<!--	【可选】查询条件-->
  </form>
<body>
<div class="page">
<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/loginconfine.do?action=list" method="post">
	<div class="searchBar" >
			<ul class="searchContent">
				<li >
					<label>开始时间：</label>
					<input type="text" name="qrybegintime" class="date" style="width:80px"  value="${qrybegintime }" readonly="readonly"/>
				</li>
				<li >
					<label>结束时间：</label>
					<input type="text" name="qryendtime" class="date" style="width:80px"  value="${qryendtime }" readonly="readonly"/>
				</li>
				<li>
					<label>ip地址：</label>
					<input type="text" name="qryIp" value="${qryIp }"/>
				</li>
				<li>
					<label>类别：</label>
					<select  name="qrytype" class="">
					<option value="">请选择</option>
						<option value="0" ${qrytype == 0?"selected":""}>黑名单</option>
						<option value="1" ${qrytype == 1?"selected":""}>白名单</option>
					</select>
				</li>
					<li><div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
		</form>
		</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}}/loginconfine.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/loginconfine.do?action=add" target="navTab" rel="添加名单"><span>添加名单</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="93">
			<thead>
				<tr>
					<th style="width:80px;" >编号</th>
					<th style="width:100px;">IP地址</th>	
					<th style="width:120px;">描述</th>
					<th style="width:100px;">类型</th>	
					<th style="width:100px;">操作人</th>	
					<th style="width:100px;">添加时间</th>				
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="loginIp" >
					<tr> 
						<td>${loginIp.loginId}</td>
						<td>${loginIp.loginIp}</td>
						<td>${loginIp.loginName}</td>
						<td>
							<c:choose>
								<c:when test="${loginIp.loginType == 0}">
									<span style="color: orange;">黑名单</span>
								</c:when>
								<c:otherwise>
								白名单
								</c:otherwise>
							</c:choose>
						</td>
						<td>${loginIp.userId == 0?'系统添加':loginIp.userName}</td>
						<td>
						<fmt:parseDate value="${loginIp.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>
						<td>
						<a name="editbutton" title="修改名单" class="btnEdit" href="${pageContext.request.contextPath }/loginconfine.do?action=edit&uid=${loginIp.loginId}&flag=edit" target="navTab" rel="editLoginConfine"><span style="color:blue">修改</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
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