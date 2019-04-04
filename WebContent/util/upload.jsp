<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%><%@ include file="/util/taglib.jsp" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %> 
<%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();

%>
  <jsp:useBean id="fileBean" scope="page" class="com.pancou.ad.util.UploadBean" />   
  <%   
  fileBean.setObjectPath("E:\\广告\\");   
  fileBean.setSize(10000*1024);   
  fileBean.setSuffix(".gif.jpg.png.jpge.html.htm.xls");   
  fileBean.setSourceFile(request);   
  String [] saSourceFile = fileBean.getSourceFile();   
  String [] saObjectFile = fileBean.getObjectFileName();   
  String [] saDescription = fileBean.getDescription();   
  int iCount = fileBean.getCount();   
  String sObjectPath = fileBean.getObjectPath();   
  for(int i=0;i<iCount;i++) {   
  out.println("<br>源始文件:");   
  out.println(saSourceFile[i]);   
  out.println("<br>目标文件:");   
  out.println(sObjectPath+saObjectFile[i]);   
  out.println("<br>上传说明:");   
  out.println(saDescription[i]);   
  out.println("<br>");   
  }   
  %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<script>
	function doSubmit(buttonValue) {
		document.getElementById('buttonValueId').value = buttonValue;
		document.getElementById('uploadform').submit();
	}
</script>


	<form method="post" action="${pageContext.request.contextPath }/up.do?action=upload&id=${id }" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this);">
		<table ><tr><td>
			<p>
					<label>文件：</label>
					<input type="file" name="upfile" value="${fileName }"/>				
			</p>
			</td><td>
			<div class="buttonActive"><div class="buttonContent"><button type="submit" >上传</button></div></div>
			</td><td><span style="color:red">※提示：请先点击“文件上传”按钮，上传文件成功后，再添加其他信息</span></td></tr></table>	
			<div class="divider"></div>
		</form>

<script type="text/javascript">
var statusCode="${sc}";
var message="${msg}";
var navTabId="";
var response = {statusCode:statusCode,
message:message,
navTabId:navTabId
}
if(window.parent.donecallback) window.parent.donecallback(response);
</script>