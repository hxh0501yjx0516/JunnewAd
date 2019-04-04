<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>广告计划管理</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/plan.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" />
	<input type="hidden" name="qrystate" value="${qrystate }" />
	<input type="hidden" name="qrycustomer" value="${qrycustomer }" />
	<input type="hidden" name="resource" value="${resource }" />
	<input type="hidden" name="currentusergroup" value="${currentusergroup }" />
  </form>
<body>
<div class="page">
<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/plan.do?action=list" method="post">
		
		<div class="searchBar" >
			<ul class="searchContent">
				<li >
					<label>计划名称：</label>
					<input type="text" name="qryname" value="${qryname }"/>
				</li>
				<c:choose>
				<c:when test="${resource == ''}">
				<li >
					<label>所属客户：</label>
					<select  name="qrycustomer" class="">
						<option value="" ${qrystate eq ""?"selected":""}>请选择</option>
						<c:forEach items="${customerList}" var="customer">
						<option value="${customer.customerId}" ${customer.customerId == qrycustomer?"selected":""}>${customer.customerName}</option>
						</c:forEach>
					</select>
				</li>
				</c:when>
				<c:otherwise>
				<input type="hidden" name="resource" value="${resource }" />
				<input type="hidden" name="qrycustomer" value="${qrycustomer }" />
				</c:otherwise>
				</c:choose>
				
				<li >
					<label>状态：</label>
					<select  name="qrystate" class="">
						<option value="" ${qrystate eq ""?"selected":""}>请选择</option>
						<option value="0" ${qrystate eq 0?"selected":""}>正常</option>
						<option value="1" ${qrystate eq 1?"selected":""}>锁定</option>
					</select>
				</li>
				<c:if test="${usergroup eq 0}">
				<li >
					<label>用户组：</label>
					<select  name="currentusergroup" class="">
						<option value="0">全部</option>
						<c:forEach items="${userGroupList}" var="ug">
							<option value="${ug.id }" ${currentusergroup eq ug.id?"selected":"" }>${ug.userGroupName }</option>
						</c:forEach>
					</select>
				</li>
				</c:if>
					<li><div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/plan.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	<c:if test="${resource == ''}">
		 <div class="panelBar">
			<ul class="toolBar">
			<li><a class="add" href="${pageContext.request.contextPath }/plan.do?action=add" target="navTab" rel="addAdPlan"><span>添加广告计划</span></a></li>
			</ul>
		</div>
		</c:if>
		<table class="table" layouth="${resource == ''?113:88}">
			<thead>
				<tr>
					<th style="width:40px;">编号</th>
					<th style="width:200px;">广告计划名称</th>
					<th style="width:200px;">所属客户</th>		
					<th style="width:100px;">客户返点</th>			
					<th style="width:100px;">添加时间</th>
					<th style="width:100px;">广告周期</th>
					<th style="width:100px;">状态</th>
					<th style="width:100px;">标识</th>
					<c:if test="${resource == ''}">
					<th style="width:200px">操作</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="plan" >
					<tr> 
						<td>${plan.adPlanId}</td>
						<td>${plan.adPlanName}</td>
						<td>${plan.customerName}</td>
						<td>${plan.adPlanRebate}%</td>
						<td>
						<fmt:parseDate value="${plan.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>
						<c:choose>
						<c:when test="${resource == ''}">
						<td>
						<a  title="广告计划周期"  href="${pageContext.request.contextPath }/plancycle.do?action=list&adPlanId=${plan.adPlanId}" target="navTab" rel="planCycleList"><span style="color:orange">${plan.cycleCount}个</span></a>
						<a class="add" href="${pageContext.request.contextPath }/plancycle.do?action=add&adPlanId=${plan.adPlanId}" target="navTab" rel="addAdCycle"><span style="color:blue;">添加周期</span></a>
						</td>
						
						<td>
						<c:if test="${0==plan.adPlanStatus}">
						<a name="editStatebutton" title="锁定广告计划，将会停止所有该计划的投放，是否确定？"   href="${pageContext.request.contextPath }/plan.do?action=edit&adPlanId=${plan.adPlanId}&adPlanState=1&flag=editState" target="navTabTodo" ><span style="color:blue">正常</span></a>
						</c:if>				
						<c:if test="${1==plan.adPlanStatus}">
						<a name="editStatebutton" title="开启广告计划，该计划的投放需手动开启，是否确定？"   href="${pageContext.request.contextPath }/plan.do?action=edit&adPlanId=${plan.adPlanId}&adPlanState=0&flag=editState" target="navTabTodo" ><span style="color:orange">锁定</span></a>
						</c:if>
						</td>
						<td>
						<c:if test="${0==plan.adPlanFlag}">
						<a name="editFlagbutton"   href="${pageContext.request.contextPath }/plan.do?action=edit&adPlanId=${plan.adPlanId}&adPlanFlag=1&flag=editFlag" target="navTabTodo" ><span style="color:blue">显示</span></a>
						</c:if>				
						<c:if test="${1==plan.adPlanFlag}">
						<a name="editFlagbutton"   href="${pageContext.request.contextPath }/plan.do?action=edit&adPlanId=${plan.adPlanId}&adPlanFlag=0&flag=editFlag" target="navTabTodo" ><span style="color:orange">隐藏</span></a>
						</c:if>
						</td>
						<td>
						<a name="editbutton" title="修改广告计划" class="btnEdit" href="${pageContext.request.contextPath }/plan.do?action=edit&adPlanId=${plan.adPlanId}&flag=edit" target="navTab" rel="editAdPlan"><span style="color:blue">修改广告计划</span></a>&nbsp;
						<a  href="${pageContext.request.contextPath }/dana.do?action=sourceDetailAction&adPlanId=${plan.adPlanId}" target="navTab" rel="sourceDetail" style="color:blue">来源表</a>&nbsp;
						<a href="${pageContext.request.contextPath }/dana.do?action=allDetailAction&adPlanId=${plan.adPlanId}" target="navTab" rel="allDetail" style="color:blue">曲线图</a>&nbsp;
						<a href="${pageContext.request.contextPath }/dana.do?action=pieDetailAction&adPlanId=${plan.adPlanId}" target="navTab" rel="pieDetail" style="color:blue">地区分布</a>&nbsp;
						</td>
						</c:when>
						<c:otherwise>
						<td>
						<a  title="广告计划周期"  href="${pageContext.request.contextPath }/plancycle.do?action=list&adPlanId=${plan.adPlanId}&resource=${resource}" target="navTab" rel="planCycleList"><span style="color:orange">${plan.cycleCount}个</span></a>
						</td>
						<td>
						<c:if test="${0==plan.adPlanStatus}">
						<span style="color:blue">正常</span>
						</c:if>				
						<c:if test="${1==plan.adPlanStatus}">
						<span style="color:orange">锁定</span>
						</c:if>
						</td>
						<td>
						<c:if test="${0==plan.adPlanFlag}">
						<span style="color:blue">显示</span>
						</c:if>				
						<c:if test="${1==plan.adPlanFlag}">
						<span style="color:orange">隐藏</span>
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