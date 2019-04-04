<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>数据核对管理</title> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/formatNumber.js"></script>
<script type="text/javascript">
function reportExcel(obj){
		var beginTime = document.dataVerifyForm.qrybegintime.value;
		var endTime = document.dataVerifyForm.qryendtime.value;
		var adPlan = document.dataVerifyForm.qryadplan.value;
		var webmaster = document.dataVerifyForm.webmaster.value;
		var adPlanCycle = document.dataVerifyForm.qryadplanCycle.value;
		var customer = document.dataVerifyForm.qrycustomer.value;
		var userGroup= "";
		//if(${sessionScope.user.userGroup} == 0){
			//userGroup =document.dataVerifyForm.qryuserGroup.value;
		//}
		 obj.href="${pageContext.request.contextPath }/report.do?action=verifyReport&qrybegintime="+beginTime+"&qryendtime="+endTime+"&qryadplan="+adPlan+"&webmaster="+webmaster+"&qryadplanCycle="+adPlanCycle+"&qrycustomer="+customer+"&userGroup="+userGroup;
	}
function resetForm(){
	document.dataVerifyForm.qrybegintime.value = '';
	document.dataVerifyForm.qryendtime.value= '';
	document.dataVerifyForm.qryadplan.value='';
	document.dataVerifyForm.qryadplanCycle.value='';
	document.dataVerifyForm.webmaster.value=''
	document.dataVerifyForm.qrycustomer.value=''
}
function mouseOver(obj,value){
		obj.style.cssText='border:1px solid;width:80px;';
		obj.value = obj.value.replace(/,/g,"").replace(/，/g,"");
		obj.focus();
		if(value){
			obj.value=value;
		}
}
function mouseOut(obj,hidName,reportId,hidRealMoney,realMoney,payType,price){
		obj.style.cssText='border:0px solid;background-color:transparent;width:80px;';
		var trueMoney = obj.value;
		if(obj.value.indexOf(",") !=-1 ){
		trueMoney = obj.value.replace(/,/g,"");
		}
		if(obj.value.indexOf("，") !=-1 ){
		trueMoney = obj.value.replace(/，/g,"");
		}
		obj.value=formatNumber(trueMoney,'#,###.###');
		document.getElementById(hidName+reportId).value = trueMoney;
		if(payType){
		var realM = payType == 1?price*trueMoney/1000:price*trueMoney;
		document.getElementById(realMoney+reportId).value = formatNumber(realM,'#,###.###');
		document.getElementById(hidRealMoney+reportId).value = realM;
		}
		obj.blur();
}
function change(obj){
	var beginTime = document.dataVerifyForm.qrybegintime.value;
	var endTime = document.dataVerifyForm.qryendtime.value;
	if(beginTime == ''){
		alertMsg.info('请选择开始时间！');
		return false;
	}
	if(endTime == ''){
		alertMsg.info('请选择结束时间！');
		return false;
	}
	return navTabSearch(obj);
}
function myAjaxDone(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == 200){
		if (json.navTabId){
			navTabSearch(document.dataVerifyForm);
		} else {
			navTabPageBreak();
		}
}
}
function getAdPlanCycleList(){
		$.ajax( {
		type : 'POST',
		url : "${pageContext.request.contextPath }/util.do?action=getAdPlanCycleList",
		data : {adPlanId : $('#qryadplan').val(),qryuserGroup : $('#qryuserGroup1').val()},
		dataType : "json",
		cache : false,
		success : function(temp){
			$('#qryadplanCycle option').remove(); 
			$("#qryadplanCycle").append($("<option>").text("===请选择===").val("") );
			for(var i=0;i<temp.length;i++){ 
			_option = $("<option>").text("┹"+temp[i].adPlanCycleName).val(temp[i].adPlanCycleId);
			$("#qryadplanCycle").append(_option); 
			}
			if($("#adPlanCycleId2").val() !=""){
				$("#qryadplanCycle").attr('value' ,$("#adPlanCycleId2").val()); 	
			$("#adPlanCycleId2").val("");
			}
		}
	});
		
}
document.onload=getAdPlanCycleList();
</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/report.do?action=verifyList&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryadplan" value="${qryadplan }" />
	<input type="hidden" name="qryadplanname" value="${qryadplanname }" />
	<input type="hidden" name="qryadplanCycle" value="${qryadplanCycle }" />
	<input type="hidden" name="qryadplancyclename" value="${qryadplancyclename }" />
	<input type="hidden" name="qrybegintime" value="${qrybegintime }" />
	<input type="hidden" name="qryendtime" value="${qryendtime }" />
	<input type="hidden" name="webmaster" value="${webmaster}">
	<input type="hidden" name="qryuserGroup" value="${qryuserGroup }" /><!--【可选】查询条件-->
	<input type="hidden" name="qrycustomer" value="${qrycustomer}" />
  </form>
<body>
<div class="page">
<div class="pageHeader">
		<div class="searchBar" >
		<form name="dataVerifyForm" onsubmit="return change(this)" action="${pageContext.request.contextPath }/report.do?action=verifyList" method="post">
		<input type="hidden" id="adPlanCycleId2" value="${qryadplanCycle}"/>
			<ul class="searchContent" style="height:auto;">
				<li >
					时间：
					<input type="text" name="qrybegintime" class="date" style="width:80px"  value="${qrybegintime }" readonly="readonly"/>-
					<input type="text" name="qryendtime" class="date" style="width:80px"  value="${qryendtime }" readonly="readonly"/>
					站长：
					<input type="text" name="webmaster" style="width:80px"  value="${webmaster }">
					计划：
					<select  id="qryadplan"  name="qryadplan"  onchange="getAdPlanCycleList()"  class="" style="width:100px">
					<option value="">===请选择===</option>
					<c:forEach var="adPlan" items="${adPlanList}">
						<option value="${adPlan.adPlanId}" ${adPlan.adPlanId == qryadplan?"selected":""}>┹${adPlan.adPlanName}</option>
					</c:forEach>
					</select>
					<input type="text" name="qryadplanname" value="${qryadplanname}" size="10" />
					周期：
					<select  id="qryadplanCycle"  name="qryadplanCycle"  class=""  style=" width:100px;">
					<option value="">===请选择===</option>
					<c:forEach var="adPlanCycle" items="${adPlanCycleList}">
						<option value="${adPlanCycle.adPlanCycleId}" ${adPlanCycle.adPlanCycleId == qryadplanCycle?"selected":""}>┹${adPlanCycle.adPlanCycleName}</option>
					</c:forEach>
					</select>
					<input type="text" id="qryadplancyclename" name="qryadplancyclename" value="${qryadplancyclename}" size="10" />
					客户：
					<select  id="qrycustomer"  name="qrycustomer"  class="" style=" width:100px;">
					<option value="">===请选择===</option>
					<c:forEach var="customer" items="${customerList}">
						<option value="${customer.customerId}" ${customer.customerId == qrycustomer?"selected":""}>┹${customer.customerName}</option>
					</c:forEach>
					</select>
				
					</li>
					<li>
					<div class="buttonActive" ><div class="buttonContent"><button type="submit" >检索</button></div></div>
					<div class="buttonActive" ><div class="buttonContent"><button type="button" onclick="resetForm();">重置</button></div></div>
					</li>
			</ul>
		</form>
		</div>
	</div>
	<div class="pageContent">
	<form method="post" name="verifyForm" action="${pageContext.request.contextPath }/report.do?action=verifyEdit" class="pageForm required-validate" onsubmit="return validateCallback(this,myAjaxDone);">
		<div class="panelBar">
			<ul class="toolBar">
			<c:if test="${!empty list }">
				<li><a class="icon" onclick="reportExcel(this);" href="javascript:void(0);" ><span>报表导出</span></a></li>
				<li><div class="button" ><div class="buttonContent"><button class="edit" type="submit" >修改并提交数据</button></div></div></li>
				<span style="color: red;">还有${uploadCount}个数据未提交</span>
			</c:if>
			</ul>
		</div>
		<table class="table" layouth="113">
			<thead>
				<tr>
					<th style="width:120px;">日期</th>
					<th style="width:100px;">媒介</th>
					<th style="width:80px;">域名</th>
					<th style="width:80px;">站长</th>
					<th style="width:150px;">广告计划</th>
					<th style="width:150px;">计划周期</th>
					<th style="width:100px;">显示(PV)</th>
					<th style="width:100px;">显示(UV)</th>
					<th style="width:100px;">点击(PV)</th>
					<th style="width:100px;">点击(UV)</th>
					<th style="width:100px;">返回量</th>
					<th style="width:100px;">应收</th>
					<th style="width:100px;">有效值</th>
					<th style="width:100px;">站长单价</th>
					<th style="width:100px;">有效佣金</th>
					<th style="width:100px;">利润</th>
					<th style="width:100px;">利润率</th>
				</tr>
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
						<td></td>
						<td></td>
						<td></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][0]}" pattern="#,###.##" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][1]}" pattern="#,###.##" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][2]}" pattern="#,###.##" /></td>
						<td></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][3]}" pattern="#,###.##" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][1]-listSum[0][3]}" pattern="#,###.##" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${(listSum[0][1]-listSum[0][3])/listSum[0][1]*100}" pattern="#,###.##" />%</td>
				</tr>
				
				<c:forEach items="${list}" var="dataVerify" varStatus="s">
					<c:choose>
						<c:when test="${dataVerify.reportStatus == 0}">
							<tr style="color: red;"> 
						</c:when>
						<c:when test="${dataVerify.money >= 1000000}">
							<tr style="color: #FF7817; font-weight: bold;"> 
						</c:when>
						<c:otherwise>
							<tr> 
						</c:otherwise>
					</c:choose>		
						<td>
						<fmt:parseDate value="${dataVerify.reportTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date"  pattern="yyyy-MM-dd"/>
						</td>
						<td>${dataVerify.userName}</td>
						<td>${dataVerify.urlName}</td>
						<td>${dataVerify.webMasterName}</td>
						<td>${dataVerify.adplanName}</td>
						<td>${dataVerify.adplanCycleName}</td>
						<td><fmt:formatNumber value="${dataVerify.browse}" pattern="#,###.###"/></td>
						<td><fmt:formatNumber value="${dataVerify.browseTrue}" pattern="#,###.###"/></td>
						<td><fmt:formatNumber value="${dataVerify.pv}" pattern="#,###.###"/></td>
						<td><fmt:formatNumber value="${dataVerify.uv}" pattern="#,###.###"/></td>
						
						<td> <fmt:formatNumber value="${dataVerify.count}" pattern="#,###.###"/> </td>
						<td>￥ <fmt:formatNumber value="${dataVerify.money}" pattern="#,###.###"/></td>
						
						<c:choose>
						<c:when test="${dataVerify.reportStatus == 2}">
						<td>
						<fmt:formatNumber value="${dataVerify.realCount}" pattern="#,###.###"/>
						</td>
						<td>
						￥<fmt:formatNumber value="${dataVerify.realMoney}" pattern="#,###.###"/>
						</td>
						</c:when>
						<c:otherwise>
						<input type="hidden"  id="hidRealMoney_${dataVerify.reportId}" name="hidRealMoney_${dataVerify.reportId}" value="${dataVerify.realMoney }" />
						<input type="hidden" id="hidRealCount_${dataVerify.reportId}" name="hidRealCount_${dataVerify.reportId}" value="${dataVerify.realCount }" />
						<td>
						<fmt:formatNumber var="fmtRealCount" value="${dataVerify.realCount}" pattern="#,###.###"/>
						<input type="text" name="realCount_${dataVerify.reportId}" value="${fmtRealCount}" style="border: 0px;background-color:transparent;width:80px;" ondblclick="javascript:this.select();"  onclick="mouseOver(this);" onblur="mouseOut(this,'hidRealCount_','${dataVerify.reportId}','hidRealMoney_','realMoney_','${dataVerify.payType}','${dataVerify.webMasterPrice}');"/>
						</td>
						<td><fmt:formatNumber value="${dataVerify.webMasterPrice}" pattern="#,###.##" /></td>
						<td>
						<fmt:formatNumber var="fmtRealMoney"  value="${dataVerify.realMoney}" pattern="#,###.###"/>
						￥<input type="text" id="realMoney_${dataVerify.reportId}" name="realMoney_${dataVerify.reportId}" value="${fmtRealMoney}" style="border: 0px;background-color:transparent;width:80px;" ondblclick="javascript:this.select();" onclick="mouseOver(this);" onblur="mouseOut(this,'hidRealMoney_','${dataVerify.reportId}');"/>
						</td>
						</c:otherwise>
						</c:choose>
						<td>
						<c:choose>
						<c:when test="${dataVerify.money-dataVerify.realMoney < 0}">
						<font color=red>￥<fmt:formatNumber value=" ${dataVerify.money-dataVerify.realMoney}" pattern="#,###.###"/></font>
						</c:when>
						<c:otherwise>
						￥<fmt:formatNumber value=" ${dataVerify.money-dataVerify.realMoney}" pattern="#,###.###"/>
						</c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:choose>
						<c:when test="${dataVerify.money eq 0}">
						0%
						</c:when>
						<c:otherwise>
						<fmt:formatNumber value=" ${(dataVerify.money-dataVerify.realMoney)/dataVerify.money*100}" pattern="#,###.##"/>%
						</c:otherwise>
						</c:choose>
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