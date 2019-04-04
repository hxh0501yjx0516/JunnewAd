<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ include file="/util/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>login.do?action=login#user">
    
    <title>北京中润无线广告有限公司</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- <link href="images/jnico.ico" rel="SHORTCUT ICON" /> -->
<link href="themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="themes/css/core.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script src="javascripts/speedup.js" type="text/javascript"></script>
<script src="javascripts/jquery-1.3.2.js" type="text/javascript"></script>
<script src="javascripts/jquery.cookie.js" type="text/javascript"></script>
<script src="javascripts/jquery.validate.js" type="text/javascript"></script>
<script src="javascripts/jquery.bgiframe.js" type="text/javascript"></script>
<script src="xheditor/xheditor-zh-cn.min.js" type="text/javascript"></script>

<script src="bin/dwz.min.js" type="text/javascript"></script>
<script src="javascripts/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login.html",
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
		}
	});
});
</script>
  </head>
  
  <body>
   	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a href="#" style=" font-size:28px; line-height:50px; font-weight:bold; color:white">骏易传媒管理系统</a>
				<ul class="nav">
					<li><a href="${pageContext.request.contextPath}/users.do?action=setPwd" target="dialog">修改密码</a></li>
					<!--<li><a href="#">反馈</a></li>-->
					<li><a href="${pageContext.request.contextPath }/login.jsp" >退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<!--<li class="t1" theme="default"><div class="selected">蓝色</div></li>
					<li class="t2" theme="green"><div>绿色</div></li>
					
					<li class="t3" theme="red"><div>红色</div></li>
					<li class="t4" theme="purple"><div>紫色</div></li>
					
					<li class="t5" theme="silver"><div>银色</div></li>-->

				</ul>
			</div>
		</div>
		
		<div id="leftside">
		<div id="sidebar_s" style="display:none;">
			<div class="collapse">
				<div class="toggleCollapse"><div></div></div>
			</div>
		</div>
		<div id="sidebar">
			<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
			<div class="accordion">
				
				<div class="accordionHeader">
					<h2><span>Folder</span>界面</h2>
				</div>
				
				<div class="accordionContent">
					<ul class="tree treeFolder collapse">
					<c:forEach items="${mlist}" var="l">
						<li><a href="#" >${l.resname}</a>
						
						<ul>
						<c:forEach items="${cmlist}" var="c">
						
						<c:if test="${l.id==c.pid}">
							
							<li><a href="${pageContext.request.contextPath }${c.resurl }" target="navTab" rel="${c.resname }">${c.resname }</a></li>
							
						</c:if>
						</c:forEach>
						</ul>
						</li>
					</c:forEach>
					</ul>
				</div>
				
				
			</div>
		</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="#"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:void(0)">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent">
					<div>
						<div class="accountInfo">
							
							
							<p><span>骏易传媒管理系统</span></p>
							<p>登录名：${user.username }</p>
						</div>
						
						<div class="pageFormContent" layoutH="80" style="margin-right:0">

	
	<h2>2011-11-24更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、数据执行：数据核对 -- 数据加注颜色标识 ，以区别数据状态
						         数据提交成功/失败提示，以避免数据遗漏
				 数据报表 -- 数据加注颜色标识 ，以区别数据状态
	2、媒介管理：数据报表 -- 数据加注颜色标识 ，以区别数据状态
	</pre>				
	<div class="divider"></div>
	
	<h2>2011-11-03更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、媒介管理：数据报表添加表列
	</pre>				
	<div class="divider"></div>
	
	<h2>2011-10-31更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、数据执行：修正添加创意丢失http://的情况
	</pre>				
	<div class="divider"></div>

	<h2>2011-10-28更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、媒介管理：投放列表--添加投放流量波动警示（以IP列数据计算）
	        注：昨天数据与历史数据对比，以产生昨天数据相对于历史数据的波动情况。
	   	       上升或下降30%以内的数据波动为正常波动，绿灯警示；
	   	       上升或下降30%以外的数据波动为异常波动，红灯警示；
	   	       点击警示灯可查看流量详情。
	2、AE文件管理：完成并更新。
	</pre>				
	<div class="divider"></div>	
	
	<h2>2011-10-21更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、站长管理：添加域名黑名单模块
	</pre>				
	<div class="divider"></div>	
					
	<h2>2011-10-19更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、数据执行：添加数据报表汇总
	2、数据执行：数据报表修正
	3、财务管理：结算修正
	</pre>				
	<div class="divider"></div>
							
						
	<h2>2011-10-14更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、AE文件管理库：新建项目可自定义所属时间（年月）
	2、AE文件管理库：同名文件可多次覆盖上传
	3、AE文件管理库：添加详细资源目录树
	</pre>				
	<div class="divider"></div>
	
	<h2>2011-10-11更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、AE文件管理库：整合入后台
	</pre>				
	<div class="divider"></div>
	
	<h2>2011-10-10更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、媒介管理：报表导出修正
	</pre>				
	<div class="divider"></div>
	
	<h2>2011-10-08更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、媒介管理：添加站长关键字查询
	</pre>				
	<div class="divider"></div>
													
	<h2>2011-09-27更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、站长后台：返回数据查看
	</pre>				
	<div class="divider"></div>
													
	<h2>2011-09-26更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、数据报表：媒介报表分页修正
	</pre>				
	<div class="divider"></div>
	
	<h2>2011-09-22更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、数据统计：添加站长关键字查询
	</pre>				
	<div class="divider"></div>
	
	<h2>2011-09-20更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、数据统计：分页修正
	</pre>				
	<div class="divider"></div>	

	<h2>2011-09-14更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、数据上传：核对列修正
	2、数据核对：汇总数据修正
	3、数据核对：佣金算法修正
	</pre>				
	<div class="divider"></div>
	
	<h2>2011-09-12更新：</h2>
	<pre style="margin:5px;line-height:1.4em">
	1、站长列表：添加新注册站长分配查询及分配操作
	</pre>	

						
						
						</div>

					</div>
				</div>
			</div>
		</div>

		<div id="taskbar" style="left:0px; display:none;">
			<div class="taskbarContent">
				<ul></ul>
			</div>
			<div class="taskbarLeft taskbarLeftDisabled" style="display:none;">taskbarLeft</div>
			<div class="taskbarRight" style="display:none;">taskbarRight</div>
		</div>
		<div id="splitBar"></div>
		<div id="splitBarProxy"></div>
	</div>
	
	<div id="footer">Copyright &copy; 2010 </div>

<!--拖动效果-->
	<div class="resizable"></div>
<!--阴影-->
	<div class="shadow" style="width:508px; top:148px; left:296px;">
		<div class="shadow_h">
			<div class="shadow_h_l"></div>
			<div class="shadow_h_r"></div>
			<div class="shadow_h_c"></div>
		</div>
		<div class="shadow_c">
			<div class="shadow_c_l" style="height:296px;"></div>
			<div class="shadow_c_r" style="height:296px;"></div>
			<div class="shadow_c_c" style="height:296px;"></div>
		</div>
		<div class="shadow_f">
			<div class="shadow_f_l"></div>
			<div class="shadow_f_r"></div>
			<div class="shadow_f_c"></div>
		</div>
	</div>
	<!--遮盖屏幕-->
	<div id="alertBackground" class="alertBackground"></div>
	<div id="dialogBackground" class="alertBackground"></div>

	<div id='background' class='background'></div>
	<div id='progressBar' class='progressBar'>数据加载中，请稍等...</div>

  </body>
</html>
