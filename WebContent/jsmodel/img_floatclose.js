// JavaScript Document
var strEndFlag = Kuwa_adSourceFile.substring(Kuwa_adSourceFile.lastIndexOf('.'),Kuwa_adSourceFile.length);
var Kuwa_IMG_LINK="";

if (strEndFlag ==".swf"){
	Kuwa_ADurl_t = Kuwa_ADurl_t.replace(/&/g,"|");
//	Kuwa_IMG_LINK += "<OBJECT classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'  WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0'  id='junnew_swf' style='position:absolute;'";
//	Kuwa_IMG_LINK += "<PARAM NAME='movie' VALUE='jMovie.swf' />";
//	Kuwa_IMG_LINK += "<PARAM NAME='quality' VALUE='high' />";
//	Kuwa_IMG_LINK += "<PARAM NAME='wmode' value='transparent' />";
//	Kuwa_IMG_LINK += "<PARAM NAME='src' value='"+Kuwa_adSourceFile+"?jurl="+Kuwa_ADurl_t+"' />";
	Kuwa_IMG_LINK += "<EMBED src='"+Kuwa_adSourceFile+"?jurl="+Kuwa_ADurl_t+"' quality=high wmode=transparent bgcolor=#FFFFFF WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' NAME='junnewMovie' ALIGN='' TYPE='application/x-shockwave-flash' PLUGINSPAGE='http://www.macromedia.com/go/getflashplayer'>";
	Kuwa_IMG_LINK += "</EMBED>";
//	Kuwa_IMG_LINK += "</OBJECT>";
} else if (strEndFlag ==".jpg" || strEndFlag ==".gif" || strEndFlag ==".png"){
	Kuwa_IMG_LINK = "<a href='"+Kuwa_ADurl_t+"' target='_blank'><img src='"+Kuwa_adSourceFile+"' WIDTH="+Kuwa_AsizeWidth+" HEIGHT="+Kuwa_AsizeHigh+" border=0></a>";
}

if (isP4p == 1){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
} else if (isP4p == 2){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
} else if (isP4p == 3){
	Kuwa_IMG_LINK += "<iframe src='"+Kuwa_ADurl_t+"' WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'></iframe> ";
}

if (strEndFlag != ""){
	document.write("<div id='Kuwa_right' style='width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; z-index:9998; position:absolute; top:0; left:0; visibility:visible;' >");
	document.write("<div style='width:13px;height:17px; position:absolute; top:0; left:0; z-index:9999; margin-left:"+(Kuwa_AsizeWidth-13)+"px;'><img src='http://img.junnew.com/img/msgClose.gif'  onclick='kuwa_closeDiv()' border=0/></div>");
	//document.write("<div style='width:70px;height:16px; margin-top:"+(Kuwa_AsizeHigh-16)+"px; margin-left:"+(Kuwa_AsizeWidth-70)+"px; line-height:16px; position:absolute; z-index:9999'><a href='http://www.junnew.com' target='_blank'><img src='http://img.junnew.com/img/logo_jiaobiao.png' border=0 /></a></div>");
	document.write(Kuwa_IMG_LINK);
	document.write("</div>")
}
if (isP4p > 0){
	document.write("<div id='Kuwa_right' style='width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; z-index:9998; position:absolute; top:0; left:0; visibility:visible;' >");
	document.write("<div style='width:13px;height:17px; position:absolute; top:0; left:0; z-index:9999'><img src='http://img.junnew.com/img/msgClose.gif' style='CURSOR: pointer; margin-left:"+(Kuwa_AsizeWidth-13)+"px;position:absolute;'  onclick='kuwa_closeDiv()' border=0/></div>");
	//document.write("<div style='width:70px;height:16px; margin-top:"+(Kuwa_AsizeHigh-16)+"px; margin-left:"+(Kuwa_AsizeWidth-70)+"px; line-height:16px; position:absolute; z-index:9999'><a href='http://www.junnew.com' target='_blank'><img src='http://img.junnew.com/img/logo_jiaobiao.png' border=0 /></a></div>");
	document.write(Kuwa_IMG_LINK);
	document.write("</div>")
}


var junyi_Obj = junyiWindowView(); 
document.getElementById("Kuwa_right").style.top=junyi_Obj.height-Kuwa_AsizeHigh + "px";
document.getElementById("Kuwa_right").style.left=junyi_Obj.width-Kuwa_AsizeWidth + "px";
moveR();
function moveR() {
var junyi_Obj = junyiWindowView(); 
document.getElementById("Kuwa_right").style.top=junyi_Obj.top+junyi_Obj.height-Kuwa_AsizeHigh + "px";
document.getElementById("Kuwa_right").style.left=junyi_Obj.left+junyi_Obj.width-Kuwa_AsizeWidth + "px";
setTimeout("moveR();",0)
}

function junyiWindowView() { 
var obj = new Object(); 
	if (document.compatMode == "BackCompat") { 
		obj.width = document.body.clientWidth; 
		obj.height = document.body.clientHeight; 
		obj.left = document.body.scrollLeft; 
		obj.top = document.body.scrollTop; 
	} else if (document.compatMode == "CSS1Compat") {
		obj.width  = document.documentElement.clientWidth; 
		obj.height = document.documentElement.clientHeight; 
		obj.left = document.documentElement.scrollLeft == 0 ? document.body.scrollLeft : document.documentElement.scrollLeft; 
		obj.top = document.documentElement.scrollTop == 0 ? document.body.scrollTop : document.documentElement.scrollTop; 
	  }
return obj; 
}


function kuwa_closeDiv() { 
	var Kuwa_right = document.getElementById('Kuwa_right');
  	Kuwa_right.style.visibility='hidden'; 
}