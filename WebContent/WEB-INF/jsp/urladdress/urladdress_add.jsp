<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加客服</title> 
<script type="text/javascript">
  function getSelectedText(obj,outObject){
   var strsel = obj.options[obj.selectedIndex].text;
   if(obj.selectedIndex == 0){
   document.getElementById(outObject).value="";
   }else{
   document.getElementById(outObject).value=strsel;
   }
  }
 </script>
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addUrlAddress" method="post" action="${pageContext.request.contextPath }/urladdress.do?action=add&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" id="urlTypeName" name="urlTypeName" />
			<input type="hidden" id="webMasterId" name="webMasterId" value="${webMasterId}"/>
			<input type="hidden" id="webMasterName" name="webMasterName" value="${webMasterName}"/>
			<div class="pageFormContent" layoutH="33">
				
				<h3 class="contentTitle">
							所属网站主：${webMasterName}
						</h3>
				<p>
					<label>域名：</label>
					<input name="url" class="required"  type="text" size="30"  />
				</p>
				<p>
					<label>网站名称 ：</label>
					<input name="urlName"   type="text" size="30"  />
				</p>
				<p>
					<label>ICP：</label>
					<input name="urlIcp" type="text" size="30"  />
				</p>
				<p>
					<label>日IP量 ：</label>
					<input name="urlDayIp"  type="text" size="30" />
				</p>
				<p>
					<label>所属地区 ：</label>
					<input name="urlArea" type="text"  size="30"  />
				</p>
				<p>
					<label>网站类型  ：</label>
					<select name="urlTypeId" class="" onchange="getSelectedText(this,'urlTypeName')">
					<option value="0" selected="selected">请选择类别</option>
					<option value=1>在线音乐</option>
					<option value=2>在线电影</option>
					<option value=3>视频分享</option>
					<option value=4>网络游戏</option>
					<option value=5>单机游戏</option>
					<option value=6>flash小游戏</option>
					<option value=7>QQ娱乐</option>
					<option value=8>文学小说</option>
					<option value=9>社区论坛</option>
					<option value=10>博客网站</option>
					<option value=11>BT下载</option>
					<option value=12>p2p共享</option>
					<option value=13>网吧资源</option>
					<option value=14>电脑网络</option>
					<option value=15>教育培训</option>
					<option value=16>英语培训</option>
					<option value=17>IT培训</option>
					<option value=18>动漫网站</option>
					<option value=19>育儿母婴</option>
					<option value=20>WAP服务</option>
					<option value=21>娱乐综合</option>
					<option value=22>交友网站</option>
					<option value=23>网址导航</option>
					<option value=24>电子杂志</option>
					<option value=25>女性网站</option>
					<option value=26>网上招聘</option>
					<option value=27>网上购物</option>
					<option value=28>地方门户</option>
					<option value=29>门户综合</option>
					<option value=30>科学资讯</option>
					<option value=31>餐饮旅游</option>
					<option value=32>软件下载</option>
					<option value=33>汽车资讯</option>
					<option value=34>服装鞋包</option>
					<option value=35>工商经济</option>
					<option value=36>工艺礼品</option>
					<option value=37>广告营销</option>
					<option value=38>化工产品</option>
					<option value=39>机械工业</option>
					<option value=40>家居家电</option>
					<option value=41>金融证券</option>
					<option value=42>轻工纺织</option>
					<option value=43>日用化妆</option>
					<option value=44>时尚美容</option>
					<option value=45>食品饮料</option>
					<option value=46>体育休闲</option>
					<option value=47>新闻媒体</option>
					<option value=48>休闲娱乐</option>
					<option value=49>医疗保健</option>
					<option value=50>仪器仪表</option>
					<option value=51>造纸印刷</option>
					<option value=52>增值业务</option>
					<option value=53>广告联盟</option>
					<option value=54>其它</option>
				</select>
				</p>
					<div class="divider"></div>
				<p>
					<label>审核状态：</label>
					<input type="radio" name="urlStatus" value="0" />待审核
					<input type="radio" name="urlStatus" value="1" checked="checked"/>审核通过
					<input type="radio" name="urlStatus" value="2" />锁定
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