<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>广告位列表</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/adbox.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<!--【可选】查询条件-->
	<input type="hidden" name="urlAddressId" value="${urlAddressId }" />
	<input type="hidden" name="webMasterId" value="${webMasterId }" />
	
  </form>
<body>
<div class="page">
<!-- 
<div class="pageHeader">
		<form name="adboxList"  action="${pageContext.request.contextPath }/adbox.do?action=list" method="post" onsubmit="return navTabSearch(this);">
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
					<li style="width:50px;"><div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
		</form>
	</div>
		 -->
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/adbox.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/adbox.do?action=add&urlAddressId=${urlAddressId}&webMasterId=${webMasterId}" target="navTab" rel="addAdBox"><span>添加广告位</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					<th style="width:80px;">编号</th>
					<th style="width:120px;">广告位名称</th>
					<th style="width:100px;">广告位规格</th>		
					<th style="width:100px;">广告位模式</th>
					<th style="width:100px;">广告位尺寸</th>
					<th style="width:100px;">所属网站主</th>
					<th style="width:100px;">所属域名</th>
					<th style="width:60px">状态</th>
					<th style="width:60px">绑定域名</th>
					<th style="width:120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="adBox" >
					<tr> 
						<td>${adBox.adBoxId}</td>
						<td>${adBox.adBoxName}</td>
						<td>${adBox.adBoxInfoName}</td>
						<td>${adBox.modelName}</td>
						<td>${adBox.sizeName}</td>
						<td>${adBox.webMasterName}</td>
						<td>${adBox.urlName}</td>
						<td>
						<c:if test="${adBox.adBoxStatus == 0}">
						<a name="editStatebutton" title="锁定广告位，将会停止所有该广告位下的投放，是否确定？"  href="${pageContext.request.contextPath }/adbox.do?action=edit&adBoxId=${adBox.adBoxId}&adBoxState=1&flag=editState" target="navTabTodo" ><span style="color:blue">正常</span></a>
						</c:if>
						<c:if test="${adBox.adBoxStatus == 1}">
						<a name="editStatebutton" title="开启广告位，该广告位下的投放需手动开启，是否确定？"  href="${pageContext.request.contextPath }/adbox.do?action=edit&adBoxId=${adBox.adBoxId}&adBoxState=0&flag=editState" target="navTabTodo" ><span style="color:orange">锁定</span></a>
						</c:if>
						</td>
						<td>
						<c:if test="${adBox.isURLBand == 0}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/adbox.do?action=edit&adBoxId=${adBox.adBoxId}&urlBandFlag=1&flag=editFlag" target="navTabTodo" ><span style="color:blue">否</span></a>
						</c:if>
						<c:if test="${adBox.isURLBand == 1}">
						<a name="editStatebutton"   href="${pageContext.request.contextPath }/adbox.do?action=edit&adBoxId=${adBox.adBoxId}&urlBandFlag=0&flag=editFlag" target="navTabTodo" ><span style="color:orange">是</span></a>
						</c:if>
						</td>
						<td>
						<a name="editbutton" title="修改广告位" class="btnEdit" href="${pageContext.request.contextPath }/adbox.do?action=edit&adBoxId=${adBox.adBoxId}&flag=edit" target="navTab" rel="editAdBox"><span style="color:blue">修改广告位</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a name="editbutton" title="获取代码"  href="${pageContext.request.contextPath }/readybox.do?action=list&adBoxId=${adBox.adBoxId}" target="navTab" rel="getCode"><span style="color:blue">获取代码</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
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