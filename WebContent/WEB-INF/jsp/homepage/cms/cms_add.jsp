<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加新闻</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addCms" method="post" action="${pageContext.request.contextPath }/cms.do?action=add&flag=save" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>标题：</label>
					<input name="name" class="required" type="text" size="30" />
				</p>
				<p>
					<label>所属类别：</label>
					<select  name="cmsClassId" class="">
					<c:forEach var="m" items="${cmsClassList}">
						<option value="${m.cmsClassId}" ${cmsClassId == m.cmsClassId?"selected":""}>${m.cmsClassName}</option>
					</c:forEach>
					</select>
				</p>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="state" value="0" checked="checked"/>正常</label>
					<label><input type="radio" name="state" value="1"/>锁定</label>
				</p>
				<p>
					<label>置顶：</label>
					<label><input type="radio" name="cmsFlag" value="0" checked="checked"/>否</label>
					<label><input type="radio" name="cmsFlag" value="1"/>是</label>
				</p>
				<div class="divider"></div>
				<p>
					<label>上传文件：</label>
				<input name="formFile" type="file" />
				</p>
				<div class="divider"></div>
					<p>
						<label><textarea name="content" class="required" cols="100" rows="20"></textarea></label>
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
