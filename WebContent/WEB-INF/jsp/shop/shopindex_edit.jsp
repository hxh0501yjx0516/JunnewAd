<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加广告创意</title> 
<script type="text/javascript">
function testUrl(id){
	window.open(document.getElementById(id).value);
}
</script>
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/shopindex.do?action=editShopIndex&flag=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			
			<div class="pageFormContent" layoutH="32">
				<p>
					<label>推广页名称：</label>
					<input name="shopindexname" class="required" type="text" size="30" value="${shopindex.shopIndexName}" />
				</p>
				<p>
					<label>推广页地址：</label>
					<input id="shopindexurl" name="shopindexurl" class="required" type="text" size="30" value="${shopindex.shopIndexUrl}" />
					<input type="button" onclick="testUrl('shopindexurl');" value="链接测试">
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

