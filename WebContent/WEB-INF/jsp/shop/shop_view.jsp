<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>购物网</title>
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
		//var parentUrl = window.location.search;//document.location.href
		//var j_u=queryString(parentUrl,"parentUrl");
		//var mid = queryString(parentUrl,"mid"); 
		//obj.href=encodeURI(starUrl+"?mid="+mid+"&j_u="+encodeURIComponent(j_u)+"&t_u="+encodeURIComponent(target)+"&fid="+fid+"&sid="+sid+"&shopId="+shopId);
		obj.href="${pageContext.request.contextPath}/shop.do?action=edit&source=view&shopid="+shopId;
	}
	function queryString(str, paraname) {
    var sValue = str.match(new RegExp("[?&]" + paraname + "=([^&]*)(&?)", "i"));
    if (sValue ? sValue[1] : sValue == null)
        return sValue ? sValue[1] : sValue;
}
</script>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
<c:if test="${fClassList != null}">
  <div class="page">
	<div class="pageContent">
	<div class="pageFormContent" layoutH="0">
<div id="wrap_bak" >
	<div class="cont_l">
        <div class="focusBox">
        <ul class="focusPic">
        <c:forEach items="${fClassList[0].shopSclassList[0].shopList}" var="shop">
         <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" width="720" height="247" alt="" /></a></li>
        </c:forEach>
        </ul>
        <div class="focusNum"><c:forEach items="${fClassList[0].shopSclassList[0].shopList}" var="shop" varStatus="s"><c:if test="${s.index ==0}" ><span class="on">${shop.shopName}</span></c:if><c:if test="${s.index != 0}"><span>${shop.shopName}</span></c:if></c:forEach></div>
        </div>
        <!-- 大1小2,店面推荐 -->
        <div class="ct1">
        <c:forEach items="${fClassList[0].shopSclassList}" var="sclass" varStatus="s">
				<c:if test="${s.index ==1}" >
					<c:forEach items="${sclass.shopList}" var="shop">
					  	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')" title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="155" height="105" /></a>
					</c:forEach>
				</c:if>
		     </c:forEach>
        </div>
    </div>
	<div class="cont_r" >
	 <!-- 大1小3 -->
    	<p style="height: auto;width: auto;">
    	 <c:forEach items="${fClassList[0].shopSclassList[2].shopList}" var="shop" varStatus="s">
    	 <c:if test="${s.index ==0}" >
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="223" height="205" /></a>
         </c:if>
	     </c:forEach>
        </p>
        <!-- 大1小4 -->
        <div id="www_zzjs_net" class="zzjs_net" >
         <c:forEach items="${fClassList[0].shopSclassList[3].shopList}" var="shop" varStatus="s">
    	 <c:if test="${s.index ==0}" >
        	 <dl class="on">
			    <dt  style="width: 100%;"><img src="images/${s.count}.jpg" alt="" width="13" height="13" />&nbsp;${shop.shopName}</dt>
			    <dd  ><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="223" height="73" /></a></dd>
			  </dl>
         </c:if>
         <c:if test="${s.index !=0}" >
          <dl>
		    <dt style="width: 100%;"><img src="images/${s.count}.jpg" alt="" width="13" height="13" />&nbsp;${shop.shopName}</dt>
		    <dd><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"> <img src="${shop.shopImg}" alt="" width="223" height="73" /></a></dd>
		  </dl>
          </c:if>
	     </c:forEach>
		</div>
    </div>
    <div class="clear"></div>
    <!-- 大2开始-->
    <div class="cont2" style="">
    	<div class="ct_t" style="">
        	<span><a>${fClassList[1].shopFclassName}</a></span>
        	<p style="width: auto;">
        	 <c:forEach items="${fClassList[1].shopSclassList[0].shopList}" var="shop" varStatus="s">
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>
	     </c:forEach>
		</p>
        </div>
        <strong ><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[1].shopSclassList[1].shopList[0].shopUrl}','${fClassList[1].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[1].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[1].shopSclassList[1].shopList[0].shopId}')"   title="${shop.shopName}" target="navTab" rel="editShop"><img src="${fClassList[1].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct" style="display: inline;">
        <c:forEach items="${fClassList[1].shopSclassList[2].shopList}" var="shop" varStatus="s">
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>
				 	￥<fmt:formatNumber value="${shop.shopPrice}" pattern="#,###.###"/>
				</em>
                <b>
                	￥<fmt:formatNumber value="${shop.shopNewPrice}" pattern="#,###.###"/>
                </b>
            </span>
	     	</c:forEach>
        </p>
        <span class="rt" >
         <c:forEach items="${fClassList[1].shopSclassList[3].shopList}" var="shop" varStatus="s">
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        </c:forEach>
        </span>
    </div>
      <!-- 大3开始-->
    <div class="cont2">
    	<div class="ct_t">
        	<span><a>${fClassList[2].shopFclassName}</a></span>
           <p style="width:auto;">
             <c:forEach items="${fClassList[2].shopSclassList[0].shopList}" var="shop" varStatus="s">
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>
	    	 	</c:forEach>
			</p>
        </div>
        <strong><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[2].shopSclassList[1].shopList[0].shopUrl}','${fClassList[2].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[2].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[2].shopSclassList[1].shopList[0].shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${fClassList[2].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <c:forEach items="${fClassList[2].shopSclassList[2].shopList}" var="shop" varStatus="s">
        	<span>
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>
				 	￥<fmt:formatNumber value="${shop.shopPrice}" pattern="#,###.###"/>
				</em>
                <b>
                	￥<fmt:formatNumber value="${shop.shopNewPrice}" pattern="#,###.###"/>
                </b>
            </span>
	     	</c:forEach>
        </p>
        <span class="rt">
         <c:forEach items="${fClassList[2].shopSclassList[3].shopList}" var="shop" varStatus="s">
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        </c:forEach>
        </span>
    </div>
     <!-- 大4开始-->
    <div class="cont2">
    	<div class="ct_t">
    	<span><a>${fClassList[3].shopFclassName}</a></span>
            <p style="width:auto;">
             <c:forEach items="${fClassList[3].shopSclassList[0].shopList}" var="shop" varStatus="s">
        		<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>
	    	 	</c:forEach>
			</p>
        </div>
         
         <strong><a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[3].shopSclassList[1].shopList[0].shopUrl}','${fClassList[3].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[3].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[3].shopSclassList[1].shopList[0].shopId}')"   title="${shop.shopName}" target="navTab" rel="editShop"><img src="${fClassList[3].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <c:forEach items="${fClassList[3].shopSclassList[2].shopList}" var="shop" varStatus="s">
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>
				 	￥<fmt:formatNumber value="${shop.shopPrice}" pattern="#,###.###"/>
				</em>
                <b>
                	￥<fmt:formatNumber value="${shop.shopNewPrice}" pattern="#,###.###"/>
                </b>
            </span>
	     	</c:forEach>
        </p>
        <span class="rt">
         <c:forEach items="${fClassList[3].shopSclassList[3].shopList}" var="shop" varStatus="s">
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</c:forEach>
        </span>
    </div>
    <!-- 大5开始-->
    <div class="cont2">
    	<div class="ct_t">
    	<span><a>${fClassList[4].shopFclassName}</a></span>
            <p style="width:auto;">
             <c:forEach items="${fClassList[4].shopSclassList[0].shopList}" var="shop" varStatus="s">
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>
	    	 	</c:forEach>
			</p>
        </div>
        <strong><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[4].shopSclassList[1].shopList[0].shopUrl}','${fClassList[4].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[4].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[4].shopSclassList[1].shopList[0].shopId}')"   title="${shop.shopName}" target="navTab" rel="editShop"><img src="${fClassList[4].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
         <c:forEach items="${fClassList[4].shopSclassList[2].shopList}" var="shop" varStatus="s">
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>
				 	￥<fmt:formatNumber value="${shop.shopPrice}" pattern="#,###.###"/>
				</em>
                <b>
                	￥<fmt:formatNumber value="${shop.shopNewPrice}" pattern="#,###.###"/>
                </b>
            </span>
	     	</c:forEach>
        </p>
        <span class="rt">
         <c:forEach items="${fClassList[4].shopSclassList[3].shopList}" var="shop" varStatus="s">
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</c:forEach>
        </span>
    </div>
     <!-- 大6开始-->
    <div class="cont2">
    	<div class="ct_t">
    	<span><a>${fClassList[5].shopFclassName}</a></span>
            <p style="width:auto;">
             <c:forEach items="${fClassList[5].shopSclassList[0].shopList}" var="shop" varStatus="s">
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>
	    	 	</c:forEach>
			</p>
        </div>
        <strong><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[5].shopSclassList[1].shopList[0].shopUrl}','${fClassList[5].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[5].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[5].shopSclassList[1].shopList[0].shopId}')"   title="${shop.shopName}" target="navTab" rel="editShop"><img src="${fClassList[5].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
         <c:forEach items="${fClassList[5].shopSclassList[2].shopList}" var="shop" varStatus="s">
        	<span>
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>
				 	￥<fmt:formatNumber value="${shop.shopPrice}" pattern="#,###.###"/>
				</em>
                <b>
                	￥<fmt:formatNumber value="${shop.shopNewPrice}" pattern="#,###.###"/>
                </b>
            </span>
	     	</c:forEach>
        </p>
        <span class="rt">
         <c:forEach items="${fClassList[5].shopSclassList[3].shopList}" var="shop" varStatus="s">
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</c:forEach>
        </span>
    </div>
    <!-- 大7开始-->
    <div class="cont2">
   	  <div class="ct_t">
        	<span><a>${fClassList[6].shopFclassName}</a></span>
           <p style="width:auto;">
            <c:forEach items="${fClassList[6].shopSclassList[0].shopList}" var="shop" varStatus="s">
        		<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>
	    	 	</c:forEach>
			</p>
        </div>
         
        <strong><a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[6].shopSclassList[1].shopList[0].shopUrl}','${fClassList[6].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[6].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[6].shopSclassList[1].shopList[0].shopId}')"   title="${shop.shopName}" target="navTab" rel="editShop"><img src="${fClassList[6].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <c:forEach items="${fClassList[6].shopSclassList[2].shopList}" var="shop" varStatus="s">
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>
				 	￥<fmt:formatNumber value="${shop.shopPrice}" pattern="#,###.###"/>
				</em>
                <b>
                	￥<fmt:formatNumber value="${shop.shopNewPrice}" pattern="#,###.###"/>
                </b>
            </span>
	     	</c:forEach>
        </p>
        <span class="rt">
         <c:forEach items="${fClassList[6].shopSclassList[3].shopList}" var="shop" varStatus="s">
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</c:forEach>
        </span>
    </div>
     <!-- 大8开始-->
    <div class="cont2">
      <div class="ct_t"> 
      <span><a>${fClassList[7].shopFclassName}</a></span>
            <p style="width:auto;">
             <c:forEach items="${fClassList[7].shopSclassList[0].shopList}" var="shop" varStatus="s">
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>
	    	 	</c:forEach>
			</p>
      </div>
     
      <strong><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[7].shopSclassList[1].shopList[0].shopUrl}','${fClassList[7].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[7].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[7].shopSclassList[1].shopList[0].shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${fClassList[7].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
         <c:forEach items="${fClassList[7].shopSclassList[2].shopList}" var="shop" varStatus="s">
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>
				 	￥<fmt:formatNumber value="${shop.shopPrice}" pattern="#,###.###"/>
				</em>
                <b>
                	￥<fmt:formatNumber value="${shop.shopNewPrice}" pattern="#,###.###"/>
                </b>
            </span>
	     	</c:forEach>
        </p>
        <span class="rt">
         <c:forEach items="${fClassList[7].shopSclassList[3].shopList}" var="shop" varStatus="s">
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</c:forEach>
        </span>
         </div>
    <!-- 大9开始-->
    <div class="cont2">
   	 <div class="ct_t"> 
      <span><a>${fClassList[8].shopFclassName}</a></span>
            <p style="width:auto;">
             <c:forEach items="${fClassList[8].shopSclassList[0].shopList}" var="shop" varStatus="s">
        		<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>
	    	 	</c:forEach>
			</p>
      </div>
      
      <strong><a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${fClassList[8].shopSclassList[1].shopList[0].shopUrl}','${fClassList[8].shopSclassList[1].shopList[0].shopFclassId}','${fClassList[8].shopSclassList[1].shopList[0].shopSclassId}','${fClassList[8].shopSclassList[1].shopList[0].shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${fClassList[8].shopSclassList[1].shopList[0].shopImg}" alt="" width="203" height="203" /></a></strong>
        <p class="ct">
        <c:forEach items="${fClassList[8].shopSclassList[2].shopList}" var="shop" varStatus="s">
        	<span>
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="118" height="118" />${shop.shopName}</a>
				<em>
				 	￥<fmt:formatNumber value="${shop.shopPrice}" pattern="#,###.###"/>
				</em>
                <b>
                	￥<fmt:formatNumber value="${shop.shopNewPrice}" pattern="#,###.###"/>
                </b>
            </span>
	     	</c:forEach>
        </p>
        <span class="rt">
        <c:forEach items="${fClassList[8].shopSclassList[3].shopList}" var="shop" varStatus="s">
        	<a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="224" height="63" /></a>
        	</c:forEach>
        </span>
    </div>
    <!-- 大10开始-->
    <div style=" margin:10px 0;"><img src="images/bgb.jpg" alt="" width="960" height="10" /></div>
    <div class="rmhd">
    	<span>${fClassList[9].shopFclassName} <b>POP Events</b></span>
        <p style="height: auto;">
        <c:forEach items="${fClassList[9].shopSclassList[0].shopList}" var="shop" varStatus="s">
        	<a  href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop"><img src="${shop.shopImg}" alt="" width="118" height="58"/><em>${shop.shopName}</em></a>
        	</c:forEach>
        </p>
    </div>
     <!-- 大11开始-->
    <div class="gjc">
    	<div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[0].shopSclassName}</h3>
       	  <c:forEach items="${fClassList[10].shopSclassList[0].shopList}" var="shop" varStatus="s">
            <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[0].shopList)-1}" >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </c:if>
        	</c:forEach>
        	<ul>
        	<c:forEach items="${fClassList[10].shopSclassList[0].shopList}" var="shop" varStatus="s">
        	 <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[0].shopList)-1}" >
        	  <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[0].shopList)-2}" >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a></li>
            </c:if>
             <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[0].shopList)-2}" >
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>|</li>
            </c:if>
        	</c:if>
        	</c:forEach>
            <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[1].shopSclassName}</h3>
       	   <c:forEach items="${fClassList[10].shopSclassList[1].shopList}" var="shop" varStatus="s">
            <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[1].shopList)-1}" >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </c:if>
        	</c:forEach>
            <ul>
           <c:forEach items="${fClassList[10].shopSclassList[1].shopList}" var="shop" varStatus="s">
        	 <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[1].shopList)-1}" >
        	  <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[1].shopList)-2}" >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a></li>
            </c:if>
             <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[1].shopList)-2}" >
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>|</li>
            </c:if>
        	</c:if>
        	</c:forEach>
        	<div class="clear"></div>
                <div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[2].shopSclassName}</h3>
           <c:forEach items="${fClassList[10].shopSclassList[2].shopList}" var="shop" varStatus="s">
            <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[2].shopList)-1}" >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </c:if>
        	</c:forEach>
            <ul>
           <c:forEach items="${fClassList[10].shopSclassList[2].shopList}" var="shop" varStatus="s">
        	 <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[2].shopList)-1}" >
        	  <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[2].shopList)-2}" >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a></li>
            </c:if>
             <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[2].shopList)-2}" >
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>|</li>
            </c:if>
        	</c:if>
        	</c:forEach>
			<div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[3].shopSclassName}</h3>
          <c:forEach items="${fClassList[10].shopSclassList[3].shopList}" var="shop" varStatus="s">
            <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[3].shopList)-1}" >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </c:if>
        	</c:forEach>
            <ul>
           <c:forEach items="${fClassList[10].shopSclassList[3].shopList}" var="shop" varStatus="s">
        	 <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[3].shopList)-1}" >
        	  <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[3].shopList)-2}" >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a></li>
            </c:if>
             <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[3].shopList)-2}" >
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>|</li>
            </c:if>
        	</c:if>
        	</c:forEach>
              <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[4].shopSclassName}</h3>
            <c:forEach items="${fClassList[10].shopSclassList[4].shopList}" var="shop" varStatus="s">
            <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[4].shopList)-1}" >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </c:if>
        	</c:forEach>
            <ul>
           <c:forEach items="${fClassList[10].shopSclassList[4].shopList}" var="shop" varStatus="s">
        	 <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[4].shopList)-1}" >
        	  <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[4].shopList)-2}" >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a></li>
            </c:if>
             <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[4].shopList)-2}" >
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>|</li>
            </c:if>
        	</c:if>
        	</c:forEach>
              <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[5].shopSclassName}</h3>
           <c:forEach items="${fClassList[10].shopSclassList[5].shopList}" var="shop" varStatus="s">
            <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[5].shopList)-1}" >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </c:if>
        	</c:forEach>
            <ul>
           <c:forEach items="${fClassList[10].shopSclassList[5].shopList}" var="shop" varStatus="s">
        	 <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[5].shopList)-1}" >
        	  <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[5].shopList)-2}" >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a></li>
            </c:if>
             <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[5].shopList)-2}" >
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>|</li>
            </c:if>
        	</c:if>
        	</c:forEach>
              <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[6].shopSclassName}</h3>
           <c:forEach items="${fClassList[10].shopSclassList[6].shopList}" var="shop" varStatus="s">
            <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[6].shopList)-1}" >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </c:if>
        	</c:forEach>
            <ul>
           <c:forEach items="${fClassList[10].shopSclassList[6].shopList}" var="shop" varStatus="s">
        	 <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[6].shopList)-1}" >
        	  <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[6].shopList)-2}" >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a></li>
            </c:if>
             <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[6].shopList)-2}" >
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a>|</li>
            </c:if>
        	</c:if>
        	</c:forEach>
              <div class="clear"></div><div class="blank"></div> 
          </ul>
        </div>
        <div class="gjc_con">
       	  <h3>${fClassList[10].shopSclassList[7].shopSclassName}</h3>
            <c:forEach items="${fClassList[10].shopSclassList[7].shopList}" var="shop" varStatus="s">
            <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[7].shopList)-1}" >
            <p><img src="${shop.shopImg}" width="57" height="48" alt="" /></p>
            </c:if>
        	</c:forEach>
            <ul>
           <c:forEach items="${fClassList[10].shopSclassList[7].shopList}" var="shop" varStatus="s">
        	 <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[7].shopList)-1}" >
        	  <c:if test="${s.index == fn:length(fClassList[10].shopSclassList[7].shopList)-2}" >
            <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" target="navTab" rel="editShop">${shop.shopName}</a></li>
            </c:if>
             <c:if test="${s.index != fn:length(fClassList[10].shopSclassList[7].shopList)-2}" >
             <li><a href="javascript:void(0);" onclick="statistics(this,'${starUrl}','${shop.shopUrl}','${shop.shopFclassId}','${shop.shopSclassId}','${shop.shopId}')"  title="${shop.shopName}" rel="editShop">${shop.shopName}</a>|</li>
            </c:if>
        	</c:if>
        	</c:forEach>
              <div class="clear"></div><div class="blank"></div>
          </ul>
        </div>
    </div>
    
</div>
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
</c:if> 
</body>
</html>
