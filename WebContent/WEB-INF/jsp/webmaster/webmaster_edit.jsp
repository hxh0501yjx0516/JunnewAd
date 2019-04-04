<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/util/taglib.jsp" %>
 <html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>编辑网站主</title> 
</head>
 <body>
    <div class="page">
	<div class="pageContent">
		<form name="editWebMaster" method="post" action="${pageContext.request.contextPath }/webmaster.do?action=edit" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="cid" value="${webMaster.webMasterId}"/>
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>网站主名：</label>
					<input name="name" class="required" type="text" size="30" value="${webMaster.webMasterName}"/>
				</p>
				<p>
					<label>密码：</label>
					<input name="password" class="required"  type="text" size="30"  value="${webMaster.webMasterPassWord}"/>
				</p>
				<p>
					<label>所属媒介 ：</label>
					<select  name="medium"  class="">
					<option value="0" >请选择</option>
					<c:forEach var="m" items="${userslist}">
						<option value="${m.userId}" ${webMaster.userId == m.userId?"selected":""}>${m.realname}</option>
					</c:forEach>
					</select>
				</p>
				<div class="divider"></div>
				<p>
					<label>联系人：</label>
					<input name="contactName" type="text"  size="30"  value="${webMaster.webMasterContactName}"/>
				</p>
				<p>
					<label>身份证号码 ：</label>
					<input name="card" type="text" size="30" value="${webMaster.webMasterCard}"/>
				</p>
				<p>
					<label>地址 ：</label>
					<input name="address" type="text"  size="30"  value="${webMaster.webMasterAddress}"/>
				</p>
				<p>
					<label>邮编 ：</label>
					<input name="post"  type="text"  size="30"   value="${webMaster.webMasterPost}"/>
				</p>
				<p>
					<label>移动电话 ：</label>
					<input name="phone"  type="text"  size="30"  value="${webMaster.webMasterMobile}" />
				</p>
				<p>
					<label>QQ号码 ：</label>
					<input name="qq" type="text"  size="30"   value="${webMaster.webMasterQQ}"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>开户银行 ：</label>
					<input name="bankAddress" type="text"  size="30"   value="${webMaster.webMasterBank}"/>
				</p>
				<p>
					<label>开户名称：</label>
					<input name="bankName"  type="text"  size="30"   value="${webMaster.webMasterBankName}"/>
				</p>
				 <p>
					<label>银行帐号：</label>
					<input name="bankNumber"  type="text"  size="30"   value="${webMaster.webMasterBankNumber}"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>用户状态：</label>
					<label><input type="radio" name="state" value="0" ${webMaster.webMasterStatus ==0?"checked":""}/>正常</label>
					<label><input type="radio" name="state" value="1" ${webMaster.webMasterStatus ==1?"checked":""}/>锁定</label>
				</p>
			</div>
			<div class="formBar">
				<ul>					
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
  </body>
</html>
