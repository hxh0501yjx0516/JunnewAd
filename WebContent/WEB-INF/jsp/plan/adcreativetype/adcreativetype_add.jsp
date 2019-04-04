<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加广告创意类型</title> 
<script type="text/javascript">
function showpid()
{
	var obj = document.getElementById("adCreativeTypeFather");
	obj.style.display="block";
}
	 
function hidepid()
{
	var obj = document.getElementById("adCreativeTypeFather");
	obj.style.display="none";
}

</script>
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addAdCreativeType" method="post" action="${pageContext.request.contextPath }/adcreativetype.do?action=add&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">

			<div class="pageFormContent" layoutH="33">
				<p>
					<label>菜单类型：</label>
					<input name="adCreativeTypeTid" id="adCreativeTypeTid1" type="radio" value="0" checked	onclick="hidepid();" />父类型&nbsp;&nbsp;
					<input name="adCreativeTypeTid" id="adCreativeTypeTid2" type="radio" value="1" onclick="showpid();"/>子类型
				</p>
				

				<div id="adCreativeTypeFather" style=" display: none">
				<div class="divider"></div>
				<p>
				<label>父类型：</label>
				<select name="adCreativeTypeMid">
					<option value="0" selected>==请选择==</option>
					<c:forEach items="${adCreativeTypeFather}" var="f">
					<option value="${f.adCreativeTypeId}--${f.adCreativeTypeName}">${f.adCreativeTypeName}</option>
					</c:forEach>
				</select>

				</p>
				</div>
				
				<div class="divider"></div>
				<p>
					<label>类型名称：</label>
					<input name="adCreativeTypeName" class="required" type="text" size="30" />
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
