function insertplanTask(vtable, rows,vdateTime,vIp) {
	var dateTime = vdateTime || "";
	var ip = vIp || "";
	this.table = document.getElementById(vtable);
	if (!isNaN(rows)) {
		for ( var j = 0; j < rows; j++) {
			this.tr = document.createElement("TR");
			this.table.appendChild(this.tr);
			for ( var i = 1; i <= 4; i++) {
				var td = document.createElement("TD")
				td.style.cssText = "border:1px solid #ededed;";
				switch (i) {
				case 1:
					td.innerHTML = this.tr.rowIndex;
					break;
				case 2:
					var input2 = document.createElement("INPUT");
					input2.id = "dateTime_" + this.tr.rowIndex;
					input2.name = "dateTime_" + this.tr.rowIndex;
					input2.type = "text";
					input2.className = "date required";
					input2.pattern = "yyyy-MM-dd";
					input2.readOnly = true;
					input2.value = dateTime;
					input2.style.cssText = "float:left;";

					var a = document.createElement("A");
					a.href = "javascript:void(0);";
					a.innerHTML = "选择";
					a.className = "inputDateButton";
					a.style.cssText = "float:left";
					td.appendChild(input2);

					td.appendChild(a);
					break;
				case 3:
					var input3 = document.createElement("INPUT");
					input3.id = "flow_" + this.tr.rowIndex;
					input3.name = "flow_" + this.tr.rowIndex;
					input3.className = "required number";
					input3.value=ip;
					td.appendChild(input3);
					break;
				case 4:
					var a = document.createElement("A");
					a.href = "javascript:void(0);";
					a.innerHTML = "删除";
					a.className = "btnDel";
					a.onclick = deletePlanTask;
					td.appendChild(a);
					break;
				}
				this.tr.appendChild(td);
			}
		}
		$(document).ready(function() {  
        $('.date').datepicker();  
    }); 
	}
}
function deletePlanTask() {
	var rowIndex = event.srcElement.parentNode.parentNode.rowIndex - 1;
	var table = event.srcElement.parentNode.parentNode.parentNode;
	table.deleteRow(rowIndex);
	if (table.rows.length >= 1) {
		for ( var i = 0; i < table.rows.length; i++) {
			table.rows[i].cells[0].innerHTML = i + 1;
		}
	}
}