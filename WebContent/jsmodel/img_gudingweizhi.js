// JavaScript Document
var strEndFlag = Kuwa_adSourceFile.substring(Kuwa_adSourceFile.lastIndexOf('.'),Kuwa_adSourceFile.length);
var Kuwa_IMG_LINK="";
if (strEndFlag == ".swf"){
	Kuwa_ADurl_t = Kuwa_ADurl_t.replace(/&/g,"|");	//�滻���� & Ϊ |��swf���滻���� | Ϊ  &
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>";
	//Kuwa_IMG_LINK += "<div style='width:70px;height:16px; margin-top:"+(Kuwa_AsizeHigh-16)+"px; margin-left:"+(Kuwa_AsizeWidth-70)+"px; line-height:16px; position:absolute; z-index:9999'><a href='http://www.junnew.com' target='_blank'><img src='http://img.junnew.com/img/logo_jiaobiao.png' border=0 /></a></div>";
//	Kuwa_IMG_LINK += "<OBJECT classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'  WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0'  id='junnew_swf' style='position:absolute;'";
//	Kuwa_IMG_LINK += "<PARAM NAME='movie' VALUE='jMovie.swf' />";
//	Kuwa_IMG_LINK += "<PARAM NAME='quality' VALUE='high' />";
//	Kuwa_IMG_LINK += "<PARAM NAME='wmode' value='transparent' />";
//	Kuwa_IMG_LINK += "<PARAM NAME='src' value='"+Kuwa_adSourceFile+"?jurl="+Kuwa_ADurl_t+"' />";
	Kuwa_IMG_LINK += "<EMBED src='"+Kuwa_adSourceFile+"?jurl="+Kuwa_ADurl_t+"' quality=high wmode=transparent bgcolor=#FFFFFF WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' NAME='junnewMovie' ALIGN='' TYPE='application/x-shockwave-flash' PLUGINSPAGE='http://www.macromedia.com/go/getflashplayer'>";
	Kuwa_IMG_LINK += "</EMBED>";
//	Kuwa_IMG_LINK += "</OBJECT>";
	Kuwa_IMG_LINK += "</div>"
} else if (strEndFlag ==".jpg" || strEndFlag ==".gif" || strEndFlag ==".png"){
	Kuwa_IMG_LINK += "<div style='width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; z-index:9998;' >";
	//Kuwa_IMG_LINK += "<div style='width:70px;height:16px; margin-top:"+(Kuwa_AsizeHigh-16)+"px; margin-left:"+(Kuwa_AsizeWidth-70)+"px; line-height:16px; position:absolute; z-index:9999'><a href='http://www.junnew.com' target='_blank'><img src='http://img.junnew.com/img/logo_jiaobiao.png' border=0 /></a></div>";
	Kuwa_IMG_LINK += "<a href='"+Kuwa_ADurl_t+"' target='_blank'><img src='"+Kuwa_adSourceFile+"' WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' border=0></a>";
	Kuwa_IMG_LINK += "</div>";
}


if (isP4p == 1){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
	//�Ǳ� Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'><div style='width:70px;height:16px; margin-top:"+(Kuwa_AsizeHigh-16)+"px; margin-left:"+(Kuwa_AsizeWidth-70)+"px; line-height:16px; position:absolute; z-index:9999'><a href='http://www.junnew.com' target='_blank'><img src='http://img.junnew.com/img/logo_jiaobiao.png' border=0 /></a></div>"+p4pString+"</div>";
} else if (isP4p == 2){
	Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'>"+p4pString+"</div>";
	//�Ǳ� Kuwa_IMG_LINK += "<div style=' width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; border:none'><div style='width:70px;height:16px; margin-top:"+(Kuwa_AsizeHigh-16)+"px; margin-left:"+(Kuwa_AsizeWidth-70)+"px; line-height:16px; position:absolute; z-index:9999'><a href='http://www.junnew.com' target='_blank'><img src='http://img.junnew.com/img/logo_jiaobiao.png' border=0 /></a></div>"+p4pString+"</div>";
} else if (isP4p == 3){
	Kuwa_IMG_LINK += "<iframe src='"+Kuwa_ADurl_t+"' WIDTH='"+Kuwa_AsizeWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'></iframe> ";
}


document.write(Kuwa_IMG_LINK);
