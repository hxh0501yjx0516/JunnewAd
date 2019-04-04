<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>获取代码</title> 
<script language="javascript"> 
function copyToClipboard(theField,isalert)
{
var obj=document.getElementById(theField);
if(obj)
{
    var clipBoardContent=obj.value;
    obj.select();
    if(window.clipboardData){
    	window.clipboardData.clearData();   
    	window.clipboardData.setData("Text",clipBoardContent); 
    }else{
            var flashcopier = 'flashcopier';
            if(!document.getElementById(flashcopier)){
              var divholder = document.createElement('div');
              divholder.id = flashcopier;
              document.body.appendChild(divholder);
            }
            document.getElementById(flashcopier).innerHTML = '';
            var divinfo = '<embed src="js/_clipboard.swf" FlashVars="clipboard='+encodeURIComponent(clipBoardContent)+'" width="0" height="0" type="application/x-shockwave-flash"></embed>';
            document.getElementById(flashcopier).innerHTML = divinfo;
        }
    if(isalert!=false)
    	alertMsg.correct("复制成功！");
}
else{
    alertMsg.error("复制出错！");
}
}
</script>
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="">
			<div class="pageFormContent" layoutH="33">
				<p>
					<div style="display:block; height:50px;width: 400px;">
					<textarea id="codeContent" cols="60" rows="6">${httpUrl}</textarea>
						<a class="button" href="javascript:void(0);" onclick="copyToClipboard('codeContent',true)"><span>复制代码</span></a>
						<div class="buttonActive"><div class="buttonContent"><button class="close">关闭窗口 </button></div></div>
					</div>
				</p>
			</div>
		</form>
	</div>
</div>
  </body>
</html>
