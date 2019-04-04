<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑广告位</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="editAdBox" method="post" action="${pageContext.request.contextPath }/adbox.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="adBoxId" value="${adBox.adBoxId}"/>
			<div class="pageFormContent" layoutH="33">
			<c:if test="${adBox.adBoxFlag == 1}">
				<h2 class="contentTitle">所属域名:${urlAddressName}</h2>
				</c:if>
				<p>
					<label>广告位名称：</label>
					<input name="adBoxName" type="text" size="30" class="required" value="${adBox.adBoxName}"/>
				</p>
				<p>
					<label>广告位规格：</label>
					<select  name="adBoxInfoId" class="">
					<c:forEach var="info" items="${adBoxInfoList}">
						<option value="${info.adBoxInfoId}" ${adBox.adBoxInfoId == info.adBoxInfoId?"selected":""}>${info.adBoxInfoName}--${info.modelName}--${info.adSizeName}</option>
					</c:forEach>
					</select>
				</p>
				<div class="divider"></div>
				<p>
					<label>状 态：</label>
					<label><input type="radio" name="adBoxState"  value="0" ${adBox.adBoxStatus == 0?"checked":""}/>正常</label>
					<label><input type="radio" name="adBoxState"  value="1"  ${adBox.adBoxStatus == 1?"checked":""}/>锁定</label>
				</p>
				<p>
					<label>绑定域名：</label>
					<label><input type="radio" name="urlBandFlag"  value="1" ${adBox.isURLBand == 1?"checked":""}/>是</label>
					<label><input type="radio" name="urlBandFlag"  value="0" ${adBox.isURLBand == 0?"checked":""}/>否</label>
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
