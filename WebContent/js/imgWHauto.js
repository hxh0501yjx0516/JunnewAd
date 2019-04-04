// JavaScript Document
//图片自适应宽高
var flag=false;
function imgWHauto(ImgD,Iw,Ih){
var image=new Image();
var iwidth = Iw; //定义允许图片宽度
var iheight = Ih; //定义允许图片高度
image.src=ImgD.src;
if(image.width>0 && image.height>0){
flag=true;
if(image.width/image.height>= iwidth/iheight){
if(image.width>iwidth){ 
ImgD.width=iwidth;
ImgD.height=(image.height*iwidth)/image.width;
}else{
ImgD.width=image.width; 
ImgD.height=image.height;
}
//ImgD.alt=image.width+"×"+image.height;
}
else{
if(image.height>iheight){ 
ImgD.height=iheight;
ImgD.width=(image.width*iheight)/image.height; 
}else{
ImgD.width=image.width; 
ImgD.height=image.height;
}
//ImgD.alt=image.width+"×"+image.height;
}
}
} 
