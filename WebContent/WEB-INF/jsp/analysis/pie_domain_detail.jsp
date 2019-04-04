<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();%>  
<%@ include file="/util/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
	<script language="JavaScript"
			src="${pageContext.request.contextPath }/charts/FusionCharts.js">
	</script>
	<script type="text/javascript">
		function downExcel()
		{
			var from = document.getElementById("addTime1").value;
			var to = document.getElementById("addTime2").value;
			var domain1 = document.getElementById("domain1").value;
			alert(domain1);
			var isaccept1 = document.getElementById("isaccept1").value;
			//alert(from);
			window.location = "?action=downLoadExcel&addTimef="+from+"&addTimet="+to+"&domain="+domain1+"&isaccept="+isaccept1;
		}
	</script>

</head>
<!-- 分页 -->
<form id="pagerForm" method="post" action="${pageContext.request.contextPath }/dana.do?action=pieDomainDetailAction&flag=pager" >
<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
<input type="hidden" name="addTimef" value="${addTimef }" /><!--【可选】查询条件-->
<input type="hidden" name="addTimet" value="${addTimet }" /><!--【可选】查询条件-->
<input type="hidden" name="webmaster" value="${webmaster }" /><!--【可选】查询条件-->
<input type="hidden" name="url" value="${url }" /><!--【可选】查询条件-->
<input type="hidden" name="userid" value="${userid }" /><!--【可选】查询条件-->
<input type="hidden" name="isaccept" value="${isaccept }" /><!--【可选】查询条件-->
<input type="hidden" name="domain" value="${domain }" /><!--【可选】查询条件-->
</form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/dana.do?action=pieDomainDetailAction" method="post">
		<input type="hidden" id="adPlanCycleId1" value="${adPlanCycleId}"/>
		<div class="searchBar">
			<ul >		
				<li> 
					时间： 
					<input type="text" name="addTimef" id="addTime1" class="date" value="${addTimef}" readonly size="8"/>
					--
					<input type="text" name="addTimet" id="addTime2" class="date" value="${addTimet}" readonly size="8"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					域名：
					<input type="text" id="domain1"  name="domain" value="${domain }" size="30" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					是否合法来源：
					<select id="isaccept1" name="isaccept">
						<option value=-1>全部</option>
						<option value=1 <c:if test="${isaccept==1}"> selected </c:if>>绑定 </option>
						<option value=0 <c:if test="${isaccept==0}"> selected </c:if>>非绑定 </option>
					</select>
				</li>
				
			</ul>
			<div class="subBar">
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div>
				</div>
						
				&nbsp;&nbsp;&nbsp;&nbsp;		
				<a href="<html:rewrite action='/dana'/>?action=downLoadExcel&addTimef=${addTimef }&addTimet=${addTimet }&domain=${domain }&isaccept=${isaccept }"><font color="blue">导出Excel</font></a>					
			
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/dana.do?action=pieDomainDetailAction" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="400">
			<thead>
				<tr>
				<th style="width:100px;">域名</th>
				<!-- th style="width:100px;">显示（PV）</th>	
				<th style="width:100px;">显示（UV）</th-->
				<th style="width:100px;">PV</th>
				<th style="width:100px;">UV</th>
				<th style="width:120px;">IP</th>

			</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="adboxc" >
				<tr> 
					<td><c:if test="${adboxc[0]==null }">其他</c:if>
					<c:if test="${adboxc[0]!=null }">${adboxc[0]}</c:if>
					</td>
					<td>${adboxc[1]}</td>
					<td>${adboxc[2]}</td>
					<td>${adboxc[3]}</td>

				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<c:if test="${numPerPage==20}"><option value="20" selected>20</option></c:if>
					<c:if test="${numPerPage!=20}"><option value="20" >20</option></c:if>
					<c:if test="${numPerPage==50}"><option value="50" selected>50</option></c:if>
					<c:if test="${numPerPage!=50}"><option value="50" >50</option></c:if>		
					<c:if test="${numPerPage==100}"><option value="100" selected>100</option></c:if>
					<c:if test="${numPerPage!=100}"><option value="100" >100</option></c:if>
					<c:if test="${numPerPage==200}"><option value="200" selected>200</option></c:if>
					<c:if test="${numPerPage!=200}"><option value="200" >200</option></c:if>
				</select>
				<span>条，共${totalCount}条</span>
			</div>
			<!-- 分页 -->
			<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage }" pageNumShown="10" currentPage="${pageNum }"></div>

		</div>
		<table align="center" width="95%">
			<tr >
			<tr >
				<td>								
					<div id="chartdivm" align="center">
						FusionCharts.
					</div>
					<script type="text/javascript">
						var chart = new FusionCharts("${pageContext.request.contextPath}/charts/FCF_MSLine.swf", "ChartId", "600", "300");
						chart.setDataXML("${dataXMLDomainLine}");	   
						chart.render("chartdivm");
					</script>
				</td>
				<td colspan="3">
					<div id="chartdivpSource" align="center">
						FusionCharts.
					</div>
					<script type="text/javascript"> 
						var chart = new FusionCharts("${pageContext.request.contextPath}/charts/Chart/Pie2D.swf", "ChartId", "500", "300");
						chart.setDataXML("${dataXMLp}");	   
						chart.render("chartdivpSource");
					</script>
				</td>
			</tr>
		</table>
	</form>
	</div>
</div>
</body>
 </html>