package com.pancou.ad.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import javax.persistence.Entity;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

@Entity
public class InCompressUtil {
	  /** 
     *  
     * @param inputFileName ����һ���ļ��� 
     * @param zipFileName   ���һ��ѹ���ļ��У������ļ����� 
     * @throws Exception 
     */  
    public static void zip(String inputFileName, String zipFileName) throws Exception {  
        zip(zipFileName, new File(inputFileName));  
    }  
  
    private static void zip(String zipFileName, File inputFile) throws Exception {  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
                zipFileName));
        out.setEncoding("GBK");
        zip(out, inputFile, "");  
//        System.out.println("zip done!"); 
        out.close();  
    }  
  
    private static void zip(ZipOutputStream out, File f, String base) throws Exception { 
        if (f.isDirectory()) {  //�ж��Ƿ�ΪĿ¼  
            File[] fl = f.listFiles();  
            out.putNextEntry(new ZipEntry(base + "/"));  
            base = base.length() == 0 ? "" : base + "/";  
            for (int i = 0; i < fl.length; i++) {  
                zip(out, fl[i], base + fl[i].getName());  
            }  
        } else {                //ѹ��Ŀ¼�е������ļ�  
            out.putNextEntry(new ZipEntry(base));  
            FileInputStream in = new FileInputStream(f); 
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "GBK"), 10 * 1024 * 10);
            int b;  
//            System.out.println(base);  
            while ((b = br.read()) != -1) { 
                out.write(b);  
            }  
            br.close();  
        }  
    }  
	  public static void main(String[] args) {     
		  String inputFileName = "E:\\upload\\img\\ae";
		  String zipFileName = "E:\\upload\\img\\ae.rar"; 
		  try {
			zip(inputFileName,zipFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    } 
		    
}
