<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>结算数据管理</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/databalance.do?action=details&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<c:choose>
		<c:when test="${payId !=null}">
		<input type="hidden" name="payId" value="${payId }" />
		</c:when>
		<c:otherwise>
		<input type="hidden" name="adPlanId" value="${adPlanId }" />
		<input type="hidden" name="webMasterId" value="${webMasterId }" />
		</c:otherwise>
	</c:choose>
  </form>
<body>
<div class="page">
	<div class="pageContent">
	<form method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="48">
			<thead>
				<tr>
					<th style="width:40px;">编号</th>
					<th style="width:120px;">网站主</th>
					<th style="width:100px;">所属媒介</th>					
					<th style="width:100px;">广告计划</th>
					<th style="width:100px;">返回值</th>
					<th style="width:100px;">应收</th>
					<th style="width:100px;">有效值</th>
					<th style="width:100px;">有效佣金</th>
					<th style="width:120px">日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dataBalance" varStatus="s">
					<tr> 
						<td>${dataBalance.reportId}</td>
						<td>${dataBalance.webMasterName}</td>
						<td>${dataBalance.userName}</td>
						<td>${dataBalance.adplanName}</td>
						<td><fmt:formatNumber value="${dataBalance.count}" pattern="#,###.###"/></td>
						<td>￥<fmt:formatNumber value="${dataBalance.money}" pattern="#,###.###"/></td>
						<td><fmt:formatNumber value="${dataBalance.realCount}" pattern="#,###.###"/></td>
						<td>￥<fmt:formatNumber value="${dataBalance.realMoney}" pattern="#,###.###"/></td>
						<td>
						<fmt:parseDate value="${dataBalance.reportTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd"/>
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