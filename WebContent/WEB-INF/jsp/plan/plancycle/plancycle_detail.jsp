<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>广告计划周期流量详细</title> 
</head>
<body>
<div class="page">
	<div class="pageContent">
	<h3 class="contentTitle">
							所属广告计划周期：${adPlanCycle.adPlanCycleName}
						</h3>
	<form method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="75">
			<thead>
				<tr>
					<th style="width:50px;">编号</th>
					<th style="width:100px;">排期时间</th>
					<th style="width:100px;">
					排期流量(
					<span style="font-weight: bold;">
					<c:choose>
					<c:when test="${adPlanCycle.showType == 1}">
						UV
					</c:when>
					<c:when test="${adPlanCycle.showType == 2}">
						PV
					</c:when>
					<c:when test="${adPlanCycle.showType == 3}">
						IP
					</c:when>
					</c:choose>
					</span>)
							</th>
					<th style="width:100px;">
					实际流量(
					<span style="font-weight: bold;">
					<c:choose>
					<c:when test="${adPlanCycle.showType == 1}">
						UV
					</c:when>
					<c:when test="${adPlanCycle.showType == 2}">
						PV
					</c:when>
					<c:when test="${adPlanCycle.showType == 3}">
						IP
					</c:when>
					</c:choose>
					</span>)
					</th>
				</tr>
			</thead>
			<tbody>
					<tr> 
						<td style="color:blue;font-weight: bold;"> 汇总： </td>
						<td>
						</td>
						<td style="color:blue;font-weight: bold;">
						<fmt:formatNumber value="${adPlanCycle.flowList}" pattern="#,###.###"/>
						</td>
						<td style="color:blue;font-weight: bold;">
						<fmt:formatNumber value="${adPlanCycle.realFlowList}" pattern="#,###.###"/>
						</td>
					</tr>
				<c:forEach items="${adPlanCycle.dayFlow}" var="plancycle" varStatus="s">
					<tr> 
						<td>${s.count}</td>
						<td>
						<fmt:parseDate value="${plancycle.dateTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd"/>
						</td>
						<td>
						<fmt:formatNumber value="${plancycle.preFlow}" pattern="#,###.###"/>
						</td>
						<td>
						<fmt:formatNumber value="${plancycle.realFlow}" pattern="#,###.###"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</form>
	</div>
</div>
</body>
 </html>