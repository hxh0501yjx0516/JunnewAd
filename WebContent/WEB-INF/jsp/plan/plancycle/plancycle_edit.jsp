<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑计划周期</title> 
<script type="text/javascript" src="js/dailyFlow.js"></script>
<script type="text/javascript">
function autoLoadTableEdit(){
	var isParamter = document.getElementById("hidIsParameter").value;
	showOrHidUrlEdit(isParamter);
	var dateTime = document.getElementById("hidDateTime").value;
	var flowList = document.getElementById("hidFlow").value;
	//回显日ip流量控制表格
	if(dateTime){
		//alert("ip流量控制验证成功，进入此方法");
		var showIp = dateTime.split("|");
		var flowArr = flowList.split("|");
		for(var i=0;i<showIp.length;i++){
			var dateTime = showIp[i];
			var flow = flowArr[i];
			var newDate = dateTime.substring(0,4)+"-"+dateTime.substring(5,7)+"-"+dateTime.substring(8);
		insertplanTask("creativeDailyTbodyEdit",1,newDate,flow);
		}
	}
	}
function showOrHidUrlEdit(state){
	var url = document.getElementById("urlAddressEdit");
	var adCreativeUrl = document.getElementById("adPlanCycleUrlEdit");
	if(state == 0){
		url.style.display = "block";
		adCreativeUrl.className = "required";
	}else{
		url.style.display = "none";
		adCreativeUrl.className = "";
	}
}
function testUrlEdit(obj){
	var url = document.getElementById(obj).value;
		if( url !=''){
		if(url.indexOf("http://")==-1){
			url = "http://"+url;
		}
		window.open(url);
		}
	}
document.onload =autoLoadTableEdit();
</script>
</head>
<body >
    <div class="page">
	<div class="pageContent">
		<form name="addAdPlan" method="post" action="${pageContext.request.contextPath }/plancycle.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input id="adPlanCycleId" name="adPlanCycleId" type="hidden" value="${adPlanCycle.adPlanCycleId}"/>
			<input id="hidIsParameter" name="hidIsParameter" type="hidden" value="${adPlanCycle.adPlanIsParameter}"/>
			<input id="hidDateTime" name="hidDateTime" type="hidden" value="${adPlanCycle.dateString}"/>
			<input id="hidFlow" name="hidFlow" type="hidden" value="${adPlanCycle.dataString}"/>
			<div class="pageFormContent" layoutH="33">
						<h3 class="contentTitle">
							所属广告计划：${adPlanName}
						</h3>
				<p>
					<label>计划周期名称：</label>
					<label><input name="adPlanCycleName" class="required" type="text" size="30" value="${adPlanCycle.adPlanCycleName}"/></label>
				</p>
				<p>
						<fmt:parseDate value="${adPlanCycle.beginTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" var="beginTime" pattern="yyyy-MM-dd"/>
					<label>起始时间：</label>
					<input name="beginTime" class="date required" pattern="yyyy-MM-dd" readonly="true"  type="text" size="30" value="${beginTime}"/><a class="inputDateButton" href="#">选择</a>
				</p>
				<p>
						<fmt:parseDate value="${adPlanCycle.endTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" var="endTime" pattern="yyyy-MM-dd"/>
					<label>结束时间：</label>
					<input name="endTime" class="date required"  pattern="yyyy-MM-dd" readonly="true" type="text" size="30" value="${endTime}"/><a class="inputDateButton" href="#">选择</a>
				</p>
				
				<p>
					<label>扣量：</label>
					<label><input name="disCount" class="required number" min="-100" max="100" type="text" size="30" value="${adPlanCycle.disCount}"/></label>
					</label>
				</p>
				
				<p>
					<label>客户单价：</label>
					<label><input name="customerPrice" class="required number" type="text" size="30" value="${adPlanCycle.customerPrice}"/></label>
					</label>
				</p>
				<p>
					<label>预算：</label>
					<label><input name="customerAllPrice" class="required number"  type="text" size="30" value="${adPlanCycle.customerAllPrice}"/></label>
					</label>
				</p>
				<div class="divider"></div>
				
				<p>
					<label>媒体最低单价：</label>
					<label><input name="webMasterPrice" class="required number" type="text" size="30" value="${adPlanCycle.webMasterPrice}" ${role == false?"readonly":"" }/></label>
					</label>
				</p>
				
				<div class="divider"></div>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="adPlanCycleState" value="0" ${adPlanCycle.adPlanCycleStatus == 0?"checked":""}/>正常</label>
					<label><input type="radio" name="adPlanCycleState" value="1" ${adPlanCycle.adPlanCycleStatus == 1?"checked":""}/>锁定</label>
				</p>
					<p>
					<label>传参：</label>
					<label><input type="radio" name="isParameter" value="0" onclick="showOrHidUrlEdit(0)" ${adPlanCycle.adPlanIsParameter == 0?"checked":""}/>是</label>
					<label><input type="radio" name="isParameter" value="1" onclick="showOrHidUrlEdit(1)" ${adPlanCycle.adPlanIsParameter == 1?"checked":""}/>否</label>
				</p>
				<p id="urlAddressEdit" style="display: none;">
					<label>目标地址：</label>
						<input id="adPlanCycleUrlEdit"  name="adPlanCycleUrl" class="required" type="text" size="30" value="${adPlanCycle.adPlanCycleUrl}" />
						<input type="button" onclick="testUrlEdit('adPlanCycleUrlEdit');" value="链接测试"/>
				</p>
					<p>
					<label>显示类型：</label>
					<select  name="showType" >
						<option value="1" ${adPlanCycle.showType == 1?"selected":""}>UV</option>
						<option value="2" ${adPlanCycle.showType == 2?"selected":""}>PV</option>
						<option value="3" ${adPlanCycle.showType == 3?"selected":""}>IP</option>
					</select>
				</p>
				<h3 class="contentTitle">
							计划数据
						</h3>
						<div class="button">
							<div class="buttonContent">
								<button type="button"
									onclick="insertplanTask('creativeDailyTbodyEdit',document.getElementById('rowNum2').value)">
									新建
								</button>
							</div>
						</div>
						<input type="text" id="rowNum2" 
							class="textInput valid" style="margin: 2px;" value="1" size="2">
						<table class="list nowrap" id="dailyTable" width="100%">
							<thead>
								<tr>
									<th width="40">
										编号
									</th>
									<th width="100">
										日期
									</th>
									<th width="100">
										日流量
									</th>
									<th width="100">
										操作
									</th>
								</tr>
							</thead>
							<tbody id="creativeDailyTbodyEdit">
							</tbody>
						</table>
			</div>
			<div class="formBar">
				<ul>					
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
  </body>
</html>
