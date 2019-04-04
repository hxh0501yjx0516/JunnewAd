<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>

<head>
<title>投放列表</title> 
<script type="text/javascript">
function reportExcel(){
	alert("aa");
	 var adPlanName = document.readyBoxForm.adPlanName.value;
	 var adPlanCycleName = document.readyBoxForm.adPlanCycleName.value;
	 var adBoxName = document.readyBoxForm.adBoxName.value;
	 var urlName = document.readyBoxForm.urlName.value;
	 var webMasterName = document.readyBoxForm.webMasterName.value;
	 var readyboxstatus = document.readyBoxForm.readyboxstatus.value;
	 var isdefault = document.readyBoxForm.isdefault.value;
	 //if(${userGroup eq 0}){
	//	qryuserGroup =  document.readyBoxForm.qryuserGroup.value;
	 //}
	 alert("${pageContext.request.contextPath}/media.do?action=readyBoxToExcel&adPlanName="+adPlanName+"&adPlanCycleName="+adPlanCycleName+"&adBoxName="+adBoxName+"&urlName="+urlName+"&webMasterName="+webMasterName+"&readyboxstatus="+readyboxstatus+"&isdefault="+isdefault+"&qryuserGroup="+qryuserGroup);
	 //location.href="${pageContext.request.contextPath}/media.do?action=readyBoxToExcel&adPlanName="+adPlanName+"&adPlanCycleName="+adPlanCycleName+"&adBoxName="+adBoxName+"&urlName="+urlName+"&webMasterName="+webMasterName+"&readyboxstatus="+readyboxstatus+"&isdefault="+isdefault+"&qryuserGroup="+qryuserGroup;
 
}
</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/media.do?action=readyBoxList&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
	<input type="hidden" name="adPlanName" value="${adPlanName }" />
	<input type="hidden" name="adPlanCycleName" value="${adPlanCycleName}"/>
	<input type="hidden" name="adBoxName" value="${adBoxName}"/>
	<input type="hidden" name="urlName" value="${urlName}"/>
	<input type="hidden" name="webMasterName" value="${webMasterName}"/>
	<input type="hidden" name="readyboxstatus" value="${readyboxstatus}"/>
	<input type="hidden" name="isdefault" value="${isdefault}"/>
		<input type="hidden" name="qryuserGroup" value="${qryuserGroup }" /><!--【可选】查询条件-->		
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form name="readyBoxForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/media.do?action=readyBoxList" method="post">
		<input type="hidden" id="selectedId_demo"/>
		<table>
		<tr>
		<td>	<ul class="searchContent">
				<li>
					<label>计划：</label>
					<input type="text" size="8" name="adPlanName" value="${adPlanName}"/>	
					<label>周期：</label>
					<input type="text" size="8" name="adPlanCycleName" value="${adPlanCycleName}"/>
					<label>广告位：</label>
					<input type="text" size="8" name="adBoxName" value="${adBoxName}"/>
					<label>域名：</label>
					<input type="text" size="8" name="urlName" value="${urlName}"/>
					<label>网站主：</label>
					<input type="text" size="8" name="webMasterName" value="${webMasterName}"/>
					<label>投放状态：</label>
					<select name="readyboxstatus">
						<option value="" ${readyboxstatus eq ""?"selected":"" }>全部状态</option>
						<option value="0" ${readyboxstatus eq "0"?"selected":""}>正常</option>
						<option value="1" ${readyboxstatus eq "1"?"selected":""}>锁定</option>
						<option value="2" ${readyboxstatus eq "2"?"selected":""}>废弃</option>
					</select>
					<label>创意类型：</label>
					<select name="isdefault">
						<option value="" ${isdefault eq ""?"selected":"" }>全部创意</option>
						<option value="0" ${isdefault eq "0"?"selected":""}>普通创意</option>
						<option value="1" ${isdefault eq "1"?"selected":""}>默认创意</option>
					</select>		
				</li>
				<c:if test="${sessionScope.user.userGroup eq 0}">
				<li>
					用户组：
					<select name="qryuserGroup">
						<option value="" >请选择</option>
						<c:forEach items="${userGroupList}" var="userGroup">
						<option value="${userGroup.id}" ${qryuserGroup == userGroup.id?"selected":""}>${userGroup.userGroupName}</option>
						</c:forEach>
					</select>
					</li>
					</c:if>
			</ul>	</td><td>	<ul>
					<li>
					<div class="buttonActive">
					<div class="buttonContent"><button type="submit">检索</button></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/media.do?action=readyBoxList" target="navTab" title="投放列表" rel="投放列表"><button>重置</button></a></div>
					</div>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="reportExcel();">导出</button>
						</div>
					</div>
					</li>
				</ul>	</td>
		</tr>
		</table>
	
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/edia.do?action=readyBoxList" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<!--  <div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/admodel.do?action=readyBoxAdd" target="navTab" rel="admodel"><span>添加模板</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		-->
		<table class="table" layouth="89">
			<thead>
				<tr>
					
					<th style="width:100px;">投放编号</th>
					<th style="width:100px;">广告创意</th>
					<th style="width:100px;">广告位</th>					
					<th style="width:100px;">域名</th>
					<th style="width:100px;">网站主</th>
					<th style="width:100px;">计划周期</th>
					<th style="width:100px;">广告计划</th>
					<th style="width:110px;">流量波动</th>
					<th style="width:40px;">状态</th>
					<th style="width:80px;">创意类型</th>
					<th style="width:100px;_width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="readyBox" varStatus="v">
					<tr> 						
						<td>${readyBox.readyBoxId}</td>
						<c:choose>
							<c:when test="${!empty readyBox.adCreativeImg}">
							<td onmouseover="document.getElementById('${readyBox.readyBoxId}').style.display='block';" onmouseout="document.getElementById('${readyBox.readyBoxId}').style.display='none';">${readyBox.adCreativeName}</td>
							</c:when>
							<c:otherwise>
							<td >${readyBox.adCreativeName}</td>
							</c:otherwise>
						</c:choose>
						<td>${readyBox.adBoxName }</td>
						<td>${readyBox.urlName }</td>
						<td>${readyBox.webMasterName }</td>   
						<td>${readyBox.adPlanCycleName}</td>	
						<td>${readyBox.adPlanName}</td>
						<td align="center">
						
						<c:if test="${readyBox.readyBoxStatus == 0}">
						<c:choose>
							<c:when test="${readyBox.waveState == 0}">
							<a href="${pageContext.request.contextPath}/dana.do?action=allDetailAction&readyBoxId=${readyBox.readyBoxId}&addTimef=${readyBox.waveBeginTime }&addTimet=${readyBox.waveBDTime}" alt="查看详细流量" target="navTab" title="详细"><img src="${pageContext.request.contextPath }/images/green.png" width="15" height="15" align="adbmiddle" border="0"/></a>&nbsp;
							<c:if test="${readyBox.wavePercent > 1}">
							<font color=blue>上升&nbsp;<fmt:formatNumber value="${(readyBox.wavePercent-1)*100}" pattern="#,###"/>%</font>
							</c:if>
							<c:if test="${readyBox.wavePercent == 1}">
							<font color=blue>正常</font>
							</c:if>
							<c:if test="${readyBox.wavePercent < 1 && readyBox.wavePercent > 0 }">
							<font color=blue>下降&nbsp;<fmt:formatNumber value="${(1-readyBox.wavePercent)*100}" pattern="#,###"/>%</font>
							</c:if>
							<c:if test="${readyBox.wavePercent == 0}">
							<font color=blue>正常</font>
							</c:if>
							</c:when>
							<c:otherwise>
							<a href="${pageContext.request.contextPath}/dana.do?action=allDetailAction&readyBoxId=${readyBox.readyBoxId}&addTimef=${readyBox.waveBeginTime }&addTimet=${readyBox.waveBDTime}" alt="查看详细流量" target="navTab" title="详细"><img src="${pageContext.request.contextPath }/images/red.png" width="15" height="15" align="adbmiddle" border="0"/></a>&nbsp;
							<c:if test="${readyBox.wavePercent >= 1}">
							<font color=red>上升&nbsp;<fmt:formatNumber value="${(readyBox.wavePercent-1)*100}" pattern="#,###"/>%</font>
							</c:if>
							<c:if test="${readyBox.wavePercent < 1}">
							<font color=red>下降&nbsp;<fmt:formatNumber value="${(1-readyBox.wavePercent)*100}" pattern="#,###"/>%</font>
							</c:if>
							</c:otherwise>
						</c:choose>
						</c:if>	
						</td>
						<td>
						<c:choose>
							<c:when test="${readyBox.readyBoxStatus == 0}">
							<span style="color: blue">正常</span>
							</c:when>
							<c:when test="${readyBox.readyBoxStatus == 1}">
							<span style="color: orange">锁定</span>
							</c:when>
							<c:otherwise>
							<span style="color: red">废弃</span>
							</c:otherwise>
						</c:choose>	
						</td>	
						<c:choose>
							<c:when test="${readyBox.isDefault == 0}">
							<td>
							<span style="color: blue">普通创意</span>
							</td>
							<td>
							<c:if test="${readyBox.readyBoxStatus ne 3}">
								<a name="editbutton" title="修改投放创意" class="btnEdit" href="${pageContext.request.contextPath }/readybox.do?action=edit&readyBoxId=${readyBox.readyBoxId}&flag=edit&isDefault=${readyBox.isDefault}&adCreativeId=${readyBox.adCreativeId}" target="navTab" rel="editReadyBox"><span style="color:blue">修改</span></a>
							<a class="" href="${pageContext.request.contextPath }/readybox.do?action=getcode&adBoxId=${readyBox.adBoxId}" target="dialog" rel="getCodeDialog"  mask="true" style="margin: 0 auto;float:none;width:80px;color:blue;border:0px solid red;"><span>提取代码</span></a>
							</c:if>
							</td>
							</c:when>
							<c:otherwise>
							<td>
							<span style="color: orange">默认创意</span>
							</td>
							<td>
							<c:if test="${readyBox.readyBoxStatus ne 3}">
								<a name="editbutton" title="修改投放创意" class="btnEdit" href="${pageContext.request.contextPath }/readybox.do?action=edit&readyBoxId=${readyBox.readyBoxId}&flag=edit&isDefault=${readyBox.isDefault}&adCreativeId=${readyBox.adCreativeId}" target="navTab" rel="editReadyBox"><span style="color:blue">修改</span></a>
							<a class="" href="${pageContext.request.contextPath }/readybox.do?action=getcode&adBoxId=${readyBox.adBoxId}" target="dialog" rel="getCodeDialog"  mask="true" style="margin: 0 auto;float:none;width:80px; color:blue;border:0px solid red;"><span>提取代码</span></a>
							</c:if>
							</td>	
							</c:otherwise>
						</c:choose>			
					</tr>
					<div style='position: absolute;right:5px;bottom:0;display: none; border:1px solid #B6CAE3;z-index:99;background:#F2F6FB;' id='${readyBox.readyBoxId}'>
					<c:choose>
					<c:when test="${readyBox.adCreativeImg == ''}">
					<splan style="color:orange">暂无</splan>
					</c:when>
					<c:when test="${fn:indexOf(readyBox.adCreativeImg,'.swf') > 0}">
					<embed id="showimg${readyBox.readyBoxId}" src="${readyBox.adCreativeImg}" width="${readyBox.adWidth }" height="${readyBox.adHeight }" border="0" wmode="transparent"></embed>
					</c:when>
					<c:otherwise>
					<img alt="" src="${readyBox.adCreativeImg}" width="${readyBox.adWidth }" height="${readyBox.adHeight }" /><br />
					</c:otherwise>
					</c:choose>
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