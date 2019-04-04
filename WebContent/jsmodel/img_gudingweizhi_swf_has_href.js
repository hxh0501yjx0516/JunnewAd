// JavaScript Document
var strEndFlag = Kuwa_adSourceFile.substring(Kuwa_adSourceFile.lastIndexOf('.'),Kuwa_adSourceFile.length);
var Kuwa_IMG_LINK="";
if (strEndFlag == ".swf"){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>";
	Kuwa_IMG_LINK += "<EMBED src='"+Kuwa_adSourceFile+"' quality=high wmode=transparent bgcolor=#FFFFFF WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' NAME='junnewMovie' ALIGN='' TYPE='application/x-shockwave-flash' PLUGINSPAGE='http://www.macromedia.com/go/getflashplayer'>";
	Kuwa_IMG_LINK += "</EMBED>";
	Kuwa_IMG_LINK += "</div>";
} else if (strEndFlag ==".jpg" || strEndFlag ==".gif" || strEndFlag ==".png"){
	Kuwa_IMG_LINK += "<div style='width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; z-index:9998;' >";
	Kuwa_IMG_LINK += "<a href='"+Kuwa_ADurl_t+"' target='_blank'><img src='"+Kuwa_adSourceFile+"' WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' border=0></a>";
	Kuwa_IMG_LINK += "</div>";
}


if (isP4p == 1){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
} else if (isP4p == 2){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
} else if (isP4p == 3){
	Kuwa_IMG_LINK += "<iframe src='"+Kuwa_ADurl_t+"' WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'></iframe> ";
}


document.write(Kuwa_IMG_LINK);
