<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/shopclass.do?action=fClassEdit&flag=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="shopfclassid" value="${shopfclass.shopFclassId}">
			<div class="pageFormContent" layoutH="32">
				<p>
					<label>父栏目名称：</label>
					<input name="shopfclassname" class="required" type="text" size="30" value="${shopfclass.shopFclassName}" />
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

