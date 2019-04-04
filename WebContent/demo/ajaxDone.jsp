<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
{
	statusCode:${statusCode}, 
	message:"${message}", 
	navTabId:"${param.navTabId}", 
	callbackType:"${param.callbackType}",
	forwardUrl:"${param.forwardUrl}${objectId}"
}


<%--
{
"statusCode":"状态码200或300", 
"message":"提示信息", 
"navTabId":"操作成功后需要指定navTab时使用", 
"callbackType":"closeCurrent或forward",
"forwardUrl":"callbackType是forward时使用"
}
--%>