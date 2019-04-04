<form id="pagerForm" method="post" action="demo_page1.html">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
		<input type="hidden" id="selectedId_demo"/>
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>我的客户：</label>
					<input type="text" />
				</li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
					<li><a class="button" href="demo_page6.html" target="dialog" rel="dlg_page1" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>
				<li><a class="delete" href="ajaxDone.html" target="navTabTodo" title="确定要删除吗?"><span>删除</span></a></li>
				<li><a class="edit" href="demo_page4.html" target="navTab"><span>修改</span></a></li>
				<li class="line">line</li>
				<li><a class="icon" href="#"><span>导入EXCEL</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="142">
			<thead>
				<tr>
					<th style="width:80px;"></th>
					<th style="width:120px;">客户号</th>
					<th style="width:140px;">客户名称</th>
					<th style="width:100px;">客户类型</th>
					<th style="width:150px;">证件号码</th>
					<th style="width:50px;">信用等级</th>
					<th style="width:75px;">所属行业</th>
					<th style="width:60px;">建档日期</th>
				</tr>
			</thead>
			<tbody>
				<tr target="selectedId_demo" rel="123456">
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
				<tr>
					<td>天津农信社</td>
					<td>A120113196309052434</td>
					<td>天津市华建装饰工程有限公司</td>
					<td>联社营业部</td>
					<td>29385739203816293</td>
					<td>5级</td>
					<td>政府机构</td>
					<td>2009-05-21</td>
				</tr>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
				<span>条，共${totalCount}条</span>
			</div>
			
			<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

		</div>
	</div>
</div>