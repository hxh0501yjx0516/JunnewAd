package com.pancou.ad.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

@Entity
public class ReadExcel {
	
	private static InputStream is = null;
	private static Workbook rwb = null;

	static String createTableSql = "";// ������ݿ��sql
	static String colType = "TEXT";// �ֶ�����
	static String key = "id";// ����
	static String charSet = "utf8";// ����ַ�����
	static String ENGINE = "InnoDB";// �������
	static String tableName = "second1";// �����
	static String colName = "col";// Ĭ���ֶ���
	static Connection conn = null;

	/**
	 * ȡ����ݺ����ArrayList��
	 * @param fileName Excel�ļ�·������
	 * @return ���� ArrayList
	 */
	public static ArrayList readExcel(HttpServletRequest request, String fileName) {
		
		ArrayList<HashMap<Integer,String>> al = new ArrayList<HashMap<Integer,String>>();
		try {
			is = new FileInputStream(fileName);// ��������
			rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0); // ��ȡ��һ��sheet
			int colNum = rs.getColumns();// ����
			int rowNum = rs.getRows();// ����

			String strValue = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			for (int i = 1; i < rowNum; i++) {
				strValue = "";
				HashMap<Integer,String> hs = new HashMap<Integer,String>();
				for (int j = 0; j < colNum; j++) {
					Cell c = rs.getCell(j, i);
					if ( j == 0 ) {
						DateCell datec00 = (DateCell) rs.getCell(0, i);
						//System.out.println("UUUUUUUUUUUUUUUu"+sdf.format(datec00.getDate()));
						strValue = sdf.format(datec00.getDate());	
					
					} else {
						
						strValue = c.getContents();						
					}
					hs.put(j, strValue);
				}
				al.add(hs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			// �ر� workbook
			if (rwb != null) {
				rwb.close();
			}
            if(is !=null){
                try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
			// �����ļ���ɾ����ļ�
			Common.deleteFile(fileName);
			// ���session�е� �ļ���
			request.getSession().setAttribute("fileName", "");
		}
		return al;
	}

}
