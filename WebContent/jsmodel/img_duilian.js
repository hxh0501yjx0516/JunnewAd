// JavaScript Document

var strEndFlag = Kuwa_adSourceFile.substring(Kuwa_adSourceFile.lastIndexOf('.'),Kuwa_adSourceFile.length);
var Kuwa_IMG_LINK="";
if(strEndFlag ==".swf"){
	Kuwa_IMG_LINK += "<embed id=ce2  src='"+Kuwa_adSourceFile+"'  width='"+Kuwa_AsizeWidth+"' height='"+Kuwa_AsizeHigh+"' border='0' wmode='transparent' ";
	Kuwa_IMG_LINK += "style='position:absolute;'></embed><a href=\""+Kuwa_ADurl_t+"\" target='_blank'>";
	Kuwa_IMG_LINK += "<a href=\""+Kuwa_ADurl_t+"\" target='_blank'>";
	Kuwa_IMG_LINK += "<div style=\"position:relative;filter:alpha(opacity=0); opacity:0;left:0;top:0;background:#CDEAF6;width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px;z-index:10;cursor:pointer\"></div></a>" ;
} else if (strEndFlag == ".jpg" || strEndFlag == ".gif"){
	Kuwa_IMG_LINK = "<a href='"+Kuwa_ADurl_t+"' target='_blank'><img src='"+Kuwa_adSourceFile+"' WIDTH="+Kuwa_AsizeWidth+" HEIGHT="+Kuwa_AsizeHigh+" border=0></a>";
}

if (isP4p == 1){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
} else if (isP4p == 2){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
} else if (isP4p == 3){
	Kuwa_IMG_LINK += "<iframe src='"+Kuwa_ADurl_t+"' WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'></iframe> ";
}

function kuwa_closeDiv(div) { 
	var kuwa_mydiv= document.getElementById(div);
  	kuwa_mydiv.style.visibility='hidden'; 
}


document.write("<div id='AdLayer1' style=' position: fixed;_position:absolute; visibility:visible;z-index:100;top:100px; _top:expression(eval(document.documentElement.scrollTop+100)); left:5px'><img src='http://img.junnew.com/img/msgClose.gif' style='position:absolute; top:0; left:"+(Kuwa_AsizeWidth-13)+"px; cursor:pointer; z-index:99' onclick=kuwa_closeDiv('AdLayer1')>"+Kuwa_IMG_LINK+"</div>"
+"<div id='AdLayer2' style=' position: fixed; _position:absolute;  visibility:visible;z-index:100;top:100px; _top:expression(eval(document.documentElement.scrollTop+100)); right:5px'><img src='http://img.junnew.com/img/msgClose.gif'  style='position:absolute; top:0; left:"+(Kuwa_AsizeWidth-13)+"px; cursor:pointer; z-index:99' onclick=kuwa_closeDiv('AdLayer2')>"+Kuwa_IMG_LINK+"</div>");