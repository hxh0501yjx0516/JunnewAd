// JavaScript Document
var strEndFlag = Kuwa_adSourceFile.substring(Kuwa_adSourceFile.lastIndexOf('.'),Kuwa_adSourceFile.length);
var Kuwa_IMG_LINK="";
if (strEndFlag ==".swf"){
	Kuwa_IMG_LINK = "<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' width='"+Kuwa_AsizeWidth+"px' height='"+Kuwa_AsizeHigh+"px'><param name='movie' value='"+Kuwa_adSourceFile+"' /><param name='wmode' value='transparent'><param name='quality' value='high' /><embed wmode='transparent' src='"+Kuwa_adSourceFile+"' quality='high' pluginspage='http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash' type='application/x-shockwave-flash' width='"+Kuwa_AsizeWidth+"' height='"+Kuwa_AsizeHigh+"'></embed></object>";
} else if (strEndFlag ==".jpg" || strEndFlag ==".gif"){
	Kuwa_IMG_LINK = "<a href='"+Kuwa_ADurl_t+"' target='_blank'><img src='"+Kuwa_adSourceFile+"' WIDTH="+Kuwa_AsizeWidth+" HEIGHT="+Kuwa_AsizeHigh+" border=0></a>";
} else {
	Kuwa_IMG_LINK = "<iframe src='"+Kuwa_ADurl_t+"' WIDTH="+Kuwa_AsizeWidth+" HEIGHT="+Kuwa_AsizeHigh+" frameborder='0' scrolling='no' marginheight='0' marginwidth='0'></iframe> "
}


document.write("<div id='Kuwa_left' style='width:"+Kuwa_AsizeWidth+"px;height:"+(Kuwa_AsizeHigh+17)+"px; z-index:1000' >");
document.write("<div style='width:13px;height:17px; text-align:left' ><img src='http://tp.junyilm.com/IndexAD/msgClose.gif' align='top' style='CURSOR: pointer;'  onclick='kuwa_closeDiv()' border=0/></div>");
document.write("<div style='width:13px;height:17px;' >"+Kuwa_IMG_LINK+"</div>");
document.write("</div>");


function scrollx(p){ 
var d = document,dd = d.documentElement,db = d.body,w = window,o = d.getElementById(p.id),ie6 = /msie 6/i.test(navigator.userAgent),style,timer; 
if(o){ 
o.style.cssText +=";position:"+(p.f&&!ie6?'fixed':'absolute')+";"+(p.l==undefined?'right:0;':'left:'+p.l+'px;')+(p.t!=undefined?'top:'+p.t+'px':'bottom:0'); 
if(p.f&&ie6){ 
o.style.cssText +=';left:expression(documentElement.scrollLeft + '+(p.l==undefined?dd.clientWidth-o.offsetWidth:p.l)+' + "px");top:expression(documentElement.scrollTop +'+(p.t==undefined?dd.clientHeight-o.offsetHeight:p.t)+'+ "px" );'; 
dd.style.cssText +=';background-image: url(about:blank);background-attachment:fixed;'; 
}else{ 
if(!p.f){ 
w.onresize = w.onscroll = function(){ 
clearInterval(timer); 
timer = setInterval(function(){ 
//˫ѡ��Ϊ���޸�chrome ��xhtml����ʱdd.scrollTopΪ 0 
var st = (dd.scrollTop||db.scrollTop),c; 
c = st - o.offsetTop + (p.t!=undefined?p.t:(w.innerHeight||dd.clientHeight)-o.offsetHeight); 
if(c!=0){ 
o.style.top = o.offsetTop + Math.ceil(Math.abs(c)/10)*(c<0?-1:1) + 'px'; 
}else{ 
clearInterval(timer); 
} 
},10) 

} 

} 
} 
} 
} 
scrollx({ 
id:'Kuwa_left',
l:0
}) 
//scrollx({ 
//id:'bb', 
//l:0, 
//t:200, 
//f:1 
//}) 
/* 
id ��Ҫ���������ݵ�id 
l ������λ�� ��дΪ�����ұ� 
t ��Ҫ����ҳ����Ǹ�λ��Ĭ�������ŵױ� 0�����Ŷ��� 
f 1��ʾ�̶� ��д����0��ʾ���� 
*/ 
function kuwa_closeDiv() { 
	var Kuwa_left = document.getElementById('Kuwa_left');
  	Kuwa_left.style.visibility='hidden'; 
}
