<h2 class="contentTitle">表单验证</h2>

<div class="page">
	<div class="pageContent">
	
	<form method="post" action="ajaxDone.html" class="pageForm" onsubmit="return validateCallback(this)">
		<div class="pageFormContent" layoutH="97">

			<p>
				<label>真实姓名：</label>
				<input type="text" name="name" size="30" maxlength="20" class="required" />
			</p>
			<p>
				<label>电子邮箱：</label>
				<input type="text" name="email" size="30" class="required email" alt="请输入您的电子邮件"/>
			</p>
			<p>
				<label>电话：</label>
				<input type="text" name="phone" size="30" class="phone" alt="请输入您的电话"/>
			</p>
			<p>
				<label>密码：</label>
				<input type="password" name="password" size="30" class="required alphanumeric" alt="字母、数字、下划线"/>
			</p>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
	</div>
</div>
