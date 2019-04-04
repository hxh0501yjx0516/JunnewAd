<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>域名信息</title> 
<script type="text/javascript">
function sourceDetail(obj,urlName,webMasterName,userId){
		 obj.href="${pageContext.request.contextPath }/dana.do?action=sourceDetailAction&url="+encodeURI(encodeURI(urlName))+"&webmaster=" +encodeURI(encodeURI(webMasterName))+"&userid="+userId;
	}
function allDetail(obj,urlName,webMasterName,userId){
		 obj.href="${pageContext.request.contextPath }/dana.do?action=allDetailAction&url="+encodeURI(encodeURI(urlName))+"&webmaster=" +encodeURI(encodeURI(webMasterName))+"&userid="+userId;
	}
function pieDetail(obj,urlName,webMasterName,userId){
		 obj.href="${pageContext.request.contextPath }/dana.do?action=pieDetailAction&url="+encodeURI(encodeURI(urlName))+"&webmaster=" +encodeURI(encodeURI(webMasterName))+"&userid="+userId;
	}
function urlToExcel(){
	var qryurl = document.urlForm.qryurl.value;
	var qrywebmaster = document.urlForm.qrywebmaster.value;
	var qryuserGroup = "";
	//if(${sessionScope.user.userGroup}==0){
	//	qryuserGroup = document.urlForm.qryuserGroup.value;
	//}
	location.href = "${pageContext.request.contextPath}/urladdress.do?action=urlToExcel&qryurl="+qryurl+"&qrywebmaster="+qrywebmaster+"&qryuserGroup="+qryuserGroup;
}
</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/urladdress.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<!--【可选】查询条件-->
	<input type="hidden" name="qryurl" value="${qryurl }" />
	<input type="hidden" name="qrywebmaster" value="${qrywebmaster }" />
	<input type="hidden" name="webMasterId" value="${webMasterId }" />
	<input type="hidden" name="qryuserGroup" value="${qryuserGroup }" />
	
  </form>
<body>
<div class="page">
<div class="pageHeader">
		<form name="urlForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/urladdress.do?action=list" method="post">
		<input type="hidden" name="webMasterId" value="${webMasterId }" />
		<div class="searchBar" >
			<ul class="searchContent" >
				<li>
					<label>域名：</label>
					<input type="text" name="qryurl" value="${qryurl }"/>
				</li>
				<li >
					<label>网站主：</label>
					<input type="text" name="qrywebmaster" value="${qrywebmaster }"/>
				</li>
				<c:if test="${sessionScope.user.userGroup eq 0}">
				<li>
				<label>用户组：</label>
					<select name="qryuserGroup">
						<option value="" >请选择</option>
						<c:forEach items="${userGroupList}" var="userGroup">
						<option value="${userGroup.id}" ${qryuserGroup eq userGroup.id?"selected":""}>${userGroup.userGroupName}</option>
						</c:forEach>
					</select>
				</li>
				</c:if>
					<li>
					<div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div>
					<div class="buttonActive">
						<div class="buttonContent">
							<a href="${pageContext.request.contextPath }/urladdress.do?action=list&webMasterId=${webMasterId}" target="navTab" title="域名列表" rel="域名列表"><button>重置</button></a>
						</div>
					</div>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="urlToExcel();">域名导出</button>
						</div>
					</div>
					</li>
			</ul>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/urladdress.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		
		<table class="table" layouth="88">
			<thead>
				<tr>
					<th style="width:60px;">编号</th>
					<th style="width:100px;">所属网站主</th>
					<th style="width:120px;">网站名称</th>
					<th style="width:100px;">域名</th>					
					<th style="width:100px;">类型</th>
					<th style="width:100px;">每日IP</th>
					<th style="width:80px;">ALEXA排名</th>
					<th style="width:80px;">地区</th>
					<th style="width:100px">广告位</th>
					<th style="width:80px">状态</th>
					<th style="width:200px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="urladdress" >
					<tr> 
						<td>${urladdress.urlId}</td>
						<td>${urladdress.webMasterName}</td>
						<td>${urladdress.urlName}</td>
						<td>${urladdress.url}</td>
						<td>${urladdress.urlTypeName}</td>
						<td>${urladdress.urlDayIp}</td>
						<td>${urladdress.urlAlexa}</td>
						<td>${urladdress.urlArea}</td>
						<td>
						<a name="planbutton" title="广告位列表"  href="${pageContext.request.contextPath }/adbox.do?action=list&urlAddressId=${urladdress.urlId}&webMasterId=${webMasterId}" style="color:orange" target="navTab" rel="adBoxList">${urladdress.adBoxCount}个</a>
						<a class="add" href="${pageContext.request.contextPath }/adbox.do?action=add&urlAddressId=${urladdress.urlId}&webMasterId=${webMasterId}" target="navTab" title="添加广告位" rel="addAdBox"><span style="color:blue">添加广告位</span></a>
						</td>
						<td>
						<c:if test="${urladdress.urlStatus == 0}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/urladdress.do?action=edit&urlId=${urladdress.urlId}&urlStatus=1&flag=editState" target="navTabTodo" ><span style="color:orange">待审核</span></a>
						</c:if>
						<c:if test="${urladdress.urlStatus == 1}">
						<a name="editStatebutton" title="锁定域名，将会停止所有该域名下的投放，是否确定？"   href="${pageContext.request.contextPath }/urladdress.do?action=edit&urlId=${urladdress.urlId}&urlStatus=2&flag=editState" target="navTabTodo" ><span style="color:blue">审核通过</span></a>
						</c:if>
						<c:if test="${urladdress.urlStatus == 2}">
						<a name="editStatebutton" title="开启域名，该域名下的投放需手动开启，是否确定？"    href="${pageContext.request.contextPath }/urladdress.do?action=edit&urlId=${urladdress.urlId}&urlStatus=0&flag=editState" target="navTabTodo" ><span style="color:orange">锁定</span></a>
						</c:if>
						</td>
						<td>
						<a name="editbutton" title="修改域名信息" class="btnEdit" href="${pageContext.request.contextPath }/urladdress.do?action=edit&urlId=${urladdress.urlId}&flag=edit" target="navTab" rel="editUrlAddress"><span style="color:blue">修改域名</span></a>&nbsp;&nbsp;
						<a href="javascript:void(0);"  onclick="sourceDetail(this,'${urladdress.urlName}','${urladdress.webMasterName}','${urladdress.userId}');"  target="navTab" rel="sourceDetail" style="color:blue">来源表</a>&nbsp;&nbsp;
						<a href="javascript:void(0);"  onclick="allDetail(this,'${urladdress.urlName}','${urladdress.webMasterName}','${urladdress.userId}');"  target="navTab" rel="allDetail" style="color:blue">曲线图</a>&nbsp;&nbsp;
						<a href="javascript:void(0);"  onclick="pieDetail(this,'${urladdress.urlName}','${urladdress.webMasterName}','${urladdress.userId}');"  target="navTab" rel="pieDetail" style="color:blue">地区分布</a>&nbsp;&nbsp;
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