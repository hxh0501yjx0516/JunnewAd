<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>数据分析</title> 
<script language="JavaScript" src="${pageContext.request.contextPath }/charts/FusionCharts.js"></script>
</head>

<body>
<div class="page">
<c:if test="${sessionScope.user.userGroup eq 0}">
 <div class="pageHeader">
		<form name="danaForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/dana.do?action=mainAction" method="post">
		<div class="searchBar" >
			<ul class="searchContent">
					<li>
					<label>用户组：</label>
					<select id="qryuserGroup" name="qryuserGroup">
						<option value="" >请选择</option>
						<c:forEach items="${userGroupList}" var="userGroup">
						<option value="${userGroup.id}" ${qryuserGroup == userGroup.id?"selected":""}>${userGroup.userGroupName}</option>
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
		<div class="panelBar">
			<span style="line-height: 26px;">
			&nbsp;&nbsp;&nbsp;&nbsp;今日流量概况&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/dana.do?action=allDetailAction" target="navTab" rel="allDetail" style="color:blue">>>详细</a>
			</span>
		</div>
		<table class="table" layouth=${sessionScope.user.userGroup==0?"432":"392"}>
			<thead>
				<tr>
				<th style="width:100px;"></th>	
				<th style="width:120px;">日期</th>
				<th style="width:100px;">显示（PV）</th>	
				<th style="width:100px;">显示（UV）</th>
				<th style="width:100px;">PV</th>
				<th style="width:100px;">UV</th>
				<th style="width:120px;">IP</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listA}" var="adboxc" >
				<tr> 
					<td>总计：</td>
					<td>
					<fmt:parseDate value="${adboxc[5]}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
					<fmt:formatDate value="${date}" type="date"  pattern="yyyy-MM-dd"/>
					</td>
					<td><fmt:formatNumber value="${adboxc[0]}" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${adboxc[1]}" pattern="#,###"/></td>
					<td><fmt:formatNumber value="${adboxc[2]}" pattern="#,###"/></td>
					<td><fmt:formatNumber value="${adboxc[3]}" pattern="#,###"/></td>
					<td><fmt:formatNumber value="${adboxc[4]}" pattern="#,###"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
		<div class="panelBar" style="line-height: 26px;">
			&nbsp;&nbsp;&nbsp;&nbsp;24小时流量趋势&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/dana.do?action=allDetailAction" target="navTab" rel="allDetail" style="color:blue">>>详细</a>
						
			<span style=" margin-left: 550px">地区分布</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/dana.do?action=pieDetailAction" target="navTab" rel="pieDetail" style="color:blue">>>详细</a>
		</div>	
		<table align="center" width="100%" >
			<tr >
				<td>				
				
					<div id="chartdiv" align="center">
						FusionCharts.
					</div>
					<script type="text/javascript">
						var chart = new FusionCharts("${pageContext.request.contextPath}/charts/FCF_MSLine.swf", "ChartId", "600", "300");
						chart.setDataXML("${dataXML}");		   
						chart.render("chartdiv");
					</script>
				</td>
				<td colspan="3">
					<div id="chartdivpie" align="center">
						FusionCharts.
					</div>
					<script type="text/javascript">
						var chart = new FusionCharts("${pageContext.request.contextPath}/charts/FCF_Pie2D.swf", "ChartId", "400", "300", "0", "0");
						chart.setDataXML("${dataXMLpie}");		   
						chart.render("chartdivpie");
					</script>
				</td>
			</tr>

		</table>
		
		<div class="panelBar" style="line-height: 26px;">
		&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/dana.do?action=sourceDetailAction" target="navTab" rel="sourceDetail" style="color:blue">【来源列表】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href=
		"${pageContext.request.contextPath }/snapshot.do?action=list" target="navTab" rel="sourceDetail" style="color:blue">【来源快照】</a>
		</div>
		</div>	
</body>
 </html>