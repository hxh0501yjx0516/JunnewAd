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
</script>
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/shopweb.do?action=edit&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="shopwebid" value="${shopWeb.shopWebId}">
			<div class="pageFormContent" layoutH="32">
				<p>
					<label>站点名称：</label>
					<input name="shopwebname" class="required" type="text" size="30" value="${shopWeb.shopWebName}" />
				</p>
				<p>
					<label>域名：</label>
					<input  id="shopweburl" name="shopweburl" class="required" type="text" size="30" value="${shopWeb.shopWebUrl}" />
					<input type="button" onclick="testUrl('shopweburl');" value="链接测试"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>联系人：</label>
					<input name="shopwebcontact" type="text" size="30" value="${shopWeb.shopWebContact}" />
				</p>
				<p>
					<label>电话：</label>
					<input name="shopwebtel" type="text" size="30" value="${shopWeb.shopWebTel}" />
				</p>
				<div class="divider"></div>
				<p>
					<label>QQ/MSN：</label>
					<input name="shopwebqq" type="text" size="30" value="${shopWeb.shopWebTel}" />
				</p>
				<p>
					<label>状态：</label>
					<input type="radio" name="shopwebstatus" value="0" ${shopWeb.shopWebStatus eq 0?"checked":""}>正常&nbsp;&nbsp;
					<input type="radio" name="shopwebstatus" value="1" ${shopWeb.shopWebStatus eq 1?"checked":""}>锁定
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

