<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
{
	statusCode:${statusCode}, 
	message:"${message}", 
	navTabId:"${navTabId}", 
	callbackType:"${callbackType}",
	forwardUrl:"${forwardUrl}${objectId}",
	"rel":"${rel}"
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