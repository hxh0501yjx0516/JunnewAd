<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/users.do?action=editAction&flag=edit&value=${user.userId }" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class=searchBar>
			<br><br>
			&nbsp;&nbsp;&nbsp;&nbsp;<b>修改后台用户信息:</b>
			</div>
			<div class="pageFormContent" layoutH="300">
				<p>
					<label>用户名：</label>
					<input name="name" class="required" type="text" size="30" value="${user.username }" />
				</p>
				<p>
					<label>真实姓名：</label>
					<input name="realname" class="required" type="text" size="30" value="${user.realname }" />
				</p>
				
				<p>
					<label>联系电话：</label>
					<input name="tel" type="text" class="required" size="30" value="${user.tel }" />
				</p>
				<p>
					<label>所在部门：</label>
					<input name="dep" type="text" size="30" value="${user.dept }" />
				</p>
				<p>
					<label>职 务：</label>
					<input name="title" type="text" size="30" value="${user.title }" />
				</p>
				<p>
					<label>状 态：</label>
					<select id="state" name="state">
						<c:if test="${0==user.state}"><option value="0" selected>正 常</option></c:if>
						<c:if test="${0!=user.state}"><option value="0" >正 常</option></c:if>
						<c:if test="${1==user.state}"><option value="1" selected>暂 停</option></c:if>
						<c:if test="${1!=user.state}"><option value="1" >暂 停</option></c:if>
						
					</select>
				</p>
				<div class="divider"></div>
				
					<label>用户组：</label>
					<input type="radio" name="usergroup" value="0" ${user.userGroup eq 0?"checked":""} />所有&nbsp;&nbsp;
					<c:forEach items="${userGroupList}" var="ug" varStatus="ugindex">
						<input type="radio" name="usergroup" value="${ug.id}" ${ug.id eq user.userGroup?"checked":""}>${ug.userGroupName}&nbsp;&nbsp;
					</c:forEach>
					
				
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

