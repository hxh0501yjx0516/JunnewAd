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
<link href="css/index.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
//焦点图
function Focus(focusBox,focusPic,focusTxt,focusNum,focusOn){
	$(focusPic).children().eq(0).show();
	$(focusTxt).children().eq(0).show();
	var len = $(focusNum).children().length;
	var index= 0;
	//数字索引显示图片
	$(focusNum).children().mouseover(function(){
		index = $(focusNum).children().index(this);
		showImg(index);
	});
	//鼠标经过图片区域停止播放
	$(focusBox).hover(function(){
		if(palyImg){
			clearInterval(palyImg);
		}
	},function(){
		palyImg = setInterval(function(){
			showImg(index);
			index++;
			if(index==len) {index=0}
		},3000);	
	});
	//自动播放
	var palyImg = setInterval(function(){
		showImg(index);
		index++;
		if(index==len) {index=0}
	},3000);
	function showImg(i){
		$(focusPic).children().eq(i).stop(true,true).fadeIn().siblings().fadeOut();
		$(focusTxt).children().eq(i).stop(true,true).fadeIn().siblings().fadeOut();
		$(focusNum).children().eq(i).addClass(focusOn).siblings().removeClass(focusOn);
	}
}
</script>
	
<title>浮动层</title> 
</head>
<body>
<div class="page" style="overflow:auto">

	<div class="pageContent">
<div id="shop">
<iframe id='j_s_f' name='j_s_f' src='http://img.junnew.com/shop' scrolling='no' frameborder='0' marginheight='0' marginwidth='0' hspace='0' vspace='0' scrolling='no' width='963' height='2858' style=' margin:0 auto'></iframe>
</div>

<div id="shopcss">
<div id="wrap_css">
	<div class="cont_l_css">
        <div class="focusBox_css">
        <ul class="focusPic">
            <li>
            <a href="#" class="fclass"  style=" width:720px; height:247px;line-height:247px">
            1111111
            </a><img src="#" width="0" height="0">
</li>
            <li>
            <a href="#" class="fclass"  style=" width:720px; height:247px;line-height:247px">
            2222222222
            </a><img src="#" width="0" height="0">
</li>
            <li>
            <a href="#" class="fclass"  style=" width:720px; height:247px;line-height:247px">
            333333333
            </a><img src="#" width="0" height="0">
</li>
            <li>
            <a href="#" class="fclass"  style=" width:720px; height:247px;line-height:247px">
            444444444
            </a><img src="#" width="0" height="0">
          </li>
        </ul>
        <div class="focusNum"><span class="on">&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span></div>
        </div>
        
<div class="ct1_css">
        	<a href="#" class="bk_css">11114444444444444441</a>
        	<a href="#" class="bk_css">22222</a>
        	<a href="#" class="bk_css">33333</a>
        </div>
    </div>
	<div class="cont_r_css">
    	<p>
        <a href="#">1111111</a>
        </p>
        <div id="www_zzjs_net" class="zzjs_net">
  <dl class="on">
    <dt><img src="images/1.jpg" alt="" width="13" height="13" /></dt>
    <dd><a href="#" style=" width:223px; height:73px; background:red; font-size:14px; font-weight:bold; color:#FFF; line-height:73px; text-align:center" />111111ddada111111</a></dd>
  </dl>
  <dl>
    <dt><img src="images/2.jpg" alt="" width="13" height="13" /></dt>
    <dd><a href="#" style=" width:223px; height:73px; background:#F00; font-size:14px; font-weight:bold; color:#FFF; line-height:73px; text-align:center" />22222222</a></dd>
  </dl>
  <dl>
    <dt><img src="images/3.jpg" alt="" width="13" height="13" /></dt>
    <dd><a href="#" style=" width:223px; height:73px; background:#F00; font-size:14px; font-weight:bold; color:#FFF; line-height:73px; text-align:center" />333333333</a></dd>
  </dl>
  <dl>
    <dt><img src="images/4.jpg" alt="" width="13" height="13" /></dt>
    <dd><a href="#" style=" width:223px; height:73px; background:#F00; font-size:14px; font-weight:bold; color:#FFF; line-height:73px; text-align:center" />444444444</a></dd>
  </dl>
</div>
    </div>
    <div class="clear"></div>
    
    
    <div class="cont2_css ">
    	<div class="ct_t_css ">
        	<span><a href="#" style=" width:100px; height:35px; line-height:35px; font-size:14px; font-weight:bold; color:#FFF; background:#F00">1111111</a></span>
            <p><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a>
</p>
        </div>
        <strong><a href="#" class="fclass" style=" width:203px; height:203px; line-height:203px;">1234</a></strong>
        <p class="ct_css">
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        </p>
        <span class="rt_css">
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        </span>
    </div>
    
    <div class="cont2_css ">
    	<div class="ct_t_css ">
        	<span><a href="#" style=" width:100px; height:35px; line-height:35px; font-size:14px; font-weight:bold; color:#FFF; background:#F00">1111111</a></span>
            <p><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a>
</p>
        </div>
        <strong><a href="#" class="fclass" style=" width:203px; height:203px; line-height:203px;">1234</a></strong>
        <p class="ct_css">
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        </p>
        <span class="rt_css">
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        </span>
    </div>
    
    <div class="cont2_css ">
    	<div class="ct_t_css ">
        	<span><a href="#" style=" width:100px; height:35px; line-height:35px; font-size:14px; font-weight:bold; color:#FFF; background:#F00">1111111</a></span>
            <p><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a>
</p>
        </div>
        <strong><a href="#" class="fclass" style=" width:203px; height:203px; line-height:203px;">1234</a></strong>
        <p class="ct_css">
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        </p>
        <span class="rt_css">
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        </span>
    </div>
    
    <div class="cont2_css ">
    	<div class="ct_t_css ">
        	<span><a href="#" style=" width:100px; height:35px; line-height:35px; font-size:14px; font-weight:bold; color:#FFF; background:#F00">1111111</a></span>
            <p><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a>
</p>
        </div>
        <strong><a href="#" class="fclass" style=" width:203px; height:203px; line-height:203px;">1234</a></strong>
        <p class="ct_css">
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        </p>
        <span class="rt_css">
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        </span>
    </div>
    
    <div class="cont2_css ">
    	<div class="ct_t_css ">
        	<span><a href="#" style=" width:100px; height:35px; line-height:35px; font-size:14px; font-weight:bold; color:#FFF; background:#F00">1111111</a></span>
            <p><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a>
</p>
        </div>
        <strong><a href="#" class="fclass" style=" width:203px; height:203px; line-height:203px;">1234</a></strong>
        <p class="ct_css">
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        </p>
        <span class="rt_css">
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        </span>
    </div>
    
    <div class="cont2_css ">
    	<div class="ct_t_css ">
        	<span><a href="#" style=" width:100px; height:35px; line-height:35px; font-size:14px; font-weight:bold; color:#FFF; background:#F00">1111111</a></span>
            <p><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a>
</p>
        </div>
        <strong><a href="#" class="fclass" style=" width:203px; height:203px; line-height:203px;">1234</a></strong>
        <p class="ct_css">
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        </p>
        <span class="rt_css">
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        </span>
    </div>
    
    <div class="cont2_css ">
    	<div class="ct_t_css ">
        	<span><a href="#" style=" width:100px; height:35px; line-height:35px; font-size:14px; font-weight:bold; color:#FFF; background:#F00">1111111</a></span>
            <p><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a>
</p>
        </div>
        <strong><a href="#" class="fclass" style=" width:203px; height:203px; line-height:203px;">1234</a></strong>
        <p class="ct_css">
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        </p>
        <span class="rt_css">
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        </span>
    </div>
    
    <div class="cont2_css ">
    	<div class="ct_t_css ">
        	<span><a href="#" style=" width:100px; height:35px; line-height:35px; font-size:14px; font-weight:bold; color:#FFF; background:#F00">1111111</a></span>
            <p><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a><a href="#" class="fclass">1111</a>
</p>
        </div>
        <strong><a href="#" class="fclass" style=" width:203px; height:203px; line-height:203px;">1234</a></strong>
        <p class="ct_css">
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        	<span>
            	<a href="#" class="fclass" style=" width:118px; height:203px; line-height:203px ; color:#FFF">11111111</a>
            </span>
        </p>
        <span class="rt_css">
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        	<a href="#" class="fclass" style=" width:224px; height:63px; line-height:63px;">1111</a>
        </span>
    </div>
    
   
    
<div style=" margin:10px 0;"><img src="images/bgb.jpg" alt="" width="960" height="10" /></div>
    <div class="rmhd">
    	<span></span>
        <p>
        	<a href="#" class="fclass" style=" width:118px; height:87px; line-height:87px">11111</a>
        	<a href="#" class="fclass" style=" width:118px; height:87px; line-height:87px">11111</a>
        	<a href="#" class="fclass" style=" width:118px; height:87px; line-height:87px">11111</a>
        	<a href="#" class="fclass" style=" width:118px; height:87px; line-height:87px">11111</a>
        	<a href="#" class="fclass" style=" width:118px; height:87px; line-height:87px">11111</a>
        	<a href="#" class="fclass" style=" width:118px; height:87px; line-height:87px">11111</a>
        	<a href="#" class="fclass" style=" width:118px; height:87px; line-height:87px">11111</a>
        </p>
    </div>
    <div class="gjc">
    	<div class="gjc_con">
       	  <h3></h3>
            <p></p>
            <ul>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              
                <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3></h3>
            <p></p>
            <ul>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
                <div class="clear"></div>
                <div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3></h3>
            <p></p>
            <ul>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
                <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3></h3>
            <p></p>
            <ul>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
                <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3></h3>
            <p></p>
            <ul>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
                <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3></h3>
            <p></p>
            <ul>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
                <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3></h3>
            <p></p>
            <ul>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
                <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3></h3>
            <p></p>
            <ul>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
              <li><a href="#" class="fclass">1111</a>|</li>
                <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
    </div>
</div>
<script>Focus('.focusBox','.focusPic','.focusTxt','.focusNum','on');</script>
<script language="javascript">
function slide_zzjs_net(e){
   for(var r=document.getElementById(e).getElementsByTagName('dl'),c=clearInterval,i=-1,p=r[0],j; j=r[++i];){
       j.style.height=(i?24:100)+'px';
       j.onmouseover=function(){clearTimeout(j);var i=this;j=setTimeout(function(){if(p!=i)_(p,100,24)(p=i,24,100)},200)};
   }//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
   function _(el,f,t){
       c(el.$1);el.className=f>t?'':'on';
       return el.$1=setInterval(function(){el.style.height=(f+=Math[f>t?'floor':'ceil']((t-f)*.3))+'px';if (f==t)c(el.$1)},10),_
   }
};//欢迎来到站长特x效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
new slide_zzjs_net( "www_zzjs_net");
</script>
<div id="footer"></div>
</div>
	</div>
</div>
</body>
 </html>