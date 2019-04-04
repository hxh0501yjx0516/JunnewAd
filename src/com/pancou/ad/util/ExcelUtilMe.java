package com.pancou.ad.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Entity
public class ExcelUtilMe {
	private static FileInputStream is = null;
	private static Workbook wb = null;
	
	
	/**
	 * 写入excel
	 * @param ds
	 * @param labels
	 * @param fileName
	 * @param os
	 * @throws Exception
	 */
	public static void exportExcel(List<Object[]> ds,List<String> labels,String fileName,OutputStream os) throws Exception{
		try { 
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
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	
	/**
	 * 返回 ae excel list数据 
	 * @param filePath
	 * @return
	 */
	public static ArrayList<HashMap<Integer,String>> readExcel(String filePath){
		ArrayList<HashMap<Integer,String>> list = new ArrayList<HashMap<Integer,String>>();
		File file = new File(filePath);
		if (file.exists()){
			
			try {
				is = new FileInputStream(filePath);
				wb = Workbook.getWorkbook(is);
				Sheet sheet = wb.getSheet(0);
				int rows = sheet.getRows();
				int cols = sheet.getColumns();
				
				String cellContent = "";
				for (int i = 1; i < rows; i++){
					HashMap<Integer,String> map = new HashMap<Integer,String>();
					cellContent = "";
					for (int j = 0; j < cols; j++){
						Cell cell = sheet.getCell(j,i);
						cellContent = cell.getContents();
//						if (j == 10){
//							System.out.println("date:" + sheet.getCell(j, i).getContents());
//							DateCell dc = (DateCell)sheet.getCell(j, i);
//							map.put(j, sdf.format(dc.getDate()));
//						} else {
							map.put(j, cellContent);
//						}
						
					}
					list.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (wb != null){
					wb.close();
				}
				if (is != null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				ExcelUtilMe.delExcel(filePath);
			}
		}
		return list;
	}
	
	/**
	 * DEL FILE
	 * @param filePath
	 * @return
	 */
	public static void delExcel(String filePath){
		File file = new File(filePath);
		if (file.isFile()){
			file.delete();
		}
	}
	
	public static void main(String[] args){
		String filePath = "e://home/pancou/upload/fanli/excel/ae/2012/3/1334741041499.xls";
		ExcelUtilMe.delExcel(filePath);
	}
}
