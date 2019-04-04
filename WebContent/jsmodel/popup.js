// JavaScript Document
var targetUrl = Kuwa_ADurl_t;
	
var Sys = {};
var ua = navigator.userAgent.toLowerCase();

if (window.ActiveXObject){
	try{
		window.external;
		Sys.sogou = ua.match(/msie ([\d.]+)/)[1];
	} catch (e){
		Sys.ie = ua.match(/msie ([\d.]+)/)[1];
	}
} else if (document.getBoxObjectFor){
	//old firefox
    Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1];
} else if (window.MessageEvent && !document.getBoxObjectFor){
    try{
    	window.external.max_version;
    	Sys.maxthon = ua.match(/maxthon\/([\d.]+)/)[1];
    } catch (e){
    	if (ua.indexOf("firefox") > 0){
    		//new firefox
    		Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1];
    	} 
    	if (ua.indexOf("chrome") > 0) {
    		Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1];
    	}
    }
} else if (window.opera){
    Sys.opera = ua.match(/opera.([\d.]+)/)[1];
} else if (window.openDatabase){
    Sys.safari = ua.match(/version\/([\d.]+)/)[1];
}  
    
if (Sys.chrome ||	Sys.firefox || Sys.opera || Sys.safari){
	hitPopup(targetUrl);
} else if (Sys.sogou || Sys.maxthon){
	hiddenPopup(targetUrl);
} else {
	autoPopup(targetUrl);
}

var cookiesName = "JunnewHitPopupCookies";
var cookiesValue = "yes";
var openStep = 5;		//mintues

function autoPopup(url){
	var _autoPopup = window.open("about:blank","_blank","width="+ screen.width + ", height=" + screen.height);
	if (!_autoPopup){
		hitPopup(url);
	} else {
		_autoPopup.blur();
		_autoPopup.opener.focus();
		_autoPopup.location = url;
	}
}

function hitPopup(url){
	document.onclick=function(event){ 
		var e=event||window.event ;
		var obj =e.srcElement||e.target ;
		if(obj.nodeName=="BODY" || obj.nodeName=="HTML"){
			if(readJC(cookiesName) == null){
				setJC(cookiesName,cookiesValue,openStep);
				var _hitPopup = window.open("","","width="+ screen.width + ", height=" + screen.height);
				_hitPopup.blur();
				_hitPopup.opener.focus();
				_hitPopup.location = url;
			}
		}
	};
}

function hiddenPopup(url){
	var _hiddenPopup="";
		_hiddenPopup += "<img src='"+url+"' width='0' height='0' style='display:none' />";
	document.write(_hiddenPopup);
}

function setJC(name,value,openStep){
		  var Minutes = openStep;
		  var exp  = new Date(); 
		  exp.setTime(exp.getTime() + Minutes*60*1000);
		  document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
	
function readJC(name){
	var otherWin = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(otherWin != null) return unescape(otherWin[2]); return null;
}	