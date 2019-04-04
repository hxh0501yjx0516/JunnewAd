package com.pancou.ad.util;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;

@Entity
public class ShowDirTree {
	private static void tree(File f, List<Map<String,Object>> treeList)
    {
     File[] childs=f.listFiles();//�г�Ŀ¼�����е��ļ�
     for(int i=0;i<childs.length;i++) 
     {
      List<Map<String,Object>> dateList = new ArrayList<Map<String,Object>>();
//      System.out.println(childs[i].getPath());
//      System.out.println(childs[i].getName());
      Map<String,Object> map = new HashMap<String, Object>();
 	   map.put("path", childs[i].getPath());
 	   map.put("name", childs[i].getName());
      if(childs[i].isDirectory())
      {
       tree(childs[i],dateList);//����Ǹ�Ŀ¼,�ͼ���ݹ��г���������ļ�.
      }
	  map.put("subDir",dateList);
	  treeList.add(map);
     }
    }
	@SuppressWarnings("unused")
	public static List<Map<String,Object>> getSubFiles(String path)
    {
		List<Map<String,Object>> dateList = new ArrayList<Map<String,Object>>();
		try {
			if("".equals(path)){
				path = Configure.get("upload.img.url")+File.separator+FileManager.UPLOAD_PATH+File.separator+FileManager.UPLOAD_AE_PATH;
			}
		File f = new File(path);
		File[] childs=f.listFiles();//�г�Ŀ¼�����е��ļ�
		 for(int i=0;i<childs.length;i++) 
		 {
		  Map<String,Object> map = new HashMap<String, Object>();
		   map.put("name", childs[i].getName());
		   map.put("path", childs[i].getPath());
		   map.put("size", childs[i].length()/1024 == 0?"1":childs[i].length()/1024);
		   map.put("lastTime",DatetimeHandle.formatDate(childs[i].lastModified()));
		   if(childs[i].isDirectory())
	      {
		   map.put("type","0");
	      }else {
		   map.put("type","1");
		   map.put("suffix",childs[i].getName().substring( childs[i].getName().lastIndexOf(".")+1));
	      }
		   dateList.add(map);
		 }
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return dateList;
    }
	 public static List<Map<String,Object>>  showTree(String subDir){
		 File file = new File(Configure.get("upload.img.url")+File.separator+FileManager.UPLOAD_PATH+File.separator+FileManager.UPLOAD_AE_PATH+File.separator+subDir);
		 List<Map<String,Object>> treeList = new ArrayList<Map<String,Object>>();
		 tree(file,treeList);
		 //show(treeList);
		 return treeList;
	 }
	private static void show(List<Map<String,Object>> treeList){
		for(Map<String,Object> map:treeList){
			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String next = it.next();
				if("name".equals(next)){
					if(((List)map.get("subDir")).size()>0){
						
						//System.out.println("+"+map.get(next)+"("+map.get("path")+")");
					}else{
						//System.out.println("  |--"+map.get(next)+"("+map.get("path")+")");
					}
				}
				if("subDir".equals(next)){
					show((List<Map<String,Object>>)(map.get(next)));
				}
			}
		}
	}
	public static void doMain(){
		 File file =new File("E://upload/img/ae");
		 List<Map<String,Object>> treeList = new ArrayList<Map<String,Object>>();
		 tree(file,treeList);
		 show(treeList);
//		 Map<String,Object> map = new HashMap<String, Object>();
//		 map.put("path", "E://upload/img/ae");
//		 Map<String,Object> trees =  tree(map);
//		 show1(trees);
	 }
	 public static void main(String[] args){
		 File file =new File("E://upload/img/ae");
		 List<Map<String,Object>> treeList = new ArrayList<Map<String,Object>>();
		 tree(file,treeList);
		 show(treeList);
//		 Map<String,Object> map = new HashMap<String, Object>();
//		 map.put("path", "E://upload/img/ae");
//		 Map<String,Object> trees =  tree(map);
//		 show1(trees);
	 }
//	 private static void show1(Map<String,Object> map){
//			Set<String> set = map.keySet();
//			Iterator<String> it = set.iterator();
//			while(it.hasNext()){
//				String next = it.next();
//				if("name".equals(next)){
//					if(((List)map.get("subDir")).size()>0){
//						
//						System.out.println("+"+map.get(next)+"("+map.get("path")+")");
//					}else{
//						System.out.println("  |--"+map.get(next)+"("+map.get("path")+")");
//					}
//				}
//				if("subDir".equals(next)){
//					show1(map);
//				}
//			}
//	 }
}
