<h2 class="contentTitle">日历控件</h2>

<div class="pageFormContent" layouth="56">
	<p>
		<label>默认格式：</label>
		<input type="text" name="date1" class="date" />
		<a class="inputDateButton" href="#">选择</a>
		<span class="info">yyyy-mm-dd</span>
	</p>
	<p>
		<label>定义年份：</label>
		<input type="text" name="date2" class="date" yearstart="-80" yearend="5"/>
		<a class="inputDateButton" href="#">选择</a>
	</p>
	<p>
		<label>自定义日期格式：</label>
		<input type="text" name="date2" class="date" pattern="yyyy/mm/dd" />
		<a class="inputDateButton" href="#">选择</a>
		<span class="info">yyyy/mm/dd</span>
	</p>
	<p>
		<label>自定义日期格式：</label>
		<input type="text" name="date2" class="date" pattern="dd/mm/yyyy" yearStart="-20" yearEnd="5"/>
		<a class="inputDateButton" href="#">选择</a>
		<span class="info">dd/mm/yyyy</span>
	</p>
</div>

