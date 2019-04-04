<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑广告位规格</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addAdBoxInfo" method="post" action="${pageContext.request.contextPath }/adboxinfo.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input name="cid"  type="hidden" value="${adBoxInfo.adBoxInfoId}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>名称：</label>
					<input name="adBoxInfoName" class="required" type="text" size="30" value="${adBoxInfo.adBoxInfoName}"/>
				</p>
				<p>
					<label>广告模式：</label>
					<select  name="modelId" class="">
					<c:forEach var="m" items="${modelList}">
						<option value="${m.modelId}" ${adBoxInfo.modelId == m.modelId?"selected":""}>${m.modelName}</option>
					</c:forEach>
					</select>
				</p>
				<p>
					<label>广告尺寸：</label>
					<select  name="adSizeId" class="">
					<c:forEach var="a" items="${adSizeList}">
						<option value="${a.adSizeId}" ${adBoxInfo.adSizeId == a.adSizeId?"selected":""}>${a.adWidth}x${a.adHeight}</option>
					</c:forEach>
					</select>
				</p>
				<p>
					<label>状 态：</label>
					<label><input type="radio" name="adBoxInfoState"  value="0" ${adBoxInfo.adBoxInfoStatus == 0?"checked":""}/>正常</label>
					<label><input type="radio" name="adBoxInfoState"  value="1" ${adBoxInfo.adBoxInfoStatus== 1?"checked":""}/>锁定</label>
				</p>
				<div class="divider"></div>
				<p>
					<label>备注：</label>
				<label><textarea rows="20" name="adBoxInfoRemarks" cols="100">${adBoxInfo.adBoxInfoRemarks}</textarea></label>
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
