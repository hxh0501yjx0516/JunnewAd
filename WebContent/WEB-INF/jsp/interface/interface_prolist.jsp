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
<title>单品成交</title> 
<script type="text/javascript">
function ToExcel(){
	var customerInfo =	document.ecForm2.customerInfo.value;
	var urlFlag =	document.ecForm2.urlFlag.value;
	var beginTime = document.ecForm2.beginTime.value;
	var endTime = document.ecForm2.endTime.value;
	location.href="${pageContext.request.contextPath}/interface.do?action=toExcel2&customerInfo="+customerInfo+"&urlFlag="+urlFlag+"&beginTime="+beginTime+"&endTime="+endTime;
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
		<form name="ecForm2" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/interface.do?action=proList" method="post">
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
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/interface.do?action=proList" target="navTab" rel="单品成交" title="单品成交"><button>重置</button></a></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><button type="button" onclick="ToExcel();">导出报表</button></div>
					</div>
					</li>
				</ul>	</td>
		</tr>
		</table>
	
		</form>
	</div>
	<div class="pageContent">
	
	<form method="post" action="${pageContext.request.contextPath}/interface.do?action=proList" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="79">
			<thead>
				<tr>
					
					<th style="width:500px;">单品名称</th>
					<th style="width:100px;">成交数量</th>
					
					
				</tr>

			</thead>
			<tbody>
				<c:forEach items="${proList}" var="l" >
					<tr> 						
						<td>${l.proName }</td>
						<td><font color=blue>${l.proNum }</font></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</form>
	</div>
</div>
</body>
 </html>