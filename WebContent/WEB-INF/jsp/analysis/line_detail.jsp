<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();%>  
<%@ include file="/util/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<!-- 分页 -->
<form id="pagerForm" method="post" action="${pageContext.request.contextPath }/cp.do?action=list&flag=pager" >
<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
<input type="hidden" name="cpid" value="${cpid }" /><!--【可选】查询条件-->
</form>
<body>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/cp.do?action=list" method="post">
		<input type="hidden" id="selectedId_demo"/>
		<div class="searchBar">
			<ul >		
				<li>
					用户登录名称：
					<input type="text" name="qryname" value="${qryname }"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					用户编号：
					<input type="text"   name="cpid" value="${cpid }"  class="digits"/>
				</li>
				
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/cp.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	<!-- 
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/cp.do?action=doAction&flag=ainit" target="navTab"><span>添加用户</span></a></li>
				
				<li class="line">line</li>
				
			</ul>
		</div>
	 -->
		<table class="table" layouth="142">
			<thead>
				<tr>
					
					<th style="width:120px;">用户编号</th>
					<th style="width:150px;">用户名称</th>
					<th style="width:100px;">公司名称</th>
					<th style="width:100px;">所在部门</th>
					<th style="width:100px;">联系电话</th>
					
					<th style="width:70px;">余 额</th>
					<th style="width:70px;">状 态</th>
					<th style="width:150px;">操 作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l" varStatus="status">
					<tr> 
						
						<td>${l.cpid}</td>
						<td>${l.name}</td>
						<td>${l.company}</td>
						<td>${l.dept}</td>
						<td>${l.tel}</td>
						
						<td>${l.balance}</td>
						<td>
						<c:if test="${1==l.state}"><font color=blue>正 常</font></c:if>						
						<c:if test="${0==l.state}"><font color=red>暂 停</font></c:if>						
						
						</td> 
						<td>
						<a name="editbutton" class="edit" href="${pageContext.request.contextPath }/cp.do?action=doAction&flag=einit&value=${l.cpid }" target="navTab"><span style="color:blue">修改用户</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a name="delbutton" class="delete" href="${pageContext.request.contextPath }/cp.do?action=delInter&flag=del&value=${l.cpid}" target="navTabTodo" title="确定要删除吗?"><span style="color:blue">删除用户</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a name="delbutton" class="delete" href="${pageContext.request.contextPath }/userinter.do?action=list&cpid=${l.cpid}" target="navTab"><span style="color:blue">通道管理</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${0==l.pcpid}"><a name="editbutton" class="edit" href="${pageContext.request.contextPath }/cp.do?action=list&value=${l.cpid}&pcpid=${l.pcpid}" target="navTab" rel=son${l.cpid }><span style="color:blue">子账户</span></a></c:if>
						</td>
						<td>
						<a class="edit" href="${pageContext.request.contextPath }/cp.do?action=editPwd&flag=einit&cpid=${l.cpid}" target="navTab"><span style="color:blue">修改密码</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<c:if test="${numPerPage==20}"><option value="20" selected>20</option></c:if>
					<c:if test="${numPerPage!=20}"><option value="20" >20</option></c:if>
					<c:if test="${numPerPage==50}"><option value="50" selected>50</option></c:if>
					<c:if test="${numPerPage!=50}"><option value="50" >50</option></c:if>		
					<c:if test="${numPerPage==100}"><option value="100" selected>100</option></c:if>
					<c:if test="${numPerPage!=100}"><option value="100" >100</option></c:if>
					<c:if test="${numPerPage==200}"><option value="200" selected>200</option></c:if>
					<c:if test="${numPerPage!=200}"><option value="200" >200</option></c:if>
				</select>
				<span>条，共${totalCount}条</span>
			</div>
			<!-- 分页 -->
			<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage }" pageNumShown="10" currentPage="${pageNum }"></div>

		</div>
		
	</form>
	</div>
</div>
</body>
 </html>