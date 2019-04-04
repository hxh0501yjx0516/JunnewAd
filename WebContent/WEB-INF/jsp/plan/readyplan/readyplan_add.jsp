<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加广告计划</title> 
<script type="text/javascript">
 function checkWord()
{
   var wordvalue=document.getElementById("adPlanIdSelect").value.toLowerCase();
   var alltxt=document.getElementById("all").value;
   var alltxtpp=alltxt.toLowerCase();
   var alltxt_xiang=alltxt.split("|");
   var alltxt_xiang1=alltxtpp.split("|");
   var inhtml="<ul>"
   var isyou=0;
   for (i=0;i<alltxt_xiang1.length;i++)
   {
       //if (alltxt_xiang1[i].substr(0,wordvalue.length)==wordvalue)
       if (alltxt_xiang1[i].indexOf(wordvalue) > 0)
       {
           inhtml=inhtml+"<li onclick=\"document.getElementById('adPlanIdSelect').value=this.innerHTML;document.getElementById('showmenu').style.display='none';\" onmouseover=\"this.style.backgroundColor='#f7f6c6'\" onmouseout=\"this.style.backgroundColor=''\" style='line-height:20px'>"+alltxt_xiang[i]+"</li>";
           isyou=1;
       }
   }
   inhtml=inhtml+"</ul>";
   if (isyou==1)
   {
       document.getElementById("showmenu").innerHTML=inhtml;
       document.getElementById("showmenu").style.display="";
   }
   else
   {
       document.getElementById("showmenu").innerHTML="";
       document.getElementById("showmenu").style.display="none";
   }
   if (wordvalue=="")
   {
       document.getElementById("showmenu").innerHTML="";
       document.getElementById("showmenu").style.display="none";
   }
}
</script>
</head>
<body onload="">
    <div class="page">
	<div class="pageContent">
		<form name="addReadyPlan" method="post" action="${pageContext.request.contextPath }/readyplan.do?action=add&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input name="adPlanName" id="adPlanName" type="hidden" />
			<input name="webMasterId" type="hidden" value="${webMasterId}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>计划名称：</label>
					<label>
					<input type="text" id="adPlanIdSelect" name="adPlanId" size="30" onkeyup="checkWord()" value="计划关键字搜索" onclick="this.select();" style="color:gray"/>
					<div style=" position: absolute; width: 184px; height:auto; z-index: 1;  left: 120px; left:110px\9; top: 38px;border:1px solid #80a09f; background:#fafafa; display:none;" id="showmenu"></div>
					<input type="hidden" id="all" name="all" value="${planString}" />
					<!-- 
						<select class="" id="adPlanId" name="adPlanId" >
							<c:forEach items="${adPlanList}" var="adPlan">
								<option value="${adPlan.adPlanId}">${adPlan.adPlanName}</option>
							</c:forEach>
						</select>
					 -->
					</label>
					
				</p>
				<div class="divider"></div>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="readyPlanState" value="0" checked="checked"/>正常</label>
					<label><input type="radio" name="readyPlanState" value="1"/>锁定</label>
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
