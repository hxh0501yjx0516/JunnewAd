<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
 <html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑新闻分类</title> 
</head>
 <body>
    <div class="page">
	<div class="pageContent">
		<form name="editCmsClass" method="post" action="${pageContext.request.contextPath }/cmsclass.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="cid" value="${cmsClass.cmsClassId}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>类型名称：</label>
					<input name="name" class="required" type="text" size="30" value="${cmsClass.cmsClassName}"/>
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
