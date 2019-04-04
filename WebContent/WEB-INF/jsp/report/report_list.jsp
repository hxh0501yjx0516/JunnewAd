<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>数据统计</title> 
<script type="text/javascript">
function reportExcel(){
		var begintime = document.adBoxCountForm.begintime.value;
		var endtime = document.adBoxCountForm.endtime.value;
		var adplanname = document.adBoxCountForm.adplanname.value;
		var adplanid = document.adBoxCountForm.adplanid.value;
		var username = document.adBoxCountForm.username.value;
		var isdefault = document.adBoxCountForm.isdefault.value;
		var webmastername = document.adBoxCountForm.webmastername.value;
		var qryuserGroup = "";
		//if(${sessionScope.user.userGroup eq 0}){
		//	qryuserGroup = document.adBoxCountForm.qryuserGroup.value;
		//}
		location.href="${pageContext.request.contextPath }/report.do?action=adBoxCountToExcel&begintime="+begintime+"&endtime="+endtime+"&adplanname="+adplanname+"&adplanid="+adplanid+"&username="+username+"&isdefault="+isdefault+"&webmastername="+webmastername+"&qryuserGroup="+qryuserGroup;
	}
</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/report.do?action=adBoxCountList&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="adplanname" value="${qryadplan }" />
	<input type="hidden" name="adplanid" value="${qryadplanid }" />
	<input type="hidden" name="username" value="${qryuser }" />
	<input type="hidden" name="begintime" value="${begintime }"/>
	<input type="hidden" name="endtime" value="${endtime }"/>
	<input type="hidden" name="isdefault" value="${isdefault }"/>
	<input type="hidden" name="webmastername" value="${webmastername}">
	<input type="hidden" name="qryuserGroup" value="${qryuserGroup }" />
	
  </form>
<body>
<div class="page">

		<!-- 
		<div class="panelBar">
			<div style=" text-indent:20px; line-height:26px;">
			显示（PV）：111111111&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			显示（UV）：111111111&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			点击（PV）：111111111&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			点击（UV）：111111111&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			点击（IP）：111111111	
			</div>
		</div>
		 -->
<div class="pageHeader">
		<form name="adBoxCountForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/report.do?action=adBoxCountList" method="post">
		<input type="hidden" name="qryadplan" value="${qryadplan }" />
		<input type="hidden" name="qryuser" value="${qryuser }" />
		<input type="hidden" name="resource" value="${resource }" />
		
		
		<div class="searchBar" >
			<ul class="searchContent">
				<li>
					<label>日期：</label>
					<label><input name="begintime" value="${begintime }" class="date" pattern="yyyy-MM-dd" readonly="true"  type="text" size="8" /></label>
					<label><input name="endtime" value="${endtime }" class="date"  pattern="yyyy-MM-dd" readonly="true" type="text" size="8" /></label>	
				</li><li >
					<label>计划：</label>
					<input type="text" name="adplanname" size="5" value="${qryadplan}" />
					<select name="adplanid" class="" style="width:130px">
						<option value="" ${qrystate eq ""?"selected":""}>请选择</option>
						<c:forEach items="${adPlanList}" var="idList">
						<option value="${idList.adPlanId}" ${idList.adPlanId == qryadplanid?"selected":""}>${idList.adPlanName}</option>
						</c:forEach>
					</select>
				</li><li >
					<label>媒介：</label>
					<select name="username" class="">
						<option value="" ${qrystate eq ""?"selected":""}>请选择</option>
						<c:forEach items="${userslist}" var="users">
						<option value="${users.userId}" ${users.userId == qryuser?"selected":""}>${users.realname}</option>
						</c:forEach>
					</select>
				</li><li >
					<label>站长：</label>
					<input type="text" name="webmastername" size="5" value="${webmastername}" />
				</li><li >
					<label>创意类型：</label>
					<select name="isdefault" class="">
						<option value="" ${empty isdefault?"selected":""}>全部创意</option>
						<option value="0" ${isdefault eq "0"?"selected":""}>普通创意</option>
						<option value="1" ${isdefault eq "1"?"selected":""}>默认创意</option>
					</select>
				</li>
				<c:if test="${sessionScope.user.userGroup eq 0}">
				<li>
				<label>用户组：</label>
					<select name="qryuserGroup" style="width:100px">
						<option value="" >请选择</option>
						<c:forEach items="${userGroupList}" var="userGroup">
						<option value="${userGroup.id}" ${qryuserGroup eq userGroup.id?"selected":""}>${userGroup.userGroupName}</option>
						</c:forEach>
					</select>
				</li>
				</c:if>
				<li>
				<div class="buttonActive" >
					<div class="buttonContent">
					<button type="submit">检索</button>
					</div>
				</div>
				<div class="buttonActive" >
					<div class="buttonContent">
					<a href="${pageContext.request.contextPath }/report.do?action=adBoxCountList" target="navTab" rel="数据统计" title="数据统计"><button type="button">重置</button></a>
					</div>
				</div>
				<div class="buttonActive" >
					<div class="buttonContent" >
					<button type="button" onclick="reportExcel()">报表导出</button>
					</div>
				</div>	
				
				</li>
				
			</ul>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/report.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		
		<table class="table" layouth="88">
			<thead>
				<tr>
					<th style="width:60px;">投放编号</th>
					<th style="width:50px;">站长</th>
					<th style="width:50px;">媒介</th>					
					<th style="width:50px;">域名</th>
					<th style="width:50px;">计划</th>
					<th style="width:50px;">周期</th>
					<th style="width:40px;">创意</th>
					<th style="width:80px;">显示(PV)</th>
					<th style="width:80px;">显示(UV)</th>
					<th style="width:120px;">点击(PV) </th>
					<th style="width:120px;">点击(UV) </th>
					<th style="width:120px;">点击(IP) </th>
					<th style="width:60px;">点击率</th>
					<th style="width:40px;">扣量 </th>   
					<th style="width:80px;">站长查看列</th>  
					<th style="width:80px;">日期</th>  
					    
				</tr>  
				</thead>
			</thead>
			<tbody>
				<tr> 
						<td style=" color:blue; font-weight:bold;">汇总</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][0]}" pattern="#,###" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][1]}" pattern="#,###" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][2]}" pattern="#,###" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][3]}" pattern="#,###" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][4]}" pattern="#,###" /></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				<c:forEach items="${list}" var="l" >
					<tr> 
						<td>${l.readyBoxId}</td>
						<td>${l.webMasterName}</td>
						<td>${l.realname}</td>
						<td>${l.url}</td>
						<td><a title="${l.adPlanName}">${l.adPlanName}</a></td>
						<td><a title="${l.adPlanCycleName}">${l.adPlanCycleName}</a></td>
						<td><a title="${l.adCreativeName}">${l.adCreativeName}</a></td>
						<td><fmt:formatNumber value="${l.browseC}" pattern="#,###"/></td>
						<td><fmt:formatNumber value="${l.browseTrue}" pattern="#,###"/></td>
						<td>
						<fmt:formatNumber value="${l.pv}" pattern="#,###"/>
						</td>
						<td>
						<fmt:formatNumber value="${l.uv}" pattern="#,###"/>
						</td>
						<td>
						<fmt:formatNumber value="${l.ip}" pattern="#,###"/>
						</td>
						<td>
						<c:if test="${l.browseTrue ne 0}">
						<fmt:formatNumber value="${l.ip/l.browseTrue*100}" pattern="#.##" type="percent" />%
						</c:if>
						</td>
						<td>${l.discount }%</td>
						<td>
							<c:if test="${l.discount eq 0}">
									<c:if test="${l.showType eq 0}">
									<font color="blue">PV</>：&nbsp;<fmt:formatNumber value="${l.pv}" pattern="#,###"/>
									</c:if>
									<c:if test="${l.showType eq 1}">
									<font color="blue">UV</font>：&nbsp;<fmt:formatNumber value="${l.uv}" pattern="#,###"/>
									</c:if>
									<c:if test="${l.showType eq 2}">
									<font color="blue">IP</font>：&nbsp;<fmt:formatNumber value="${l.ip}" pattern="#,###"/>
									</c:if>
							</c:if>
							<c:if test="${l.discount ne 0}">
									<c:if test="${l.showType eq 0}">
									<font color="blue">PV</font>：&nbsp;<fmt:formatNumber value="${l.pv * (1- l.discount/100)}" pattern="#,###"/>
									</c:if>
									<c:if test="${l.showType eq 1}">
									<font color="blue">UV</font>：&nbsp;<fmt:formatNumber value="${l.uv * (1- l.discount/100)}" pattern="#,###"/>
									</c:if>
									<c:if test="${l.showType eq 2}">
									<font color="blue">IP</font>：&nbsp;<fmt:formatNumber value="${l.ip * (1- l.discount/100)}" pattern="#,###"/>
									</c:if>
							</c:if>
						</td>
						<td>
						<fmt:parseDate value="${l.adBoxCountTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>
					</tr>
					<div style='position: absolute;right:5px;bottom:0;display: none; border:1px solid #B6CAE3;z-index:99;background:#F2F6FB;' id='${l.readyBoxId}'>
						<c:if test="${l.adCreativeImg ne ''}">
						<img src="${l.adCreativeImg}" />
						</c:if>
					</div>
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