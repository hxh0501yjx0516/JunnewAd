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
	

	
<title>媒介--数据报表</title> 
<script type="text/javascript">
function reportExcel(){
		var beginTime = document.balanceMediaForm.beginTime.value;
		var endTime = document.balanceMediaForm.endTime.value;
		var payType = document.balanceMediaForm.paytype.value;
		var webMasterName = document.balanceMediaForm.webmastername.value;
		var adplanname = document.balanceMediaForm.adplanname.value;
		 location.href="${pageContext.request.contextPath }/media.do?action=reportCountExcel&begintime="+beginTime+"&endtime="+endTime+"&adplanname="+adplanname+"&paytype="+payType+"&webmastername="+webMasterName;
	}
</script>
</head>
	<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/media.do?action=reportCountList&search=yes&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="qryname" value="${qryname }" /><!--【可选】查询条件-->
	<input type="hidden" name="beginTime" value="${begintime}">
	<input type="hidden" name="endTime" value="${endtime}">
	<input type="hidden" name="paytype" value="${paytype}">
	<input type="hidden" name="adplanname" value="${adplanname}">
	<input type="hidden" name="webmastername" value="${webmastername}">
  </form>
<body>
<div class="page">
	<div class="pageHeader">
		<form name="balanceMediaForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/media.do?action=reportCountList&search=yes" method="post">
		<input type="hidden" id="selectedId_demo"/>
		<table>
		<tr>
				<td>
					<ul class="searchContent">
					<li>
					<label>开始日期：</label>
					<label><input name="beginTime" value="${begintime }" class="date" pattern="yyyy-MM-dd" readonly="true"  type="text" size="10" /></label>
					<label>结束日期：</label>
					<label><input name="endTime" value="${endtime }" class="date"  pattern="yyyy-MM-dd" readonly="true" type="text" size="10" /></label>
					<label>数据状态：</label>
					<select name="paytype" class="">
						<option value="" ${paytype eq ""?"selected":""}>==全部状态==</option>
						<option value="0" ${paytype eq "0"?"selected":""}>┹已上传</option>
						<option value="1" ${paytype eq "1"?"selected":""}>┹已提交</option>
						<option value="2" ${paytype eq "2"?"selected":""}>┹已结算</option>
					</select>
					<label>广告计划：</label>
					<label><input name="adplanname" value="${adplanname }" type="text" size="10" /></label>
					<label>站长：</label>
					<label><input name="webmastername" value="${webmastername }" type="text" size="10" /></label>
						
					</li>
					</ul>	
				</td>
				<td>	
					<ul>
					<li>
					<div class="buttonActive">
					<div class="buttonContent"><button type="submit">检索</button></div>
					</div>
					<div class="buttonActive">
					<div class="buttonContent"><a href="${pageContext.request.contextPath }/media.do?action=reportCountList" target="navTab" rel="数据报表" title="数据报表"><button>重置</button></a></div>
					</div>
					
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="reportExcel();">报表导出</button></div></div>
					</li>
					</ul>	
				</td>
		</tr>
		</table>
	
		</form>
	</div>
	<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/media.do?action=reportcountlist" class="pageForm required-validate" onsubmit="return validateCallback(this);">
	
		
		<table class="table" layouth="85">
			<thead>
				<tr>
					
					<th width="120">站长</th>
					<th width="120">域名</th>
					<th width="120">广告计划</th>
					<th width="120">周期</th>	
					<th width="120">广告位</th>
					<th width="120">创意</th>							
					<th width="120">站长有效值</th>
					<th width="120">站长佣金</th>
					<th width="120">客户返回值</th>
					<th width="120">应收佣金</th>
					<th width="120">利润</th>
					<th width="120">利润率</th>
					<th width="120">日期</th>
					<th width="120">数据状态</th>
					<th width="120">备注</th>
				</tr>
			</thead>
			<tbody>
				<tr> 						
						<td style=" color:blue; font-weight:bold;">汇总</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][0]}" pattern="#,###.##" /></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][1]}" pattern="#,###.##"/></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][2]}" pattern="#,###.##"/></td>
						<td style=" color:blue; font-weight:bold"><fmt:formatNumber value="${listSum[0][3]}" pattern="#,###.##"/></td>
						<td style=" color:blue; font-weight:bold">
						<c:choose>
							<c:when test="${listSum[0][3]-listSum[0][1] ne 0 }">
							<fmt:formatNumber value="${listSum[0][3]-listSum[0][1]}" pattern="#,###.##"/>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						</td>
						<td style=" color:blue; font-weight:bold">
						<c:choose>
							<c:when test="${listSum[0][3]-listSum[0][1] ne 0 }">
							<fmt:formatNumber value="${(listSum[0][3]-listSum[0][1])/listSum[0][3]*100}" pattern="#,###.##"/>%
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						</td>
						<td></td>			
						<td></td>
						<td></td>
					</tr>
				<c:forEach items="${list}" var="l" >
					<c:choose>
						<c:when test="${l.reportStatus == 0}">
							<tr style="color: red;"> 
						</c:when>
						<c:when test="${l.reportStatus == 1}">
							<tr style="color: blue;"> 
						</c:when>
						<c:otherwise>
							<tr> 
						</c:otherwise>
					</c:choose>								
						<td>${l.webMasterName }</td>
						<td>${l.urlName }</td>
						<td>${l.adplanName }</td>
						<td>${l.adplanCycleName }</td>
						<td>${l.adBoxName }</td>
						<td>${l.adCreativeName }</td>
						<td><fmt:formatNumber value="${l.realCount}" pattern="#,###.##" /></td>
						<td><fmt:formatNumber value="${l.realMoney}" pattern="#,###.##"/></td>
						<td><fmt:formatNumber value="${l.count}" pattern="#,###.##"/></td>
						<td><fmt:formatNumber value="${l.money}" pattern="#,###.##"/></td>
						<td><fmt:formatNumber value="${l.money-l.realMoney}" pattern="#,###.##"/></td>
						<td><fmt:formatNumber value="${(l.money-l.realMoney)/l.money*100}" pattern="#,###.##"/>%</td>
						<td>
						${fn:substring(l.reportTime,0,10)}
						<fmt:formatDate value="${date}" type="date"/>
						</td>			
						<td>
						<c:choose>
						<c:when test="${l.reportStatus == 0}">
							已上传
						</c:when>
						<c:when test="${l.reportStatus == 1}">
							已提交
						</c:when>
						<c:otherwise>
							已结算
						</c:otherwise>
					</c:choose>
						</td>
						<td>${l.remarks }</td>
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