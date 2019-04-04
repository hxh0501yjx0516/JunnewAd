<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page session="true"%>
<%
String RndData ="";
char Upper = 'z';
char Lower = 'a';
   Random r = new Random();
for(int i=0;i<20;i++)
{
   int tempval = (int)((int)Lower + (r.nextFloat() * ((int)(Upper - Lower))));
   RndData += new Character((char)tempval).toString();
}
session.setAttribute("RandomData",RndData);
%>

<html>
<HEAD>
<title>北京中润无线广告有限公司</title>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<STYLE TYPE="text/css">
<!--
@import "test.css";
-->
</STYLE>
<!-- <link href="images/jnico.ico" rel="SHORTCUT ICON" /> -->	
<script src="javascripts/jquery-1.3.2.js" type="text/javascript"></script>
<script type="text/javascript">
var timeout;
	function changeTypes(pvalue){
		if(pvalue == 1){
			document.getElementById("userkey").style.display = "block";
			document.getElementById("smskey").style.display = "none";
			
		}if(pvalue == 2){
			document.getElementById("userkey").style.display = "none";
			document.getElementById("smskey").style.display = "block";
		}
	}
	function send(second){
		 second--;
		if(second==-1){
		document.getElementById("sendbutton").disabled = "";
		document.getElementById("sendbutton").value = "重新发送";
		return null;
		}
		 document.getElementById("sendbutton").value = second+"秒后重新发送";
		 timeout=setTimeout("send("+second+");",1000);
}
	function getverifyNumber(){
		var sendButton = document.getElementById("sendbutton");
		var submitSms = document.getElementById("submitSms");
		var serialNumber = document.getElementById("serialNumber");
		document.getElementById("sendSpan").style.display="block";
		sendButton.disabled = "disabled";
		submitSms.disabled = "";
		send(121);
		location.href="${pageContext.request.contextPath }/login.do?action=sendSms&serialNumber="+serialNumber.value;
}
	function getverifyNumber(){
		var sendButton = document.getElementById("sendbutton");
		var submitSms = document.getElementById("submitSms");
		document.getElementById("sendSpan").style.display="block";
		sendButton.disabled = "disabled";
		submitSms.disabled = "";
		send(121);
		$.ajax( {
		type : 'POST',
		url : "${pageContext.request.contextPath }/login.do?action=sendSms",
		data : {serialNumber : $('#serialNumber').val()},
		dataType : "json",
		cache : false,
		success : function(temp){
			if(temp == 1){
				clearTimeout(timeout);
				document.getElementById("returnStatus").innerHTML = "短信发送失败！如多次发送失败请与管理员联系";
				document.getElementById("sendbutton").disabled = "";
				document.getElementById("sendbutton").value = "重新发送";
				document.getElementById("submitSms").disabled = "disabled";
			}
			if(temp == 0){
			document.getElementById("returnStatus").innerHTML = "短信发送成功！";
			}
		}
	});
		
}
	function validateSms(){
		var re = /[1-9]\d{5}(?!\d)/;
	     var vnumber = document.getElementById("verifyNumber");
		if( vnumber.value== null || vnumber.value == ""){
			alert("请填入获取的短信验证码！");
			return false;	
		}
		if (!re.test(vnumber.value))
	    {
	    alert("请填入6位数字短信验证码！");
        vnumber.focus();
        return false;
        }else{
		document.getElementById("hidSelectType").value=document.getElementById("selectType").value
		return true;
        }
	}
	
</script>
<script language=vbscript>
Dim FirstDigest
Dim Digest 
Digest= "01234567890123456"

dim bErr

sub ShowErr(Msg)
	bErr = true
	MsgBox Msg
'	Document.Writeln "<FONT COLOR='#FF0000'>"
'	Document.Writeln "<P>&nbsp;</P><P>&nbsp;</P><P>&nbsp;</P><P ALIGN='CENTER'><B>ERROR:</B>"
'	Document.Writeln "<P>&nbsp;</P><P ALIGN='CENTER'>"
'	Document.Writeln Msg
'	Document.Writeln " failed, and returns 0x" & hex(Err.number) & ".<br>"
'	Document.Writeln "<P>&nbsp;</P><P>&nbsp;</P><P>&nbsp;</P>"
'	Document.Writeln "</FONT>"
End Sub

function Validate()
	Digest = "01234567890123456"
	On Error Resume Next
	Dim TheForm
	Set TheForm = Document.forms("ValidForm")
	If Len(TheForm.UserPIN.Value) < 4  Then
		MsgBox "密码为空或密码长度小于4!!"	 
		Validate = FALSE
		Exit Function
	End If

	bErr = false

	'Let detecte whether the ePass 1000ND Safe Active Control loaded.
	'If we call any method and the Err.number be set to &H1B6, it 
	'means the ePass 1000ND Safe Active Control had not be loaded.
	ePass.GetLibVersion


	If Err.number = &H1B6 Then

		ShowErr "请加载Active控件！"
		Validate = false
		Exit function
	Else
	

		ePass.OpenDevice 1, ""
		
		If Err then
			ShowErr "请插入USBKEY！"
			Validate = false
			Exit function
		End if
		
			
		'you can use this function to reset securityState
		'ePass.ResetSecurityState 0
		dim results
		results = "01234567890123456"
		results = ePass.GetStrProperty(7, 0, 0)
		
		
        'ePass.VerifyPIN CInt(TheForm.Identity.Value), CStr(TheForm.UserPIN.Value)
		ePass.VerifyPIN 0, CStr(TheForm.UserPIN.Value)
		If Err Then
			ShowErr "验证用户密码失败!!!"
			Validate = false
			ePass.CloseDevice
			Exit function
		End If
		

		If Not bErr Then
			ePass.ChangeDir &H300, 0, "ASP_DEMO"
			If Err then 
				ShowErr "Change to demo directory"
				Validate = false
				ePass.CloseDevice
				Exit function
			End If
		End If


		'Open the first key file.
		If Not bErr Then
			ePass.OpenFile 0, 1
			If Err Then 
				ShowErr "Open first KEY-file"
				Validate = false
				ePass.CloseDevice
				Exit function
			End If
		End If

		'Do HASH-MD5-HMAC compute.
		If Not bErr Then
			Digest = ePass.HashToken (1, 2,"<%=(String)session.getAttribute("RandomData")%>")
			If Err Then 
				ShowErr "HashToken compute"
				Validate = false
				ePass.CloseDevice
				Exit function
			End If
			
			DigestID.innerHTML = "<input type='hidden' name='Digest' Value='" & Digest & "'>"
			snID.innerHTML = "<input type='hidden' name='SN_SERAL' Value='" & results & "'>"
		End If
	End If
	ePass.CloseDevice
End function
</script>
</HEAD>
<body >
                     
<object CLASSID="clsid:0272DA76-96FB-449E-8298-178876E0EA89"
	CODEBASE="${pageContext.request.contextPath }/epass/install.cab#Version=1,0,6,413"
	BORDER="0" VSPACE="0" HSPACE="0" ALIGN="TOP" HEIGHT="0" WIDTH="0"
	id=ePass name = ePass style="LEFT: 0px; TOP: 0px" 
	 VIEWASTEXT></object>
	 <div style="margin:0px auto;width:590px;margin-top: 80px;">
<table WIDTH="590" height="300px;" BORDER="0" cellpadding="0" cellspacing="0" ALIGN="center" style="border: 1px solid #E5ECF2;background-color: white;">
	 <tr>
	 <td style="background-color:#C0D1E5;height: 40px;padding-left: 20px;font-weight: bold;" >
	 	<span style="color: white;">登陆验证方式</span>	
	 </td>
	 </tr>
<tr>
	<td style="text-align: center;height: 40px;border-bottom: 1px solid #E7EDF2;">验证类型：
		<select id="selectType" name="selectType" onchange="changeTypes(this.value)">
			<option value="1" ${hidSelectType == 1?"selected":""}>USBKEY验证</option>
			<option value="2"  ${hidSelectType == 2?"selected":""}>短信验证</option>
		</select>
	</td>
</tr>
<tr>
<td>
<table id="userkey" width="590" height="300px;" border="0" align="center">
  <tr >
    <td style='border-bottom: 0px solid #E7EDF2;'>
      <P ALIGN="left">您选择了USBKEY验证方式，USBKEY验证方式只支持IE内核浏览器<br/>
      				   如未下载Activex控件请点击浏览器上方黄色条幅下载Activex控件，<br/>
      				   并插入手中的USBKEY后提交本页。
  	   </P>

<script id=clientEventHandlersVBS language=vbscript>
<!--
		'Now you had get the result of HASH compute and the random data
		' use to HASH compute. You should post these data to server and
		' do verify operation.
		Document.Writeln "<P>&nbsp;</P></TD></TR><TR ><TD>"
		Document.Writeln "<FORM id=ValidForm METHOD='post' ACTION='login.do?action=validateKey' onsubmit='return Validate();' language='jscript'>"

		'Post the result of HASH compute by ePass can use by server.

		Document.Writeln "<span id=DigestID></span>"
		Document.Writeln "<span id=snID></span>"
		'Document.Writeln "<input type='hidden' name='Digest' Value='" & Digest & "'>"

		'Create a table and let user input the PIN.
		Document.Writeln "<TABLE WIDTH='250' BORDER='0' ALIGN='center' CELLSPACING='0' BORDERCOLORDARK='#E7EBFF' BORDERCOLORLIGHT='#000000'>"
		'If you add so pin verify ,you maybe add these codes as these
		'Document.Writeln "<TR><TD ALIGN='right'>Identity:</TD><TD>"
		'Document.Writeln "<select name='Identity'>"
		'Document.Writeln "<option  value='0'>User PIN</option>"
		'Document.Writeln "<option value='1'>So PIN</option>"
		'Document.Writeln "</select>"
		'Document.Writeln "</TD></TR>"
		
		Document.Writeln "<TR><TD ALIGN='right' style='display:none;'>User PIN:</TD><TD><INPUT TYPE='hidden' NAME='UserPIN' CLASS='inputtext' value='1234'></TD></TR>"

		Document.Writeln "</TABLE><P>&nbsp;</P><P ALIGN='center'>"
		Document.Writeln "<INPUT TYPE='hidden' NAME='logId' VALUE='${logId}'>"
		Document.Writeln "<INPUT TYPE='submit' style='background: url(images/bg_comb.png) no-repeat 0px 0px;height: 32px;width: 100px;border: 0px;color: white;cursor: pointer;padding-right:2px;' NAME='Submit' VALUE='进入系统' CLASS=''>"
		Document.Writeln "<INPUT TYPE='hidden' NAME='Reset' VALUE='Re-input' CLASS='inputbtn'></P></FORM>"
-->
</script>
	
	</td>
  </tr>
</table>
<table id="smskey" WIDTH="590" height="350px;" BORDER="0" ALIGN="center" style="display: none;">
<form id="smsForm" name = "smsForm" method="post" action="login.do?action=validateSms" onsubmit='return validateSms();'>
  <tr >
    <td style='border-bottom: 1px solid #E7EDF2;'>
    	您选择了短信验证方式，点击获取验证码后将向您的手机${telephone}发送短信
    	<br/>请收到短信后，将短信中的验证码填入本页后提交。
	</td>
  </tr>
   <tr >
    <td style="text-align: center;border-bottom: 1px solid #E7EDF2;">
   		 <input type='hidden' name='logId' value='${logId}'>
   		 <input type='hidden' name='telephone' value='${telephone}'>
   		 <input type='hidden' id='hidSelectType' name='hidSelectType' value='${hidSelectType}'>
    	<input type="hidden" id="serialNumber" name="serialNumber" value="${serialNumber}" />
    	验证码：<input type="text" id="verifyNumber" name="verifyNumber" maxlength="6"/>
    	<input type="button" id="sendbutton" name="sendbutton"  value="获取验证码" onclick="getverifyNumber();">
    	<span id="sendSpan" style="display: none;">（发送编号为：${serialNumber}）</span>
	</td>
  </tr>
  <tr >
    <td  style="text-align: center;">
    	<span style="color: red;">${mes}</span><span id="returnStatus"  style="color: red;"></span>
	</td>
  </tr>
   <tr >
    <td  style="padding-left: 240px;">
    	<div style="background: url('images/bg_comb.png') no-repeat top right;height: 32px;width:101px;padding-right: 2px;"> 
    	  	<input style="background: url('images/bg_comb.png') no-repeat 0px 0px;height: 32px;width: 100px;border: 0px;color: white;cursor: pointer;padding-right:2px; " type="submit" value="进入系统" id="submitSms" name="submitSms" disabled="disabled">
    	   </div>
	</td>
  </tr>
  </form>
</table>
</td>
</tr>
</table>
<div style="background: url('images/box_bot.png');_height:3px;height:10px;"></div>
</div>
</body>
<script type="text/javascript">
function initOnload(){
	var selectType=document.getElementById("selectType").value;
	if(selectType == 2){
	document.getElementById("sendSpan").style.display="block";
	document.getElementById("submitSms").disabled="";
	}
	changeTypes(selectType);
}
document.onload=initOnload();
	
</script>
</html>