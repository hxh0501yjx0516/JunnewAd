<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>北青页游</title> 
<script type="text/javascript">
function reportExcel(){
	var webMasterName = document.getElementById("webMasterName").value;
	window.location.href="${pageContext.request.contextPath }/beiqing.do?action=toExcel&webMasterName=" + webMasterName;
}

</script>
</head>
<body>
<div class="page">
    <div class="pageHeader">
       <form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/beiqing.do?action=list" method="post">
        <table>
        <tr>
        <td>    
        <ul class="searchContent">
                <li>
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
    <form method="post" action="${pageContext.request.contextPath}/beiqing.do?action=list" class="pageForm required-validate" onsubmit="return validateCallback(this);">
    
        <table class="table" layouth="92">
            <thead>
                <tr>
                    <th style="width:150px;">注册用户ID</th>
                    <th style="width:120px;">游戏ID</th>                   
                    <th style="width:120px;">游戏区服ID</th>                   
                    <th style="width:120px">渠道ID</th>
                    <th style="width:120px">渠道名称</th>
                    <th style="width:100px">金额</th>
                    <th style="width:100px">动作</th>
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
                        <td></td>
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
			<div class="pages">
				<span style="color:blue">当前查询： ${regCount } 次注册，${rechargeCount } 次充值</span>
			</div>
        </div>
        
    </form>
    </div>
</div>
</body>
</html>