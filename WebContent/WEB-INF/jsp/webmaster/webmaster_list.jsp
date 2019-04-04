<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>网站主信息</title> 
<script type="text/javascript">
function sourceDetail(obj,webMasterName,userId){
		 obj.href="${pageContext.request.contextPath }/dana.do?action=sourceDetailAction&webmaster=" +encodeURI(encodeURI(webMasterName))+"&userid="+userId;
	}
function allDetail(obj,webMasterName,userId){
		 obj.href="${pageContext.request.contextPath }/dana.do?action=allDetailAction&webmaster=" +encodeURI(encodeURI(webMasterName))+"&userid="+userId;
	}
function pieDetail(obj,webMasterName,userId){
		 obj.href="${pageContext.request.contextPath }/dana.do?action=pieDetailAction&webmaster=" +encodeURI(encodeURI(webMasterName))+"&userid="+userId;
	}
function reportExcel(){
	var webmasterName = document.webmasterForm.qryname.value;
	var qrystate = document.webmasterForm.qrystate.value;
	var qryuserid = document.webmasterForm.qryuserid.value;
	var searchtype = document.webmasterForm.searchtype.value;
	var qryuserGroup ="";
	//if(${userGroup eq 0}){
	//	qryuserGroup =  document.webmasterForm.qryuserGroup.value;
	//}
	location.href="${pageContext.request.contextPath}/webmaster.do?action=webmasterToExcel&qryname="+webmasterName+"&qrystate="+qrystate+"&qryuserid="+qryuserid+"&searchtype="+searchtype+"&qryuserGroup="+qryuserGroup;
}
</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/webmaster.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" />
	<input type="hidden" name="qrystate" value="${qrystate }" /><!--【可选】查询条件-->
	<input type="hidden" name="searchtype" value="${searchtype }"> <!--检索类型 -->
	<input type="hidden" name="qryuserid" value="${qryuserid}">
	<input type="hidden" name="qryuserGroup" value="${qryuserGroup}">
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form name="webmasterForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/webmaster.do?action=list" method="post">
		<input type="hidden" name="searchtype" value="${searchtype }">
		<div class="searchBar" >
			<ul class="searchContent">
				<li>
					<label>网站主：</label>
					<input type="text" name="qryname" value="${qryname }"/>
				</li>
				<li>
					<label>状态：</label>
					<select  name="qrystate" class="">
						<option value="" ${qrystate eq ""?"selected":""}>请选择</option>
						<option value="0" ${qrystate eq 0?"selected":""}>正常</option>
						<option value="1" ${qrystate eq 1?"selected":""}>锁定</option>
					</select>
					<select name="qryuserid">
						<option value="1" ${qryuserid ne 0?"selected":""}>已分配媒介</option>
						<option value="0" ${qryuserid eq 0?"selected":""}>未分配媒介</option>
					</select>
					</li>
					<li>
					<label>媒介：</label>
					<select  name="userId" class="">
						<option value="">请选择</option>
						<c:forEach items="${userslist}" var="users">
						<option value="${users.userId}" ${userId eq users.userId?"selected":""}>${users.realname}</option>
						</c:forEach>
					</select>
					</li>
					<c:if test="${userGroup eq 0}">
					<li>
					<label>用户组：</label>
					<select name="qryuserGroup">
						<option value="" >请选择</option>
						<c:forEach items="${userGroupList}" var="userGroup">
						<option value="${userGroup.id}" ${qryuserGroup == userGroup.id?"selected":""}>${userGroup.userGroupName}</option>
						</c:forEach>
					</select>
				</li>
					</c:if>
					<li>
					<div class="buttonActive" ><div class="buttonContent"><button type="submit">检索</button></div></div>
					<div class="buttonActive">
						<div class="buttonContent">
							<a href="${pageContext.request.contextPath }/webmaster.do?action=list&searchtype=${searchtype}" target="navTab" title="站长列表" rel="站长列表"><button>重置</button></a>
						</div>
					</div>
					<div class="buttonActive">
						<div class="buttonContent">

							<button type="button" onclick="reportExcel();">站长导出</button>

						</div>
					</div>
					
					</li>
			</ul>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/webmaster.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/webmaster.do?action=add" target="navTab" rel="添加网站主"><span>添加网站主</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="113">
			<thead>
				<tr>
					<th style="width:60px;">编号</th>
					<th style="width:120px;">网站主</th>
					<th style="width:100px;">联系人</th>					
					<th style="width:100px;">电话</th>
					<th style="width:100px;">QQ</th>
					<th style="width:120px">所属媒介</th>
					<th style="width:80px;">注册日期</th>
					<th style="width:60px">状态</th>
					<th style="width:100px;">广告计划</th>
					<th style="width:100px;">域名</th>
					<th style="width:200px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="webmaster" >
					<tr> 
						<td>${webmaster.webMasterId}</td>
						<td>${webmaster.webMasterName}</td>
						<td>${webmaster.webMasterContactName}</td>
						<td>${webmaster.webMasterMobile}</td>
						<td>${webmaster.webMasterQQ}</td>
						<td>${webmaster.userId == 0 ? "暂未配置" :webmaster.userName}</td>
						<td>
						<fmt:parseDate value="${webmaster.addTime}" var="date" pattern="yyyy-MM-dd"/>
						<fmt:formatDate value="${date}" type="date" dateStyle="default"/>
						</td>
						<td>
						<c:if test="${0==webmaster.webMasterStatus}">
						<a name="editStatebutton" title="锁定站长，将会停止所有该站长下的投放，是否确定？"   href="${pageContext.request.contextPath }/webmaster.do?action=edit&cid=${webmaster.webMasterId}&state=1&flag=editState" target="navTabTodo" ><span style="color:blue">正常</span></a>
						</c:if>				
						<c:if test="${1==webmaster.webMasterStatus}">
						<a name="editStatebutton" title="开启站长，该站长下的投放需手动开启，是否确定？"  href="${pageContext.request.contextPath }/webmaster.do?action=edit&cid=${webmaster.webMasterId}&state=0&flag=editState" target="navTabTodo" ><span style="color:orange;">锁定</span></a>
						</c:if>
						</td>
						<td>
						<a name="planbutton" title="广告计划列表"  href="${pageContext.request.contextPath }/readyplan.do?action=list&webMasterId=${webmaster.webMasterId}" style="color:orange" target="navTab" rel="广告计划列表">${webmaster.planCount}个</a>
						<a name="addReadyPlanButton" title="添加广告计划" href="${pageContext.request.contextPath }/readyplan.do?action=add&webMasterId=${webmaster.webMasterId}" target="navTab" rel="addReadyPlan"><span style="color:blue">添加计划</span></a>
						</td>	
						<td>
						<span>
						    <a name="urlbutton" title="域名列表"  href="${pageContext.request.contextPath }/urladdress.do?action=list&webMasterId=${webmaster.webMasterId}" target="navTab" rel="域名列表"><span style="color:orange">${webmaster.urlCount}个</span></a></span>
						    <a name="editbutton" href="${pageContext.request.contextPath }/urladdress.do?action=add&webMasterId=${webmaster.webMasterId}" target="navTab"><span style="color:blue">添加域名</span></a>
						</td>
						<td>
						<a name="editbutton" title="修改网站主" class="btnEdit" href="${pageContext.request.contextPath }/webmaster.do?action=edit&cid=${webmaster.webMasterId}&flag=edit" target="navTab" rel="editMaster"><span style="color:blue">修改网站主</span></a>&nbsp;&nbsp;
						<a href="javascript:void(0);"  onclick="sourceDetail(this,'${webmaster.webMasterName}','${webmaster.userId}');"  target="navTab" rel="sourceDetail" style="color:blue">来源表</a>&nbsp;&nbsp;
						<a href="javascript:void(0);"  onclick="allDetail(this,'${webmaster.webMasterName}','${webmaster.userId}');"  target="navTab" rel="allDetail" style="color:blue">曲线图</a>&nbsp;&nbsp;
						<a href="javascript:void(0);"  onclick="pieDetail(this,'${webmaster.webMasterName}','${webmaster.userId}');" target="navTab" rel="pieDetail" style="color:blue">地区分布</a>&nbsp;&nbsp;
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