<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="true" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>错误提示</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles1.css">

<script type="text/javascript">
	function goBack(){
		window.parent.location="login.jsp";
	}
</script>
</head>

<body>
<table width="50%" align="center" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><table width="419" height="226" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="center" background="<%=request.getContextPath() %>/images/error.jpg"><table width="388" height="194" border="0" cellpadding="0" cellspacing="0">
        	<tr>	
        		<td width="100" height="50"><img src="<%=request.getContextPath() %>/images/login_title.gif"></td>
        	</tr>
          <tr align="center"><td>&nbsp;</td>
            <td align="center"><font style="font-weight:bold">会话失效，请重新登录！</font><br>
              <br>
              <input name="Submit" type="submit" class="btn" value=" 确 定 " onClick="goBack()"></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
<center>
</center>
</body>
</html>
