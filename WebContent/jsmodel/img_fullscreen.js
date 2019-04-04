// JavaScript Document
   var junnewWidth = Kuwa_AsizeWidth;			//广告宽度
   var junnewHeight = Kuwa_AsizeHigh;			//广告高度
   var junnewImage = Kuwa_adSourceFile;		//图片
   var junnewUrl = Kuwa_ADurl_t;						//跳转地址
   
   var runSpeed = 5;				//收缩速度
   var stopTime = 5000;			//停留时间	5秒
   
    var time = junnewHeight;
    var h = 0;
	//展开
    function addCount()
    {
        if(time>0)
        {
            time--;
            h = h+20;
        }
        else
        {
            return;
        }
        if(h>junnewHeight)  //高度
        {
            return;
        }
        document.getElementById("junnewfullscreen").style.display = "";
        document.getElementById("junnewfullscreen").style.width = junnewWidth;
        document.getElementById("junnewfullscreen").style.height = h+"px";
        setTimeout("addCount()",runSpeed); 
    }

	window.onload = function showAds()
    {
        addCount();
        setTimeout("noneAds()",stopTime); //停留时间自己适当调整
    }
	
	
	//收缩
    var T = junnewHeight;
    var N = junnewHeight; //高度
    function noneAds()
    {
        if(T>0)
        {
            T--;
            N = N-20;
        }
        else
        {
            return;
        }
        if(N<0)
        {
            document.getElementById("junnewfullscreen").style.display = "none";
            return;
        }
        document.getElementById("junnewfullscreen").style.height = N+"px";
        setTimeout("noneAds()",runSpeed); 
    }
	   
	var divString = "<div align='center'><div id='junnewfullscreen' style=' display:block; width:0px; height:0px; background-color:#fafafa; overflow:hidden; text-align:left;'><a href='"+junnewUrl+"' target='_blank'><img src='"+junnewImage+"' width='"+junnewWidth+"' height='"+junnewHeight+"' border='0'></a></div></div>"
	document.write(divString)	

	
	
