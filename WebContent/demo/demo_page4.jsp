<div class="page">
	<div class="pageContent">
		<form method="post" action="ajaxDone.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>客 户 号：</label>
					<input name="sn" type="text" size="30" value="100001" readonly="readonly"/>
				</p>
				<p>
					<label>客户名称：</label>
					<input name="name" class="required" type="text" size="30" value="" alt="请输入客户名称"/>
				</p>
				<p>
					<label>识 别 码：</label>
					<input name="code" class="digits" type="text" size="30" alt="请输入数字"/>
				</p>
				<p>
					<label>客户类型：</label>
					<input name="type" type="text" size="30" />
					<a class="inputButton" href="#">选择</a>
				</p>
				<p>
					<label>营业执照号：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>执照签发日期：</label>
					<input type="text" name="startDate" class="date" size="30" />
					<a class="inputDateButton" href="#">选择</a>
				</p>
				<p>
					<label>执照到期日期：</label>
					<input type="text" name="endDate" class="date" size="30" />
					<a class="inputDateButton" href="#">选择</a>
				</p>
				<p>
					<label>注册资金：</label>
					<input type="text" size="30" /><span class="unit">万元</span>
				</p>
				<p>
					<label>注册类型：</label>
					<input type="text" size="30" />
					<a class="inputButton" href="#">选择</a>
				</p>
				<p>
					<label>注册地址：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>所属行业：</label>
					<input type="text" size="30" />
					<a class="inputButton" href="#">选择</a>
				</p>
				<p>
					<label>组织机构代码：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>国税登记证号码：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>地税登记证号码：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>贷款卡编码：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>法人姓名：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>法人代表身份证号：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>身份证到期日期：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>其他证件及号码：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>曾用名称：</label>
					<input type="text" size="30" />
				</p>
				<p>
					<label>首次贷款日期：</label>
					<input readonly="readonly" type="text" size="30" />
				</p>
				<div class="divider"></div>
				<p>
					<label>建档日期：</label>
					<input readonly="readonly" type="text" size="30" />
				</p>
				<p>
					<label>管户经理：</label>
					<input readonly="readonly" type="text" size="30" />
				</p>
				<p>
					<label>最新修改时间：</label>
					<input readonly="readonly" type="text" size="30" />
				</p>
				<p>
					<label>最新修改人员：</label>
					<input readonly="readonly" type="text" size="30" />
				</p>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="#"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>