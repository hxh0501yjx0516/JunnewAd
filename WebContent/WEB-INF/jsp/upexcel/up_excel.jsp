<%@ page language="java" import="java.util.*" pageEncoding="GBK"%><%@ include file="/util/taglib.jsp" %>

<%response.setHeader("Pragma","No-cache");

response.setHeader("Cache-Control","no-cache");

response.setDateHeader("Expires", 0);

response.flushBuffer();%> 
<%

String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<%@ include file="/util/upload.jsp" %>   

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<script language=javascript>

function msgChange(textareaName,svalue){

	if(svalue!="0")

	{

		strEval="document.addtxtform."+textareaName+".value='"+svalue+"'";	

		eval(strEval);

		changeContent();

	}	

}



</script>



<script type="text/javascript">

			function showpid()

			{

				var obj = document.getElementById("trpidtxt");

				obj.style.display="block";

			}

			function hidepid()

			{

				var obj = document.getElementById("trpidtxt");

				obj.style.display="none";

			}

		

	</script>

<form name=addtxtform method="post" action="${pageContext.request.contextPath }/up.do?action=doAction&cpid=${cpid }" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return validateCallback(this);">

<table CELLSPACING=5>
<input type="hidden" id="filename" name="filename"  value="${filename }"/>


<tr>

<td colspan=6> <br>注意事项:${filename }</td>

</tr>

<tr>

<td colspan=6>

<table border="0" width="100%">

				<tr style="height:20px;color:#5555ff"><td>

				

				一、文本导入模板下载 &nbsp;&nbsp;&nbsp;

					<a href="${pageContext.request.contextPath }/up.do?action=downLoad"> <span style="color:red">《导入文件模板》下载 </span></a><br/>

				</td></tr>

				<tr style="height:20px;color:#5555ff"><td>				

				（如果数据量特别大或者您的网络状态不是很好,请您在提交后耐心等待,中途请勿刷新或者离开本页面.）

				</td></tr>

				<tr style="height:20px;color:#5555ff"><td>	

				二、内容不得包含涉嫌诈骗、色情、反动、政治等字眼

			

				</td></tr>

			</table>

</td>

</tr>

<tr >

<td colspan=6 align=right>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<button type="submit" style="line-height:19px;"> 保  存 </button>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<button type="Button" onclick="navTab.closeCurrentTab()" style="line-height:19px;"> 取   消 </button>

</td>

</tr>

</table>
</form>

