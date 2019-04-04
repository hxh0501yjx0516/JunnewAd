<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();
%>  
<%@ include file="/util/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<script type="text/javascript">
function getInter(){
	//http://www.taoxie.com/api/cps/bmunion/query.aspx?start=20121121&end=20121123
	var spec = "http://www.taoxie.com/api/cps/bmunion/query.aspx";
	
	var beginTime = document.getElementById("beginTime").value;
	var endTime = document.getElementById("endTime").value;
	//alert(beginTime);
	//alert(endTime);
	if (beginTime == "" || endTime == ""){
		 alert("请选择查询日期");
	} else {
	   beginTime = beginTime.replaceAll("-","");
	   endTIme = endTime.replaceAll("-","");
	   spec = spec + "?start=" + beginTime + "&end=" + endTime;
	   window.open(spec);
	}
}
</script>

	
<title>淘鞋接口</title> 
</head>
<body>
<div class="page">
    <div class="pageContent">
       
            <div class="pageFormContent" layoutH="33">
                <h2 style="font-weight:bold; color:blue; font-size:20px; line-height:50px; border-bottom:1px solid #C0D2FA">淘鞋接口数据获取</h2><br/>
                
                <h2 style="font-weight:bold; color:#FC5858; font-size:14px; line-height:50px; border-bottom:1px solid #F59D9D">Step 1：获取数据</h2><br/>
                <p>
                    <label>开始日期:</label>
                    <input type="text" id="beginTime" name="beginTime" class="date required" />
                </p>
                
                <p>
                    <label>结束日期:</label>
                    <input type="text" id="endTime" name="endTime" class="date required" />
                </p>
                
                <div class="divider"></div>
                <button type="Button" style="margin-left:108px" onclick="getInter();">点击获取数据</button>
                <br/><br/><br/><br/>
                
                 <form name="addAdBox" method="post" action="${pageContext.request.contextPath }/taoxie.do?action=get&flag=save" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
                <h2 style="font-weight:bold; color:#FC5858; font-size:14px; line-height:50px; border-bottom:1px solid #F59D9D">Step 2：上传数据</h2><br/>
                <p>
                    <label>选择文件:</label>
                    <input type="file" name="formFile"/>
                </p>
                
                <div class="divider"></div>
                
                <button type="submit" style="margin-left:108px">保存数据</button>
                </form>
            </div>
        
    </div>
</div>
</body>
 </html>