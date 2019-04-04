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
<script type="text/javascript">
/*如果复选框被选中，则显示删除和添加按钮*/
function buttonDisable() {
	var checkbox = document.getElementsByName("c1");
	
	for(var i=0;i<checkbox.length;i++){   
              if (checkbox[i].checked) {   
                  document.getElementById("add_id").disabled=false;
                  return;   
              }else{   
                  document.getElementById("add_id").disabled=true;
              }                        
    }   	
}

function buttonDisable2() {
	var checkbox = document.getElementsByName("c2");
	for(var i=0;i<checkbox.length;i++){   
              if (checkbox[i].checked) {   
                  document.getElementById("del_id").disabled=false;   
                  return;   
              }else{   
                  document.getElementById("del_id").disabled=true;   
              }   
                     
    }   
	
}
// 添加
function chkadd(){
	// 获得复选框name
	var aa = document.getElementsByName("c1");	
	var flag;  
	var resid;
	var ad=null;
	var mod=null;
	var del=null;
    for(var i=0;i<aa.length;i++)
    {
   		if (aa[i].checked) {//只修改选中的第一个
   			flag=0;
   			resid = aa[i].value ;
   			//if (document.getElementsByName("ad"+aa[i].value)[i].checked) {
	   		//	ad = document.getElementsByName("ad"+aa[i].value)[i];  				
   			//}  
   			//if (document.getElementsByName("mod"+aa[0].value)[0].checked) {
	   		//	mod = document.getElementsByName("mod"+aa[0].value)[0];  				
   			//}
   			//if (document.getElementsByName("del"+aa[0].value)[0].checked) {
	   		//	del = document.getElementsByName("del"+aa[0].value)[0];  			
   			//}
    	} 
    }
	document.getElementById("add").href  = document.getElementById("add").href+"&resid="+resid;	
	    
    //alert(document.getElementById('chk1').checked)
	//document.forms['pagerForm'].submit();	
	//var temp = document.getElementById("editbutton").href+"&value=";
	//alert(temp);
}

// 删除
function chkdel(){
	// 获得复选框name
	var aa = document.getElementsByName("c2");	
	var flag;  
	var value;
    for(var i=0;i<aa.length;i++)
    {
   		if (aa[i].checked) {//只修改选中的第一个
   			flag=0;
   			value = aa[i].value ;
    	} 
    }
    alert(value);
	document.getElementById("del").href  = document.getElementById("del").href+"&value="+value;	
	    
    //alert(document.getElementById('chk1').checked)
	//document.forms['pagerForm'].submit();	
	//var temp = document.getElementById("editbutton").href+"&value=";
	//alert(temp);
}

</script>
	

	
<title>添加信息</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/resource.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<div class="pageHeader">
	</div>
	<div class="pageContent">
	<form method="post" id="addre" action="${pageContext.request.contextPath }/role.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath }/role.do?action=addAction&flag=ainit" target="navTab" rel="resrole"><span>给角色分配资源</span></a></li>
				
				<li class="line">line</li>
				<li><a class="icon" href="#"><span>导入EXCEL</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="56">
			<thead>
				<tr>
					<th style="width:40px;"></th>
					<th style="width:70px;">编号</th>
					<th style="width:120px;">资源名称</th>
					<!-- <th style="width:120px;">操作权限</th> -->
					<th style="width:100px;">资源模块</th>
					<th style="width:100px;">类型</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resourcelist}" var="l" varStatus="status">
					<tr> 
						<td style="width:40px;"><label><input type="checkbox" id="chk${l.resourceId}" name="c2" value="${l.resourceId}" onclick="buttonDisable2();"/></label></td>
						<td style="width:70px;">${l.resourceId}</td>
						<td style="width:120px;">${l.resourceName}</td>
					<!--<td style="width:120px;">
							<c:if test="${!empty l.resourceUrl }">
							<c:if test="${l.ad=='on'}"><font color="blue">添加</font></c:if>
							<c:if test="${l.modify=='on'}"><font color="blue">修改</font></c:if>
							<c:if test="${l.del=='on'}"><font color="blue">删除</font></c:if>
							</c:if>
						</td> -->
						<td style="width:100px;">${l.resourceModual}</td>
						<td style="width:100px;">${l.resourceType}</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
		<ul class="toolBar">
		
		<li><a class="del" id="del_id" name="del" onclick="chkdel()" href="${pageContext.request.contextPath }/role.do?action=setResourceForRole&roleId=${roleId }&flag=del" disabled="disabled" target="navTab"><span>移除选中</span></a></li>
		<input name="button" type="button" value=" 返 回 "
			onclick="history.go(-1)" />
	</ul></div>
		<table class="table" layouth="142">
			<thead>
				<tr>
					<th style="width:40px;"></th>
					<th style="width:70px;">编号</th>
					<th style="width:120px;">资源名称</th>
					<!-- <th style="width:120px;">操作权限</th> -->
					<th style="width:100px;">资源模块</th>
					<th style="width:100px;">类型</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="l" varStatus="status">
					<tr> 
						<td><label><input type="checkbox" id="chk${l.resourceId}" name="c1" value="${l.resourceId}" onclick="buttonDisable();"/></label></td>
						<td>${l.resourceId}</td>
						<td>${l.resourceName}</td>
					<!-- <td>
							<input type="checkbox" name="ad${l.resourceId}" value="${l.resourceId}"/>添加
							<input type="checkbox" name="mod${l.resourceId}" value="${l.resourceId}"/>修改
							<input type="checkbox" name="del${l.resourceId}" value="${l.resourceId}"/>删除
						</td> -->
						<td>${l.resourceModual}</td>
						<td>${l.resourceType}</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<div class="panelBar">
		<ul class="toolBar">
		
		<li><a class="add" id="add_id" name="add" onclick="chkadd()" href="${pageContext.request.contextPath }/role.do?action=setResourceForRole&roleId=${roleId }" disabled="disabled" target="navTab"><span>添加选中</span></a></li>
		<input name="button" type="button" value=" 返 回 "
			onclick="history.go(-1)" />
	</ul></div>
			
			
	</form>
	</div>
</div>
</body>
 </html>