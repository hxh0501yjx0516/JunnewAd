// JavaScript Document
   var junnewWidth = Kuwa_AsizeWidth;			//�����
   var junnewHeight = Kuwa_AsizeHigh;			//���߶�
   var junnewImage = Kuwa_adSourceFile;		//ͼƬ
   var junnewUrl = Kuwa_ADurl_t;						//��ת��ַ
   
   var runSpeed = 5;				//�����ٶ�
   var stopTime = 5000;			//ͣ��ʱ��	5��
   
    var time = junnewHeight;
    var h = 0;
	//չ��
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
        if(h>junnewHeight)  //�߶�
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
        setTimeout("noneAds()",stopTime); //ͣ��ʱ���Լ��ʵ�����
    }
	
	
	//����
    var T = junnewHeight;
    var N = junnewHeight; //�߶�
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

	
	
