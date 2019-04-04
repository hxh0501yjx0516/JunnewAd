<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>收款信息</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		
			<div class="pageFormContent" layoutH="33">
				<h3 class="contentTitle">
				推广周期：${adPlanCycle.adPlanCycleName}
				&nbsp;&nbsp;
				(
				<fmt:parseDate value="${adPlanCycle.beginTime}" var="bt" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate value="${bt}" type="date" pattern="yyyy-MM-dd" />
				至
				<fmt:parseDate value="${adPlanCycle.endTime}" var="et" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate value="${et}" type="date" pattern="yyyy-MM-dd" />
				)
				</h3>
				<p>
					<label>预算：￥<fmt:formatNumber value="${adPlanCycle.customerAllPrice}" pattern="#,###.##"/></label>
				</p>
				<p>
					<label>单价：￥<fmt:formatNumber value="${adPlanCycle.customerPrice}" pattern="#,###.##"/></label>
				</p>
				<div class="divider"></div>
				
				<form name="addAdBox" method="post" action="${pageContext.request.contextPath }/databalance.do?action=addMoneyReceipt&adPlanCycleId=${adPlanCycle.adPlanCycleId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
					<input type="text" placeholder="回款日期" id="receiptDate" name="receiptDate" readonly="readonly" class="date required"/>
					<input type="text" placeholder="回款金额" id="receiptMoney" name="receiptMoney"  class="required" style="margin-left:10px"/>
					<input type="text" placeholder="备注" id="remarks" name="remarks" style="margin-left:10px" />
					<button type="submit" style="margin-left:10px">新增回款</button>
				</form>
				<div class="divider"></div>
				
				<br /><br />
				
				<h3 class="contentTitle">回款信息</h3>				
				<table class="table" layouth="113">
					<thead>
						<tr>
							<th style="width:50px;">编号</th>
		                    <th style="width:100px;">回款日期</th>
		                    <th style="width:200px;">回款金额</th>                  
		                    <th style="width:500px;">备注</th>
						</tr>
					</thead>
				
					<tbody>
					<c:forEach items="${receipts }" var="receipt" varStatus="r">
						<tr>
							<td>${receipt.id }</td>
							<td>
								<fmt:parseDate value="${receipt.receiptDate}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
								<fmt:formatDate value="${date}" type="date"  pattern="yyyy-MM-dd"/>
							</td>
							<td>￥<fmt:formatNumber value="${receipt.receiptMoney}" pattern="#,###.##"/></td>
							<td>${receipt.remarks}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
				
				
				<!-- <div class="formBar">
				<ul>					
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">新增回款</button></div></div></li>
				</ul>
				</div> -->
			</div>
		</form>
	</div>
</div>
  </body>
</html>
