<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
 <html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑用户组</title> 
</head>
 <body>
    <div class="page"> 
	<div class="pageContent">
		<form name="editUserGroup" method="post" action="${pageContext.request.contextPath }/usergroup.do?action=edit&flag=editsave" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input name="id"  type="hidden" value="${usergroup.id}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>组名称：</label>
					<label><input name="usergroupname" class="required" type="text" size="30" value="${usergroup.userGroupName}"/></label>
				    
				</p>
				<div class="divider"></div>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="usergroupstatus" value="0" ${usergroup.userGroupStatus == 0?"checked":""}/>正常</label>
					<label><input type="radio" name="usergroupstatus" value="1" ${usergroup.userGroupStatus == 1?"checked":""}/>锁定</label>
				</p>
			</div>
			<div class="formBar">
				<ul>					
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
  </body>
</html>
