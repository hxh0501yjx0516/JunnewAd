<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
	<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>内容列表</title> 
<script type="text/javascript">
function testUrl(obj){
	var url = document.getElementById(obj).value;
		if( url !=''){
		if(url.indexOf("http://")==-1){
			url = "http://"+url;
		}
		window.open(url,"","");
		}
	}
function mySubmit(obj){
	var url = document.getElementById("shopurl");
	url.value = escape(url.value);
	return iframeCallback(obj,navTabAjaxDone);
}
</script>
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="shopAddForm" method="post" action="${pageContext.request.contextPath }/shop.do?action=add&flag=save" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return mySubmit(this);">
			<div class="pageFormContent" layoutH="32">
				<p>
					<label>父栏目：</label>
					<select name="shopfclassid" class="required">
					<option value="">==请选择==</option>
					<c:forEach items="${shopFclassList}" var="l">
						<option value="${l.shopFclassId}">┞${l.shopFclassName}</option>
					</c:forEach>
					</select>
				</p>
				<p>
					<label>子栏目：</label>
					<select name="shopsclassid" class="required">
					<option value="">==请选择==</option>
					<c:forEach items="${shopSclassList}" var="l">
						<option value="${l.shopSclassId}">┞${l.shopSclassName}</option>
					</c:forEach>
					</select>
				</p>
				<div class="divider"></div>
				<p>
					<label>标题：</label>
					<input name="shopname" class="required" type="text" size="30" value="" />
				</p>
				<p>
					<label>链接地址：</label>
					<input name="shopurl" id="shopurl" class="required" type="text" size="30" value="" />
					<input type="button" onclick="testUrl('shopurl');" value="链接测试"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>原价：</label>
					<input name="shopprice" class="number" type="text" size="30" value="0" />
				</p>
				<p>
					<label>现价：</label>
					<input name="shopnewprice"  class="number" type="text" size="30" value="0" />
				</p>
				<div class="divider"></div>
				<p>
					<label>图片：</label>
					<input name="formFile" type="file" size="30" value="" />
				</p>
				<div class="divider"></div>
				<p>
					<label>备注：</label>
					<textarea name="shopremarks" cols="100" rows="20"></textarea>
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
