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
			src="${pageContext.request.contextPath }/charts/FusionCharts.js"></script>

<script type="text/javascript">
function getAdPlanCycleList(){
		$.ajax( {
		type : 'POST',
		url : "${pageContext.request.contextPath }/util.do?action=getAdPlanCycleList",
		data : {adPlanId : $('#adPlanId2').val(),qryuserGroup : $('#qryuserGroup1').val()},
		dataType : "json",
		cache : false,
		success : function(temp){
			$('#select3 option').remove(); 
			$("#select3").append($("<option>").text("全部").val("") );
			for(var i=0;i<temp.length;i++){ 
			_option = $("<option>").text(temp[i].adPlanCycleName).val(temp[i].adPlanCycleId);
			$("#select3").append(_option); 
			}
			if($("#adPlanCycleId2").val() !=""){
				$("#select3").attr('value' ,$("#adPlanCycleId2").val()); 	
			$("#adPlanCycleId2").val("");
			}
		}
	});
		
}
document.onload=getAdPlanCycleList();
</script>
</head>
<!-- 分页 -->
<form id="pagerForm" method="post" action="${pageContext.request.contextPath }/dana.do?action=allDetailAction&flag=pager" >
<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
<input type="hidden" name="addTimef" value="${addTimef }" /><!--【可选】查询条件-->
<input type="hidden" name="addTimet" value="${addTimet }" /><!--【可选】查询条件-->
<input type="hidden" name="webmaster" value="${webmaster }" /><!--【可选】查询条件-->
<input type="hidden" name="url" value="${url }" /><!--【可选】查询条件-->
<input type="hidden" name="userid" value="${userid }" /><!--【可选】查询条件-->
<input type="hidden" name="adPlanId" value="${adPlanId }" /><!--【可选】查询条件-->
<input type="hidden" name="adPlanCycleId" value="${adPlanCycleId }" /><!--【可选】查询条件-->
<input type="hidden" name="readyBoxId" value="${readyBoxId }" /><!--【可选】查询条件-->
<input type="hidden" name="qryuserGroup" value="${qryuserGroup }" /><!--【可选】查询条件-->

</form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/dana.do?action=allDetailAction" method="post">
		<input type="hidden" id="adPlanCycleId2" value="${adPlanCycleId}"/>
		<div class="searchBar">
			<ul >		
				<li> 
					时间： 
					<input type="text" name="addTimef" id="addTimef" class="date" value="${addTimef}" readonly size="8"/>
					--
					<input type="text" name="addTimet" id="addTimet" class="date" value="${addTimet}" readonly size="8"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					站长：
					<input type="text" name="webmaster" value="${webmaster }" size="8"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					域名：
					<input type="text"   name="url" value="${url }"  size="8" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					媒介：
					<select name="userid">
						<option value="">全部</option>
						<c:forEach items="${userlist}" var="ul">
							<option value="${ul.userId }" <c:if test="${userid==ul.userId}"> selected </c:if>>${ul.realname }</option>
						</c:forEach>					
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					计划：
					<select id="adPlanId2" name="adPlanId" style="width: 120px;" onchange="getAdPlanCycleList()">
						<option value="">全部</option>
						<c:forEach items="${adPlanlist}" var="ap">
							<option value="${ap.adPlanId }" <c:if test="${adPlanId==ap.adPlanId}"> selected </c:if>>${ap.adPlanName }</option>
						</c:forEach>					
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					周期：
					<select id="select3" name="adPlanCycleId" style="width: 120px;">
						<option value="">全部</option>
						<c:forEach items="${adPlanCyclelist}" var="apc">
							<option value="${apc.adPlanCycleId }" <c:if test="${adPlanCycleId==apc.adPlanCycleId}"> selected </c:if>>${apc.adPlanCycleName }</option>
						</c:forEach>					
					</select>
					<c:if test="${sessionScope.user.userGroup eq 0}">
					用户组：
					<select id="qryuserGroup1" name="qryuserGroup">
						<option value="" >请选择</option>
						<c:forEach items="${userGroupList}" var="userGroup">
						<option value="${userGroup.id}" ${qryuserGroup == userGroup.id?"selected":""}>${userGroup.userGroupName}</option>
						</c:forEach>
					</select>
					</c:if>
				</li>
				
			</ul>
			<div class="subBar">
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</div> 
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/dana.do?action=allDetailAction" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="400">
			<thead>
				<tr>
				<th style="width:120px;">日期</th>
				<th style="width:100px;">显示（PV）</th>	
				<th style="width:100px;">显示（UV）</th>
				<th style="width:100px;">PV</th>
				<th style="width:100px;">UV</th>
				<th style="width:120px;">IP</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="adboxc" >
				<tr> 
					<td>${adboxc[5]}</td>
					<td><fmt:formatNumber value="${adboxc[0]}" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${adboxc[1]}" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${adboxc[2]}" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${adboxc[3]}" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${adboxc[4]}" pattern="#,###" /></td>

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
		<table align="center" width="95%" layouth="100">
			<tr >
				<td colspan="3">
					<div id="chartdivd" align="center">
						FusionCharts.
					</div>
					<script type="text/javascript">
						var chart = new FusionCharts("${pageContext.request.contextPath}/charts/FCF_MSLine.swf", "ChartId", "800", "300");
						chart.setDataXML("${dataXML}");		   
						chart.render("chartdivd");
					</script>
				</td>
			</tr>
		</table>
	</form>
	</div>
</div>
</body>
 </html>