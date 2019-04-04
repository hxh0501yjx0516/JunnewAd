<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>结算审核</title> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/formatNumber.js"></script>
</head>
    <!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/finance.do?action=list&flag=pager" >
    <input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
    <input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
    <input type="hidden" name="qrywebmaster" value="${qrywebmaster }" />
    <input type="hidden" name="qrysales" value="${qrysales }" />
    <input type="hidden" name="qryadplan" value="${qryadplan }" />
    <input type="hidden" name="qrybegintime" value="${qrybegintime }" />
    <input type="hidden" name="currentusergroup" value="${currentusergroup }" />
  </form>
<body>
<div class="page">
        <!--div class="pageHeader">
        <form name="balanceReportForm" onsubmit="return navTabSearch(this)" action="${pageContext.request.contextPath }/finance.do?action=list" method="post">
	    <input type="hidden" name="financeflag" value="${financeflag }" />
    
        <div class="searchBar" >
            <ul class="searchContent">
                <li>
                    <label>支付状态：</label>
                    <select name="financeflag"  class="">
                    <option value="" >请选择</option>
                    <option value="0" ${financeflag eq 0?"selected":""}>已核对，待审核</option>
                    <option value="1" ${financeflag eq 1?"selected":""}>已审核，待支付</option>
                    <option value="2" ${financeflag eq 2?"selected":""}>支付成功</option>
                    <option value="3" ${financeflag eq 3?"selected":""}>未支付成功</option>
                    </select>
                </li>
                    <li>
                    <div class="buttonActive" ><div class="buttonContent"><button type="submit" >检索</button></div></div>
                    <div class="buttonActive" ><div class="buttonContent"><button type="button" onclick="resetForm();">重置</button></div></div>
                    </li>
            </ul>
        </div>
        </form>
    </div-->
    <div class="pageContent">
    <form method="post" action="${pageContext.request.contextPath }/finance.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
        <div class="panelBar">
            <ul class="toolBar">
                <li><a title="确实要审核通过这些记录吗?" target="selectedTodo" rel="ids" href="${pageContext.request.contextPath }/finance.do?action=review" class="delete"><span>批量审核</span></a></li>
            </ul>
        </div>
        <table class="table" layouth="33">
            <thead>
                <tr>
                    <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                    <th style="width:40px;">编号</th>
                    <th style="width:90px;">开始时间</th>
                    <th style="width:90px;">结束时间</th>                  
                    <th style="width:40px;">站长</th>
                    <th style="width:40px;">媒介</th>
                    <th style="width:100px;">广告计划</th>
                    <th style="width:100px;">返回值</th>
                    <th style="width:100px;">应收</th>
                    <th style="width:100px;">实收</th>
                    <th style="width:50px;">返点</th>
                    <th style="width:100px;">收款</th>
                    <th style="width:100px;">利润</th>
                    <th style="width:40px;">利润率</th>
                    <th style="width:100px;">有效值</th>
                    <th style="width:100px;">有效佣金</th>
                    <th style="width:100px;">实际支付佣金</th>
                    <th style="width:40px;">税点</th>
                    <th style="width:100px;">备注</th>
                    <th style="width:100px;">收款状态</th>
                    <th style="width:100px;">支付状态</th>
                    <th style="width:100px;">说明</th>
                    <th style="width:100px;">支付公司</th>
                    <th style="width:40px">操作</th>
                </tr>
            </thead>
                
            <tbody>
                <tr style="color: blue;font-weight: bold;">
                    <td></td>
                    <td>汇总:</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><fmt:formatNumber value="${countSum}" pattern="#,###.##"/></td>
                    <td>￥<fmt:formatNumber value="${moneyBeforeRebateSum}" pattern="#,###.##"/></td>
                    <td>￥<fmt:formatNumber value="${moneySum}" pattern="#,###.##"/></td>
                    <td></td>
					<td></td>
                    <td>￥<fmt:formatNumber value="${moneySum-realMoneySum}" pattern="#,###.##"/></td>
                    <td>
                    <c:choose>
                        <c:when test="${moneySum eq 0}">
                        0%
                        </c:when>
                        <c:otherwise>
                            <fmt:formatNumber value="${(moneySum-realMoneySum)*100/moneySum}" pattern="#,###.##"/>%
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td><fmt:formatNumber value="${realCountSum}" pattern="#,###.##"/></td>
                    <td>￥<fmt:formatNumber value="${realMoneySum}" pattern="#,###.##"/></td>
                    <td>￥<fmt:formatNumber value="${trueMoneySum}" pattern="#,###.##"/></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach items="${list}" var="dataReport" varStatus="s">
                    <tr ${dataReport.financeFlag eq 3?"style='color:red'":""}> 
                        <td><input name="ids" value="${dataReport.payId}" type="checkbox"></td>
                        <td>${dataReport.payId}</td>
                        <td>
                        <fmt:parseDate value="${dataReport.payBeginTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <fmt:formatDate value="${date}" type="date"  pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                        <fmt:parseDate value="${dataReport.payEndTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />
                        </td>
                        <td>${dataReport.webMasterName}</td>
                        <td>${dataReport.userName}</td>
                        <td>${dataReport.adPlanName}</td>
                        <td><fmt:formatNumber value="${dataReport.count}" pattern="#,###.##"/></td>
                        <td>￥<fmt:formatNumber value="${dataReport.moneyBeforeRebate}" pattern="#,###.##"/></td>
                        <td>￥<fmt:formatNumber value="${dataReport.money}" pattern="#,###.##"/></td>
                        <td>${dataReport.rebate}%</td>
						<td>${dataReport.payee}</td>
                        <td>￥<fmt:formatNumber value="${dataReport.money-dataReport.realMoney}" pattern="#,###.##"/></td>
                        <td>
                        <c:if test="${dataReport.money ne 0}">
                        <fmt:formatNumber value=" ${(dataReport.money-dataReport.realMoney)/dataReport.money*100}" pattern="#,###.##"/>%
                        </c:if>
                        </td>
                        <td> <fmt:formatNumber value="${dataReport.realCount}" pattern="#,###.##"/> </td>
                        <td>￥ <fmt:formatNumber value="${dataReport.realMoney}" pattern="#,###.##"/></td>
                        
                        <td>
                         <fmt:formatNumber var="fmtRealMoney" value="${dataReport.trueMoney}" pattern="#,###.##"/>
                        ￥${fmtRealMoney}</td>
                        <td><fmt:formatNumber value="${dataReport.taxNum}" pattern="#,###.##"/></td>
                        <td>${dataReport.remarks}</td>
                        <td>
                        <c:if test="${dataReport.moneyReceipt eq 0}"> <a title="未收款" style="color:red">未收款</a></c:if>
                        <c:if test="${dataReport.moneyReceipt eq 1}"> <a title="已收款" style="color:blue">已收款</a></c:if>
                        </td>
						<td>
                        <c:if test="${dataReport.financeFlag eq 0}"> <a title="待审核"  style="color:green">待审核</a></c:if>
                        <c:if test="${dataReport.financeFlag eq 1}"> <a title="已审核，待支付">已审核，待支付</a></c:if>
                        <c:if test="${dataReport.financeFlag eq 2}"> <a title="支付成功" style="color:blue">支付成功</a></c:if>
                        <c:if test="${dataReport.financeFlag eq 3}"> <a title="未支付成功" style="color:red">未支付成功</c:if></a>
                        </td>
                        <td><a title="${dataReport.financeMark}">${dataReport.financeMark}</a></td>
                        <td>
                        <c:choose>
                        <c:when test="${dataReport.payCompanyType eq 1}">
                            <c:set var="company" value="盘酷科技" />
                        </c:when>
                        <c:when test="${dataReport.payCompanyType eq 2}">
                            <c:set var="company" value="环宇移通" />
                        </c:when>
                        <c:when test="${dataReport.payCompanyType eq 3}">
                            <c:set var="company" value="中润无限" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="company" value="未知" />
                        </c:otherwise>
                        </c:choose>
                        <a title="${company }">${company}</a>
                        </td>
                        <td>
                        <a name="editbutton" title="${dataReport.webMasterName}-${dataReport.adPlanName}-详情"  href="${pageContext.request.contextPath }/databalance.do?action=details&payId=${dataReport.payId}" target="navTab" rel="balanceDetails"><span style="color:blue">详情</span></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </form>
    </div>
</div>
</body>
 </html>