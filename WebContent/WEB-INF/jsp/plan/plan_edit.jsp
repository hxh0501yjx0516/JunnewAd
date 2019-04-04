<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
 <html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑广告计划</title> 
</head>
 <body>
    <div class="page"> 
	<div class="pageContent">
		<form name="editAdPlan" method="post" action="${pageContext.request.contextPath }/plan.do?action=edit"enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);">
			<input name="adPlanId"  type="hidden" size="30" value="${adPlan.adPlanId}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>计划名称：</label>
					<label><input name="name" class="required" type="text" size="30" value="${adPlan.adPlanName}"/></label>
				</p>
					<p>
					<label>所属客户：</label>
					<label><select  name="customerId" class="">
						<c:forEach items="${customerList}" var="customer">
						<option value="${customer.customerId}" ${customer.customerId == adPlan.customerId?"selected":""}>${customer.customerName}</option>
						</c:forEach>
					</select>
					</label>
				</p>
				<p>
					<label>上传文件：</label>
				<input name="formFile" type="file" />
				</p>
				<div class="divider"></div>
				<p>
					<label>客户返点：</label>
					<label><input name="adPlanRebate" class="required" type="text" size="5" value="${adPlan.adPlanRebate }" />%</label>
				</p>
				
				<div class="divider"></div>
				<p>
					<label>状态：</label>
					<input type="radio" name="adPlanState" value="0" ${adPlan.adPlanStatus == 0?"checked":""}/>正常
					<input type="radio" name="adPlanState" value="1" ${adPlan.adPlanStatus == 1?"checked":""}/>锁定
				</p>
					<p>
					<label>标识：</label>
					<label><input type="radio" name="adPlanFlag" value="0" ${adPlan.adPlanFlag == 0?"checked":""}/>显示</label>
					<label><input type="radio" name="adPlanFlag" value="1" ${adPlan.adPlanFlag == 1?"checked":""}/>隐藏</label>
				</p>
				<div class="divider"></div>
				<p>
					<label>预览：</label>
					<c:choose>
					<c:when test="${adPlan.adPlanImg eq null or adPlan.adPlanImg eq ''}"><splan style="color:orange">暂无</splan></c:when>
					<c:otherwise>
					<img alt="" src="${adPlan.adPlanImg}">
					</c:otherwise>
					</c:choose>
				</p>
			</div>
			<div class="formBar">
				<ul>					
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()"><div>取消</div></button></div></div>
					</li>
				</ul> 
			</div>
		</form>
	</div>
</div>
  </body>
</html>
