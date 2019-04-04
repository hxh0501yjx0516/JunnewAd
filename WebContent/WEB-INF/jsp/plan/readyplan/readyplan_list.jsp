<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>广告计划与媒体管理</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/readyplan.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" />
	<input type="hidden" name="qrystate" value="${qrystate }" />
	<input type="hidden" name="webMasterId" value="${webMasterId }" />
  </form>
<body>
<div class="page">
<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/readyplan.do?action=list" method="post">
			<input type="hidden" name="webMasterId" value="${webMasterId }" />
		<div class="searchBar" >
			<ul class="searchContent">
				<li >
					<label>计划名称：</label>
					<input type="text" name="qryname" value="${qryname }"/>
				</li>
				<li >
					<label>状态：</label>
					<select  name="qrystate" class="">
						<option value="" ${qrystate eq ""?"selected":""}>请选择</option>
						<option value="0" ${qrystate eq 0?"selected":""}>正常</option>
						<option value="1" ${qrystate eq 1?"selected":""}>锁定</option>
					</select>
				</li>
					<li><div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/readyplan.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		 <div class="panelBar">
			<ul class="toolBar">
			<li><a class="add" href="${pageContext.request.contextPath }/readyplan.do?action=add&webMasterId=${webMasterId}" target="navTab" rel="addReadyPlan"><span>添加广告计划</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="113">
			<thead>
				<tr>
					<th style="width:120px;">编号</th>
					<th style="width:120px;">广告计划名称</th>
					<th style="width:100px;">所属网站主</th>					
					<th style="width:100px;">添加时间</th>
					<th style="width:100px;">状态</th>
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="readyPlan" >
					<tr> 
						<td>${readyPlan.readyPlanId}</td>
						<td>${readyPlan.adPlanName}</td>
						<td>${readyPlan.webMasterName}</td>
						<td>
						<fmt:parseDate value="${readyPlan.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>
						<td>
						<c:if test="${0==readyPlan.readyPlanStatus}">
						<a name="editStatebutton" title="锁定广告计划，将会停止所有该计划的投放，是否确定？"    href="${pageContext.request.contextPath }/readyplan.do?action=edit&readyPlanId=${readyPlan.readyPlanId}&readyPlanState=1&flag=editState" target="navTabTodo" ><span style="color:blue">正常</span></a>
						</c:if>				
						<c:if test="${1==readyPlan.readyPlanStatus}">
						<a name="editStatebutton" title="开启广告计划，该计划的投放需手动开启，是否确定？"    href="${pageContext.request.contextPath }/readyplan.do?action=edit&readyPlanId=${readyPlan.readyPlanId}&readyPlanState=0&flag=editState" target="navTabTodo" ><span style="color:orange">锁定</span></a>
						</c:if>
						</td>
						<td>
						<a name="editbutton" title="修改广告计划" class="btnEdit" href="${pageContext.request.contextPath }/readyplan.do?action=edit&readyPlanId=${readyPlan.readyPlanId}&flag=edit" target="navTab" rel="editAdPlan"><span style="color:blue">修改广告计划</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
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