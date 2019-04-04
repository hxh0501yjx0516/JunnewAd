<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑广告计划</title> 
<script type="text/javascript">
  function getSelectedText(obj,outObject){
   var strsel = obj.options[obj.selectedIndex].text;
   document.getElementById(outObject).value=strsel;
  }
 </script>
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="editReadyPlan" method="post" action="${pageContext.request.contextPath }/readyplan.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input id="adPlanName" name="adPlanName" type="hidden" value="${readyPlan.adPlanName}"/>
			<input name="readyPlanId" type="hidden" value="${readyPlan.readyPlanId}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>计划名称：</label>
					<label>
						<select class="" name="adPlanId" >
							<c:forEach items="${adPlanList}" var="adPlan">
								<option value="${adPlan.adPlanId}" ${readyPlan.adPlanId == adPlan.adPlanId?"selected":""}>${adPlan.adPlanName}</option>
							</c:forEach>
						</select>
					</label>
				</p>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="readyPlanState" value="0" ${readyPlan.readyPlanStatus==0?"checked":""}/>正常</label>
					<label><input type="radio" name="readyPlanState" value="1" ${readyPlan.readyPlanStatus==1?"checked":""}/>锁定</label>
				</p>
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
