<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/admodel.do?action=editAction&flag=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="modelId"  value="${models.modelId}">
			<div class="pageFormContent" layoutH="32">
				<p>
					<label>模板名称：</label>
					<input name="admodelname" class="required" type="text" size="30" value="${models.modelName}" />
				</p>
				<p>
					<label>模板文件：</label>
					<input name="admodeljs" type="text"  size="30" value="${models.modelJs}"  />
				</p>
					<div class="divider"></div>
				<p>
					<label>类型：</label>
					<select  name="modelFlag" >
					<option value="0" ${models.modelFlag == 0?"selected":""}>纯点击</option>
					<option value="1"  ${models.modelFlag == 1?"selected":""}>弹窗广告</option>
					<option value="2"  ${models.modelFlag == 2?"selected":""}>创意广告</option>
					</select>
				</p>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="state" value="0" ${models.modelStatus ==0?"checked":""}/>正常</label>
					<label><input type="radio" name="state" value="1" ${models.modelStatus ==1?"checked":""}/>锁定</label>
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

