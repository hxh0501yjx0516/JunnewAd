// JavaScript Document
var strEndFlag = Kuwa_adSourceFile.substring(Kuwa_adSourceFile.lastIndexOf('.'),Kuwa_adSourceFile.length);
var Kuwa_IMG_LINK="";
if (strEndFlag ==".swf"){
	Kuwa_IMG_LINK += "<embed id =kuwa_ce2 src='"+Kuwa_adSourceFile+"'  width='"+Kuwa_AsizeWidth+"' height='"+Kuwa_AsizeHigh+"' border='0' wmode='transparent' ";
	Kuwa_IMG_LINK += "style='position:absolute;'></embed>";
	Kuwa_IMG_LINK += "<a href=\""+Kuwa_ADurl_t+"\" target='_blank'>" ;
	Kuwa_IMG_LINK += "<div style=\"position:relative;filter:alpha(opacity=0);opacity:0;left:0;top:0;background:#CDEAF6;width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px;z-index:10;cursor:pointer\"></div></a>" ;
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


document.write("<div id='Kuwa_right' style='width:"+Kuwa_AsizeWidth+"px;height:"+Kuwa_AsizeHigh+"px; z-index:9999; position:absolute; top:0; left:0; visibility:visible' >");
//document.write("<div style='width:13px;height:17px;' ><img src='http://img.junnew.com/img/msgClose.gif' align='top' style='CURSOR: pointer; margin-left:"+(Kuwa_AsizeWidth-13)+"px; display:inline'  onclick='kuwa_closeDiv()' border=0/></div>");
document.write("<div>"+Kuwa_IMG_LINK+"</div>");
document.write("</div>")

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