<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>数据执行报表管理</title> 
<script type="text/javascript">
function resetForm(){
	document.dataReportForm.qrybegintime.value = '';
	document.dataReportForm.qryendtime.value= '';
	document.dataReportForm.qrywebmaster.value= '';
	document.dataReportForm.qryadplan.value= '';
	document.dataReportForm.qrysales.value= 0;
}
function reportExcel(obj){
		var beginTime = document.dataReportForm.qrybegintime.value;
		var endTime = document.dataReportForm.qryendtime.value;
		var adPlan = document.dataReportForm.qryadplan.value;
		var webMaster = document.dataReportForm.qrywebmaster.value;
		var sales = document.dataReportForm.qrysales.value;
		var payType = document.dataReportForm.paytype.value;
		var qryuserGroup = "";
		//if(${sessionScope.user.userGroup} == 0){
		// qryuserGroup = document.dataReportForm.qryuserGroup.value;
		//}
		 obj.href="${pageContext.request.contextPath }/report.do?action=dataReport&qrybegintime="+beginTime+"&qryendtime="+endTime+"&qryadplan="+adPlan
		 +"&qrywebmaster="+webMaster+"&qrysales="+sales+"&paytype="+payType+"&qryuserGroup="+qryuserGroup;
	}
</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/report.do?action=dataReportList&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qrywebmaster" value="${qrywebmaster }" />
	<input type="hidden" name="qrysales" value="${qrysales }" />
	<input type="hidden" name="qryadplan" value="${qryadplan }" />
	<input type="hidden" name="qrybegintime" value="${qrybegintime }" />
	<input type="hidden" name="qryendtime" value="${qryendtime }" />
	<input type="hidden" name="paytype" value="${paytype}">
	<input type="hidden" name="qryuserGroup" value="${qryuserGroup }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
<div class="pageHeader">
		<form name="dataReportForm" onsubmit="return navTabSearch(this)" action="${pageContext.request.contextPath }/report.do?action=dataReportList" method="post">
		
		<table>
		<tr>
				<td>
					<ul class="searchContent">
					<li>
					<label>开始日期：</label>
					<label><input type="text" name="qrybegintime" class="date" size="8" value="${qrybegintime }" readonly="readonly"/></label>
					<label>结束日期：</label>
					<label><input type="text" name="qryendtime" class="date" size="8" value="${qryendtime }" readonly="readonly"/></label>
					<label>网站主：</label>
					<label><input type="text" name="qrywebmaster" size="8" value="${qrywebmaster }"/></label>
					<label>广告计划：</label>
					<label><input type="text" name="qryadplan" size="8" value="${qryadplan }"/></label>
					
					<label>所属媒介：</label>
					<select  name="qrysales"  class="">
					<option value="" >请选择</option>
					<c:forEach var="m" items="${userslist}">
						<option value="${m.userId}" ${m.userId == qrysales?"selected":""}>${m.realname}</option>
					</c:forEach>
					</select>
					
					
					<label>数据状态：</label>
					<select name="paytype" class="">
						<option value="" ${paytype eq ""?"selected":""}>==全部状态==</option>
						<option value="0" ${paytype eq "0"?"selected":""}>┹已上传</option>
						<option value="1" ${paytype eq "1"?"selected":""}>┹已提交</option>
						<option value="2" ${paytype eq "2"?"selected":""}>┹已结算</option>
					</select>	

					<c:if test="${sessionScope.user.userGroup eq 0}">
					用户组：
					<select name="qryuserGroup" style="width:100px">
						<option value="" >请选择</option>
						<c:forEach items="${userGroupList}" var="userGroup">
						<option value="${userGroup.id}" ${qryuserGroup == userGroup.id?"selected":""}>${userGroup.userGroupName}</option>
						</c:forEach>
					</select>		
					</c:if>
					</li>
					</ul>	
				</td>
				<td>	
					<ul>
					<li>
					<div class="buttonActive">
					<div class="buttonContent"><button type="submit">检索</button></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/report.do?action=dataReportList" target="navTab" rel="数据报表" title="数据报表"><button>重置</button></a></div>
					</div>
					</li>
					</ul>	
				</td>
		</tr>
		</table>
		</form>
	</div>
	<div class="pageContent">
	<form method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this);">
			<div class="panelBar">
			<ul class="toolBar">
			<c:if test="${!empty list }">
				<li><a class="icon" onclick="reportExcel(this);" href="javascript:void(0);" ><span>报表导出</span></a></li>
			</c:if>
			</ul>
		</div>
		<table class="table" layouth="113">
			<thead>
				<tr>
					<th style="width:40px;" orderField="reportId" class="">编号</th>
					<th style="width:120px;">网站主</th>
					<th style="width:100px;">所属媒介</th>					
					<th style="width:100px;">广告计划</th>
					<th style="width:100px;">计划周期</th>
					<th style="width:100px;">返回值</th>
					<th style="width:100px;">应收</th>
					<th style="width:100px;">有效值</th>
					<th style="width:100px;">站长单价</th>
					<th style="width:100px;">有效佣金</th>
					<th style="width:100px;">利润</th>
					<th style="width:100px;">利润率</th>
					<th style="width:100px">日期</th>
					<th style="width:100px">数据状态</th>
					<th style="width:100px">操作</th>
				</tr>
			</thead>
			<tbody>
					<tr> 
						<td style=" color:blue; font-weight:bold;">汇总</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][0]}" pattern="#,###.##" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][1]}" pattern="#,###.##" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][2]}" pattern="#,###.##" /></td>
						<td></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][3]}" pattern="#,###.##" /></td>
						<td style=" color:blue; font-weight:bold">
						<c:choose>
							<c:when test="${listSum[0][1]-listSum[0][3] ne 0 }">
							<fmt:formatNumber value="${listSum[0][1]-listSum[0][3]}" pattern="#,###.##"/>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						</td>
						<td style=" color:blue; font-weight:bold">
						<c:choose>
							<c:when test="${listSum[0][1]-listSum[0][3] ne 0 }">
							<fmt:formatNumber value="${(listSum[0][1]-listSum[0][3])/listSum[0][1]*100}" pattern="#,###.##"/> %
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				<c:forEach items="${list}" var="dataBalance" varStatus="s">
					<c:choose>
						<c:when test="${dataBalance.reportStatus == 0}">
							<tr style="color: red;"> 
						</c:when>
						<c:when test="${dataBalance.reportStatus == 1}">
							<tr style="color: blue;"> 
						</c:when>
						<c:otherwise>
							<tr> 
						</c:otherwise>
					</c:choose>
						<td>${dataBalance.reportId}</td>
						<td>${dataBalance.webMasterName}</td>
						<td>${dataBalance.userName}</td>
						<td>${dataBalance.adplanName}</td>
						<td>${dataBalance.adplanCycleName}</td>
						<td><fmt:formatNumber value="${dataBalance.count}" pattern="#,###.###"/></td>
						<td>￥<fmt:formatNumber value="${dataBalance.money}" pattern="#,###.###"/></td>
						<td><fmt:formatNumber value="${dataBalance.realCount}" pattern="#,###.###"/></td>
						<td><fmt:formatNumber value="${dataBalance.webMasterPrice}" pattern="#,###.##"/></td>
						<td>￥<fmt:formatNumber value="${dataBalance.realMoney}" pattern="#,###.###"/></td>
						<td><fmt:formatNumber value="${dataBalance.money-dataBalance.realMoney}" pattern="#,###.##"/></td>
						<td><fmt:formatNumber value="${(dataBalance.money-dataBalance.realMoney)/dataBalance.money*100}" pattern="#,###.##"/>%</td>
						<td>
						<fmt:parseDate value="${dataBalance.reportTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd"/>
						</td>
						<td>
						<c:choose>
						<c:when test="${dataBalance.reportStatus == 0}">
							已上传
						</c:when>
						<c:when test="${dataBalance.reportStatus == 1}">
							已提交
						</c:when>
						<c:otherwise>
							已结算
						</c:otherwise>
					</c:choose>
						</td>
						<td>
						<c:if test="${roleId == 20 || roleId == 10 &&  dataBalance.reportStatus < 2}"><!-- 总监修改权限 -->
						<a name="editbutton" title="修改媒体单价" class="btnEdit" href="${pageContext.request.contextPath }/readybox.do?action=edit&readyBoxId=1151&flag=edit&isDefault=1&adCreativeId=${dataBalance.adCreativeId}" target="navTab" rel="editReadyBox"><span style="color:blue">修改媒体单价</span></a>
						</c:if>
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