<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>购物网</title>
<script type="text/javascript" src="css/jQuery.js"></script><!--jQuery库文件-->

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
	function statistics(obj,starUrl,target,fid,sid,shopId){
		var parentUrl = window.location.search;//document.location.href
		var j_u=queryString(parentUrl,"parentUrl");
		var mid = queryString(parentUrl,"mid"); 
		obj.href=starUrl+"?mid="+mid+"&j_u="+escape(j_u)+"&t_u="+escape(target)+"&fid="+fid+"&sid="+sid+"&shopId="+shopId;
		
	}
	function queryString(str, paraname) {
    var sValue = str.match(new RegExp("[?&]" + paraname + "=([^&]*)(&?)", "i"));
    if (sValue ? sValue[1] : sValue == null)
        return sValue ? sValue[1] : sValue;
}
</script>
<link href="css/index.css" rel="stylesheet" type="text/css" />
</head>

<body>
<#if fClassList?exists>
<div id="wrap">
	<div class="cont_l">
        <div class="focusBox">
        <ul class="focusPic">
			<#list fClassList[0].shopSclassList[0].shopList as shop>
					 <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" width="720" height="247" alt="" /></a></li>
		    </#list>
        </ul>
        <div class="focusNum"><#list fClassList[0].shopSclassList[0].shopList as shop><#if shop_index ==0 ><span class="on">${shop.shopName}</span><#else><span>${shop.shopName}</span></#if></#list></div>
        </div>
        
        <div class="ct1">
        <#list fClassList[0].shopSclassList as sclass>
				<#if sclass_index ==1 >
					<#list sclass.shopList as shop>
					  	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="155" height="105" /></a>
					</#list>
				</#if>
		    </#list>
        </div>
    </div>
	<div class="cont_r">
	 <!-- 大1小3 -->
    	<p>
    	 <#list fClassList[0].shopSclassList[2].shopList as shop>
    	 <#if shop_index ==0 >
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="223" height="205" /></a>
         </#if>
	     </#list>
        </p>
        <!-- 大1小4 -->
        <div id="www_zzjs_net" class="zzjs_net">
        <#list fClassList[0].shopSclassList[3].shopList as shop>
    	 <#if shop_index ==0 >
        	 <dl class="on">
			    <dt><img src="images/${shop_index+1}.jpg" alt="" width="13" height="13" />&nbsp;${shop.shopName}</dt>
			    <dd><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="223" height="73" /></a></dd>
			  </dl>
         <#else>
          <dl>
		    <dt><img src="images/${shop_index+1}.jpg" alt="" width="13" height="13" />&nbsp;${shop.shopName}</dt>
		    <dd><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"> <img src="${shop.shopImg}" alt="" width="223" height="73" /></a></dd>
		  </dl>
         </#if>
	     </#list>
		</div>
    </div>
    <div class="clear"></div>
    <!-- 大2开始-->
    <div class="cont2">
    	<div class="ct_t">
        	<span><a>${fClassList[1].shopFclassName}</a></span>
        	<p>
         <#list fClassList[1].shopSclassList[0].shopList as shop>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>
	     </#list>
		</p>
        </div>
        <strong><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[1].shopSclassList[1].shopList[0].shopUrl}','${fClassList[1].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[1].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[1].shopSclassList[1].shopList[0].shopId}')"  target="_blank"><img src="${fClassList[1].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        	<#list fClassList[1].shopSclassList[2].shopList as shop>
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>￥${shop.shopPrice}</em>
                <b>￥${shop.shopNewPrice}</b>
            </span>
	     	</#list>
        </p>
        <span class="rt">
        <#list fClassList[1].shopSclassList[3].shopList as shop>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        </#list>
        </span>
    </div>
      <!-- 大3开始-->
    <div class="cont2">
    	<div class="ct_t">
        	<span><a>${fClassList[2].shopFclassName}</a></span>
            <p>
            	<#list fClassList[2].shopSclassList[0].shopList as shop>
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>
	    	 	</#list>
			</p>
        </div>
        <strong><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[2].shopSclassList[1].shopList[0].shopUrl}','${fClassList[2].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[2].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[2].shopSclassList[1].shopList[0].shopId}')" target="_blank"><img src="${fClassList[2].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        	<#list fClassList[2].shopSclassList[2].shopList as shop>
        	<span>
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>￥${shop.shopPrice}</em>
                <b>￥${shop.shopNewPrice}</b>
            </span>
	     	</#list>
        </p>
        <span class="rt">
        <#list fClassList[2].shopSclassList[3].shopList as shop>
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        </#list>
        </span>
    </div>
     <!-- 大4开始-->
    <div class="cont2">
    	<div class="ct_t">
    	<span><a>${fClassList[3].shopFclassName}</a></span>
            <p>
            	<#list fClassList[3].shopSclassList[0].shopList as shop>
        		<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>
	    	 	</#list>
			</p>
        </div>
         
         <strong><a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[3].shopSclassList[1].shopList[0].shopUrl}','${fClassList[3].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[3].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[3].shopSclassList[1].shopList[0].shopId}')"  target="_blank"><img src="${fClassList[3].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <#list fClassList[3].shopSclassList[2].shopList as shop>
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>￥${shop.shopPrice}</em>
                <b>￥${shop.shopNewPrice}</b>
            </span>
	     	</#list>
        </p>
        <span class="rt">
        	<#list fClassList[3].shopSclassList[3].shopList as shop>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</#list>
        </span>
    </div>
    <!-- 大5开始-->
    <div class="cont2">
    	<div class="ct_t">
    	<span><a>${fClassList[4].shopFclassName}</a></span>
            <p>
            	<#list fClassList[4].shopSclassList[0].shopList as shop>
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>
	    	 	</#list>
			</p>
        </div>
        <strong><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[4].shopSclassList[1].shopList[0].shopUrl}','${fClassList[4].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[4].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[4].shopSclassList[1].shopList[0].shopId}')"  target="_blank"><img src="${fClassList[4].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <#list fClassList[4].shopSclassList[2].shopList as shop>
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>￥${shop.shopPrice}</em>
                <b>￥${shop.shopNewPrice}</b>
            </span>
	     	</#list>
        </p>
        <span class="rt">
        <#list fClassList[4].shopSclassList[3].shopList as shop>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</#list>
        </span>
    </div>
     <!-- 大6开始-->
    <div class="cont2">
    	<div class="ct_t">
    	<span><a>${fClassList[5].shopFclassName}</a></span>
            <p>
            	<#list fClassList[5].shopSclassList[0].shopList as shop>
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>
	    	 	</#list>
			</p>
        </div>
        <strong><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[5].shopSclassList[1].shopList[0].shopUrl}','${fClassList[5].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[5].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[5].shopSclassList[1].shopList[0].shopId}')"  target="_blank"><img src="${fClassList[5].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <#list fClassList[5].shopSclassList[2].shopList as shop>
        	<span>
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>￥${shop.shopPrice}</em>
                <b>￥${shop.shopNewPrice}</b>
            </span>
	     	</#list>
        </p>
        <span class="rt">
        	<#list fClassList[5].shopSclassList[3].shopList as shop>
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</#list>
        </span>
    </div>
    <!-- 大7开始-->
    <div class="cont2">
   	  <div class="ct_t">
        	<span><a>${fClassList[6].shopFclassName}</a></span>
            <p>
            	<#list fClassList[6].shopSclassList[0].shopList as shop>
        		<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>
	    	 	</#list>
			</p>
        </div>
         
        <strong><a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[6].shopSclassList[1].shopList[0].shopUrl}','${fClassList[6].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[6].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[6].shopSclassList[1].shopList[0].shopId}')"  target="_blank"><img src="${fClassList[6].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <#list fClassList[6].shopSclassList[2].shopList as shop>
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>￥${shop.shopPrice}</em>
                <b>￥${shop.shopNewPrice}</b>
            </span>
	     	</#list>
        </p>
        <span class="rt">
        	<#list fClassList[6].shopSclassList[3].shopList as shop>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</#list>
        </span>
    </div>
     <!-- 大8开始-->
    <div class="cont2">
      <div class="ct_t"> 
      <span><a>${fClassList[7].shopFclassName}</a></span>
            <p>
            	<#list fClassList[7].shopSclassList[0].shopList as shop>
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>
	    	 	</#list>
			</p>
      </div>
     
      <strong><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[7].shopSclassList[1].shopList[0].shopUrl}','${fClassList[7].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[7].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[7].shopSclassList[1].shopList[0].shopId}')" target="_blank"><img src="${fClassList[7].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <#list fClassList[7].shopSclassList[2].shopList as shop>
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>￥${shop.shopPrice}</em>
                <b>￥${shop.shopNewPrice}</b>
            </span>
	     	</#list>
        </p>
        <span class="rt">
        	<#list fClassList[7].shopSclassList[3].shopList as shop>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</#list>
        </span>
         </div>
    <!-- 大9开始-->
    <div class="cont2">
   	 <div class="ct_t"> 
      <span><a>${fClassList[8].shopFclassName}</a></span>
            <p>
            	<#list fClassList[8].shopSclassList[0].shopList as shop>
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>
	    	 	</#list>
			</p>
      </div>
      
      <strong><a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[8].shopSclassList[1].shopList[0].shopUrl}','${fClassList[8].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[8].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[8].shopSclassList[1].shopList[0].shopId}')" target="_blank"><img src="${fClassList[8].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <#list fClassList[8].shopSclassList[2].shopList as shop>
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>￥${shop.shopPrice}</em>
                <b>￥${shop.shopNewPrice}</b>
            </span>
	     	</#list>
        </p>
        <span class="rt">
        	<#list fClassList[8].shopSclassList[3].shopList as shop>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</#list>
        </span>
    </div>
    <!-- 大10开始-->
    <div style=" margin:10px 0;"><img src="images/bgb.jpg" alt="" width="960" height="10" /></div>
    <div class="rmhd">
    	<span>${fClassList[9].shopFclassName} <b>POP Events</b></span>
        <p>
        <#list fClassList[9].shopSclassList[0].shopList as shop>
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank"><img src="${shop.shopImg}" alt="" width="118" height="58"/><em>${shop.shopName}</em></a>
        	</#list>
        </p>
    </div>
     <!-- 大11开始-->
    <div class="gjc">
    	<div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[0].shopSclassName}</h3>
            <#list fClassList[10].shopSclassList[0].shopList as shop>
            <#if shop_index == fClassList[10].shopSclassList[0].shopList?size-1 >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </#if>
        	</#list>
        	<ul>
            <#list fClassList[10].shopSclassList[0].shopList as shop>
            <#if shop_index != fClassList[10].shopSclassList[0].shopList?size-1 >
            <#if shop_index == fClassList[10].shopSclassList[0].shopList?size-2 >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a></li>
            <#else>
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>|</li>
            </#if>
        	</#if>
        	</#list>
            <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[1].shopSclassName}</h3>
       	  <#list fClassList[10].shopSclassList[1].shopList as shop>
            <#if shop_index == fClassList[10].shopSclassList[1].shopList?size-1 >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </#if>
        	</#list>
            <ul>
            <#list fClassList[10].shopSclassList[1].shopList as shop>
            <#if shop_index != fClassList[10].shopSclassList[1].shopList?size-1 >
            <#if shop_index == fClassList[10].shopSclassList[1].shopList?size-2 >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a></li>
            <#else>
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>|</li>
            </#if>
        	</#if>
        	</#list>
        	<div class="clear"></div>
                <div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[2].shopSclassName}</h3>
            <#list fClassList[10].shopSclassList[2].shopList as shop>
            <#if shop_index == fClassList[10].shopSclassList[2].shopList?size-1 >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </#if>
        	</#list>
            <ul>
            <#list fClassList[10].shopSclassList[2].shopList as shop>
            <#if shop_index != fClassList[10].shopSclassList[2].shopList?size-1 >
            <#if shop_index == fClassList[10].shopSclassList[2].shopList?size-2 >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a></li>
            <#else>
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>|</li>
            </#if>
        	</#if>
        	</#list>  
			<div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[3].shopSclassName}</h3>
           <#list fClassList[10].shopSclassList[3].shopList as shop>
            <#if shop_index == fClassList[10].shopSclassList[3].shopList?size-1 >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </#if>
        	</#list>
           <ul>
              <#list fClassList[10].shopSclassList[3].shopList as shop>
             <#if shop_index != fClassList[10].shopSclassList[3].shopList?size-1 >
            <#if shop_index == fClassList[10].shopSclassList[3].shopList?size-2 >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a></li>
            <#else>
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>|</li>
            </#if>
        	</#if>
        	</#list>
              <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[4].shopSclassName}</h3>
            <#list fClassList[10].shopSclassList[4].shopList as shop>
            <#if shop_index == fClassList[10].shopSclassList[4].shopList?size-1 >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </#if>
        	</#list>
            <ul>
              <#list fClassList[10].shopSclassList[4].shopList as shop>
            <#if shop_index != fClassList[10].shopSclassList[4].shopList?size-1 >
            <#if shop_index == fClassList[10].shopSclassList[4].shopList?size-2 >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a></li>
            <#else>
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>|</li>
            </#if>
        	</#if>
        	</#list>
              <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[5].shopSclassName}</h3>
            <#list fClassList[10].shopSclassList[5].shopList as shop>
            <#if shop_index == fClassList[10].shopSclassList[5].shopList?size-1 >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </#if>
        	</#list>
            <ul>
              <#list fClassList[10].shopSclassList[5].shopList as shop>
            <#if shop_index != fClassList[10].shopSclassList[5].shopList?size-1 >
            <#if shop_index == fClassList[10].shopSclassList[5].shopList?size-2 >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a></li>
            <#else>
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>|</li>
            </#if>
        	</#if>
        	</#list>
              <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[6].shopSclassName}</h3>
           <#list fClassList[10].shopSclassList[6].shopList as shop>
            <#if shop_index == fClassList[10].shopSclassList[6].shopList?size-1 >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </#if>
        	</#list>
           <ul>
              <#list fClassList[10].shopSclassList[6].shopList as shop>
             <#if shop_index != fClassList[10].shopSclassList[6].shopList?size-1 >
            <#if shop_index == fClassList[10].shopSclassList[6].shopList?size-2 >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a></li>
            <#else>
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>|</li>
            </#if>
        	</#if>
        	</#list>
              <div class="clear"></div><div class="blank"></div> 
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[7].shopSclassName}</h3>
            <#list fClassList[10].shopSclassList[7].shopList as shop>
            <#if shop_index == fClassList[10].shopSclassList[7].shopList?size-1 >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </#if>
        	</#list>
            <ul>
              <#list fClassList[10].shopSclassList[7].shopList as shop>
            <#if shop_index != fClassList[10].shopSclassList[7].shopList?size-1 >
            <#if shop_index == fClassList[10].shopSclassList[7].shopList?size-2 >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a></li>
            <#else>
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" target="_blank">${shop.shopName}</a>|</li>
            </#if>
        	</#if>
        	</#list>
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
</#if>
</body>
</html>
