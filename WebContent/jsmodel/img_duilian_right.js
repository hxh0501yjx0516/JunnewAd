// JavaScript Document
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


function initEcAd() {
//document.all.AdLayer1.style.posTop = -200;
//document.all.AdLayer1.style.visibility = 'visible'
document.all.AdLayer2.style.posTop = -200;
document.all.AdLayer2.style.visibility = 'visible'
//MoveLeftLayer('AdLayer1');
MoveRightLayer('AdLayer2');
}
/*function MoveLeftLayer(layerName) {
var x = 5;
var y = 100;
var diff = (document.body.scrollTop + y - document.all.AdLayer1.style.posTop)*.40;
var y = document.body.scrollTop + y - diff;
eval("document.all." + layerName + ".style.posTop = parseInt(y)");
eval("document.all." + layerName + ".style.posLeft = x");
setTimeout("MoveLeftLayer('AdLayer1');", 20);
}
*/
function MoveRightLayer(layerName) {
var x = 5;
var y = 100;
var diff = (document.body.scrollTop + y - document.all.AdLayer2.style.posTop)*.40;
var y = document.body.scrollTop + y - diff;
eval("document.all." + layerName + ".style.posTop = y");
eval("document.all." + layerName + ".style.posRight = x");
setTimeout("MoveRightLayer('AdLayer2');", 20);
}

document.write("<div id=AdLayer2 style='position: absolute;visibility:hidden;z-index:1'>"+Kuwa_IMG_LINK+"</div>");
initEcAd()