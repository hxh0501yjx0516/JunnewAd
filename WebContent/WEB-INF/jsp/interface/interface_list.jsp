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
<title>数据查询</title> 
<script type="text/javascript">
function ToExcel(){
	var customerInfo =	document.ecForm.customerInfo.value;
	var urlFlag =	document.ecForm.urlFlag.value;
	var beginTime = document.ecForm.beginTime.value;
	var endTime = document.ecForm.endTime.value;
	location.href="${pageContext.request.contextPath}/interface.do?action=toExcel&customerInfo="+customerInfo+"&urlFlag="+urlFlag+"&beginTime="+beginTime+"&endTime="+endTime;
}

</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/interface.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
	<input type="hidden" name="customerInfo" value="${customerInfo }" />
	<input type="hidden" name="beginTime" value="${beginTime }" />
	<input type="hidden" name="endTime" value="${endTime }" />
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form name="ecForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/interface.do?action=list" method="post">
		<input type="hidden" id="selectedId_demo"/>
		<table>
		<tr>
		<td>	<ul class="searchContent">
				<li>
					<label>客户信息：</label>
					<label><input name="customerInfo" value="${customerInfo }" type="text" size="10" /></label>
					<label>推广标识：</label>
					<label><input name="urlFlag" value="${urlFlag }" type="text" size="10" /></label>
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
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/interface.do?action=list" target="navTab" rel="数据查询" title="数据查询"><button>重置</button></a></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><button type="button" onclick="ToExcel();">导出报表</button></div>
					</div>
					<label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/interface.do?action=proList" target="navTab" rel="单品成交" title="单品成交" style="line-height:22px; color:blue">单品成交量</a>
					</label>
					</li>
				</ul>	</td>
		</tr>
		</table>
	
		</form>
	</div>
	<div class="pageContent">
	
	<form method="post" action="${pageContext.request.contextPath}/interface.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
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
		<div class="panelBar">
			<div class="pages">
				<%@ include file="/util/pager.jsp" %>
			</div>
		</div>
		
	</form>
	</div>
</div>
</body>
 </html>