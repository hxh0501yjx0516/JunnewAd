<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();
%>  
<%@ include file="/util/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
	

	
<title>数据获取</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/interface.do?action=get&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/interface.do?action=get&flag=pager" method="post">
		<input type="hidden" id="selectedId_demo"/>
		<table>
		<tr>
		<td>	<ul class="searchContent">
				<li>
					<label>开始日期：</label>
					<label><input name="beginTime" value="${beginTime }" class="date" pattern="yyyy-MM-dd" readonly="true"  type="text" size="10" /></label>
					<label>结束日期：</label>
					<label><input name="endTime" value="${endTime }" class="date"  pattern="yyyy-MM-dd" readonly="true" type="text" size="10" /></label>
				</li>
			</ul>	</td><td>	<ul>
					<li>
					<div class="buttonActive">
					<div class="buttonContent"><button type="submit">检索</button></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/interface.do?action=get" target="navTab" rel="数据获取" title="数据获取"><button>重置</button></a></div>
					</div>
					<div class="buttonActive" style="display:${isMedia eq true?"none":"block" }">
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/interface.do?action=get&flag=save&beginTime=${beginTime}&endTime=${endTime}&urlflag=${urlflag}" target="ajaxTodo" rel="数据获取" title="数据保存"><button  ${dataExists eq 'none'?"disabled":"" }>数据保存</button></a></div>
					</div>
					</li>
				</ul>	</td>
		</tr>
		</table>
	
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/interface.do?action=get" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="89">
			<thead>
				<tr>
					
					<th style="width:80px;">客户信息</th>
					<th style="width:80px;">推广标识</th>
					<th style="width:150px;">订单编号</th>
					<th style="width:200px;">订单商品</th>
					<th style="width:200px;">订单金额</th>
					<th style="width:80px;">总额</th>
					<th style="width:80px;">订单状态</th>				
					<th style="width:150px;">订单日期</th>
					
				</tr>

			</thead>
			<tbody>
				<c:forEach items="${ec}" var="l" >
					<tr> 						
						<td>${l.customerInfo }</td>
						<td>${l.urlFlag }</td>
						<td>${l.orderId }</td>
						<td><a title="${l.productName }">${fn:substring(l.productName,0,20) }</a></td>
						<td><a title="${l.amount }">${fn:substring(l.amount,0,20) }</a></td>
						<td>${l.allAmount }</td>
						<td>${l.orderStatus }</td>
						<td>${l.addTime }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</form>
	</div>
</div>
</body>
 </html>