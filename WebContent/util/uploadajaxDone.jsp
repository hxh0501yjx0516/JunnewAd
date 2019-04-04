<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
var response={
	statusCode:${statusCode}, 
	message:"${message}", 
	navTabId:"${navTabId}", 
	callbackType:"${callbackType}",
	forwardUrl:"${forwardUrl}${objectId}"
}
if(window.parent.donecallback) window.parent.donecallback(response);
//-->
</script>
