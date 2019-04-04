<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();
%>  
<%@ include file="/util/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>渠道接口</title> 
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/mediaiter.do?action=list&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
  </form>
<body>
<div class="page">
	<!-- 
	<div class="pageHeader">
		<form name="ecForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/mediaiter.do?action=list" method="post">
		<input type="hidden" id="selectedId_demo"/>
		<table>
		<tr>
		<td>	<ul class="searchContent">
				<li>
					<label>客户信息：</label>
					<label><input name="customerInfo" value="${customerInfo }" type="text" size="10" /></label>
					<label>链接标识：</label>
					<label><input name="urlFlag" value="${urlFlag }" type="text" size="10" /></label>
					<label>开始日期：</label>
					<label><input name="beginTime" value="${beginTime }" class="date" pattern="yyyy-MM-dd" readonly="true"  type="text" size="10" /></label>
					<label>结束日期：</label>
					<label><input name="endTime" value="${endTime }" class="date"  pattern="yyyy-MM-dd" readonly="true" type="text" size="10" /></label>	
				</li>
			</ul>	</td><td>	<ul>
					<li>
					<div class="buttonActive">
					<div class="buttonContent"><button type="submit">检索</button></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/mediaiter.do?action=list" target="navTab" rel="数据查询" title="数据查询"><button>重置</button></a></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><button type="button" onclick="ToExcel();">导出报表</button></div>
					</div>
					</li>
				</ul>	</td>
		</tr>
		</table>
	
		</form>
	</div>
	 -->
	<div class="pageContent">
	
		<div class="panelBar">
			<ul class="toolBar">
			<li><a class="add" href="${pageContext.request.contextPath }/mediaiter.do?action=add" target="navTab" title="新增渠道接口" rel="新增渠道接口"><span>新增渠道接口</span></a></li>
			</ul>
			<a href="${pageContext.request.contextPath }/mediaiter.do?action=downLoad"><span style="color:red; line-height:22px; margin-left:50px;">文档下载</span></a>
		</div>
	<form method="post" action="${pageContext.request.contextPath}/mediaiter.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
		<table class="table" layouth="89">
			<thead>
				<tr>
					
					<th style="width:50px;">编号</th>
					<th style="width:100px;">媒介ID</th>
					<th style="width:100px;">媒介</th>
					<th style="width:100px;">渠道ID</th>
					<th style="width:150px;">渠道名称</th>
					<th style="width:100px;">渠道标识</th>	
					<th style="width:100px;">推广标识</th>				
					<th style="width:100px;">客户信息</th>
					<th style="width:100px;">客户标识</th>
					<th style="width:200px;">操作</th>
				</tr>

			</thead>
			<tbody>
				<c:forEach items="${mediaIterList}" var="l" >
					<tr> 						
						<td>${l.mediaIterId }</td>
						<td>${l.userId }</td>
						<td>${l.userName }</td>
						<td>${l.webMasterId }</td>
						<td>${l.webMasterName }</td>
						<td>${l.mediaFlag }</td>
						<td>${l.urlFlag }</td>
						<td>${l.customerName }</td>
						<td>${l.customerFlag }</td>
						<td>
						<a class="btnEdit" href="${pageContext.request.contextPath }/mediaiter.do?action=edit&mediaIterId=${l.mediaIterId}" target="navTab" title="编辑渠道接口" rel="编辑渠道接口">编辑渠道接口</a>
						<a href="${pageContext.request.contextPath }/mediaiter.do?action=getCode&mediaIterId=${l.mediaIterId}" target="dialog"><font color=blue>获得HTTP接口</font></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<%@ include file="/util/pager.jsp" %>
			</div>
		</div>
		
	</form>
	</div>
</div>
</body>
 </html>