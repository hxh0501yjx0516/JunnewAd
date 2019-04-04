<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>淘鞋本地数据查询</title> 
</head>
    <!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/taoxie.do?action=list&flag=pager" >
    <input type="hidden" name="pageNum" value="1" /><!-- 【必须】value=1可以写死-->
    <input type="hidden" name="numPerPage" value="${numPerPage }" /><!-- 【可选】每页显示多少条-->
    <!--    【可选】查询条件-->
  </form>
<body>
<div class="page">
    <div class="pageHeader">
       <form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/taoxie.do?action=list" method="post">
        <table>
        <tr>
        <td>    <ul class="searchContent">
                <li>
                    <label>订单编号：</label>
                    <input type="text" name="orderNumber" value="${orderNumber }"/>
                    
                </li>
            </ul>   </td><td>   <ul>
                    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
                </ul>   </td>
        </tr>
        </table>
        </form>
    </div>
    
    <div class="pageContent">
    <form method="post" action="${pageContext.request.contextPath}/taoxie.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
    
        <table class="table" layouth="92">
            <thead>
                <tr>
                    <th style="width:40px;" >编号</th>
                    <th style="width:50px;">标识</th>
                    <th style="width:120px;">订单号</th>                   
                    <th style="width:120px">商品</th>
                    <th style="width:80px">订单金额</th>
                    <th style="width:50px">数量</th>
                    <th style="width:50px">邮费</th>
                    <th style="width:120px">订单金额（扣去邮费）</th>
                    <th style="width:100px">订单总金额</th>
                    <th style="width:100px">优惠类型</th>
                    <th style="width:100px">状态</th>
                    <th style="width:120px">订单日期</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="l" >
                    <tr> 
                        <td>${l.id}</td>
                        <td>${l.flag}</td>
                        <td>${l.orderNumber}</td>
                        <td><a title="${l.product}">${l.product }</a></td>
                        <td>${l.productAmount }</td>
                        <td>${l.productNumber }</td>
                        <td>${l.postAmount }</td>
                        <td>${l.productAmount2 }</td>
                        <td>${l.allAmount }</td>
                        <td>${l.orderType }</td>
                        <td>${l.orderState }</td>
                        <td>${l.orderTime }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="panelBar">
                <%@ include file="/util/pager.jsp" %>
        </div>
        
    </form>
    </div>
</div>
</body>
</html>