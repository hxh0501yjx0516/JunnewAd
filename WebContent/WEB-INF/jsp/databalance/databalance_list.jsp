<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>结算数据管理</title> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/formatNumber.js"></script>
<script type="text/javascript">
	function submitBalance(obj,reportId){
		var count = document.getElementById("databalance_count_"+reportId).value;
		var money = document.getElementById("databalance_money_"+reportId).value;
		var realCount = document.getElementById("databalance_realCount_"+reportId).value;
		var realMoney = document.getElementById("databalance_realMoney_"+reportId).value;
		var trueMoney = document.getElementById("databalance_trueMoney_"+reportId).value;
		if(trueMoney.indexOf(",") !=-1){
		trueMoney = trueMoney.replace(/,/g,"");
		}
		var remark = document.getElementById("remark_"+reportId).value;
		var beginTime = document.getElementById("beginTime").value;
		var endTime = document.getElementById("endTime").value;
		var taxNum = document.getElementById("tax_"+reportId).value;
		
		var moneyRebate = document.getElementById("databalance_moneyRebate_"+reportId).value;
		if(moneyRebate.indexOf(",") !=-1){
			moneyRebate = moneyRebate.replace(/,/g,"");
		}
		var rebate = document.getElementById("databalance_rebate_"+reportId).value;
		var payee = document.getElementById("databalance_payee_"+reportId).value;
			
		 obj.href="${pageContext.request.contextPath }/databalance.do?action=balance&reportId="+reportId+"&count="+count+"&money="+money
		+"&realCount="+realCount+"&realMoney="+realMoney+"&trueMoney="+trueMoney+"&remark="+encodeURI(encodeURI(remark))
		+"&beginTime="+beginTime+"&endTime="+endTime+"&taxNum="+taxNum
		+"&moneyRebate="+moneyRebate+"&rebate="+rebate+"&payee="+payee;
	}
function FormatNumber(srcStr,nAfterDot){
　　var srcStr,nAfterDot;
　　var resultStr,nTen;
　　srcStr = ""+srcStr+"";
　　strLen = srcStr.length;
　　dotPos = srcStr.indexOf(".",0);
　　if (dotPos == -1){
　　　　resultStr = srcStr+".";
　　　　for (i=0;i<nAfterDot;i++){
　　　　　　resultStr = resultStr+"0";
　　　　}
　　　　return resultStr;
　　}
　　else{
　　　　if ((strLen - dotPos - 1) >= nAfterDot){
　　　　　　nAfter = dotPos + nAfterDot + 1;
　　　　　　nTen =1;
　　　　　　for(j=0;j<nAfterDot;j++){
　　　　　　　　nTen = nTen*10;
　　　　　　}
　　　　　　resultStr = Math.round(parseFloat(srcStr)*nTen)/nTen;
　　　　　　return resultStr;
　　　　}
　　　　else{
　　　　　　resultStr = srcStr;
　　　　　　for (i=0;i<(nAfterDot - strLen + dotPos + 1);i++){
　　　　　　　　resultStr = resultStr+"0";
　　　　　　}
　　　　　　return resultStr;
　　　　}
　　}
} 
//实收： 实收=应收 *（1-返点）
function rebate(input,reveNum,taxinput){
	var rebate = document.getElementById(taxinput).value;
	document.getElementById(input).value = FormatNumber(reveNum * (100-rebate) / 100,2);
}
//有效佣金扣税： 实际支付佣金=有效佣金 *（1-税点）
function koushui(input,reveNum,taxinput){
	var tax = document.getElementById(taxinput).value;
	document.getElementById(input).value = FormatNumber(reveNum * (1-tax),2);
}
function change(obj){
	var beginTime = document.balanceForm.qrybegintime.value;
	var endTime = document.balanceForm.qryendtime.value;
	if(beginTime == ''){
		alertMsg.info('请选择周期开始时间！');
		return false;
	}
	if(endTime == ''){
		alertMsg.info('请选择周期结束时间！');
		return false;
	}
	return navTabSearch(obj);
}
function resetForm(){
	document.balanceForm.qrybegintime.value = '';
	document.balanceForm.qryendtime.value= '';
	document.balanceForm.qrywebmaster.value= '';
	document.balanceForm.qryadplan.value= '';
	document.balanceForm.qrysales.value= 0;
}
function mouseOver(obj,flag,value){
		obj.style.cssText='border:1px solid;width:80px;';
		if(!flag){
			obj.value = obj.value.replace(/,/g,"").replace(/，/g,"");
		}
		obj.focus();
		if(value){
			obj.value=value;
		}
}
function mouseOut(obj,flag,value){
		obj.style.cssText='border:0px solid;background-color:transparent;width:80px;';
		if(!flag){
			obj.value = formatNumber(obj.value.replace(/,/g,"").replace(/，/g,""),'#,###.###');
		}
		obj.blur();
		if(value){
			obj.value=value;
		}
}
</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/databalance.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qrywebmaster" value="${qrywebmaster }" />
	<input type="hidden" name="qrysales" value="${qrysales }" />
	<input type="hidden" name="qryadplan" value="${qryadplan }" />
	<input type="hidden" name="qrybegintime" value="${qrybegintime }" />
	<input type="hidden" name="qryendtime" value="${qryendtime }" />
	<input type="hidden" name="currentusergroup" value="${currentusergroup }" />
  </form>
<body>
<div class="page">
<div class="pageHeader">
		<form name="balanceForm" onsubmit="return change(this)" action="${pageContext.request.contextPath }/databalance.do?action=list" method="post">
		
		<div class="searchBar" >
			<ul class="searchContent">
				<li >
					<label>开始时间：</label>
					<input type="text" name="qrybegintime" class="date" style="width:80px"  value="${qrybegintime }" readonly="readonly"/>
				</li>
					<li >
					<label>结束时间：</label>
					<input type="text" name="qryendtime" class="date" style="width:80px"  value="${qryendtime }" readonly="readonly"/>
				</li>
				<li >
					<label>网站主：</label>
					<input type="text" name="qrywebmaster" style="width:80px"  value="${qrywebmaster }"/>
				</li>
					<li >
					<label>广告计划：</label>
					<input type="text" name="qryadplan" style="width:80px" value="${qryadplan }"/>
				</li>
				</li>
					<li >
					<label>周期：</label>
					<input type="text" name="qryadplancycle" style="width:80px" value="${qryadplancycle }"/>
				</li>
				<li >
					<label>所属媒介：</label>
					<select  name="qrysales"  class="">
					<option value="0" >请选择</option>
					<c:forEach var="m" items="${userslist}">
						<option value="${m.userId}" ${m.userId == qrysales?"selected":""}>${m.realname}</option>
					</c:forEach>
					</select>
				</li>
				<c:if test="${usergroup eq 0}">
				<li >
					<label>用户组：</label>
					<select  name="currentusergroup" class="">
						<option value="0">全部</option>
						<c:forEach items="${userGroupList}" var="ug">
							<option value="${ug.id }" ${currentusergroup eq ug.id?"selected":"" }>${ug.userGroupName }</option>
						</c:forEach>
					</select>
				</li>
				</c:if>
					<li>
					<div class="buttonActive" ><div class="buttonContent"><button type="submit" >检索</button></div></div>
					<div class="buttonActive" ><div class="buttonContent"><button type="button" onclick="resetForm();">重置</button></div></div>
					</li>
			</ul>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/databalance.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	<input type="hidden" id="beginTime" value="${qrybegintime }" />
	<input type="hidden" id="endTime" value="${qryendtime }" />
		<table class="table" layouth="88">
			<thead>
				<tr>
					<th style="width:60px;">编号</th>
					<th style="width:100px;">网站主</th>
					<th style="width:100px;">所属媒介</th>					
					<th style="width:100px;">广告计划</th>
					<th style="width:50px;">返回值</th>
					<th style="width:100px;">应收</th>
					<th style="width:100px;">实收</th>
					<th style="width:50px;">返点</th>
					<th style="width:50px;">收款</th>
					<th style="width:100px;">利润</th>
					<th style="width:80px;">利润率</th>
					<th style="width:50px;">有效值</th>
					<th style="width:100px;">有效佣金</th>
					<th style="width:100px;">实际支付佣金</th>
					<th style="width:30px;">税点</th>
					<th style="width:50px;">备注</th>
					<th style="width:140px">操作</th>
				</tr>
			</thead>
				
			<tbody>
			<tr style="color: blue;font-weight: bold;">
					<td>汇总:</td>
					<td></td>
					<td></td>
					<td></td>
					<td><fmt:formatNumber value="${countSum}" pattern="#,###.##"/></td>
					<td>￥<fmt:formatNumber value="${moneySum}" pattern="#,###.##"/></td>
					<td>￥<fmt:formatNumber value="${moneyRebateSum}" pattern="#,###.##"/></td>
					<td></td>
					<td></td>
					<td>￥<fmt:formatNumber value="${moneyRebateSum-realMoneySum}" pattern="#,###.##"/></td>
					<td>
					<c:if test="${moneySum ==0}"></c:if>
					<c:choose>
						<c:when test="${moneySum == 0}">
							0%
						</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${(moneyRebateSum-realMoneySum)/moneyRebateSum*100}" pattern="#,###.##"/>%
						</c:otherwise>
					</c:choose>
					</td>
					<td><fmt:formatNumber value="${realCountSum}" pattern="#,###.##"/></td>
					<td>￥<fmt:formatNumber value="${realMoneySum}" pattern="#,###.##"/></td>
					<td>￥<fmt:formatNumber value="${realMoneySum}" pattern="#,###.##"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<c:forEach items="${list}" var="dataBalance" varStatus="s">
					<tr> 
						<input type="hidden" id="databalance_realCount_${dataBalance.reportId}" value="${dataBalance.realCount}" />
						<input type="hidden" id="databalance_count_${dataBalance.reportId}" value="${dataBalance.count}"  />
						<input type="hidden" id="databalance_money_${dataBalance.reportId}" value="${dataBalance.money}" />
						<input type="hidden" id="databalance_realMoney_${dataBalance.reportId}" value="${dataBalance.realMoney}"  />
						<td>${s.count}</td>
						<td>${dataBalance.webMasterName}</td>
						<td>${dataBalance.userName}</td>
						<td>${dataBalance.adplanName}</td>
						<td><fmt:formatNumber value="${dataBalance.count}" pattern="#,###.##"/></td>
						<td>￥<fmt:formatNumber value="${dataBalance.money}" pattern="#,###.##"/></td>
						<td>
						<fmt:formatNumber var="fmtMoneRebate" value="${dataBalance.moneyRebate}" pattern="#,###.##"/>
						￥<input type="text" id="databalance_moneyRebate_${dataBalance.reportId}" value="${fmtMoneRebate}" style="border: 0px;background-color:transparent;width:80px;" ondblclick="javascript:this.select()" onclick="mouseOver(this)"  onblur="mouseOut(this)"/>
						</td>
						<td>
						<input type="text" id="databalance_rebate_${dataBalance.reportId}" style="border: 0px;background-color:transparent;width:30px;" ondblclick="javascript:this.select()" onclick="this.style.cssText='border:1px solid;width:30px;';this.focus()" onblur="this.style.cssText='border:0px solid;color:#000000;background-color:transparent;width:80px;';this.blur()" value="${dataBalance.adPlanRebate}"/>
						</td>
						<td>
						${dataBalance.payee}
						<input type="hidden" id="databalance_payee_${dataBalance.reportId}" value="${dataBalance.payee}">
						</td>
						<td>￥<fmt:formatNumber value="${dataBalance.profit}" pattern="#,###.##"/></td>
						<td><fmt:formatNumber value="${dataBalance.profitRate}" pattern="#,###.##"/>%</td>
						<td><fmt:formatNumber value="${dataBalance.realCount}" pattern="#,###.##"/></td>
						<td>￥<fmt:formatNumber value="${dataBalance.realMoney}" pattern="#,###.##"/></td>
						<td>
						<fmt:formatNumber var="fmtRealMoney" value="${dataBalance.realMoney}" pattern="#,###.##"/>
						￥<input type="text" id="databalance_trueMoney_${dataBalance.reportId}" value="${fmtRealMoney}" style="border: 0px;background-color:transparent;width:80px;" ondblclick="javascript:this.select()" onclick="mouseOver(this)"  onblur="mouseOut(this)"/></td>
						<td ><input type="text" id="tax_${dataBalance.reportId}" style="border: 0px;background-color:transparent;width:50px;" ondblclick="javascript:this.select()" onclick="this.style.cssText='border:1px solid;width:80px;';this.focus()" onblur="this.style.cssText='border:0px solid;color:#000000;background-color:transparent;width:80px;';this.blur()" value="0.06"/></td>
						<td ><input type="text" id="remark_${dataBalance.reportId}" style="border: 0px;background-color:transparent;width:80px;" ondblclick="javascript:this.select()" onclick="this.style.cssText='border:1px solid;width:80px;';this.focus()" onblur="this.style.cssText='border:0px solid;color:#000000;background-color:transparent;width:80px;';this.blur()"/></td>
						<td>
						<a name="rebateButton" href="#" onclick="rebate('databalance_moneyRebate_${dataBalance.reportId}','${dataBalance.money}','databalance_rebate_${dataBalance.reportId}')"  ><span style="color:blue">返点</span></a>&nbsp;&nbsp;
						<a name="discountButton" href="#" onclick="koushui('databalance_trueMoney_${dataBalance.reportId}','${dataBalance.realMoney}','tax_${dataBalance.reportId}')"  ><span style="color:blue">扣税</span></a>&nbsp;&nbsp;
						<a name="balanceButton" href="javascript:void(0);" title="确认要结算？" onclick="submitBalance(this,'${dataBalance.reportId}');"   target="navTabTodo" ><span style="color:blue">结算</span></a>&nbsp;&nbsp;
						<a name="editbutton" title="${dataBalance.webMasterName}-${dataBalance.adplanName}-详情"  href="${pageContext.request.contextPath }/databalance.do?action=details&adPlanId=${dataBalance.adplanId}&webMasterId=${dataBalance.webMasterId}&beginTime=${qrybegintime}&endTime=${qryendtime}" target="navTab" rel="balanceDetails"><span style="color:blue">详情</span></a>
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