<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
 <html>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加客服</title> 
<head>
<script type="text/javascript">
  function getSelectedText(obj){
   var strsel = obj.options[obj.selectedIndex].text;
   if(obj.selectedIndex == 0){
   document.getElementById("urlTypeName").value="";
   }else{
   document.getElementById("urlTypeName").value=strsel;
   }
  }
 </script>
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="editUrlAddress" method="post" action="${pageContext.request.contextPath }/urladdress.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="urlId" value="${urlAddress.urlId}" />
			<input type="hidden" name="urlTypeName" value="${urlAddress.urlTypeName}"/>
			<div class="pageFormContent" layoutH="33">
				<h3 class="contentTitle">
							所属网站主：${urlAddress.webMasterName}
				</h3>
				<p>
					<label>域名：</label>
					<input name="url"  type="text" size="30" value="${urlAddress.url}" />
				</p>
				<p>
					<label>网站名称 ：</label>
					<input name="urlName"   type="text" size="30" value="${urlAddress.urlName}"  />
				</p>
				<p>
					<label>ICP：</label>
					<input name="urlIcp" type="text" size="30" value="${urlAddress.urlICP}" />
				</p>
				<p>
					<label>日IP量 ：</label>
					<input name="urlDayIp"  type="text" size="30" value="${urlAddress.urlDayIp}"/>
				</p>
				<p>
					<label>所属地区 ：</label>
					<input name="urlArea" type="text"  size="30" value="${urlAddress.urlArea}" />
				</p>
				<p>
					<label>网站类型  ：</label>                                                                     
					<select name="urlTypeId" class="" onchange="getSelectedText(this)">             
					<option value="0" ${urlAddress.urlTypeId == 0?"selected":""}>请选择类别</option>      
					<option value=1 ${urlAddress.urlTypeId == 1?"selected":""}>在线音乐</option>          
					<option value=2 ${urlAddress.urlTypeId == 2?"selected":""}>在线电影</option>          
					<option value=3 ${urlAddress.urlTypeId == 3?"selected":""}>视频分享</option>          
					<option value=4 ${urlAddress.urlTypeId == 4?"selected":""}>网络游戏</option>          
					<option value=5 ${urlAddress.urlTypeId == 5?"selected":""}>单机游戏</option>          
					<option value=6 ${urlAddress.urlTypeId == 6?"selected":""}>flash小游戏</option>       
					<option value=7 ${urlAddress.urlTypeId == 7?"selected":""}>QQ娱乐</option>            
					<option value=8 ${urlAddress.urlTypeId == 8?"selected":""}>文学小说</option>          
					<option value=9 ${urlAddress.urlTypeId == 9?"selected":""}>社区论坛</option>          
					<option value=10 ${urlAddress.urlTypeId == 10?"selected":""}>博客网站</option>        
					<option value=11 ${urlAddress.urlTypeId == 11?"selected":""}>BT下载</option>          
					<option value=12 ${urlAddress.urlTypeId == 12?"selected":""}>p2p共享</option>         
					<option value=13 ${urlAddress.urlTypeId == 13?"selected":""}>网吧资源</option>        
					<option value=14 ${urlAddress.urlTypeId == 14?"selected":""}>电脑网络</option>        
					<option value=15 ${urlAddress.urlTypeId == 15?"selected":""}>教育培训</option>        
					<option value=16 ${urlAddress.urlTypeId == 16?"selected":""}>英语培训</option>        
					<option value=17 ${urlAddress.urlTypeId == 17?"selected":""}>IT培训</option>          
					<option value=18 ${urlAddress.urlTypeId == 18?"selected":""}>动漫网站</option>        
					<option value=19 ${urlAddress.urlTypeId == 19?"selected":""}>育儿母婴</option>        
					<option value=20 ${urlAddress.urlTypeId == 20?"selected":""}>WAP服务</option>         
					<option value=21 ${urlAddress.urlTypeId == 21?"selected":""}>娱乐综合</option>        
					<option value=22 ${urlAddress.urlTypeId == 22?"selected":""}>交友网站</option>        
					<option value=23 ${urlAddress.urlTypeId == 23?"selected":""}>网址导航</option>        
					<option value=24 ${urlAddress.urlTypeId == 24?"selected":""}>电子杂志</option>        
					<option value=25 ${urlAddress.urlTypeId == 25?"selected":""}>女性网站</option>        
					<option value=26 ${urlAddress.urlTypeId == 26?"selected":""}>网上招聘</option>        
					<option value=27 ${urlAddress.urlTypeId == 27?"selected":""}>网上购物</option>        
					<option value=28 ${urlAddress.urlTypeId == 28?"selected":""}>地方门户</option>        
					<option value=29 ${urlAddress.urlTypeId == 29?"selected":""}>门户综合</option>        
					<option value=30 ${urlAddress.urlTypeId == 30?"selected":""}>科学资讯</option>        
					<option value=31 ${urlAddress.urlTypeId == 31?"selected":""}>餐饮旅游</option>        
					<option value=32 ${urlAddress.urlTypeId == 32?"selected":""}>软件下载</option>        
					<option value=33 ${urlAddress.urlTypeId == 33?"selected":""}>汽车资讯</option>        
					<option value=34 ${urlAddress.urlTypeId == 34?"selected":""}>服装鞋包</option>        
					<option value=35 ${urlAddress.urlTypeId == 35?"selected":""}>工商经济</option>        
					<option value=36 ${urlAddress.urlTypeId == 36?"selected":""}>工艺礼品</option>        
					<option value=37 ${urlAddress.urlTypeId == 37?"selected":""}>广告营销</option>        
					<option value=38 ${urlAddress.urlTypeId == 38?"selected":""}>化工产品</option>        
					<option value=39 ${urlAddress.urlTypeId == 39?"selected":""}>机械工业</option>        
					<option value=40 ${urlAddress.urlTypeId == 40?"selected":""}>家居家电</option>        
					<option value=41 ${urlAddress.urlTypeId == 41?"selected":""}>金融证券</option>        
					<option value=42 ${urlAddress.urlTypeId == 42?"selected":""}>轻工纺织</option>        
					<option value=43 ${urlAddress.urlTypeId == 43?"selected":""}>日用化妆</option>        
					<option value=44 ${urlAddress.urlTypeId == 44?"selected":""}>时尚美容</option>        
					<option value=45 ${urlAddress.urlTypeId == 45?"selected":""}>食品饮料</option>        
					<option value=46 ${urlAddress.urlTypeId == 46?"selected":""}>体育休闲</option>        
					<option value=47 ${urlAddress.urlTypeId == 47?"selected":""}>新闻媒体</option>        
					<option value=48 ${urlAddress.urlTypeId == 48?"selected":""}>休闲娱乐</option>        
					<option value=49 ${urlAddress.urlTypeId == 49?"selected":""}>医疗保健</option>        
					<option value=50 ${urlAddress.urlTypeId == 50?"selected":""}>仪器仪表</option>        
					<option value=51 ${urlAddress.urlTypeId == 51?"selected":""}>造纸印刷</option>        
					<option value=52 ${urlAddress.urlTypeId == 52?"selected":""}>增值业务</option>
					<option value=53 ${urlAddress.urlTypeId == 53?"selected":""}>广告联盟</option>          
					<option value=54 ${urlAddress.urlTypeId == 54?"selected":""}>其它</option>            
				</select>                                                                               
				</p>   
					<div class="divider"></div>
				<p>
					<label>审核状态：</label>
					<input type="radio" name="urlStatus" value="0" ${urlAddress.urlStatus == 0?"checked":""}/>待审核
					<input type="radio" name="urlStatus" value="1" ${urlAddress.urlStatus == 1?"checked":""}/>审核通过
					<input type="radio" name="urlStatus" value="2" ${urlAddress.urlStatus == 2?"checked":""}/>锁定
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
