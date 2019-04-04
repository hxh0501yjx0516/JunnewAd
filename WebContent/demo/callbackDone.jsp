<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp" %>

<script type="text/javascript">
	var statusCode="${statusCode}";
	var message="${message}";
	var navTabId = "${param.navTabId}";
	var forwardUrl="${param.forwardUrl}${objectId}"; 
	var callbackType="${param.callbackType}"
	
	if (!statusCode) statusCode="${param.statusCode}";
	if (!message) message="${param.message}";

	var response = {statusCode:statusCode, 
		message:message, 
		navTabId:navTabId, 
		forwardUrl:forwardUrl, 
		callbackType:callbackType
	}
	if(window.parent.donecallback)window.parent.donecallback(response);
</script>
