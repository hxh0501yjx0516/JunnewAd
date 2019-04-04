// JavaScript Document
var cookiesName = "JunnewDownCookies";
var cookiesValue = "yes";
var url = Kuwa_ADurl_t;
var openStep = 720;

document.onclick=function(event){ 
var e=event||window.event 
var obj =e.srcElement||e.target 
if(obj.nodeName=="BODY"||obj.nodeName=="HTML") 
	if(readJC(cookiesName) == null){
		setJC(cookiesName,cookiesValue,url,openStep)
	} else {
		//not to do somthing
	}
} 


function setJC(name,value,smart_id,open_step){
	  var Minutes = open_step;
	  var exp  = new Date(); 
	  exp.setTime(exp.getTime() + Minutes*60*1000);
	  document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	  window.open(smart_id);
}

function readJC(name){
	var otherWin = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(otherWin != null) return unescape(otherWin[2]); return null;
}