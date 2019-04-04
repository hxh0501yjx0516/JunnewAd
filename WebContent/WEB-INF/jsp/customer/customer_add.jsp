<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加客户</title> 
</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="addCustomer" method="post" action="${pageContext.request.contextPath }/customer.do?action=add&flag=save" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="33">
				<p>
					<label>客户名：</label>
					<input name="name" class="required" type="text" size="30" />
				</p>
				<p>
					<label>密码：</label>
					<input name="password" class="required"  type="text" size="30"  />
				</p>
				<div class="divider"></div>
				<p>
				<label>收款人：</label>
					<select  name="payee" class="required">
						<option value="北京盘酷科技有限公司" selected>北京盘酷科技有限公司</option>
						<option value="北京中润无线广告有限公司" >北京中润无线广告有限公司</option>
						<option value="北京蓝鲸联众科技有限公司" >北京蓝鲸联众科技有限公司</option>
					</select>
				</p>
				<p>
					<label>所属销售：</label>
					<select  name="medium" class="">
					<option value="0" >请选择</option>
					<c:forEach var="m" items="${xslist}">
						<option value="${m.userId}" >${m.realname}</option>
					</c:forEach>
					</select>
				</p>
				<div class="divider"></div>
				<p>
					<label>联系人：</label>
					<input name="contactName"  type="text" size="30"  />
				</p>
				<p>
					<label>联系电话：</label>
					<input name="contactMobile" type="text"  size="30"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>联系地址：</label>
					<input name="contactAddress" type="text"  size="30"  />
				</p>
				<p>
					<label>邮编：</label>
					<input name="post" type="text" size="30" />
				</p>
				
				<div class="divider"></div>
				<p>
					<label>网址：</label>
					<input name="url" type="text"  size="30"  />
				</p>
				
				<div class="divider"></div>
				<p>
					<label>状态：</label>
					<label><input type="radio" name="state" value="0" checked="checked"/>正常</label>
					<label><input type="radio" name="state" value="1"/>锁定</label>
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
