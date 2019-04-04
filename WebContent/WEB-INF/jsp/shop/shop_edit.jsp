<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
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
		<form method="post" action="${pageContext.request.contextPath }/shop.do?action=edit&flag=save" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return mySubmit(this);">
			<input type="hidden" name="shopid" value="${shop.shopId}" />
			<input type="hidden" name="source" value="${source}" />
			<div class="pageFormContent" layoutH="32">
				<p>
					<label>父栏目：</label>
					<select name="shopfclassid" class="required">
					<option value="">==请选择==</option>
					<c:forEach items="${shopFclassList}" var="l">
						<option value="${l.shopFclassId}" ${l.shopFclassId eq shop.shopFclassId?"selected":""}>┞${l.shopFclassName}</option>
					</c:forEach>
					</select>
				</p>
				<p>
					<label>子栏目：</label>
					<select name="shopsclassid" class="required">
					<option value="">==请选择==</option>
					<c:forEach items="${shopSclassList}" var="l">
						<option value="${l.shopSclassId}" ${l.shopSclassId eq shop.shopSclassId?"selected":""}>┞${l.shopSclassName}</option>
					</c:forEach>
					</select>
				</p>
				<div class="divider"></div>
				<p>
					<label>标题：</label>
					<input name="shopname" class="required" type="text" size="30" value="${shop.shopName}" />
				</p>
				<p>
					<label>链接地址：</label>
					<input id="shopurl" name="shopurl" class="required" type="text" size="30" value="${shop.shopUrl}" />
					<input type="button" onclick="testUrl('shopurl');" value="链接测试"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>原价：</label>
					<fmt:formatNumber var="fmtshopPrice" value="${shop.shopPrice}" pattern="####.###"/>
					<input name="shopprice" type="text"  class="number" size="30" value="${fmtshopPrice}" />
				</p>
				<p>
					<label>现价：</label>
					<fmt:formatNumber var="fmtshopNewPrice" value="${shop.shopNewPrice}" pattern="####.###"/>
					<input name="shopnewprice" type="text"  class="number" size="30" value="${fmtshopNewPrice}" />
				</p>
				<div class="divider"></div>
				<p>
					<label>图片：</label>
					<input name="formFile" type="file" size="30" value="" />
				</p>
				<div class="divider"></div>
				<p>
					<textarea name="shopremarks" cols="100" rows="20">${shop.shopRemarks}</textarea>
					<c:choose>
					<c:when test="${shop.shopImg ne ''}">
					<img alt="" src="${shop.shopImg}">
					</c:when>
					<c:otherwise><span style="color:orange">暂无图片</span></c:otherwise>
					</c:choose>
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

