<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/shopclass.do?action=sClassEdit&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="shopsclassid" value="${shopsclasslist.shopSclassId}">
			<div class="pageFormContent" layoutH="32">
				<p>
					<label>父栏目名称：</label>
					<select name="shopfclassid">
						<option value="">请选择</option>
						<c:forEach items="${shopFclassList}" var="l">
							<option value="${l.shopFclassId}" ${l.shopFclassId eq shopsclasslist.shopFclassId?"selected":""}>${l.shopFclassName}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>子栏目名称：</label>
					<input name="shopsclassname" class="required" type="text" size="30" value="${shopsclasslist.shopSclassName}" />
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

