// JavaScript Document
smart_id = Kuwa_ADurl_t;
open_num = open_num;
open_step = open_step;
junnew_name = "JunnewOpen";

document.documentElement.onclick=function(){junnewTar(junnew_name,smart_id,open_num,open_step)};

function junnewTar(junnew_name,smart_id,open_num,open_step){
	var locationHref = window.location.href;
    var arr = document.cookie.match(new RegExp("(^| )"+junnew_name+"=([^;]*)(;|$)"));
	if (arr == null){
		setOtherWin("junnew_otheropen","yes");
		var junnew_value = "1|" + locationHref;
		setNewWinStep(junnew_name,junnew_value,smart_id,open_step);
	} else {
		var otherWin = readOtherWin("junnew_otheropen");
		if(otherWin == null){
			var realArr = parseInt(unescape(arr[2].split("|")[0]));
			var arr_Length = unescape(arr[2]).toString().replace(/[^\x00-\xff]/g,"**").length;
			var realUrl = unescape(arr[2]).toString().substring(2,arr_Length);		
			if (parseInt(realArr) < open_num){          //realUrl.indexOf(locationHref) < 0
			var junnew_value = parseInt(realArr) + 1 + "|" + realUrl + "|" + locationHref;
			setNewWinStep(junnew_name,junnew_value,smart_id,open_step);
			}
		}
	}
}

function setNewWinStep(name,value,smart_id,open_step){		//������ַ��Ƶ���趨
	  var Minutes = open_step; // Ƶ�ʼ��ʱ�䣬��λ������
	  var exp  = new Date(); 
	  exp.setTime(exp.getTime() + Minutes*60*1000);
	  document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	  window.open(smart_id);	//����
}

function setOtherWin(name,value){
	  var Minutes = 0; // Ƶ�ʼ��ʱ�䣬��λ������
	  var exp  = new Date(); 
	  exp.setTime(exp.getTime() + Minutes*60*1000);
	  document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function readOtherWin(name){
	var otherWin = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(otherWin != null) return unescape(otherWin[2]); return null;
}
