// JavaScript Document　H5
var strEndFlag = Kuwa_adSourceFile.substring(Kuwa_adSourceFile.lastIndexOf('.'),Kuwa_adSourceFile.length);
var Kuwa_IMG_LINK="";
var newWidth = "100%";

if (strEndFlag == ".swf"){
	Kuwa_ADurl_t = Kuwa_ADurl_t.replace(/&/g,"|");
	Kuwa_IMG_LINK += "<EMBED src='"+Kuwa_adSourceFile+"?jurl="+Kuwa_ADurl_t+"' quality=high wmode=transparent bgcolor=#FFFFFF WIDTH='"+newWidth+"' HEIGHT='"+Kuwa_AsizeHigh+"' NAME='junnewMovie' ALIGN='' TYPE='application/x-shockwave-flash' PLUGINSPAGE='http://www.macromedia.com/go/getflashplayer'>";
	Kuwa_IMG_LINK += "</EMBED>";
} else if (strEndFlag ==".jpg" || strEndFlag ==".gif" || strEndFlag ==".png"){
	Kuwa_IMG_LINK = "<a href='"+Kuwa_ADurl_t+"' target='_blank'><img src='"+Kuwa_adSourceFile+"' WIDTH="+newWidth+" HEIGHT="+Kuwa_AsizeHigh+" border=0></a>";
}

document.write("<div id='Kuwa_right' style='width:"+newWidth+";height:"+Kuwa_AsizeHigh+"px; z-index:9998; position:absolute; position:fixed;left:0;bottom:0; visibility:visible;' >");
document.write("<div style='position:absolute; top:0; right:0; z-index:9999; '><img src='http://img.junnew.com/img/msgClose.gif'  onclick='kuwa_closeDiv()' border=0/></div>");
document.write(Kuwa_IMG_LINK);
document.write("</div>");

function kuwa_closeDiv() { 
	var Kuwa_right = document.getElementById('Kuwa_right');
	Kuwa_right.style.visibility='hidden'; 
	
	setTimeout("kuwa_openDiv()", 10000);
}

function kuwa_openDiv(){
	var Kuwa_right = document.getElementById('Kuwa_right');
	Kuwa_right.style.visibility='visible';
}