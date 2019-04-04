<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<style type="text/css">
.photo_main{
	margin: 5px 5px;
	padding:10px;
	border: 1px solid silver;
	width: 220px;
	float: left;
	cursor: pointer;
	
}
.photo_main ul li{
   text-align: center;
}
.photo_main ul li span{
	text-align: left;
	width: 100%;
}
.photo_main ul li span a{
	color: blue;
	text-decoration: none;
}
.photo_main ul li span a:hover{
	text-decoration:underline;
}
.zoom{ position:relative;}
.smallImgOver{ filter:alpha(opacity=80); -moz-opacity:0.8; opacity: 0.8;}
.zoomBox{ display:none; position:absolute; top:0; left:0; width:150px; height:150px; border:1px solid #ddd; overflow:hidden; cursor:crosshair;}
.zoomBox img{ position:absolute;}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/imgWHauto.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/qsoft.PopBigImage.v0.35.js"></script>
<script type="text/javascript">
function borderit(which,flag){
//if IE 4+ or NS 6+
if (document.all||document.getElementById){
	if(flag){
	which.style.cssText="border: 1px solid orange;background-color: #eee;";
	}else{
	which.style.cssText="border: 1px solid silver;";
	}
}
}
function getAdPlanCycleList(){
		$.ajax( {
		type : 'POST',
		url : "${pageContext.request.contextPath }/util.do?action=getAdPlanCycleList",
		data : {adPlanId : $('#adPlanId').val(),qryuserGroup : $('#qryuserGroup1').val()},
		dataType : "json",
		cache : false,
		success : function(temp){
			$('#select1 option').remove(); 
			$("#select1").append($("<option>").text("全部").val("") );
			for(var i=0;i<temp.length;i++){ 
			_option = $("<option>").text(temp[i].adPlanCycleName).val(temp[i].adPlanCycleId);
			$("#select1").append(_option); 
			}
			if($("#adPlanCycleId").val() !=""){
				$("#select1").attr('value' ,$("#adPlanCycleId").val()); 	
			$("#adPlanCycleId").val("");
			}
		}
	});
		
}
document.onload=getAdPlanCycleList();
</script>
</head>
<!-- 分页 -->
<form id="pagerForm" method="post" action="${pageContext.request.contextPath }/snapshot.do?action=list&flag=pager" >
<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
<input type="hidden" name="addTimef" value="${addTimef }" /><!--【可选】查询条件-->
<input type="hidden" name="addTimet" value="${addTimet }" /><!--【可选】查询条件-->
<input type="hidden" name="webmaster" value="${webmaster }" /><!--【可选】查询条件-->
<input type="hidden" name="url" value="${url }" /><!--【可选】查询条件-->
<input type="hidden" name="userid" value="${userid }" /><!--【可选】查询条件-->
<input type="hidden" name="adPlanId" value="${adPlanId }" /><!--【可选】查询条件-->
<input type="hidden" name="adPlanCycleId" value="${adPlanCycleId }" /><!--【可选】查询条件-->
<input type="hidden" name="qryuserGroup" value="${qryuserGroup }" /><!--【可选】查询条件-->
</form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/snapshot.do?action=list" method="post">
		<input type="hidden" id="adPlanCycleId" value="${adPlanCycleId}"/>
		<div class="searchBar">
			<ul >		
				<li> 
					时间： 
					<input type="text" name="addTimef" id="addTimef" class="date" value="${addTimef}" size="8" readonly/>
					--
					<input type="text" name="addTimet" id="addTimet" class="date" value="${addTimet}" size="8" readonly/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					站长：
					<input type="text" name="webmaster" value="${webmaster }" size="8"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					域名：
					<input type="text"   name="url" value="${url }" size="8"  />
					&nbsp;&nbsp;&nbsp;&nbsp;
					媒介：
					<select name="userid">
						<option value="">全部</option>
						<c:forEach items="${userlist}" var="ul">
							<option value="${ul.userId }" <c:if test="${userid==ul.userId}"> selected </c:if>>${ul.realname }</option>
						</c:forEach>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					计划：
					<select id="adPlanId" name="adPlanId" style="width: 120px;" onchange="getAdPlanCycleList()">
						<option value="">全部</option>
						<c:forEach items="${adPlanlist}" var="ap">
							<option value="${ap.adPlanId }" <c:if test="${adPlanId==ap.adPlanId}"> selected </c:if>>${ap.adPlanName }</option>
						</c:forEach>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					周期：
					<select id="select1" name="adPlanCycleId" style="width: 120px;">
						<option value="">全部</option>
						<c:forEach items="${adPlanCyclelist}" var="apc">
							<option value="${apc.adPlanCycleId }" <c:if test="${adPlanCycleId==apc.adPlanCycleId}"> selected </c:if>>${apc.adPlanCycleName }</option>
						</c:forEach>					
					</select>
					<c:if test="${sessionScope.user.userGroup eq 0}">
					用户组：
					<select id="qryuserGroup1" name="qryuserGroup">
						<option value="" >请选择</option>
						<c:forEach items="${userGroupList}" var="userGroup">
						<option value="${userGroup.id}" ${qryuserGroup == userGroup.id?"selected":""}>${userGroup.userGroupName}</option>
						</c:forEach>
					</select>
					</c:if>
				</li>
				
			</ul>
			<div class="subBar">
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</div>
			</div>
		</form>
	</div>
	
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/snapshot.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	<div class="pageFormContent" layoutH="107" >
	<div style="text-align:right;width: 1120px;">
	<div style="border: 0px solid red;float: left;margin: 0 50px;width:auto;">
		<c:forEach var="snapshot" items="${list}" varStatus="s">
			<div class="photo_main" onmouseover="borderit(this,true)" onmouseout="borderit(this,false)">
				<ul>
				<li >
    			<a href="${path}${snapshot.path}" target="_blank">
    			<img title="点击查看大图" src="${path}${snapshot.path}" onload="imgWHauto(this,200,200)"  onmouseover="qsoft.PopBigImage.create(this,20,0,2,2,true);"/> 
				</a>
				</li>
				<li style="text-align: left;padding-left: 10px;">
    			<span>域名：<a title="${snapshot.source}" href="${snapshot.source}" target="_blank">${fn:substring(snapshot.source, 0,20)}...</a></span>
				</li>
				<li style="text-align: left;padding-left: 10px;">
    			<span>站长：${snapshot.webMasterName}&nbsp;&nbsp;&nbsp;</span>
				</li>
				<li style="text-align: left;padding-left: 10px;">
    			<span>日期：<fmt:parseDate value="${snapshot.insertTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
    			<fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
    			&nbsp;&nbsp;&nbsp;</span>
				</li>
				
				</ul>
				
			</div>
		</c:forEach>
		
		</div>
		</div>
		</div>
			<div class="panelBar">
			<%@ include file="/util/pager.jsp" %>
		</div>
	</form>
	</div>
</div>
</body>
 </html>