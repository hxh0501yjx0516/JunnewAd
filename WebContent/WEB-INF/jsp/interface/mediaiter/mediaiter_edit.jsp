<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑渠道接口</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addMediaIter" method="post" action="${pageContext.request.contextPath }/mediaiter.do?action=edit&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="mediaIterId" value="${mediaIterList.mediaIterId}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>媒介：</label>
					<select name="user">
						<option value="0">--请选择--</option>
						<c:forEach items="${userslist}" var="l">
							<option value="${l.userId}--${l.realname}" ${l.userId eq mediaIterList.userId?"selected":""}>${l.realname}</option>
						</c:forEach>
					</select>
				</p>
				<div class="divider"></div>
				<p>
					<label>渠道：</label>
					<select  name="webMaster">
					<option value="0">--请选择--</option>
					<c:forEach items="${webMasterList}" var="l">
						<option value="${l.webMasterId}--${l.webMasterName}" ${l.webMasterId eq mediaIterList.webMasterId?"selected":""}>${l.webMasterName}</option>
					</c:forEach>
					</select>
				</p>
				<p>
					<label>渠道标识：</label>
					<input name="mediaFlag" type="text" size="30" class="required" value="${mediaIterList.mediaFlag}"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>客户信息：</label>
					<select  name="customer" class="">
					<option value="0">--请选择--</option>
					<c:forEach items="${customerList}" var="l">
						<option value="${l.customerId}--${l.customerName}" ${l.customerId eq mediaIterList.customerId?"selected":""}>${l.customerName}</option>
					</c:forEach>
					</select>
				</p>
				<p>
					<label>客户标识：</label>
					<input name="customerFlag" type="text" size="30" class="required" value="${mediaIterList.customerFlag}"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>推广标识：</label>
					<input name="urlFlag" type="text" size="30" class="required" value="${mediaIterList.urlFlag}"/>
				</p>
				<div class="divider"></div>
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
