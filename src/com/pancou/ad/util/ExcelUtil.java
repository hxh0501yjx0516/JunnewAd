package com.pancou.ad.util;

import java.io.OutputStream;
import java.util.List;

import javax.persistence.Entity;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Entity
public class ExcelUtil {
	public static void export(List<Object[]> ds,List<String> labels,String fileName,OutputStream os) throws Exception{
		try 
		{ 
			WritableWorkbook wwb = Workbook.createWorkbook(os); 
			WritableSheet ws = wwb.createSheet("report", 0); 
			int i = 0;
			for(String label : labels){	
				Label labelC = new Label(i++, 0, label); 
				ws.addCell(labelC); 
			}
			for(int j=0;j<ds.size();j++){
				Object[] datas = ds.get(j);
				for(int m = 0;m<datas.length;m++){
					if(datas[m]!=null){
						Label data = new Label(m, j+1, datas[m].toString());
						ws.addCell(data);
					}
				}
			}
			wwb.write(); 
			wwb.close(); 
		}
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
	}
}
