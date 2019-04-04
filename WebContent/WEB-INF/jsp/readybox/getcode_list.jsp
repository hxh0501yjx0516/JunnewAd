<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>获取代码列表</title> 
<script type="text/javascript">
$(function(){
	
	$("#validateWeight").click(function(){
		
		var weightList = new Array();
	    var weightStatusList = new Array();
	    var i = 0;
	    var j = 0;
	    var weightCount = 0;
	    
	    $("input[name=weight]").each(function() {
	        weightList[i] = $(this).attr("value");
	        i++;
	    });
	    $("input[name=weightStatus]").each(function() {
	        weightStatusList[j] = $(this).attr("value");
	        j++;
	    });
	    

	    for(x in weightStatusList){
	    	if (weightStatusList[x] == 0){
	    		weightCount += parseInt(weightList[x]);
	    	}
	    };

	    if (weightCount != 100){;
	    	alert("正常状态的普通创意，轮播权重总和应为100，请检查轮播权重设置");
	    	return false;
	    } else {
	    	$(this).attr("target","dialog");
	    }
	    
	});
});
</script>
</head>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath }/readybox.do?action=list" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="adBoxId" value="${adBoxId}" />
  </form>
<body>
	<div class="pageContent" >
			<form method="post"  action="${pageContext.request.contextPath }/readybox.do?action=list" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="353">
			<thead>
				<tr>
					<th style="width:40px;" >编号</th>
					<th style="width:120px;">广告创意</th>
					<th style="width:100px;">广告规格</th>
					<th style="width:100px;">广告尺寸</th>
					<th style="width:100px;">计划周期</th>
					<th style="width:100px;">广告计划</th>
					<th style="width:100px;">轮播权重</th>
					<th style="width:100px;">编辑</th>
					<th style="width:100px;">状态</th>
					<th style="width:100px;">创意类型</th>
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody >
				<c:forEach items="${readyBoxList}" var="readyBox" varStatus="s">
					<tr> 
						<td>${s.count}</td>
						<td>${readyBox.adCreativeName}</td>
						<td>${readyBox.adBoxInfoName}</td>
						<td>${readyBox.adWidth}x${readyBox.adHeight}</td>
						<td>${readyBox.adPlanCycleName}</td>
						<td>${readyBox.adPlanName}</td>
						<td>
						<!-- 100-readyBox.adCreativeLevel -->
						${readyBox.adCreativeLevel}
						<input type="hidden" id="weight" name="weight" value="${readyBox.adCreativeLevel }" />
						</td>
						<td>
						<c:choose>
							<c:when test="${empty readyBox.areaFix}">
							<span style="color: orange">未编辑</span>
							</c:when>
							<c:otherwise>
							<span style="color: blue">已编辑</span>
							</c:otherwise>
						</c:choose>
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
							<span style="color: orange">废弃</span>
							</c:otherwise>
						</c:choose>
						<input type="hidden" id="weightStatus" name="weightStatus" value="${readyBox.readyBoxStatus }" />
						</td>
						<c:choose>
							<c:when test="${readyBox.isDefault == 0}">
							<td>
							<span style="color: blue">普通创意</span>
							</td>
							<td>
								<a name="editbutton" title="修改投放创意" class="btnEdit" href="${pageContext.request.contextPath }/readybox.do?action=edit&readyBoxId=${readyBox.readyBoxId}&flag=edit&isDefault=${readyBox.isDefault}&adCreativeId=${readyBox.adCreativeId}" target="navTab" rel="editReadyBox"><span style="color:blue">修改</span></a>
							<c:if test="${readyBox.readyBoxStatus ne 2}">
								<a name="deletebutton" title="确定要删除吗？" class="btnDel" href="${pageContext.request.contextPath }/readybox.do?action=delete&readyBoxId=${readyBox.readyBoxId}" target="navTabTodo" ><span style="color:blue">删除</span></a>
							</c:if>
							<c:if test="${readyBox.readyBoxStatus eq 2}">
							&nbsp;&nbsp;<a name="deletebutton" title="确定执行投放清理吗？\r\n避免投放停止后继续产生数据" href="${pageContext.request.contextPath }/readybox.do?action=delete&readyBoxId=${readyBox.readyBoxId}" target="navTabTodo" ><span style="color:blue">投放清理</span></a>
							</c:if>
							</td>
							</c:when>
							<c:otherwise>
							<td>
							<span style="color: orange">默认创意</span>
							</td>
							<td>
							<c:if test="${readyBox.readyBoxStatus ne 2}">
								<a name="editbutton" title="修改投放创意" class="btnEdit" href="${pageContext.request.contextPath }/readybox.do?action=edit&readyBoxId=${readyBox.readyBoxId}&flag=edit&isDefault=${readyBox.isDefault}&adCreativeId=${readyBox.adCreativeId}" target="navTab" rel="editReadyBox"><span style="color:blue">修改</span></a>
							</c:if>
						</td>	
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div style="text-align: center;width:100%;border:0px solid red;">
		<c:choose>
		<c:when test="${getCodeFlag ne ''}">
		<a class="buttonDisabled" href="javascript:void(0);" style="margin: 0 auto;float:none;width:80px;border:0px solid red;"><span>生成代码</span></a>
		
		<span style="color: red">将投放列表中的所有创意编辑后再生成代码</span>
		</c:when>
		<c:otherwise>
		<a class="buttonActive" href="${pageContext.request.contextPath }/readybox.do?action=getcode&adBoxId=${adBoxId}" target="dialog" rel="getCodeDialog"  mask="true" style="margin: 0 auto;float:none;width:80px;border:0px solid red;"><span>生成代码</span></a>
		<a  href="${pageContext.request.contextPath }/readybox.do?action=getCodeNewTest&adBoxId=${adBoxId}" target="" rel="getCodeDialog"  mask="true" style="margin: 0 auto;float:none;width:80px;border:0px solid red; position:relative; top:-20px; left:100px; display:inline" id="validateWeight"><span>新轮播测试：仅限纯点击</span></a>
		
		</c:otherwise>
		</c:choose>
		</div>
	</form>
	<form    class="pageForm required-validate"  action="${pageContext.request.contextPath }/readybox.do?action=add" method="post" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<input type="hidden" name="adBoxId" value="${adBoxId}" />
		<div class="accordion" >
		<div class="accordionHeader">
			<h2>默认广告创意</h2>
		</div>
		<div class="accordionContent"  style="height: 214px;">
			<table class="table" layouth="293">
			<thead>
				<tr>
					<th style="width:35px;" ></th>
					<th style="width:200px;" orderField="${defaultAdCreative.adCreativeName}" class="">广告创意</th>
					<th style="width:100px;">广告规格</th>
					<th style="width:100px;">广告尺寸</th>
					<th style="width:150px;">计划周期</th>
					<th style="width:150px;">广告计划</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty defaultFlag}">
					<c:forEach items="${adCreativeList}" var="defaultAdCreative" varStatus="s" step="1">
					<c:if test="${defaultAdCreative.isDefault == 1}">
					<tr> 
						<td>
						<input type="radio" name="defaultIds" 
						value="${defaultAdCreative.adCreativeId}" ${defaultFlag == defaultAdCreative.adCreativeId?"checked":""}/>
						</td>
						<td>${defaultAdCreative.adCreativeName}</td>
						<td>${defaultAdCreative.adBoxInfoName}</td>
						<td>${defaultAdCreative.adSizeName}</td>
						<td>${defaultAdCreative.adPlanCycleName}</td>
						<td>${defaultAdCreative.adPlanName}</td>
					</tr>
					</c:if>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<c:forEach items="${adCreativeList}" var="defaultAdCreative" varStatus="s" step="1">
					<c:if test="${defaultAdCreative.isDefault == 1}">
					<tr> 
						<td>
						<input type="radio" name="defaultIds" 
						value="${defaultAdCreative.adCreativeId}" ${s.count == 1?"checked":""}/>
						</td>
						<td>${defaultAdCreative.adCreativeName}</td>
						<td>${defaultAdCreative.adBoxInfoName}</td>
						<td>${defaultAdCreative.adSizeName}</td>
						<td>${defaultAdCreative.adPlanCycleName}</td>
						<td>${defaultAdCreative.adPlanName}</td>
					</tr>
					</c:if>
					</c:forEach>
					</c:otherwise>
					</c:choose>
			</tbody>
		</table>
		</div>
		<div class="accordionHeader">
			<h2>普通广告创意</h2>
		</div>
		<div class="accordionContent" style="height: 214px;">
			<table class="table" layouth="293">
			<thead>
				<tr>
					<th style="width:35px;" ><input type="checkbox" group="ids"  class="checkboxCtrl"/></th>
					<th style="width:200px;">广告创意</th>
					<th style="width:100px;">广告规格</th>
					<th style="width:100px;">广告尺寸</th>
					<th style="width:150px;">计划周期</th>
					<th style="width:150px;">广告计划</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${adCreativeList}" var="adCreative" varStatus="s">
					<c:if test="${adCreative.isDefault == 0}">
					<tr> 
						<td> <input type="checkbox" name="ids" value="${adCreative.adCreativeId}"/> </td>
						<td>${adCreative.adCreativeName}</td>
						<td>${adCreative.adBoxInfoName}</td>
						<td>${adCreative.adSizeName}</td>
						<td>${adCreative.adPlanCycleName}</td>
						<td>${adCreative.adPlanName}</td>
					</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div style="text-align: center;width:100%;border:0px solid red;">
		
		<c:choose>
			<c:when test="${empty adCreativeList}">
			<div class="buttonDisabled" style="margin: 0 auto;float:none;width:100px;border:0px solid red;" >
		<div class="buttonContent"  >
		<button type="submit" style="line-height: 18px;cursor: pointer;" disabled="disabled">添加创意</button>
		 </div>
		 </div>
			</c:when>
			<c:otherwise>
			<div class="buttonActive" style="margin: 0 auto;float:none;width:100px;border:0px solid red;" >
		<div class="buttonContent"  >
		<button type="submit" style="line-height: 18px;cursor: pointer;" >添加创意</button>
		 </div>
		 </div>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
		</form>
		</div>
</body>
 </html>