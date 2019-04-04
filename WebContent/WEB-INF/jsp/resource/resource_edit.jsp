<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript">
			function showpid()
			{
				var obj = document.getElementById("trpid");
				obj.style.display="block";
			}
			function hidepid()
			{
				var obj = document.getElementById("trpid");
				obj.style.display="none";
			}
		
	</script>
  </head>
  
  <body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/resource.do?action=editAction&flag=edit&id=${r.resourceId }" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="panelBar">
			<ul class="toolBar">
				<span style="font:bold">》》修改资源</span>
				<li class="line">line</li>
			</ul>
		</div>
			<div class="pageFormContent" layoutH="300">
				<p>
					<label>资源名称：</label>
					<input name="name" type="text" size="30" value="${r.resourceName }" />
				</p>
				<p>
					<label>所属模块：</label>
					<input name="modual" type="text" size="30" value="${r.resourceModual }" />
				</p>
				<p>
					<label>资源URL：</label>
					<input name="url" type="text" size="30" value="${r.resourceUrl }" />
				</p>
				<p>
					<label>资源类型：</label>
					<c:choose>
						<c:when test="${0==r.resourceType}">
							<input type="radio" name="type" value="0" checked="checked" onclick="hidepid()"/>						
						</c:when>
						<c:otherwise>
							<input type="radio" name="type" value="0" onclick="hidepid()"/>
						</c:otherwise>
					</c:choose>
					主菜单
					<c:choose>
						<c:when test="${2==r.resourceType}">
							<input type="radio" name="type" value="2" checked="checked" onclick="showpid()"/>
						</c:when>
						<c:otherwise>
							<input type="radio" name="type" value="2" onclick="showpid()"/>
						</c:otherwise>
					</c:choose>子菜单
									
				</p>
					<c:if test="${2==r.resourceType}">
					<p id="trpid">
					<label>父编号：</label>
					<select class="" id="pid" name="pid">
							<c:forEach items="${resourceList}" var="resource">
								<option value="${resource.resourceId}" ${resource.resourceId ==r.resourcePid?"selected":"" }>${resource.resourceName}</option>
							</c:forEach>
						</select>
					</p>
					</c:if>
					<c:if test="${2!=r.resourceType}">
					<p id="trpid" style="display:none">
					<label>父编号：</label>
					<select class="" id="pid" name="pid">
							<c:forEach items="${resourceList}" var="resource">
							<c:if test="${resource.resourceId ne r.resourceId}">
								<option value="${resource.resourceId}">${resource.resourceName}</option>
								</c:if>
							</c:forEach>
						</select>
					</p>
					</c:if>
					<p>
					<label>显示次序：</label>
					<input name="displayorder" class="required number"  type="text" size="30" value="${r.displayorder}" />
				</p>
			<div class="divider"></div>
			</div>
			<div class="formBar">
				<ul>					
					<!--<li><a class="buttonActive" href="#"><span>保存</span></a></li>-->
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
