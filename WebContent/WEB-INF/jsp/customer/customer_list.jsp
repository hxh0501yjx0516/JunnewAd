<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>客户信息</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/customer.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
	<input type="hidden" name="resource" value="${resource }" />
	<input type="hidden" name="qryuserGroup" value="${qryuserGroup }" />
  </form>
<body>
<div class="page">
<c:if test="${userGroup eq 0}">
<div class="pageHeader">
		<form name="customerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/customer.do?action=list" method="post">
		<div class="searchBar" >
			<ul class="searchContent">
				<li>
				<label>用户组：</label>
					<select name="qryuserGroup">
						<option value="" >请选择</option>
						<c:forEach items="${userGroupList}" var="userGroup">
						<option value="${userGroup.id}" ${qryuserGroup eq userGroup.id?"selected":""}>${userGroup.userGroupName}</option>
						</c:forEach>
					</select>
				</li>
					<li>
					<div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div>
					</li>
			</ul>
		</div>
		</form>
	</div>
	</c:if>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/customer.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/customer.do?action=add" target="navTab" rel="添加客户"><span>添加客户</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="93">
			<thead>
				<tr>
					<th style="width:50px;" orderField="customerId" >编号</th>
					<th style="width:180px;">客户名</th>
					<th style="width:80px;">联系人</th>	
					<th style="width:100px;">联系电话</th>
					<th style="width:100px;">联系地址</th>
					<th style="width:100px;">网址</th>
					<th style="width:80px;">添加时间</th>
					<th style="width:80px;">所属销售</th>
					<th style="width:150px;">收款人</th>
					<th style="width:80px;">广告计划</th>
					<th style="width:80px;">计划周期</th>
					<th style="width:60px;">状态</th>
					<c:if test="${resource =='customer'}">
					<th style="width:100px">操作</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="customer" >
					<tr> 
						<td>${customer.customerId}</td>
						<td>${customer.customerName}</td>
						<td>${customer.customerContactName}</td>
						<td>${customer.customerContactMobile}</td>
						<td>${customer.customerAddress}</td>
						<td>${customer.customerUrl}</td>
						<td>
						<fmt:parseDate value="${customer.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>	
						<td>
						${customer.userId == 0?"暂未配置":customer.userName}
						</td>
						<td>${customer.payee}</td>
						<td><a name="boxbutton" title="广告计划列表" style="color:orange"  href="${pageContext.request.contextPath }/plan.do?action=list&qrycustomer=${customer.customerId}&resource=${resource}" target="navTab" rel="广告计划列表">${customer.planCount}个</a></td>	
						<td><a name="datebutton" title="计划周期列表" style="color:orange"   href="${pageContext.request.contextPath }/plancycle.do?action=list&customerId=${customer.customerId}&resource=${resource}" target="navTab" rel="计划周期列表">${customer.planCycleCount}个</a></td>	
					<td>
						<c:choose>
						<c:when test="${resource =='customer'}">
						<c:if test="${0==customer.customerStatus}">
						<a name="editStatebutton"  href="${pageContext.request.contextPath }/customer.do?action=edit&cid=${customer.customerId}&state=1&flag=editState" target="navTabTodo" ><span style="color:blue">正常</span></a>
						</c:if>				
						<c:if test="${1==customer.customerStatus}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/customer.do?action=edit&cid=${customer.customerId}&state=0&flag=editState" target="navTabTodo" ><span style="color:orange;">锁定</span></a>
						</c:if>	
						</c:when>
						<c:otherwise>
						<c:if test="${0==customer.customerStatus}">
						<span style="color:blue">正常</span>
						</c:if>
						<c:if test="${1==customer.customerStatus}">
						<span style="color:orange;">锁定</span>
						</c:if>
						</c:otherwise>
						</c:choose>
						
						</td>
							<c:if test="${resource =='customer'}">
						<td>
						<a name="editbutton" title="修改客户" class="btnEdit" href="${pageContext.request.contextPath }/customer.do?action=edit&cid=${customer.customerId}&flag=edit" target="navTab" rel="editCutomer"><span style="color:blue">修改客户</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						</c:if>
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