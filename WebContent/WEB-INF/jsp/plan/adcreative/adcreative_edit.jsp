<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑广告创意</title> 
<script type="text/javascript">
	function testUrlEditCreative(obj){
	var url = document.getElementById(obj).value;
		if( url !=''){
		if(url.indexOf("http://")==-1){
			url = "http://"+url;
		}
		window.open(url,"","");
		}
	}
	function submitForm(form,funObj){
		var isP4p = document.getElementById("isP4p").value;
		var url = "";
		if(isP4p == 0){
			//不是p4p
			url = escape(document.getElementById("adCreativeUrlEdit").value);
		}else{
			 url = document.getElementById("htmlCode").value;
			 url = url.replace(/"/g, "\\\"").replace(/#/g, "%23").replace(/\s+\<\/script>/g, "<\\/script>").replace(/\+/g,'%2B').replace(/\s{2,}/g,"");
		}
		document.getElementById("hidadCreativeUrl").value =url;
		return iframeCallback(form,funObj);
	}
	function changeUrl(svalue,obj1,obj2){
		if(svalue == 0 || svalue == 3){
			document.getElementById(obj2).style.display = "block";
			document.getElementById(obj1).style.display = "none";
			document.getElementById("htmlCode").className = "";
			document.getElementById("adCreativeUrlEdit").className = "required";
		}else{
			document.getElementById(obj1).style.display = "block";
			document.getElementById(obj2).style.display = "none";
			document.getElementById("htmlCode").className ="required";
			document.getElementById("adCreativeUrlEdit").className = "";
		}
	}
</script>
</head>
<body>
    <div class="page">
    <h2 class="contentTitle">所属广告计划:${adPlanName}</h2>
	<div class="pageContent">
		<form name="editAdCreative" method="post" action="${pageContext.request.contextPath }/adcreative.do?action=edit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return submitForm(this,navTabAjaxDone);">
			<input type="hidden" name="adCreativeId" value="${adCreative.adCreativeId}"/>
			<div class="pageFormContent" layoutH="73">
				<p>
					<label>创意名称：</label>
					<input name="adCreativeName" class="required" type="text" size="30" value="${adCreative.adCreativeName}"/>
				</p>
				<p style="width: 50%;">
					<label>目标类型：</label>
					<select id="isP4p"   name="isP4p"  onchange="changeUrl(this.value,'targetP4pEdit','targetUrlEdit')">
						<option value="0" ${adCreative.isP4p == 0?"selected":""}>普通链接</option>
						<option value="1" ${adCreative.isP4p == 1?"selected":""}>JS代码</option>
						<option value="2" ${adCreative.isP4p == 2?"selected":""}>HTML代码</option>
						<option value="3" ${adCreative.isP4p == 3?"selected":""}>iframe链接</option>
					</select>
				</p>
				<div class="divider"></div>
				<p id="targetUrlEdit" style="width: 50%;">
					<label>目标地址：</label>
					<input id="hidadCreativeUrl"  name="adCreativeUrl"  type="hidden" size="30" value="${adCreative.adCreativeUrl}"/>
					<input id="adCreativeUrlEdit"  name="adCreativeUrl1" class="required"  type="text" size="30" value="${adCreative.adCreativeUrl}"/>
					<input type="button" onclick="testUrlEditCreative('adCreativeUrlEdit');" value="链接测试"/>
				</p>
				<p id="targetP4pEdit" style="display: none;">
					<label>目标地址：</label>
						<label><textarea rows="10" cols="100" id="htmlCode"  name="htmlCode"  >${adCreative.htmlCode}</textarea></label>
				</p>
				<div class="divider"></div>
					<p>
					<label>图片尺寸：</label>
					<select  name="adSizeId" class="">
					<c:forEach var="m" items="${adSizeList}">
						<option value="${m.adSizeId}" ${m.adSizeId == adCreative.adSizeId?"selected":""}>${m.adWidth}x${m.adHeight}</option>
						<c:if test="${m.adSizeId == adCreative.adSizeId}">
						<c:set var="mWidth" value="${m.adWidth}" />
						<c:set var="mHeight" value="${m.adHeight}" />
						</c:if>
					</c:forEach>
					</select>
				</p>
				<p>
					<label>上传文件：</label>
				<input name="formFile" type="file" />
				</p>
				<div class="divider"></div>
			<p>
					<label>状态：</label>
					<label><input type="radio" name="adCreativeState" value="0" ${adCreative.adCreativeStatus == 0?"checked":""}/>正常</label>
					<label><input type="radio" name="adCreativeState" value="1" ${adCreative.adCreativeStatus == 1?"checked":""}/>锁定</label>
				</p>
				<p>
					<label>创意类型：</label>
					<input type="radio" name="isDefault" value="0"  ${adCreative.isDefault == 0?"checked":""}/>普通创意
					<input type="radio" name="isDefault" value="1" ${adCreative.isDefault == 1?"checked":""}/>默认创意
					<input type="radio" name="isDefault" value="2" ${adCreative.isDefault == 2?"checked":""}/>自取创意
				</p>
				<div class="divider"></div>
				<p>
					<label>预览：</label>
					<c:choose>
					<c:when test="${adCreative.adCreativeImg == ''}">
						<splan style="color:orange">暂无</splan>
					</c:when>
					<c:when test="${fn:indexOf(adCreative.adCreativeImg,'.swf') > 0}">
					<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' width="${mWidth }" height="${mHeight }"  codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0'  id='showimg' style='position:absolute;'>
					<PARAM NAME=movie VALUE='jMovie.swf'>
					<PARAM NAME=quality VALUE=high>
					<PARAM NAME=wmode value=transparent>
					<PARAM NAME=Src VALUE="${adCreative.adCreativeImg}">
					<EMBED src="${adCreative.adCreativeImg}" width="${mWidth }" height="${mHeight }" quality=high wmode=transparent bgcolor=#FFFFFF NAME='junnewMovie' ALIGN='' TYPE='application/x-shockwave-flash' PLUGINSPAGE='http://www.macromedia.com/go/getflashplayer'>
					</EMBED>
					</object>
					</c:when>
					<c:otherwise>
						<img alt="${adCreative.adCreativeImg}"  src="${adCreative.adCreativeImg}"><br /><br />
						${adCreative.adCreativeImg}
					</c:otherwise>
					</c:choose>
				</p>
				<div class="divider"></div>
				<p style="color: red;width: 100%">
				注意：
				<br/>普通链接：展现方式为新窗口打开链接（纯点击/弹窗）。如现有的淘宝商城、游戏等。
				<br/>JS代码：展现方式为固定位置、漂浮、对联等。此类代码不需要图片等素材，广告位中自动展现JS代码的内容。如淘宝的JS代码形式的P4P
				<br/>HTML代码：展现方式为固定位置、漂浮、对联等。此类代码不需要图片等素材，广告位中自动展现HTML代码的内容。此类代码可根据需要自定义代码所呈现的内容。
				<br/>iframe链接：展现方式为固定位置、漂浮、对联等。此类代码不需要图片等素材，广告位中自动展现链接自身的页面内容。如淘宝的链接形式P4P
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
<<script type="text/javascript">
<!--
		changeUrl(document.getElementById("isP4p").value,'targetP4pEdit','targetUrlEdit');
		var htmlCode = document.getElementById("htmlCode").value;
		 //url = url.replace(/\s/g,"").replace(/"/g, "\\\"").replace(/#/g, "%23").replace(/\<\/script>/g, '</sc"\+""\+"ript>').replace(/\+/g,'%2B');
		document.getElementById("htmlCode").value = htmlCode.replace("\<\\/script>","<\/script>").replace(/\\\"/g, "\"").replace(/<\s?script[^>]*>/i,"<script type=\"text/javascript\">\n").replace(/;/g, ";\n");
//-->
</script>
