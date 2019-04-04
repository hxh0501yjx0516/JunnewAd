<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ include file="/util/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
	

	
<title>登陆日志</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/log.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
	<input type="hidden" id="begintime" name="begintime" value="${begintime }"/>
	<input type="hidden" id="endtime" name="endtime" value="${endtime }"/>
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/log.do?action=list" method="post">
		
		<table>
		<tr>
		<td>
		<ul class="searchContent">
				<li>
					<label>日期：</label>
					<input name="begintime" value="${begintime }" class="date" pattern="yyyy-MM-dd" readonly="true"  type="text" size="8" /> --
					<input name="endtime" value="${endtime }" class="date" pattern="yyyy-MM-dd" readonly="true"  type="text" size="8" />
				</li>
			</ul>
		</td><td>
			<ul>
			<li>&nbsp;&nbsp;<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>

		</td>
		</tr>
		</table>
	
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/log.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="104">
			<thead>
				<tr>
					<th style="width:50px;">编号</th>
					<th style="width:100px;">登陆类型</th>
					<th style="width:100px;">用户名</th>
					<th style="width:100px;">姓名</th>
					<th style="width:200px;">地址</th>
					<th style="width:100px;">省市</th>					
					<th style="width:100px;">IP</th>
					<th style="width:60px;">登陆状态</th>
					<th style="width:140px;">日期</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l" >
					<tr> 						
						<td>${l.logId }</td>
						<td>${l.logUserType}</td>
						<td>${l.logUserName}</td>
						<td>${l.logRealName}</td>
						<td>${l.logSource}</td>
						<td>${l.ipAddress}</td>
						<td>${l.logIp}</td>
						<td>
						<c:choose>
						<c:when test="${l.logUserFlag eq 0}">
							<font color=black>未验证登陆</font>
						</c:when>
						<c:when test="${l.logUserFlag eq 1}">
							<font color=red>失败</font>
						</c:when>
						<c:when test="${l.logUserFlag eq 2}">
							<font color=orange>无用户</font>
						</c:when>
						<c:when test="${l.logUserFlag eq 3}">
							<font color=black>已锁定</font>
						</c:when>
						<c:when test="${l.logUserFlag eq 4}">
							<font color=red>usbkey验证失败</font>
						</c:when>
						<c:when test="${l.logUserFlag eq 5}">
							<font color=blue>usbkey验证成功</font>
						</c:when>
						<c:when test="${l.logUserFlag eq 6}">
							<font color=red>短信验证失败</font>
						</c:when>
						<c:when test="${l.logUserFlag eq 7}">
							<font color=blue>短信验证成功</font>
						</c:when>
						<c:when test="${l.logUserFlag eq 8}">
							<font color=blue>内部登陆</font>
						</c:when>
						<c:otherwise>
							<font color=black >未知</font>
						</c:otherwise>
						</c:choose>
						</td>
						<td>${l.logTime }</td>
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