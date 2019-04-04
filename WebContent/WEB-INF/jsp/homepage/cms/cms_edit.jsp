<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑新闻</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="editCms" method="post" action="${pageContext.request.contextPath }/cms.do?action=edit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);">
			<input type="hidden" name="cid" value="${cms.cmsId}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>标题：</label>
					<input name="name" class="required" type="text" size="30" value="${cms.cmsName}"/>
				</p>
				<p>
					<label>所属类别：</label>
					<select  name="cmsClassId" class="">
					<c:forEach var="m" items="${cmsClassList}">
						<option value="${m.cmsClassId}" ${cms.cmsClassId == m.cmsClassId?"selected":""}>${m.cmsClassName}</option>
					</c:forEach>
					</select>
				</p>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="state" value="0" ${cms.cmsStatus == 0?"checked":""}/>正常</label>
					<label><input type="radio" name="state" value="1" ${cms.cmsStatus == 1?"checked":""}/>锁定</label>
				</p>
				<p>
					<label>置顶：</label>
					<label><input type="radio" name="cmsFlag" value="0" ${cms.cmsFlag== 0?"checked":""}/>否</label>
					<label><input type="radio" name="cmsFlag" value="1" ${cms.cmsFlag == 1?"checked":""}/>是</label>
				</p>
				<div class="divider"></div>
				<p>
					<label>上传文件：</label>
				<input name="formFile" type="file" />
				</p>
				
				<div class="divider"></div>
					<p>
						<textarea class="required" name="content" cols="100" rows="20">${cms.cmsContent}</textarea>
					</p>
					<p>
					<c:choose>
					<c:when test="${cms.cmsImg !=''}">
					<img alt="" src="${cms.cmsImg}">
					</c:when>
					<c:otherwise><span style="color:orange">暂无图片</span></c:otherwise>
					</c:choose>
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
