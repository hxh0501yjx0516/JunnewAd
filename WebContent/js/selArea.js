//var grow = $F("selectSub").getElementsByTagName("div").length; //组数
var showGrow = 0;//已打开组
var selectCount = 0; //已选数量 
//showSelect(showGrow);
//var items = $F("selectSub").getElementsByTagName("input");
function $F(o) { //获取对象
	if (typeof (o) == "string")
		return document.getElementById(o);
	return o;
}
function openBg(state) { //遮照打开关闭控制
	if (state == 1) {
		$F("bg").style.display = "block";
		var h = document.body.offsetHeight > document.documentElement.offsetHeight ? document.body.offsetHeight
				: document.documentElement.offsetHeight;
		//alert(document.body.offsetHeight);
		//alert(document.documentElement.offsetHeight);
		$F("bg").style.height = h + "px";
	} else {
		$F("bg").style.display = "none";
	}
}
function openSelect(state){ //选择城市层关闭打开控制
	if(state == 1) {
		$F("selectItem").style.display = "block";
		$F("selectItem").style.left = ($F("bg").offsetWidth - $F("selectItem").offsetWidth) / 2 + "px";
		$F("selectItem").style.top = document.body.scrollTop + "px";
	}else {
		$F("selectItem").style.display = "none";
	}
}
function showAllProvince(){
	var areasLen = provinceArr.length;
	var str2 = "";
	for(var i=0;i<areasLen;i++){
		str2=str2+"<li style='width:20%;float:left;CURSOR: pointer' onClick='showSelect("+provinceArr[i][0]+")'><input class='checked'   value='"+provinceArr[i][0]+".*#"+provinceArr[i][1]+"' type='checkbox' onclick='addPreItem(this)'>"+provinceArr[i][1]+"</li>";
	}
	$F("province").innerHTML = "<ul style=''>"+str2+"</ul>";
}
//打开省份选择
function openSelProvince(state){
	showAllProvince();
	
	var items = $F("selectSub").getElementsByTagName("input");
	var items2 = $F("makeSureItem").getElementsByTagName("input");
	for(var i = 0 ; i < items2.length ; i++)
	{
	if(items2[i].checked == true)
	{
		if(items2[i].value == 0){
			for(var m = 0 ; m < items.length ; m++)
			{
				items[m].disabled = items2[i].checked;
				items[m].checked = items2[i].checked;
//				if(items2[i].value == items[m].value.split("#")[0]){
//				items[m].checked = items2[i].checked;
//				}
			}
			$F("allAreas").checked = items2[i].checked;
		}else{
		for(var j = 0 ; j < items.length ; j++)
	{
			if(items2[i].value.indexOf("*") !=-1){
			if(items2[i].value.split(".")[0] == items[j].value.split(".*")[0]){
			items[j].checked = items2[i].checked;
			var subItems = $F("c0"+items2[i].value.split(".")[0]).getElementsByTagName("input");
			for(var k = 0;k<subItems.length;k++){
				subItems[k].checked = items2[i].checked;
				subItems[k].disabled = items2[i].checked;
			}
			}	
			}else{
			if(items2[i].value == items[j].value.split("#")[0]){
			items[j].checked = items2[i].checked;
			}	
			}
			
	}
		}
	}
	}
//	addPreItem();
	openBg(state);
	openSelect(state);
}
//关闭省份选择
function closeSelProvince(state){
	var items = $F("selectSub").getElementsByTagName("input");
	for(var i = 0 ; i < items.length ; i++)
	{
	if(items[i].checked == true)
	{
		items[i].checked = false;
	}
	}
	openBg(state);
	openSelect(state);
}
function showSelect(id) {
	for ( var i = 1; i <= provinceArr.length; i++) {
		$F("c0" + i).style.display = "none";
	}
	$F("c0" + id).style.display = "block";
	showGrow = id;
}
function open(id, state) { //显示隐藏控制
	if (state == 1)
		$F(id).style.display = "block";
	$F(id).style.diaplay = "none";
}
function allArea(id) {
	$F("previewItem").innerHTML = "";
	var items = $F("selectSub").getElementsByTagName("input");
		for(var i = 0 ; i < items.length ; i++)
	{
		items[i].disabled = id.checked;
		items[i].checked = id.checked;
	}
	if(id.checked == true){
	
	var mes = "<input type='checkbox'  checked='true' value='"+id.value+"' onclick='copyItem(\"previewItem\",\"previewItem\");same(this);'>全部地区";
	$F("previewItem").innerHTML += "<li style='width:80px;float:left;'>"+mes+"</li>";
//	}else{
//	 for(var i = 0 ; i < items.length ; i++)
//	{
//		items[i].disabled = false;
//		items[i].checked = false;
//	}
	}
	
}
function addPreItem(flag) {
	$F("previewItem").innerHTML = "";
	var len = 0 ;
	var items = $F("selectSub").getElementsByTagName("input");
//	for(var j = 0 ; j < items.length ; j++)
//	{
//		items[j].disabled = false;
//	}
	if((flag.value.split("#")[0]).indexOf("*") !=-1){
			
			var subItems = $F("c0"+flag.value.split(".")[0]).getElementsByTagName("input");
			for(var k = 0;k<subItems.length;k++){
				subItems[k].checked = flag.checked;
				subItems[k].disabled = flag.checked;
				}
			}
	for(var i = 0 ; i < items.length ; i++)
	{
		
			if(items[i].checked == true)
			{
			if(items[i].disabled == false){
			var mes = "<input type='checkbox' checked='true' value='"+ items[i].value +"' onclick='copyItem(\"previewItem\",\"previewItem\");same(this);'>" + items[i].value.split("#")[1];
			$F("previewItem").innerHTML += "<li style='width:20%;float:left;'>"+mes+"</li>";
			//alert(items[i].value);
			}
		}
	//len++;
	//if(len > lenMax)
	//{
	//alert("不能超过" + lenMax +"个选项！")
	//return false;
	//}
	}
}
function makeSure(){
//alert(1);
//$F("makeSureItem").innerHTML = $F("previewItem").innerHTML;
openBg(0);
openSelect(0);
copyItem("previewItem","makeSureItem",true);
}
function copyHTML(id1,id2){
$F(id2).innerHTML = $F("id1").innerHTML;
}
function copyItem(id1,id2,flag){

var mes = "";
var list = "";
var items2 = $F(id1).getElementsByTagName("input");
for(var i = 0 ; i < items2.length ; i++)
{
if(items2[i].checked == true)
{
	if(flag){
	mes += "<li style='width:20%;float:left;border:0px solid red;'><input type='checkbox' checked='true' value='"+ items2[i].value.split("#")[0]+"' disabled />" + items2[i].value.split("#")[1]+"</li>";
	if(i == items2.length-1){
		list += items2[i].value.split("#")[0];
	}else{
		list += items2[i].value.split("#")[0]+"|";
	}
	}else{
	mes += "<li style='width:100px;float:left;border:0px solid red;'><input type='checkbox' checked='true' value='"+ items2[i].value+"' onclick='copyItem(\"" + id2+ "\",\""+ id1 +"\");same(this);'>" + items2[i].value.split("#")[1]+"</li>";
	
	}
}
}
if(flag){
	$F("areaList").value = "";
	$F("areaList").value=list;
}
$F(id2).innerHTML = "";
$F(id2).innerHTML += mes;
//alert($F(id2).innerHTML);
}
function same(ck){
	var items = $F("selectSub").getElementsByTagName("input");
	if(ck.value.split("#")[0] == 0){
		$F("allAreas").checked = ck.checked;
		for(var h = 0;h<items.length;h++){
			items[h].disabled = ck.checked;
			items[h].checked  = ck.checked;
		}
	}else{
		for(var i = 0 ; i < items.length ; i++)
		{
		if(ck.value == items[i].value)
		{
			if((items[i].value.split("#")[0]).indexOf("*") !=-1){
				var subItems = $F("c0"+items[i].value.split(".")[0]).getElementsByTagName("input");
				for(var k = 0;k<subItems.length;k++){
					subItems[k].checked = ck.checked;
					subItems[k].disabled = ck.checked;
				}
		}
		items[i].checked = ck.checked;
		}
		}
	}

}
/* 鼠标拖动 */
var oDrag = "";
var ox,oy,nx,ny,dy,dx;
function drag(e,o){
var e = e ? e : event;
var mouseD = document.all ? 1 : 0;
if(e.button == mouseD)
{
oDrag = o.parentNode;
//alert(oDrag.id);
ox = e.clientX;
oy = e.clientY;
}
}
function dragPro(e){
if(oDrag != "")
{
var e = e ? e : event;
//$F(oDrag).style.left = $F(oDrag).offsetLeft + "px";
//$F(oDrag).style.top = $F(oDrag).offsetTop + "px";
dx = parseInt($F(oDrag).style.left);
dy = parseInt($F(oDrag).style.top);
//dx = $F(oDrag).offsetLeft;
//dy = $F(oDrag).offsetTop;
nx = e.clientX;
ny = e.clientY;
$F(oDrag).style.left = (dx + ( nx - ox )) + "px";
$F(oDrag).style.top = (dy + ( ny - oy )) + "px";
ox = nx;
oy = ny;
}
}
document.onmouseup = function(){oDrag = "";}
document.onmousemove = function(event){dragPro(event);}