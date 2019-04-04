<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>北青页游</title> 
<script type="text/javascript">
function reportExcel(){
	var btime = document.getElementById("btime").value;
	var etime = document.getElementById("etime").value;
	var webMasterName = document.getElementById("webMasterName").value;
	window.location.href="${pageContext.request.contextPath }/beiqing.do?action=historyToExcel&btime="+btime+"&etime="+etime+"&webMasterName=" + webMasterName;
	}
</script>
</head>
<body>
<!-- 分页 -->
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath }/beiqing.do?action=history&flag=pager" >
  	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="actionFlag" value="${actionFlag }" /><!--【可选】查询条件-->
	<input type="hidden" name="webMasterName" value="${webMasterName }" />
	<input type="hidden" name="btime" value="${btime }" />
	<input type="hidden" name="etime" value="${etime }" />
  </form>
<div class="page">
    <div class="pageHeader">
       <form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/beiqing.do?action=history" method="post">
        <table>
        <tr>
        <td>    
        <ul class="searchContent">
                <li>
                	<label>日期：</label>
                	<input type="text" id="btime" name="btime" class="date" value="${btime }"  size="10" readonly="readonly" />
                	<input type="text" id="etime" name="etime" class="date" value="${etime }"  size="10" readonly="readonly" />
                
                    <label>动作：</label>
                    <select name="actionFlag">
                    <option value="">==全部==</option>
                    <option value="1" ${actionFlag eq 1 ? "selected='selected'" : "" }>注册</option>
                    <option value="2" ${actionFlag eq 2 ? "selected='selected'" : "" }>冲值</option>
                    </select>
                    
                    <label>站长：</label>
                    <input type="text" id="webMasterName" name="webMasterName" size="10" value="${webMasterName }" placeholder="模糊查询"/>
                    
                </li> 
            </ul>   </td><td>   <ul>
                    <li>
                    <div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
                    <div class="buttonActive" >
						<div class="buttonContent">
						<button type="button" onclick="reportExcel()">报表导出</button>
						</div>
					</div>
					
                    </li>
                </ul>   </td>
        </tr>
        </table>
        </form>
    </div>
    
    <div class="pageContent">
    <form method="post" action="${pageContext.request.contextPath}/beiqing.do?action=history" class="pageForm required-validate" onsubmit="return validateCallback(this);">
    
        <table class="table" layouth="92">
            <thead>
                <tr>
                    <th style="width:150px;">注册用户ID</th>
                    <th style="width:120px;">游戏ID</th>                   
                    <th style="width:120px;">游戏区服ID</th>                   
                    <th style="width:120px">渠道ID</th>
                    <th style="width:120px">渠道名称</th>
                    <th style="width:100px">金额</th>
                    <th style="width:200px">动作</th>
                    <th style="width:150px">日期</th>
                </tr>
            </thead>
            <tbody>
            		<tr style="color:blue"> 
                        <td>汇总：</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>${allAmount }</td>
                        <td>${regCount } 次注册，${rechargeCount } 次充值</td>
                        <td></td>
                    </tr>
                <c:forEach items="${list}" var="l" >
                    <tr> 
                        <td>${l.uid}</td>
                        <td>${l.gid}</td>
                        <td>${l.sid}</td>
                        <td>${l.gunion }</td>
                        <td>${l.webMasterName }</td>
                        <td>${l.amt }</td>
                        <td>${l.action eq 1 ? "<font color='red'>注册</font>" : "<font color='blue'>冲值</font>" }</td>
                        <td>${l.indbdate }</td>
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