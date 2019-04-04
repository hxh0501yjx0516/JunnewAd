<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %><%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
 <body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/users.do?action=addAction&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class=searchBar>
			<br><br>
			&nbsp;&nbsp;&nbsp;&nbsp;<b>添加后台用户:</b>
			</div>
			<div class="pageFormContent" layoutH="180">
				<p>
					<label>用户名：</label>
					<input name="name" class="required" type="text" size="30" value="" />
				</p>
				<p>
					<label>真实姓名：</label>
					<input name="realname" class="required" type="text" size="30" value="${realname }" />
				</p>
				<p>
					<label>密 码：</label>
					<input name="pwd" type="password" size="30" value="${pwd }" class="required alphanumeric" title="6~20字符,至少1个字母、数字、特殊字符组合"/>
					<label  style="width: 100%"><font style="color:red">6~20字符,至少1个字母、数字、特殊字符组合</font></label>
				</p>
				<p>
					<label>重新输入密码：</label>
					<input name="pwd1" type="password" class="required" size="30" value="${pwd1 }" class="required alphanumeric" title="6~20字符,至少1个字母、数字、特殊字符组合"/>
					<label style="width: 100%"><font style="color:red">6~20字符,至少1个字母、数字、特殊字符组合</font></label>
				</p>
				<p>
					<label>联系电话：</label>
					<input name="tel" type="text" class="required" size="30" value="${tel }" />
				</p>
				<p>
					<label>所在部门：</label>
					<input name="dep" type="text" size="30" value="${dep }" />
				</p>
				<p>
					<label>职 务：</label>
					<input name="title" type="text" size="30" value="${title }" />
				</p>
				<p>
					<label>状 态：</label>
					<select id="state" name="state">
						<c:if test="${1==state}"><option value="0" selected>正 常</option></c:if>
						<c:if test="${1!=state}"><option value="0" >正 常</option></c:if>
						<c:if test="${0==state}"><option value="1" selected>暂 停</option></c:if>
						<c:if test="${0!=state}"><option value="1" >暂 停</option></c:if>
						
					</select>
				</p>
			<div class="divider"></div>
				
					<label>用户组：</label>	
					<input type="radio" name="usergroup" value="0">所有&nbsp;&nbsp;
					<c:forEach items="${userGroupList}" var="ug">
					<input type="radio" name="usergroup" value="${ug.id}">${ug.userGroupName}&nbsp;&nbsp;
					</c:forEach>
				
			</div>
			<div class="formBar">
				<ul>					
					<!--<li><a class="buttonActive" href="#"><span>保存</span></a></li>-->
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

