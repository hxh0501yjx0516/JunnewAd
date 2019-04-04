<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加计划周期</title> 
<script type="text/javascript" src="js/dailyFlow.js"></script>
<script type="text/javascript">
function showOrHidUrl(state){
	var url = document.getElementById("urlAddressAdd");
	var adCreativeUrl = document.getElementById("adPlanCycleUrlAdd");
	if(state == 0){
		url.style.display = "block";
		adCreativeUrl.className = "required";
	}else{
		url.style.display = "none";
		adCreativeUrl.className = "";
	}
}
function testUrl(obj){
	var url = document.getElementById(obj).value;
		if( url !=''){
		if(url.indexOf("http://")==-1){
			url = "http://"+url;
		}
		window.open(url,"","");
		}
	}
</script>
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addAdPlan" method="post" action="${pageContext.request.contextPath }/plancycle.do?action=add&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input name="adPlanId" type="hidden" value="${adPlanId}"/>
			<div class="pageFormContent" layoutH="33">
				<h3 class="contentTitle">
							所属广告计划：${adPlanName}
						</h3>
				<p>
					<label>计划周期名称：</label>
					<label><input name="adPlanCycleName" class="required" type="text" size="30" /></label>
				</p>
				<p>
					<label>起始时间：</label>
					<input name="beginTime" class="date required" pattern="yyyy-MM-dd" readonly="true"  type="text" size="30" /><a class="inputDateButton" href="#">选择</a>
				</p>
				<p>
					<label>结束时间：</label>
					<input name="endTime" class="date required"  pattern="yyyy-MM-dd" readonly="true" type="text" size="30" /><a class="inputDateButton" href="#">选择</a>
				</p>
				
				<p>
					<label>扣量：</label>
					<label><input name="disCount" class="required number"  min="-100" max="100" type="text" size="30" /></label>
					</label>
				</p>
				<p>
					<label>客户单价：</label>
					<label><input name="customerPrice" class="required number" type="text" size="30" /></label>
					</label>
				</p>
				<p>
					<label>预算：</label>
					<label><input name="customerAllPrice" class="required number" type="text" size="30" /></label>
					</label>
				</p>
				
				<div class="divider"></div>
				
				<p>
					<label>媒体最低单价：</label>
					<label><input name="webMasterPrice" class="required number" type="text" size="30" /></label>
					</label>
				</p>
				
				<div class="divider"></div>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="adPlanCycleState" value="0" checked="checked"/>正常</label>
					<label><input type="radio" name="adPlanCycleState" value="1"/>锁定</label>
				</p>
				<p>
					<label>传参：</label>
					<label><input type="radio" name="isParameter" value="0" onclick="showOrHidUrl(0)"/>是</label>
					<label><input type="radio" name="isParameter" value="1" onclick="showOrHidUrl(1)" checked="checked"/>否</label>
				</p>
				<p id="urlAddressAdd" style="display:none;">
					<label>目标地址：</label>
						<input id="adPlanCycleUrlAdd"  name="adPlanCycleUrl"  type="text" size="30" />
						<input type="button" onclick="testUrl('adPlanCycleUrlAdd');" value="链接测试"/>
				</p>
				<p>
					<label>显示类型：</label>
					<select  name="showType" >
						<option value="1" selected="selected">UV</option>
						<option value="2" >PV</option>
						<option value="3" >IP</option>
					</select>
				</p>
				<h3 class="contentTitle">
							计划数据
						</h3>
						<div class="button">
							<div class="buttonContent">
								<button type="button"
									onclick="insertplanTask('creativeDailyTbody',document.getElementById('rowNum1').value)">
									新建
								</button>
							</div>
						</div>
						<input type="text" id="rowNum1" 
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
							<tbody id="creativeDailyTbody">
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
