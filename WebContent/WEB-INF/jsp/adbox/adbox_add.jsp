<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加广告位</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addAdBox" method="post" action="${pageContext.request.contextPath }/adbox.do?action=add&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="webMasterId" value="${webMasterId}"/>
			<input type="hidden" name="urlAddressId" value="${urlAddressId}"/>
			<div class="pageFormContent" layoutH="33">
			<c:if test="${urlAddressId != ''}">
				<h2 class="contentTitle">所属域名:${urlAddressName}</h2>
				</c:if>
				<p>
					<label>广告位名称：</label>
					<input name="adBoxName" type="text" size="30" class="required"/>
				</p>
				<p>
					<label>广告位规格：</label>
					<select  name="adBoxInfoId" class="">
					<c:forEach var="info" items="${adBoxInfoList}">
						<option value="${info.adBoxInfoId}" >${info.adBoxInfoName}--${info.modelName}--${info.adSizeName}</option>
					</c:forEach>
					</select>
				</p>
				<div class="divider"></div>
				<p>
					<label>状 态：</label>
					<label><input type="radio" name="adBoxState"  checked="checked" value="0"/>正常</label>
					<label><input type="radio" name="adBoxState" value="1"/>锁定</label>
				</p>
					<c:if test="${urlAddressId != ''}">
				<p>
					<label>绑定域名：</label>
					<label><input type="radio" name="urlBandFlag"  value="1" />是</label>
					<label><input type="radio" name="urlBandFlag" checked="checked" value="0"/>否</label>
				</p>
				</c:if>
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
