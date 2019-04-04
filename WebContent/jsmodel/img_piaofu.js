// JavaScript Document
var speed=3;	
var hideafter=0 
var isie=0; 

var strEndFlag = Kuwa_adSourceFile.substring(Kuwa_adSourceFile.lastIndexOf('.'),Kuwa_adSourceFile.length);
var Kuwa_IMG_LINK="";
if(strEndFlag ==".swf"){
	Kuwa_IMG_LINK = "<div id=kuwa_c style=\" width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px;background-color:#000;clear:both;\">";
	Kuwa_IMG_LINK += "<a href=\""+Kuwa_ADurl_t+"\" target='_blank'>" ;
	Kuwa_IMG_LINK += "<embed id =kuwa_ce2 src='"+Kuwa_adSourceFile+"'  width='"+Kuwa_AsizeWidth+"' height='"+Kuwa_AsizeHigh+"' border='0' wmode='transparent' ";
	Kuwa_IMG_LINK += "style='position:absolute;'></embed>";
	Kuwa_IMG_LINK += "<div style=\"position:relative;filter:alpha(opacity=0);-moz-opacity:0;left:0;top:0;background:#CDEAF6;width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px;z-index:10;cursor:pointer;clear:both;\"></div></a><div>" ;
} else if(strEndFlag ==".jpg" || strEndFlag ==".gif"){
	Kuwa_IMG_LINK = "<a href='"+Kuwa_ADurl_t+"' target='_blank'><img src='"+Kuwa_adSourceFile+"' WIDTH="+Kuwa_AsizeWidth+" HEIGHT="+Kuwa_AsizeHigh+" border=0></a>";
}

if (isP4p == 1){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
} else if (isP4p == 2){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
} else if (isP4p == 3){
	Kuwa_IMG_LINK += "<iframe src='"+Kuwa_ADurl_t+"' WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'></iframe> ";
}

if(window.navigator.appName=="Microsoft Internet Explorer"&&window.navigator.appVersion.substring(window.navigator.appVersion.indexOf("MSIE")+5,window.navigator.appVersion.indexOf("MSIE")+8)>=5.5) { 
isie=1; 
} 
else { 
isie=0; 
} 

   if(isie){ 
      var preloadit=new Image() 
      preloadit.src=Kuwa_adSourceFile 
   } 


function pop() { 
if(isie) { 
x=x+dx;y=y+dy; 
oPopup.show(x, y, Kuwa_AsizeWidth, Kuwa_AsizeHigh); 
if(x+Kuwa_AsizeWidth+5>screen.width) dx=-dx; 
if(y+Kuwa_AsizeHigh+5>screen.height) dy=-dy; 
if(x<0) dx=-dx; 
if(y<0) dy=-dy; 
startani=setTimeout("pop();",50); 
} 
} 

function dismisspopup(){ 
clearTimeout(startani) 
oPopup.hide() 
} 

function dowhatH(){ 
if (Kuwa_ADurl_t=="dismiss") 
dismisspopup() 
else 
window.open(Kuwa_ADurl_t);
} 


if(isie) { 
var x=0,y=0,dx=speed,dy=speed; 
var oPopup = window.createPopup(); 
var oPopupBody = oPopup.document.body; 
oPopupBody.style.cursor="hand" ;
oPopupBody.innerHTML = '<IMG SRC="'+preloadit.src+'">'; 
oPopup.document.body.onmouseover=new Function("clearTimeout(startani)") ;
oPopup.document.body.onmouseout=pop ;
oPopup.document.body.onclick=dowhatH ;
pop(); 

if (hideafter>0) 
setTimeout("dismisspopup()",hideafter*1000) 
} 
