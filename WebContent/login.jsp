<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" >
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
    <%@ include file="/util/taglib.jsp" %>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>北京中润无线广告有限公司</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- <link href="images/jnico.ico" rel="SHORTCUT ICON" /> -->	
<link href="themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="themes/css/core.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->


<script type="text/javascript">

function validateCallback() {
		if (document.getElementById("name").value == null ||
		document.getElementById("name").value == "") {
			alert("请输入登录名！");
			return false;
		} else if (document.getElementById("pwd").value == null ||
		document.getElementById("pwd").value == "") {
			alert("请输入密码！");
			return false;
		} else if (document.getElementById("code").value == null ||
		document.getElementById("code").value == "") {
			alert("请输入验证码！");
			return false;
		} else {
			return true;
		}
	}

</script>
	
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />
<script>

	
</script>
</head>
<object CLASSID="clsid:0272DA76-96FB-449E-8298-178876E0EA89"
	CODEBASE="${pageContext.request.contextPath }/epass/install.cab#Version=1,0,6,413"
	BORDER="0" VSPACE="0" HSPACE="0" ALIGN="TOP" HEIGHT="0" WIDTH="0"
	id=ePass name = ePass style="LEFT: 0px; TOP: 0px" 
	 VIEWASTEXT></object>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="#" style=" font-size:28px; line-height:50px; font-weight:bold; color:black">北京中润无线广告有限公司</a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						
					</ul>
				</div>
				<h2 class="login_title"><!-- <img src="themes/default/images/login_title.png" /> --></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="${pageContext.request.contextPath }/login.do?action=login" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this);">

					<p>
						<label>登录名：</label>
						<input type="text" size="20" name="name" class="login_input required" value="orliucomma" />
					</p>
					<p>
						<label>密 码：</label>
						<input type="password" size="20" name="pwd" class="login_input required" value="loner!3018pancouu" />
					</p>
					<p>
						<label>验证码：</label>
						<input type="text" size="5" name="code" class="code required"/>
						<span><img src="${pageContext.request.contextPath }/util/checkCode.jsp" />
					</span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
					<br/>
					<table ><tr><td style="color:red"><p><c:out value="${mes}">${mes}</c:out></p></td></tr></table>
				</form>
			</div>
				<div class="login_banner"><img src="themes/default/images/login_banner.jpg" /></div>
			
			<div class="login_main">
				<ul class="helpList">
					
				</ul>
				<div class="login_inner">
					
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2010 www.xxxxx.com Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>