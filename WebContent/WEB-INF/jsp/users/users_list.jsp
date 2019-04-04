<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();%>  
    <%@ include file="/util/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
	

	
<title>用户信息</title> 
<script>
function chkedit(){
	// 获得复选框name
	var aa = document.getElementsByName("chk");	
	var flag;   
    for(var i=0;i<aa.length;i++)
    {
			//alert(document.getElementById('chk'+aa[i].value).checked);
   		if (document.getElementById('chk'+aa[i].value).checked) {//只修改选中的第一个
   			flag=0; 
   			alert(aa[i].value);  			  	
		    document.getElementById("editbutton").href  = "usersmanager.do?action=editAction&value="+aa[i].value;
			
   		} 
    }
    if (flag!=0) {
    	//alert("请选中后修改！");
    	alertMsg.error('请选中后修改！');
    	document.getElementById("delbutton").href="#";
    } 
    //alert(document.getElementById('chk1').checked)
	//var temp = document.getElementById("editbutton").href+"&value=";
	//alert(temp);
}

// 删除
function chkdel(){
	// 获得复选框name
	var aa = document.getElementsByName("chk");	
	var flag;  
	var value=null;
    for(var i=0;i<aa.length;i++)
    {
   		if (document.getElementById('chk'+aa[i].value).checked) {//只修改选中的第一个
   			flag=0;
   			value = aa[i].value + "," + value;   			  	
   		//alert(value);
   		} 
    }
    
    if (flag!=0) {
    	//alert("请选中后删除！");
    	alertMsg.error('请选中后删除！');
    	document.getElementById("delbutton").href="#";
    } 
	if (flag==0) {
		document.getElementById("delbutton").href  = document.getElementById("delbutton").href+"&value="+value;	
	}
	    
    //alert(document.getElementById('chk1').checked)
	//document.forms['pagerForm'].submit();	
	//var temp = document.getElementById("editbutton").href+"&value=";
	//alert(temp);
}

</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/users.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<!-- div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/users.do?action=list" method="post">
		<input type="hidden" id="selectedId_demo"/>
		<table>
		<tr>
		<td>	<ul class="searchContent">
				<li>
					<label>姓名：</label>
					<input type="text" name="qryname" value="${qryname }"/>
					
				</li>
			</ul>	</td><td>	<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				</ul>	</td>
		</tr>
		</table>
	
		</form>
	</div-->
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath }/users.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/users.do?action=addAction" target="navTab" rel="users"><span>添加用户</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="73">
			<thead>
				<tr>
					<th style="width:50px;">编号</th>
					<th style="width:120px;">用户名</th>
					<th style="width:100px;">真实姓名</th>
					<th style="width:100px;">分配角色</th>					
					<th style="width:100px;">所在部门</th>
					<th style="width:100px;">职务</th>
					<th style="width:100px;">电话</th>
					<th style="width:100px;">登录信息</th>
					<th style="width:100px;">状态</th>
					<th style="width:120px">操作</th>
					<th style="width:100px;"></th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l" >
					<tr> 						
						<td>${l.userId}</td>
						<td>${l.username}</td>
						<td>${l.realname}</td>
						<td>
							<a href="${pageContext.request.contextPath }/users.do?action=userRoleAction&value=${l.userId }" target="navTab"><span style="color:blue">分配角色</span></a>
						</td>
						<td>${l.dept}</td>
						<td>${l.title }</td>
						<td>${l.tel }</td>
						<td>${l.lastip} ${l.lastlogtime }</td>						
						<td>
						<c:if test="${0==l.state}"><font color=blue>正常</font></c:if>						
						<c:if test="${1==l.state}"><font color=red>锁定</font></c:if>						
						
						</td> 
						<td>				
						<a name="editbutton" class="edit" href="${pageContext.request.contextPath }/users.do?action=editAction&value=${l.userId }" target="navTab"><span style="color:blue">修改用户</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a name="delbutton" class="delete" href="${pageContext.request.contextPath }/users.do?action=delAction&value=${l.userId }" target="navTabTodo" title="确定要删除吗?"><span style="color:blue">删除用户</span></a>
				
						</td>
						<td>
						<a class="edit" href="${pageContext.request.contextPath }/users.do?action=editPwd&flag=einit&userid=${l.userId}" target="navTab"><span style="color:blue">修改密码</span></a>
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