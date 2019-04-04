<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp"%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<title>编辑广告创意</title>
<link href="css/selArea.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/areaArr2.js"></script>
<script type="text/javascript" src="js/selArea.js"></script>
<script type="text/javascript" src="js/dailyFlow.js"></script>
<script type="text/javascript">
function autoLoadTable(){
	var showIps = document.getElementById("hidShowIp").value;
	var isDefault = document.getElementById("isDefault").value;
	var areaList = document.getElementById("areaList").value;
	if(isDefault == 0){
	//回显日ip流量控制表格
	if(showIps){
		//alert("ip流量控制验证成功，进入此方法");
		var showIp = showIps.split("|");
		for(var i=0;i<showIp.length;i++){
			var ips = showIp[i].split(",")[0];
			var dateTime = showIp[i].split(",")[1];
			var newDate = dateTime.substring(0,4)+"-"+dateTime.substring(4,6)+"-"+dateTime.substring(6);
			insertplanTask("dailyTbody",1,newDate,ips);
		}
	}
	//回显地区选择
	if(areaList){
		if(areaList == 0){
		var mes = "<input type='checkbox' checked='true' value='0' disabled />全部地区";
		var mess = "<input type='checkbox' checked='true' value='0#全部地区' onclick='copyItem(\"previewItem\",\"previewItem\");same(this);'/>全部地区";
		document.getElementById("makeSureItem").innerHTML += "<li style='width:20%;float:left;border:0px solid red;'>"+mes+"</li>";
		document.getElementById("previewItem").innerHTML += "<li style='width:20%;float:left;border:0px solid red;'>"+mess+"</li>";
		}else{
		var areas = areaList.split("|");
		var areasLen = provinceArr.length;
		var items = $F("selectSub").getElementsByTagName("input");
		for(var i=0;i<areas.length;i++){
			var id = areas[i];
			if(areas[i].indexOf("*")!=-1){
			//省级列表选择
				for(var j=0;j<areasLen;j++){
				 if(provinceArr[j][0] == id.split(".")[0]){
				var mes = "<input type='checkbox' checked='true' value='"+provinceArr[j][0]+".*' disabled />"+provinceArr[j][1];
				var mess = "<input type='checkbox' checked='true' value='"+provinceArr[j][0]+".*#"+provinceArr[j][1]+"' onclick='copyItem(\"previewItem\",\"previewItem\");same(this);'/>"+provinceArr[j][1];
				document.getElementById("makeSureItem").innerHTML += "<li style='width:20%;float:left;border:0px solid red;'>"+mes+"</li>";
				document.getElementById("previewItem").innerHTML += "<li style='width:20%;float:left;border:0px solid red;'>"+mess+"</li>";
				 break;
				 }
				}
			}else{
			//市级列表选择
				for(var j=0;j<items.length;j++){
				
					var item = items[j].value.split("#");
					if(item[0] == id){
					var mes = "<input type='checkbox' checked='true' value='"+item[0]+"' disabled />"+item[1];
					var mess = "<input type='checkbox' checked='true' value='"+items[j].value+"' onclick='copyItem(\"previewItem\",\"previewItem\");same(this);'/>"+item[1];
					document.getElementById("makeSureItem").innerHTML += "<li style='width:20%;float:left;border:0px solid red;'>"+mes+"</li>";
					document.getElementById("previewItem").innerHTML += "<li style='width:20%;float:left;border:0px solid red;'>"+mess+"</li>";
					break;
					}
				}
			}
		}
	}
	}
}
}
document.onload = autoLoadTable();
</script>
	</head>
	<body >

		<div class="page">
			<div class="pageContent">
				<form name="editReadyBox" method="post"
					action="${pageContext.request.contextPath }/readybox.do?action=edit"
					class="pageForm required-validate"
					onsubmit="return validateCallback(this,navTabAjaxDone);">
					<input name="readyBoxId" type="hidden" value="${readyBox.readyBoxId}" />
					<input name="areaList" id="areaList" type="hidden" value="${readyBox.areaFix}"/>
					<input name="isDefault" id="isDefault" type="hidden" value="${isDefault}" />
					<input name="hidShowIp" id="hidShowIp" type="hidden" value="${readyBox.showIpString}"/>
					
					<h2 class="contentTitle">
						所属创意：${adCreative.adCreativeName}
					</h2>
					<div class="pageFormContent" layoutH="73">
						<p>
							<label>
								支付类型：
							</label>
							<label>
								<select name="payType" class="required">
								<option value="">请选择支付类型</option>
									<c:forEach items="${payTypeList}" var="payType">
										<option value="${payType.payTypeId}" ${readyBox.payTypeId == payType.payTypeId?"selected":""}>
											${payType.payTypeName}
										</option>
									</c:forEach>
								</select>
							</label>
						</p>
						<p>
							<label>
								网站主单价：
							</label>
							<label>
								<!-- 总监修改权限 -->
								<input name="webMasterPrice" class="required  number "
									type="text" size="30" value="${readyBox.webMasterPrice == 0?"0":readyBox.webMasterPrice}" /> <!-- ${roleId  == 20?"":"readonly"} -->
							</label>
						</p>
						<p>
							<label>
								扣量：
							</label>
							<label>
								<input name="disCount" class="required number" type="text"
									size="30" min="0" max="100"  value="${readyBox.discount}"/>
							</label>
						</p>
						<p>
							<label>
								站长查看列：
							</label>
							<label>
								<select name="showType">
								<option value="0" ${readyBox.showType eq 0?"selected":""}>PV</option>
								<option value="1" ${readyBox.showType eq 1?"selected":""}>UV</option>
								<option value="2" ${readyBox.showType eq 2?"selected":""}>IP</option>
								</select>
							</label>
						</p>
						<c:if test="${isDefault == 0}">
						<p>
							<label>
								轮播等级：
							</label>
							<label>
								<input name="creativeLevel" class="required number" type="text"
									size="30" min="0" max="100" value="${readyBox.adCreativeLevel}" /> <!-- value=100-readyBox.adCreativeLevel -->
							</label>
						</p>
						<p>
							<label>
								状态：
							</label>
								<input type="radio" name="readyBoxState" value="0" checked="checked"/>正常
								<input type="radio" name="readyBoxState" value="1" />锁定
								<input type="radio" name="readyBoxState" value="2" />废弃
						</p>
						<div class="divider"></div>
						<h3 class="contentTitle">
							投放地区
							<INPUT class="bton pointer" onclick="openSelProvince(1)"
								type=button value=请选择 name=button>
						</h3>
						<DIV class="cont left" style="width: 100%;border:0px solid black;"  id="makeSureItemDiv"><ul id="makeSureItem" style="width: 100%;border:0px solid black;"></ul></DIV>
						<DIV id="bg"></DIV>
						<DIV class=hidden id="selectItem">
							<DIV class="tit bgc_ccc move" onmousedown=drag(event,this)>
								<H2 class=left style="cursor: pointer;"><INPUT onclick="allArea(this)" id="allAreas" type="checkbox" value="0#全部地区"/>全部地区 </H2>
								<span class="right" style="margin-right: 15px;">
								<SPAN class="pointer " onclick=makeSure();>[确定]</SPAN>
								<SPAN class="pointer " onclick="closeSelProvince(0)">[取消]</SPAN>
								</span>
							</DIV>
							<DIV class=cls></DIV>
							<DIV class="cont left"  >
								<DIV id=selectSub >
									<DIV id="province"  style="border:0px solid red;float:left;width:100%"></DIV>
									<c:forEach items="${allCitys}" var="pro">
										<DIV id="c0${pro.key}" style="display:none;border:2px solid silver;float:left;width:98%;">
									<ul>
									<c:forEach items="${pro.value}" var="citys" varStatus="s">
									<c:if test="${s.count != 1}">
										<li style="width:20%;float:left;"><INPUT onclick="addPreItem(this)"  type=checkbox value="${pro.key}.${citys.cid}#${citys.p}-${citys.c}" name=ck00>${citys.c}</li>
									</c:if>
									</c:forEach>
									</ul>
									</DIV>
									</c:forEach>
								</DIV>
							</DIV>
							<DIV id="preview left" style="border: 0px solid red;">
								<DIV class="tit bgc_eee c_999 left"  style="border: 0px solid red;">
									<H2>
										已选择的城市
									</H2>
								</DIV>
								<DIV class="cont left" style="border: 0px solid red;" id="previewItemDiv"><ul id="previewItem" style="width: 100%;"></ul></DIV>
							</DIV>
						</DIV>
						<h3 class="contentTitle">
							日流量控制
						</h3>
						<div class="button">
							<div class="buttonContent">
								<button type="button"
									onclick="insertplanTask('dailyTbody',document.getElementById('rowNum').value)">
									新建
								</button>
							</div>
						</div>
						<input type="text" id="rowNum" 
							class="textInput valid" style="margin: 2px;" value="1" size="2">
						<table class="list nowrap" id="dailyTable" width="100%">
							<thead>
								<tr>
									<th width="40">
										编号
									</th>
									<th width="100">
										日期
									</th>
									<th width="100">
										日流量
									</th>
									<th width="100">
										操作
									</th>
								</tr>
							</thead>
							<tbody id="dailyTbody">
							</tbody>
						</table>
						</c:if>
					</div>
					<div class="formBar">
						<ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">
											保存
										</button>
									</div>
								</div>
							</li>
							<li>
								<div class="button">
									<div class="buttonContent">
										<button type="Button" onclick="navTab.closeCurrentTab()">
											取消
										</button>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</form>
			</div>
		</div>

	</body>
</html>
