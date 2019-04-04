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
<style type="text/css">
.tab {
	border:1px solid blue; /* 表格边框 */
	border-collapse:collapse; /* 边框重叠 */
	line-height:18px;
	}
.tab td{
	font-size: 13px;
	border:1px solid blue; /* 表格边框 */
	border-collapse:collapse; /* 边框重叠 */
	}
.tab th{
	font-size: 13px;
	font-weight: bold;
	border:1px solid blue; /* 表格边框 */
	border-collapse:collapse; /* 边框重叠 */	
	background-color:#eeeeee;
}
/**table中连续的数字和英文字符换行*/
#wrap{word-break:break-all; width:400px;overflow:auto;}

</style>
<script type="text/javascript">
function getAdPlanCycleList(){
		$.ajax( {
		type : 'POST',
		url : "${pageContext.request.contextPath }/util.do?action=getAdPlanCycleList",
		data : {adPlanId : $('#adPlanId').val(),qryuserGroup : $('#qryuserGroup1').val()},
		dataType : "json",
		cache : false,
		success : function(temp){
			$('#select1 option').remove(); 
			$("#select1").append($("<option>").text("全部").val("") );
			for(var i=0;i<temp.length;i++){ 
			_option = $("<option>").text(temp[i].adPlanCycleName).val(temp[i].adPlanCycleId);
			$("#select1").append(_option); 
			}
			if($("#adPlanCycleId").val() !=""){
				$("#select1").attr('value' ,$("#adPlanCycleId").val()); 	
			$("#adPlanCycleId").val("");
			}
		}
	});
		
}
document.onload=getAdPlanCycleList();

function reportExcel(){
	var addTimef = document.pagerForm.addTimef.value;
	var addTimet = document.pagerForm.addTimet.value;
	var webmaster = document.pagerForm.webmaster.value;
	var url = document.pagerForm.url.value;
	var userid = document.pagerForm.userid.value;
	var adPlanId = document.pagerForm.adPlanId.value;
	var adPlanCycleId = document.pagerForm.adPlanCycleId.value;
	var qryuserGroup="";
	//if(${sessionScope.user.userGroup} ==0){
	//	qryuserGroup = document.pagerForm.qryuserGroup.value;
	//}
	
	location.href="${pageContext.request.contextPath}/dana.do?action=sourceDetailReport" +
	"&addTimef="+addTimef+
	"&addTimet="+addTimet+
	"&webmaster="+webmaster+
	"&url="+url+
	"&userid="+userid+
	"&adPlanId="+adPlanId+
	"&adPlanCycleId="+adPlanCycleId+
	"&qryuserGroup="+qryuserGroup;
}
</script>
</head>
<!-- 分页 -->
<form id="pagerForm" name="pagerForm" method="post" action="${pageContext.request.contextPath }/dana.do?action=sourceDetailAction&flag=pager" >
<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
<input type="hidden" id="addTimef"  name="addTimef" value="${addTimef }" /><!--【可选】查询条件-->
<input type="hidden" id="addTimet"  name="addTimet" value="${addTimet }" /><!--【可选】查询条件-->
<input type="hidden" id="webmaster"  name="webmaster" value="${webmaster }" /><!--【可选】查询条件-->
<input type="hidden" id="url"  name="url" value="${url }" /><!--【可选】查询条件-->
<input type="hidden" id="userid"  name="userid" value="${userid }" /><!--【可选】查询条件-->
<input type="hidden" id="adPlanId"  name="adPlanId" value="${adPlanId }" /><!--【可选】查询条件-->
<input type="hidden" id="adPlanCycleId"  name="adPlanCycleId" value="${adPlanCycleId }" /><!--【可选】查询条件-->
<input type="hidden" name="qryuserGroup" value="${qryuserGroup }" /><!--【可选】查询条件-->
</form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/dana.do?action=sourceDetailAction" method="post">
		<input type="hidden" id="adPlanCycleId" value="${adPlanCycleId}"/>
		<div class="searchBar">
			<ul >		
				<li> 
					时间： 
					<input type="text" name="addTimef" id="addTimef" class="date" value="${addTimef}" size="8" readonly/>
					--
					<input type="text" name="addTimet" id="addTimet" class="date" value="${addTimet}" size="8" readonly/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					站长：
					<input type="text" name="webmaster" value="${webmaster }" size="8"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					域名：
					<input type="text"   name="url" value="${url }" size="8"  />
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
					<select id="adPlanId" name="adPlanId" style="width: 120px;" onchange="getAdPlanCycleList()">
						<option value="">全部</option>
						<c:forEach items="${adPlanlist}" var="ap">
							<option value="${ap.adPlanId }" <c:if test="${adPlanId==ap.adPlanId}"> selected </c:if>>${ap.adPlanName }</option>
						</c:forEach>					
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					周期：
					<select id="select1" name="adPlanCycleId" style="width: 120px;">
						<option value="">全部</option>
						<c:forEach items="${adPlanCyclelist}" var="apc">
							<option value="${apc.adPlanCycleId }" <c:if test="${adPlanCycleId==apc.adPlanCycleId}"> selected </c:if>>${apc.adPlanCycleName }</option>
						</c:forEach>					
					</select>
				</li>
				
			</ul>
			<div class="subBar">
				<div class="buttonActive">
				<div class="buttonContent"><button type="submit">检索</button></div>
				
				</div>
				<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="reportExcel();">报表导出</button> 
						</div>
					</div>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/dana.do?action=sourceDetailAction" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="108">
			<thead>
				<tr>					
				<th style="width:100px;">广告计划</th>	
				<th style="width:100px;">广告计划周期</th>
				<th style="width:100px;">站长</th>
				<th style="width:100px;">域名</th>
				<th style="width:120px;">媒介</th>
				<th style="width:120px;">日期</th>
			</tr>			
					
			</thead>
			<tbody>
				<c:forEach items="${sourcelist}" var="sl" >
				<tr> 						
					<td>${sl.cycn}</td>
					<td>${sl.apn}</td>
					<td>${sl.wmn}</td>
					<td  id="wrap" ><a target="_blank" style="color:blue" href="${sl.source}">${sl.source}</a></td>
					<td>${sl.usern}</td>
					<td>${sl.addt}</td>
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