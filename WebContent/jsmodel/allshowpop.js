// JavaScript Document

var junyilmDefaultUrl = new Array(0); 
junyilmDefaultUrl[0] = Kuwa_ADurl_t;

var cookie_time = 0*60*60;
var showimg=false;
if(!chk_cookie('rest')){ 
	for (index=0;index < junyilmDefaultUrl.length;index++) {
        popup_cookie_name = "j_n_w_"+index;
        if (!chk_cookie(popup_cookie_name)) {
                showimg=true;
                break;
        }
   }
}


if(showimg){
	var then = new Date();
	then.setTime(then.getTime() + 0*60*1000);  //
	document.cookie='rest=1;expires='+ then.toGMTString()+';path=/;';
    var gotourl=junyilmDefaultUrl[index];
    var popup_cookie_name = "j_n_w_"+index;
	var ptype=1;
	var state=0;
;(function(){
	var d=navigator.userAgent;
	var a={};
	a.ver={
		ie:/MSIE/.test(d),
		ie6:!/MSIE 7\.0/.test(d)&&/MSIE 6\.0/.test(d)&&!/MSIE 8\.0/.test(d),
		tt:/TencentTraveler/.test(d),
		i360:/360SE/.test(d),
		sogo:/; SE/.test(d),
		gg:window.google&&window.chrome,
		_v1:'<object id="p01" width="0" height="0" classid="CLSID:6BF5'+'2A52-394'+'A-1'+'1D3-B15'+'3-00'+'C04F'+'79FAA6"></object>',
		_v2:'<object id="p02" style="position:absolute;left:1px;top:1px;width:1px;height:1px;" classid="clsid:2D'+'360201-FF'+'F5-11'+'d1-8D0'+'3-00A'+'0C95'+'9BC0A"></object>'
	};
	if(a.ver.ie||a.ver.tt){
		document.write(a.ver._v1);
		document.write(a.ver._v2);
		}
	a.fs=null;a.fdc=null;a.timeid=0;a.first=1;a.url='';a.w=0;a.h=0;
	a.init=function(){
		try{
			if(typeof document.body.onclick=="function"){
				a.fs=document.body.onclick;document.body.onclick=null
				}
			if(typeof document.onclick=="function"){
				if(document.onclick.toString().indexOf('clickpp')<0){
					a.fdc=document.onclick;document.onclick=function(){
						a.clickpp(a.url,a.w,a.h)
						}
					}
				}
		}catch(q){}
	};
	a.donepp=function(c,g){
		if (g==1 && (!a.ver.i360 && a.ver.ie6))	return;
		if (state)	return;
		try{
			document.getElementById("p01").launchURL(c);state=1;upcookie(popup_cookie_name,cookie_time)
		}catch(q){}
	};
	a.clickpp=function(c,e,f){
		a.open(c,e,f);clearInterval(a.timeid);document.onclick=null;
		if(typeof a.fdc=="function") try{document.onclick=a.fdc}catch(q){}
		if(typeof a.fs=="function") try{document.body.onclick=a.fs}catch(q){}
	}
	a.open=function(c,e,f){
		if (state)	return;
		a.url=c;a.w=e;a.h=f;
		if (a.timeid==0) a.timeid=setInterval(a.init,100);
		var b='height='+f+',width='+e+',left=0,top=0,toolbar=yes,location=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes';
		var j='window.open("'+c+'", "_blank", "'+b+'")';
		var m=null;
		try{m=eval(j)}catch(q){}
		if(m && !(a.first && a.ver.gg)){
			if (ptype!=-1){m.focus();}else{m.blur();window.focus();}
			state=1;upcookie(popup_cookie_name,cookie_time);
			if(typeof a.fs=="function")	try{document.body.onclick=a.fs}catch(q){}
			clearInterval(a.timeid);
		}else{
			var i=this,	j=false;
			if(a.ver.ie||a.ver.tt){
				document.getElementById("p01");document.getElementById("p02");
				setTimeout(function(){
						var obj=document.getElementById("p02");
						if (state || !obj)	return;	
						try{
							var wPop=obj.DOM.Script.open(c,"_blank",b);
							if (wPop){
								if (ptype!=-1){wPop.focus();}else{wPop.blur();window.focus();}
								state=1;upcookie(popup_cookie_name,cookie_time);
							}else if (a.ver.sogo){state=1;upcookie(popup_cookie_name,cookie_time);}
						}catch(q){}},200);
			}
			if (a.first){
				a.first=0;
				try{if(typeof document.onclick=="function") a.fdc=document.onclick}catch(p){}
				document.onclick=function(){i.clickpp(c,e,f)};
				if (a.ver.ie){
					if (window.attachEvent)	window.attachEvent("onload", function(){i.donepp(c,1);});
					else if (window.addEventListener) window.addEventListener("load", function(){i.donepp(c,1);},true);
					else window.onload=function(){i.donepp(c,1);};
				}
			}
		}
	};	
	window.popup=a;
})();
	popup.open(gotourl, window.screen.width, window.screen.height);
}


function chk_cookie(Name) {
	var search = Name + "="
	var returnvalue = "";
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search)
		if (offset != -1) {
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1)
				end = document.cookie.length;
			returnvalue=unescape(document.cookie.substring(offset, end));
		}
	} 
	return returnvalue;
}



function setcookie(cName,cExpires)
{
        var cookie_time;
        try
        {
                cookie_time = parseFloat(cExpires) * 1;
        }
        catch(e)
        {
                cookie_time = 60*60;
        }
        if(isNaN(cookie_time))
                cookie_time = 60*60;
        var then = new Date();
        then.setTime(then.getTime() + cookie_time*0);
        document.cookie=cName+'=1;expires='+ then.toGMTString()+';path=/;';
}


function upcookie(cname,ctime){
	setcookie(cname,ctime);
}



	/* other open code
	var u = "6BF52A52-394A-11D3-B153-00C04F79FAA6";
    var popURL = 'http://www.baidu.com';

    //function ext()
    //{
    //    iie.launchURL(popURL);
    //}

    function brs()
    {
        document.body.innerHTML+="<object id=iie width=0 height=0 classid='CLSID:"+u+"'></object>";
		iie.launchURL(popURL);
    }
	

    eval("window.attachEvent('onload',brs);");
    //eval("window.attachEvent('onunload',ext);");
    */