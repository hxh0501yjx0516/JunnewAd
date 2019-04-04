// JavaScript Document
function JunnewSetCookie(JunnewCookieName, JunnewCookieValue, JunnewHours) 
{ 
var today = new Date(); 
var expire = new Date(); 
if (JunnewHours == null || JunnewHours == 0) 
JunnewHours = 1; 
expire.setTime(today.getTime() + 1000 * 60 * 60 * 24 * JunnewHours); 
document.cookie = JunnewCookieName + "=" + escape(JunnewCookieValue) + ";expires=" + 
expire.toGMTString(); 
}

function JunnewReadCookie(JunnewCookieName)  
{ 
var JunnewTheCookie = "" + document.cookie; 
var JunnewInd = JunnewTheCookie.indexOf(JunnewCookieName); 
if (JunnewInd == - 1 || JunnewCookieName == "") 
return ""; 
var JunnewInd1 = JunnewTheCookie.indexOf(';', JunnewInd); 
if (JunnewInd1 == - 1) 
JunnewInd1 = JunnewTheCookie.length;
return unescape(JunnewTheCookie.substring(JunnewInd + JunnewCookieName.length + 1, JunnewInd1));
}


var CookiePopCount = JunnewReadCookie("CookiePopCount" + Kuwa_ADurl_t);
if(CookiePopCount != "yes")
	window.open(Kuwa_ADurl_t,'newwindow','height=800,width=1000,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no,status=no');

if(CookiePopCount != "yes")
	JunnewSetCookie("CookiePopCount" + Kuwa_ADurl_t,"yes",0.5);
	