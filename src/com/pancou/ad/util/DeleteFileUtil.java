package com.pancou.ad.util;

import java.io.File;
import javax.persistence.Entity;

@Entity
public class DeleteFileUtil {     
    /**   
     * ɾ���ļ��������ǵ����ļ����ļ���   
     * @param   fileName    ��ɾ����ļ���   
     * @return �ļ�ɾ��ɹ�����true,���򷵻�false   
     */    
    public static boolean delete(String fileName){     
        File file = new File(fileName);     
        if(!file.exists()){     
//            System.out.println("ɾ���ļ�ʧ�ܣ�"+fileName+"�ļ�������");     
            return false;     
        }else{     
            if(file.isFile()){     
                     
                return deleteFile(fileName);     
            }else{     
                return deleteDirectory(fileName);     
            }     
        }     
    }     
         
    /**   
     * ɾ����ļ�   
     * @param   fileName    ��ɾ���ļ����ļ���   
     * @return �����ļ�ɾ��ɹ�����true,���򷵻�false   
     */    
    private static boolean deleteFile(String fileName){     
        File file = new File(fileName);     
        if(file.isFile() && file.exists()){     
            file.delete();     
//            System.out.println("ɾ����ļ�"+fileName+"�ɹ���");     
            return true;     
        }else{     
//            System.out.println("ɾ����ļ�"+fileName+"ʧ�ܣ�");     
            return false;     
        }     
    }     
         
    /**   
     * ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ�   
     * @param   dir ��ɾ��Ŀ¼���ļ�·��   
     * @return  Ŀ¼ɾ��ɹ�����true,���򷵻�false   
     */    
    private static boolean deleteDirectory(String dir){     
        //���dir�����ļ��ָ����β���Զ�����ļ��ָ���     
        if(!dir.endsWith(File.separator)){     
            dir = dir+File.separator;     
        }     
        File dirFile = new File(dir);     
        //���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�     
        if(!dirFile.exists() || !dirFile.isDirectory()){     
//            System.out.println("ɾ��Ŀ¼ʧ��"+dir+"Ŀ¼�����ڣ�");     
            return false;     
        }     
        boolean flag = true;     
        //ɾ���ļ����µ������ļ�(������Ŀ¼)     
        File[] files = dirFile.listFiles();     
        for(int i=0;i<files.length;i++){     
            //ɾ�����ļ�     
            if(files[i].isFile()){     
                flag = deleteFile(files[i].getAbsolutePath());     
                if(!flag){     
                    break;     
                }     
            }     
            //ɾ����Ŀ¼     
            else{     
                flag = deleteDirectory(files[i].getAbsolutePath());     
                if(!flag){     
                    break;     
                }     
            }     
        }     
             
        if(!flag){     
//            System.out.println("ɾ��Ŀ¼ʧ��");     
            return false;     
        }     
             
        //ɾ��ǰĿ¼     
        if(dirFile.delete()){     
//            System.out.println("ɾ��Ŀ¼"+dir+"�ɹ���");     
            return true;     
        }else{     
//            System.out.println("ɾ��Ŀ¼"+dir+"ʧ�ܣ�");     
            return false;     
        }     
    }     
         
    public static void main(String[] args) {     
        //String fileName = "g:/temp/xwz.txt";     
        //DeleteFileUtil.deleteFile(fileName);     
        String fileDir = "G:/temp/temp0/temp1";     
        //DeleteFileUtil.deleteDirectory(fileDir);     
        DeleteFileUtil.delete(fileDir);     
             
    }     
}    