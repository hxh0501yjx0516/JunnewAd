<script type="text/javascript">
function testConfirmMsg(accountId){
	alertMsg.confirm("您修改的资料未保存，请选择保存或取消！", {
		okCall: function(){
			$.post("ajaxDone.html", {
				accountId: accountId
			}, DWZ.ajaxDone, "json");
		}
	});
}
</script>

<h2 class="contentTitle">提示对话框演示</h2>

<div style="padding:0 10px;">
	
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li class="selected"><a href="#"><span>示例</span></a></li>
					<li><a href="#"><span>代码</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" layouth="100">
			<div>
				<a class="button" href="#" onclick="testConfirmMsg('accountId')"><span>确认（是/否）</span></a><br /><br /><br />
				<a class="button" href="#" onclick="alertMsg.error('您提交的数据有误，请检查后重新提交！')"><span>错误提示</span></a><br /><br /><br />
				<a class="button" href="#" onclick="alertMsg.info('您提交的数据有误，请检查后重新提交！')"><span>信息提示</span></a><br /><br /><br />
				<a class="button" href="#" onclick="alertMsg.warn('您提交的数据有误，请检查后重新提交！')"><span>警告提示</span></a><br /><br /><br />
				<a class="button" href="#" onclick="alertMsg.correct('您的数据提交成功！')"><span>成功提示</span></a><br /><br />
			</div>
			
			<div>
				<textarea name="textarea" cols="100" rows="15">
				<script type="text/javascript">
function testConfirmMsg(accountId){
	alertMsg.confirm("您修改的资料未保存，请选择保存或取消！", {
		okCall: function(){
			$.post("ajaxDone.html", {accountId: accountId}, ajaxDone, "json");
		}
	});
}
</script>

<a class="button" href="#" onclick="testConfirmMsg('accountId')"><span>确认（是/否）</span></a><br /><br />
<a class="button" href="#" onclick="alertMsg.error('您提交的数据有误，请检查后重新提交！')"><span>错误提示</span></a><br /><br />
<a class="button" href="#" onclick="alertMsg.info('您提交的数据有误，请检查后重新提交！')"><span>信息提示</span></a><br /><br />
<a class="button" href="#" onclick="alertMsg.warn('您提交的数据有误，请检查后重新提交！')"><span>警告提示</span></a><br /><br />
<a class="button" href="#" onclick="alertMsg.correct('您的数据提交成功！')"><span>成功提示</span></a><br /><br />
				</textarea>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>

</div>