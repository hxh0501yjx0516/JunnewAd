<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>广告创意列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/adcreative.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<!--【可选】查询条件-->
	<input type="hidden" name="adPlanId" value="${adPlanId }" />
	<input type="hidden" name="adPlanCycleId" value="${adPlanCycleId }" />
  </form>
<body>
<div class="page">
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/adactive.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/adcreative.do?action=add&adPlanId=${adPlanId}&adPlanCycleId=${adPlanCycleId}" target="navTab" rel="addCreative"><span>添加广告创意</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					<th style="width:60px;">编号</th>
					<th style="width:150px;">创意名称</th>
					<th style="width:60px;">创意尺寸</th>
					<th style="width:300px;">目标地址</th>
					<th style="width:100px;">所属广告计划</th>
					<th style="width:100px;">所属广告周期</th>
					<th style="width:60px">状态</th>
					<th style="width:60px">创意类型</th>
					<th style="width:60px">目标类型</th>
					<th style="width:60px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="adCreative" >
					<tr> 
						<td>${adCreative.adCreativeId}</td>
						<c:choose>
							<c:when test="${!empty adCreative.adCreativeImg}">
							<td onmouseover="document.getElementById('${adCreative.adCreativeId}').style.display='block';" onmouseout="document.getElementById('${adCreative.adCreativeId}').style.display='none';">${adCreative.adCreativeName}</td>
							</c:when>
							<c:otherwise>
							<td >${adCreative.adCreativeName}</td>
							</c:otherwise>
						</c:choose>
						<td>${adCreative.adSizeName}</td>
						<td  >
						<c:choose>
								<c:when test="${adCreative.isP4p == 0}">
									<a title="${adCreative.adCreativeUrl}">${fn:substring(adCreative.adCreativeUrl,0,60)}</a>
								</c:when>
									<c:otherwise><xmp>${fn:substring(adCreative.htmlCode,0,30)}</xmp></c:otherwise>
							</c:choose>
						</td>
						<td>${adCreative.adPlanName}</td>
						<td>${adCreative.adPlanCycleName}</td>
						<td>
						<c:if test="${adCreative.adCreativeStatus == 0}">
						<a name="editStatebutton" title="锁定广告创意，将会停止所有该创意的投放，是否确定？"    href="${pageContext.request.contextPath }/adcreative.do?action=edit&adCreativeId=${adCreative.adCreativeId}&adCreativeState=1&flag=editState" target="navTabTodo" ><span style="color:blue">正常</span></a>
						</c:if>
						<c:if test="${adCreative.adCreativeStatus == 1}">
						<a name="editStatebutton" title="开启广告创意，该创意的投放需手动开启，是否确定？"   href="${pageContext.request.contextPath }/adcreative.do?action=edit&adCreativeId=${adCreative.adCreativeId}&adCreativeState=0&flag=editState" target="navTabTodo" ><span style="color:orange">锁定</span></a>
						</c:if>
						</td>
						<td>
						<c:if test="${adCreative.isDefault == 0}">
						<a name="editFlagbutton"   href="${pageContext.request.contextPath }/adcreative.do?action=edit&adCreativeId=${adCreative.adCreativeId}&defaultFlag=1&flag=editFlag" target="navTabTodo" ><span style="color:blue">普通</span></a>
						</c:if>
						<c:if test="${adCreative.isDefault == 1}">
						<a name="editFlagbutton"   href="${pageContext.request.contextPath }/adcreative.do?action=edit&adCreativeId=${adCreative.adCreativeId}&defaultFlag=0&flag=editFlag" target="navTabTodo" ><span style="color:orange">默认</span></a>
						</c:if>
						<c:if test="${adCreative.isDefault == 2}">
						<span style="color:orange">自取</span>
						</c:if>
						</td>
						<td>
							<c:choose>
								<c:when test="${adCreative.isP4p == 0}">
									链接
								</c:when>
									<c:when test="${adCreative.isP4p == 1}">
									JS P4P
								</c:when>
									<c:when test="${adCreative.isP4p == 2}">
									HTML CODE
								</c:when>
									<c:when test="${adCreative.isP4p == 3}">
									链接地址P4P
								</c:when>
							</c:choose>
						</td>
						<td>
						<a name="editbutton" title="修改创意" class="btnEdit" href="${pageContext.request.contextPath }/adcreative.do?action=edit&adCreativeId=${adCreative.adCreativeId}&flag=edit" target="navTab" rel="editAdCreative"><span style="color:blue">修改创意</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						
					</tr>
					<div style='position: absolute;right:5px;bottom:0;display: none; border:1px solid #B6CAE3;z-index:99;background:#F2F6FB;' id='${adCreative.adCreativeId}'>
					<c:choose>
					<c:when test="${adCreative.adCreativeImg == ''}">
						<splan style="color:orange">暂无</splan>
					</c:when>
					<c:when test="${fn:indexOf(adCreative.adCreativeImg,'.swf') > 0}">
					<embed id="showimg" src="${adCreative.adCreativeImg}" border="0" wmode="transparent"></embed>
					</c:when>
					<c:otherwise>
						<img alt="" src="${adCreative.adCreativeImg}"><br />
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