<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
 <html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑尺寸</title> 
</head>
 <body>
    <div class="page">
	<div class="pageContent">
		<form name="editAdSize" method="post" action="${pageContext.request.contextPath }/adsize.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="sid" value="${adSize.adSizeId}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>尺寸：</label>
					<label style="float: left;width:80px;padding-right:0px;"><input name="width" class="required number" type="text" style="width:80px" value="${adSize.adWidth}"/></label><label style="float: left;width:auto;padding:0px;">px&nbsp;&nbsp;* </label>
					<label  style="float: left;width:80px;padding-right:0px;"><input name="height" class="required number" type="text"style="width:80px" value="${adSize.adHeight}"/></label><label style="float: left;width:20px;padding:0px;">px </label>
				   </p>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="state" value="0" ${adSize.adSizeStatus == 0?"checked":""}/>显示</label>
					<label><input type="radio" name="state" value="1" ${adSize.adSizeStatus == 1?"checked":""}/>隐藏</label>
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
