<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>广告计划周期管理</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/plancycle.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="adPlanId" value="${adPlanId }" />
	<input type="hidden" name="resource" value="${resource }" />
		<input type="hidden" name="customerId" value="${customerId }" />
  </form>
<body>
<div class="page">
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/plancycle.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	<c:if test="${resource ==''}">
		 <div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/plancycle.do?action=add&adPlanId=${adPlanId}" target="navTab" rel="addAdCycle"><span>添加计划周期</span></a></li>
			</ul>
		</div>
	</c:if>
		<table class="table" layouth="${resource == ''?"75":"48"}">
			<thead>
				<tr>
					<th style="width:50px;">编号</th>
					<th style="width:140px;">周期名称</th>					
					<th style="width:80px;">起始时间</th>
					<th style="width:80px;">结束时间</th>
					<th style="width:60px;">客户单价</th>
					<th style="width:80px">预算</th>
					<th style="width:80px">媒体最低价</th>
					<th style="width:60px">扣量</th>
					<th style="width:60px">显示类型</th>
					<th style="width:120px">所属广告计划</th>
					<c:if test="${resource ==''}">
					<th style="width:100px">广告创意</th>
					</c:if>
					<th style="width:40px">传参</th>
					<th style="width:40px">状态</th>
					<c:if test="${resource ==''}">
					<th style="width:220px">操作</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="plancycle" >
					<tr> 
						<td>${plancycle.adPlanCycleId}</td>
						<td>${plancycle.adPlanCycleName}</td>
						<td>
						<fmt:parseDate value="${plancycle.beginTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>
						<td>
						<fmt:parseDate value="${plancycle.endTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>
						<td>
						￥<fmt:formatNumber value="${plancycle.customerPrice}" pattern="#,###.###"/>
						</td>
						<td>
						￥<fmt:formatNumber value="${plancycle.customerAllPrice}" pattern="#,###.###"/>
						</td>
						<td>
						￥<fmt:formatNumber value="${plancycle.webMasterPrice}" pattern="#,###.###"/>
						</td>
						<td>${plancycle.disCount}%</td>
						<td>
						<c:choose>
							<c:when test="${plancycle.showType == 1}">
								UV
							</c:when>
							<c:when test="${plancycle.showType == 2}">
								PV
							</c:when>
							<c:when test="${plancycle.showType == 3}">
								IP
							</c:when>
						</c:choose>
						</td>
						<td>${plancycle.adPlanName}</td>
						<c:choose>
						<c:when test="${resource == ''}">
						<td>
						<a  title="广告创意列表"  href="${pageContext.request.contextPath }/adcreative.do?action=list&adPlanCycleId=${plancycle.adPlanCycleId}&adPlanId=${adPlanId}" target="navTab" rel="广告创意列表"><span style="color:orange">${plancycle.creativeCount}个</span></a>
						<a class="add" href="${pageContext.request.contextPath }/adcreative.do?action=add&adPlanId=${adPlanId}&adPlanCycleId=${plancycle.adPlanCycleId}" target="navTab" title="添加广告创意" rel="addCreative"><span style="color:blue;">添加创意</span></a>
						</td>
						<td>
						<c:if test="${0==plancycle.adPlanIsParameter}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/plancycle.do?action=edit&adPlanCycleId=${plancycle.adPlanCycleId}&adPlanCycleFlag=1&flag=editFlag" target="navTabTodo" ><span style="color:blue">是</span></a>
						</c:if>				
						<c:if test="${1==plancycle.adPlanIsParameter}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/plancycle.do?action=edit&adPlanCycleId=${plancycle.adPlanCycleId}&adPlanCycleFlag=0&flag=editFlag" target="navTabTodo" ><span style="color:orange">否</span></a>
						</c:if>
						</td>
						<td>
						<c:if test="${0==plancycle.adPlanCycleStatus}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/plancycle.do?action=edit&adPlanCycleId=${plancycle.adPlanCycleId}&adPlanCycleStatus=1&flag=editState" target="navTabTodo" ><span style="color:blue">正常</span></a>
						</c:if>				
						<c:if test="${1==plancycle.adPlanCycleStatus}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/plancycle.do?action=edit&adPlanCycleId=${plancycle.adPlanCycleId}&adPlanCycleStatus=0&flag=editState" target="navTabTodo" ><span style="color:orange">锁定</span></a>
						</c:if>
						</td>
						<td>
						<a name="editbutton" title="修改计划周期" class="btnEdit" href="${pageContext.request.contextPath }/plancycle.do?action=edit&adPlanCycleId=${plancycle.adPlanCycleId}&flag=edit" target="navTab" rel="editAdPlanCycle"><span style="color:blue">修改计划周期</span></a>&nbsp;
						<a name="editbutton" href="${pageContext.request.contextPath }/plancycle.do?action=detail&adPlanCycleId=${plancycle.adPlanCycleId}" target="navTab" rel="detailDaily"><span style="color:blue">排期</span></a>&nbsp;
						<a  href="${pageContext.request.contextPath }/dana.do?action=sourceDetailAction&adPlanId=${adPlanId}&adPlanCycleId=${plancycle.adPlanCycleId}" target="navTab" rel="sourceDetail" style="color:blue">来源表</a>&nbsp;
						<a href="${pageContext.request.contextPath }/dana.do?action=allDetailAction&adPlanId=${adPlanId}&adPlanCycleId=${plancycle.adPlanCycleId}" target="navTab" rel="allDetail" style="color:blue">曲线图</a>&nbsp;
						<a href="${pageContext.request.contextPath }/dana.do?action=pieDetailAction&adPlanId=${adPlanId}&adPlanCycleId=${plancycle.adPlanCycleId}" target="navTab" rel="pieDetail" style="color:blue">地区分布</a>
						
						</td>
						</c:when>
						<c:otherwise>
						<td>
						<c:if test="${0==plancycle.adPlanIsParameter}">
						<span style="color:blue">是</span>
						</c:if>				
						<c:if test="${1==plancycle.adPlanIsParameter}">
						<span style="color:orange">否</span>
						</c:if>
						</td>
						<td>
						<c:if test="${0==plancycle.adPlanCycleStatus}">
						<span style="color:blue">正常</span>
						</c:if>				
						<c:if test="${1==plancycle.adPlanCycleStatus}">
						<span style="color:orange">锁定</span>
						</c:if>
						</td>
						</c:otherwise>
						</c:choose>
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