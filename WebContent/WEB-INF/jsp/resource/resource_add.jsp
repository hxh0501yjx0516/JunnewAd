<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %><%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <head>
 	<script type="text/javascript">
			function showpid()
			{
				var obj = document.getElementById("trpid");
				obj.style.display="block";
			}
			function hidepid()
			{
				var obj = document.getElementById("trpid");
				obj.style.display="none";
			}
		
	</script>
  </head>
  
  <body>
    <div class="page">
	<div class="pageContent">
		<form method="post" action="${pageContext.request.contextPath }/resource.do?action=addAction&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="panelBar">
			<ul class="toolBar">
				<span style="font:bold">》》添加资源</span>
				<li class="line">line</li>
			</ul>
		</div>
			<div class="pageFormContent" layoutH="300">
				<p>
					<label>资源名称：</label>
					<input name="name" type="text" size="30" value="" />
				</p>
				<p>
					<label>所属模块：</label>
					<input name="modual" type="text" size="30" value="" />
				</p>
				<p>
					<label>资源URL：</label>
					<input name="url" type="text" size="30" value="" />
				</p>
				<p>
					<label>资源类型：</label>
					<input type="radio" name="type" value="0" checked="checked" onclick="hidepid()"/>
					主菜单
					<input type="radio" name="type" value="2" onclick="showpid()"/>
					子菜单
									
				</p>
				<p id="trpid" style="display:none">
					<label>父编号：</label>
					<select class="" id="pid" name="pid">
							<c:forEach items="${resourceList}" var="resource">
								<option value="${resource.resourceId}">${resource.resourceName}</option>
							</c:forEach>
						</select>
				</p>
				<p>
					<label>显示次序：</label>
					<input name="displayorder" class="required number"  type="text" size="30" value="" />
				</p>
			<div class="divider"></div>
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

