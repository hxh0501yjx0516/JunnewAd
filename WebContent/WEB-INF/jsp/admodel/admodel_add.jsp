<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>

<body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/admodel.do?action=add&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="32">
				<p>
					<label>模板名称：</label>
					<input name="admodelname" class="required" type="text" size="30" />
				</p>
				<p>
					<label>模板文件：</label>
					<input name="admodeljs" type="text"  size="30"  />
				</p>
				<div class="divider"></div>
				<p>
					<label>类型：</label>
					<select  name="modelFlag" >
					<option value="0" >纯点击</option>
					<option value="1" >弹窗广告</option>
					<option value="2" >创意广告</option>
					</select>
				</p>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="state" value="0" checked="checked"/>正常</label>
					<label><input type="radio" name="state" value="1"/>锁定</label>
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

