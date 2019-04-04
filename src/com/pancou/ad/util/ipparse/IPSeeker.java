package com.pancou.ad.util.ipparse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.apache.log4j.Level;
@Entity
public class IPSeeker {
	//����IP��ݿ���   
    private String IP_FILE="QQWry.Dat";   
    //������ļ���   
    private String INSTALL_DIR="f:/qqwry";
       
       
    // һЩ�̶������������¼���ȵȵ�   
    private static final int IP_RECORD_LENGTH = 7;   
    private static final byte REDIRECT_MODE_1 = 0x01;   
    private static final byte REDIRECT_MODE_2 = 0x02;   
       
    // ������Ϊcache����ѯһ��ipʱ���Ȳ鿴cache���Լ��ٲ���Ҫ���ظ�����   
    private Map<String, IPLocation> ipCache;   
    // ����ļ�������   
    private RandomAccessFile ipFile;   
    // �ڴ�ӳ���ļ�   
    private MappedByteBuffer mbb;   
    // ��ʼ����Ŀ�ʼ�ͽ���ľ��ƫ��   
    private long ipBegin, ipEnd;   
    // Ϊ���Ч�ʶ���õ���ʱ����   
    @ManyToOne
	private IPLocation loc;   
    private byte[] buf;   
    private byte[] b4;   
    private byte[] b3;   
       
    protected IPSeeker() {
	}


	public IPSeeker(String fileName,String dir){
        this.INSTALL_DIR=dir;   
        this.IP_FILE=fileName;   
        ipCache = new HashMap<String, IPLocation>();   
        loc = new IPLocation();   
        buf = new byte[100];   
        b4 = new byte[4];   
        b3 = new byte[3];   
        try {   
            ipFile = new RandomAccessFile(IP_FILE, "r");   
        } catch (FileNotFoundException e) {   
            // ����Ҳ�������ļ����ٳ����ٵ�ǰĿ¼�����������ȫ������Сд�ļ���   
            //     ��Ϊ��Щϵͳ������ִ�Сд�����Ҳ���ip��ַ��Ϣ�ļ�   
            String filename = new File(IP_FILE).getName().toLowerCase();   
            File[] files = new File(INSTALL_DIR).listFiles();   
            for(int i = 0; i < files.length; i++) {   
                if(files[i].isFile()) {   
                    if(files[i].getName().toLowerCase().equals(filename)) {   
                        try {   
                            ipFile = new RandomAccessFile(files[i], "r");   
                        } catch (FileNotFoundException e1) {   
                            LogFactory.log("IP��ַ��Ϣ�ļ�û���ҵ���IP��ʾ���ܽ��޷�ʹ��",Level.ERROR,e1);   
                            ipFile = null;   
                        }   
                        break;   
                    }   
                }   
            }   
        }    
        // �����ļ��ɹ�����ȡ�ļ�ͷ��Ϣ   
        if(ipFile != null) {   
            try {   
                ipBegin = readLong4(0);   
                ipEnd = readLong4(4);   
                if(ipBegin == -1 || ipEnd == -1) {   
                    ipFile.close();   
                    ipFile = null;   
                }              
            } catch (IOException e) {   
                LogFactory.log("IP��ַ��Ϣ�ļ���ʽ�д���IP��ʾ���ܽ��޷�ʹ��",Level.ERROR,e);   
                ipFile = null;   
            }              
        }   
    }   
       
       
    /**  
     * ��һ���ص�Ĳ���ȫ���֣��õ�һϵ�а�s�Ӵ���IP��Χ��¼  
     * @param s �ص��Ӵ�  
     * @return ��IPEntry���͵�List  
     */   
    public List getIPEntriesDebug(String s) {   
        List<IPEntry> ret = new ArrayList<IPEntry>();   
        long endOffset = ipEnd + 4;   
        for(long offset = ipBegin + 4; offset <= endOffset; offset += IP_RECORD_LENGTH) {   
            // ��ȡ����IPƫ��   
            long temp = readLong3(offset);   
            // ���temp������-1����ȡIP�ĵص���Ϣ   
            if(temp != -1) {   
                IPLocation ipLoc = getIPLocation(temp);   
                // �ж��Ƿ�����ص��������s�Ӵ��������ˣ���������¼��List�У����û�У�����   
                if(ipLoc.getCountry().indexOf(s) != -1 || ipLoc.getArea().indexOf(s) != -1) {   
                    IPEntry entry = new IPEntry();   
                    entry.country = ipLoc.getCountry();   
                    entry.area = ipLoc.getArea();   
                    // �õ���ʼIP   
                    readIP(offset - 4, b4);   
                    entry.beginIp = Util.getIpStringFromBytes(b4);   
                    // �õ�����IP   
                    readIP(temp, b4);   
                    entry.endIp = Util.getIpStringFromBytes(b4);   
                    // ��Ӹü�¼   
                    ret.add(entry);   
                }   
            }   
        }   
        return ret;   
    }   
       
    public IPLocation getIPLocation(String ip){   
        IPLocation location=new IPLocation();   
        location.setArea(this.getArea(ip));   
        location.setCountry(this.getCountry(ip));   
        return location;   
    }   
       
    /**  
     * ��һ���ص�Ĳ���ȫ���֣��õ�һϵ�а�s�Ӵ���IP��Χ��¼  
     * @param s �ص��Ӵ�  
     * @return ��IPEntry���͵�List  
     */   
    public List<IPEntry> getIPEntries(String s) {   
        List<IPEntry> ret = new ArrayList<IPEntry>();   
        try {   
            // ӳ��IP��Ϣ�ļ����ڴ���   
            if(mbb == null) {   
                FileChannel fc = ipFile.getChannel();   
                mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, ipFile.length());   
                mbb.order(ByteOrder.LITTLE_ENDIAN);                
            }   
               
            int endOffset = (int)ipEnd;   
            for(int offset = (int)ipBegin + 4; offset <= endOffset; offset += IP_RECORD_LENGTH) {   
                int temp = readInt3(offset);   
                if(temp != -1) {   
                    IPLocation ipLoc = getIPLocation(temp);   
                    // �ж��Ƿ�����ص��������s�Ӵ��������ˣ���������¼��List�У����û�У�����   
                    if(ipLoc.getCountry().indexOf(s) != -1 || ipLoc.getArea().indexOf(s) != -1) {   
                        IPEntry entry = new IPEntry();   
                        entry.country = ipLoc.getCountry();   
                        entry.area = ipLoc.getArea();   
                        // �õ���ʼIP   
                        readIP(offset - 4, b4);   
                        entry.beginIp = Util.getIpStringFromBytes(b4);   
                        // �õ�����IP   
                        readIP(temp, b4);   
                        entry.endIp = Util.getIpStringFromBytes(b4);   
                        // ��Ӹü�¼   
                        ret.add(entry);   
                    }   
                }   
            }              
        } catch (IOException e) {   
            LogFactory.log("",Level.ERROR,e);   
        }   
        return ret;   
    }   
   
    /**  
     * ���ڴ�ӳ���ļ���offsetλ�ÿ�ʼ��3���ֽڶ�ȡһ��int  
     * @param offset  
     * @return  
     */   
    private int readInt3(int offset) {   
        mbb.position(offset);   
        return mbb.getInt() & 0x00FFFFFF;   
    }   
   
    /**  
     * ���ڴ�ӳ���ļ��ĵ�ǰλ�ÿ�ʼ��3���ֽڶ�ȡһ��int  
     * @return  
     */   
    private int readInt3() {   
        return mbb.getInt() & 0x00FFFFFF;   
    }   
       
    /**  
     * ���IP�õ������  
     * @param ip ip���ֽ�������ʽ  
     * @return ������ַ�  
     */   
    public String getCountry(byte[] ip) {   
        // ���ip��ַ�ļ��Ƿ���   
        if(ipFile == null)    
            return Message.bad_ip_file;
        // ����ip��ת��ip�ֽ�����Ϊ�ַ���ʽ   
        String ipStr = Util.getIpStringFromBytes(ip);   
        // �ȼ��cache���Ƿ��Ѿ��������ip�Ľ��û���������ļ�   
        if(ipCache.containsKey(ipStr)) {   
            IPLocation ipLoc = ipCache.get(ipStr);   
            return ipLoc.getCountry();   
        } else {   
            IPLocation ipLoc = getIPLocation(ip);   
            ipCache.put(ipStr, ipLoc.getCopy());   
            return ipLoc.getCountry();   
        }   
    }   
       
    /**  
     * ���IP�õ������  
     * @param ip IP���ַ���ʽ  
     * @return ������ַ�  
     */   
    public String getCountry(String ip) {   
        return getCountry(Util.getIpByteArrayFromString(ip));   
    }   
       
    /**  
     * ���IP�õ�������  
     * @param ip ip���ֽ�������ʽ  
     * @return �������ַ�  
     */   
    public String getArea(byte[] ip) {   
        // ���ip��ַ�ļ��Ƿ���   
        if(ipFile == null)    
            return Message.bad_ip_file;   
        // ����ip��ת��ip�ֽ�����Ϊ�ַ���ʽ   
        String ipStr = Util.getIpStringFromBytes(ip);   
        // �ȼ��cache���Ƿ��Ѿ��������ip�Ľ��û���������ļ�   
        if(ipCache.containsKey(ipStr)) {   
            IPLocation ipLoc = ipCache.get(ipStr);   
            return ipLoc.getArea();   
        } else {   
            IPLocation ipLoc = getIPLocation(ip);   
            ipCache.put(ipStr, ipLoc.getCopy());   
            return ipLoc.getArea();   
        }   
    }   
       
    /**  
     * ���IP�õ�������  
     * @param ip IP���ַ���ʽ  
     * @return �������ַ�  
     */   
    public String getArea(String ip) {   
        return getArea(Util.getIpByteArrayFromString(ip));   
    }   
       
    /**  
     * ���ip����ip��Ϣ�ļ����õ�IPLocation�ṹ����������ip��������Աip�еõ�  
     * @param ip Ҫ��ѯ��IP  
     * @return IPLocation�ṹ  
     */   
    private IPLocation getIPLocation(byte[] ip) {   
        IPLocation info = null;   
        long offset = locateIP(ip);   
        if(offset != -1)   
            info = getIPLocation(offset);   
        if(info == null) {
            info = new IPLocation();   
            info.setCountry (  Message.unknown_country);   
            info.setArea(Message.unknown_area);   
        }   
        return info;   
    }      
   
    /**  
     * ��offsetλ�ö�ȡ4���ֽ�Ϊһ��long����ΪjavaΪbig-endian��ʽ������û�취  
     * ������ôһ����������ת��  
     * @param offset  
     * @return ��ȡ��longֵ������-1��ʾ��ȡ�ļ�ʧ��  
     */   
    private long readLong4(long offset) {   
        long ret = 0;   
        try {   
            ipFile.seek(offset);   
            ret |= (ipFile.readByte() & 0xFF);   
            ret |= ((ipFile.readByte() << 8) & 0xFF00);   
            ret |= ((ipFile.readByte() << 16) & 0xFF0000);   
            ret |= ((ipFile.readByte() << 24) & 0xFF000000);   
            return ret;   
        } catch (IOException e) {   
            return -1;   
        }   
    }   
   
    /**  
     * ��offsetλ�ö�ȡ3���ֽ�Ϊһ��long����ΪjavaΪbig-endian��ʽ������û�취  
     * ������ôһ����������ת��  
     * @param offset �������ʼƫ��  
     * @return ��ȡ��longֵ������-1��ʾ��ȡ�ļ�ʧ��  
     */   
    private long readLong3(long offset) {   
        long ret = 0;   
        try {   
            ipFile.seek(offset);   
            ipFile.readFully(b3);   
            ret |= (b3[0] & 0xFF);   
            ret |= ((b3[1] << 8) & 0xFF00);   
            ret |= ((b3[2] << 16) & 0xFF0000);   
            return ret;   
        } catch (IOException e) {   
            return -1;   
        }   
    }      
       
    /**  
     * �ӵ�ǰλ�ö�ȡ3���ֽ�ת����long  
     * @return ��ȡ��longֵ������-1��ʾ��ȡ�ļ�ʧ��  
     */   
    private long readLong3() {   
        long ret = 0;   
        try {   
            ipFile.readFully(b3);   
            ret |= (b3[0] & 0xFF);   
            ret |= ((b3[1] << 8) & 0xFF00);   
            ret |= ((b3[2] << 16) & 0xFF0000);   
            return ret;   
        } catch (IOException e) {   
            return -1;   
        }   
    }   
     
    /**  
     * ��offsetλ�ö�ȡ�ĸ��ֽڵ�ip��ַ����ip�����У���ȡ���ipΪbig-endian��ʽ������  
     * �ļ�����little-endian��ʽ���������ת��  
     * @param offset  
     * @param ip  
     */   
    private void readIP(long offset, byte[] ip) {   
        try {   
            ipFile.seek(offset);   
            ipFile.readFully(ip);   
            byte temp = ip[0];   
            ip[0] = ip[3];   
            ip[3] = temp;   
            temp = ip[1];   
            ip[1] = ip[2];   
            ip[2] = temp;   
        } catch (IOException e) {   
            LogFactory.log("",Level.ERROR,e);   
        }   
    }   
       
    /**  
     * ��offsetλ�ö�ȡ�ĸ��ֽڵ�ip��ַ����ip�����У���ȡ���ipΪbig-endian��ʽ������  
     * �ļ�����little-endian��ʽ���������ת��  
     * @param offset  
     * @param ip  
     */   
    private void readIP(int offset, byte[] ip) {   
        mbb.position(offset);   
        mbb.get(ip);   
        byte temp = ip[0];   
        ip[0] = ip[3];   
        ip[3] = temp;   
        temp = ip[1];   
        ip[1] = ip[2];   
        ip[2] = temp;   
    }   
       
    /**  
     * �����Աip��beginIp�Ƚϣ�ע�����beginIp��big-endian��  
     * @param ip Ҫ��ѯ��IP  
     * @param beginIp �ͱ���ѯIP��Ƚϵ�IP  
     * @return ��ȷ���0��ip����beginIp�򷵻�1��С�ڷ���-1��  
     */   
    private int compareIP(byte[] ip, byte[] beginIp) {   
        for(int i = 0; i < 4; i++) {   
            int r = compareByte(ip[i], beginIp[i]);   
            if(r != 0)   
                return r;   
        }   
        return 0;   
    }   
       
    /**  
     * ������byte�����޷������бȽ�  
     * @param b1  
     * @param b2  
     * @return ��b1����b2�򷵻�1����ȷ���0��С�ڷ���-1  
     */   
    private int compareByte(byte b1, byte b2) {   
        if((b1 & 0xFF) > (b2 & 0xFF)) // �Ƚ��Ƿ����   
            return 1;   
        else if((b1 ^ b2) == 0)// �ж��Ƿ����   
            return 0;   
        else    
            return -1;   
    }   
       
    /**  
     * ������������ip�����ݣ���λ�������ip��ҵ���ļ�¼��������һ�����ƫ��  
     * ����ʹ�ö��ַ����ҡ�  
     * @param ip Ҫ��ѯ��IP  
     * @return ����ҵ��ˣ����ؽ���IP��ƫ�ƣ����û���ҵ�������-1  
     */   
    private long locateIP(byte[] ip) {   
        long m = 0;   
        int r;   
        // �Ƚϵ�һ��ip��   
        readIP(ipBegin, b4);   
        r = compareIP(ip, b4);   
        if(r == 0) return ipBegin;   
        else if(r < 0) return -1;   
        // ��ʼ��������   
        for(long i = ipBegin, j = ipEnd; i < j; ) {   
            m = getMiddleOffset(i, j);   
            readIP(m, b4);   
            r = compareIP(ip, b4);   
            // log.debug(Utils.getIpStringFromBytes(b));   
            if(r > 0)   
                i = m;   
            else if(r < 0) {   
                if(m == j) {   
                    j -= IP_RECORD_LENGTH;   
                    m = j;   
                } else    
                    j = m;   
            } else   
                return readLong3(m + 4);   
        }   
        // ���ѭ�������ˣ���ôi��j�ض�����ȵģ������¼Ϊ����ܵļ�¼�����ǲ���   
        //     �϶����ǣ���Ҫ���һ�£�����ǣ��ͷ��ؽ����ַ��ľ��ƫ��   
        m = readLong3(m + 4);   
        readIP(m, b4);   
        r = compareIP(ip, b4);   
        if(r <= 0) return m;   
        else return -1;   
    }   
       
    /**  
     * �õ�beginƫ�ƺ�endƫ���м�λ�ü�¼��ƫ��  
     * @param begin  
     * @param end  
     * @return  
     */   
    private long getMiddleOffset(long begin, long end) {   
        long records = (end - begin) / IP_RECORD_LENGTH;   
        records >>= 1;   
        if(records == 0) records = 1;   
        return begin + records * IP_RECORD_LENGTH;   
    }   
       
    /**  
     * ��һ��ip��ҵ����¼��ƫ�ƣ�����һ��IPLocation�ṹ  
     * @param offset ��Ҽ�¼����ʼƫ��  
     * @return IPLocation����  
     */   
    private IPLocation getIPLocation(long offset) {   
        try {   
            // ���4�ֽ�ip   
            ipFile.seek(offset + 4);   
            // ��ȡ��һ���ֽ��ж��Ƿ��־�ֽ�   
            byte b = ipFile.readByte();   
            if(b == REDIRECT_MODE_1) {   
                // ��ȡ���ƫ��   
                long countryOffset = readLong3();   
                // ��ת��ƫ�ƴ�   
                ipFile.seek(countryOffset);   
                // �ټ��һ�α�־�ֽڣ���Ϊ���ʱ������ط���Ȼ�����Ǹ��ض���   
                b = ipFile.readByte();   
                if(b == REDIRECT_MODE_2) {   
                    loc.setCountry (  readString(readLong3()));   
                    ipFile.seek(countryOffset + 4);   
                } else   
                    loc.setCountry ( readString(countryOffset));   
                // ��ȡ�����־   
                loc.setArea( readArea(ipFile.getFilePointer()));   
            } else if(b == REDIRECT_MODE_2) {   
                loc.setCountry ( readString(readLong3()));   
                loc.setArea( readArea(offset + 8));   
            } else {   
                loc.setCountry (  readString(ipFile.getFilePointer() - 1));   
                loc.setArea( readArea(ipFile.getFilePointer()));   
            }   
            return loc;   
        } catch (IOException e) {  
        	e.getStackTrace();
            return null;   
        }   
    }      
       
    /**  
     * ��һ��ip��ҵ����¼��ƫ�ƣ�����һ��IPLocation�ṹ���˷���Ӧ�����ڴ�ӳ���ļ���ʽ  
     * @param offset ��Ҽ�¼����ʼƫ��  
     * @return IPLocation����  
     */   
    private IPLocation getIPLocation(int offset) {   
        // ���4�ֽ�ip   
        mbb.position(offset + 4);   
        // ��ȡ��һ���ֽ��ж��Ƿ��־�ֽ�   
        byte b = mbb.get();   
        if(b == REDIRECT_MODE_1) {   
            // ��ȡ���ƫ��   
            int countryOffset = readInt3();   
            // ��ת��ƫ�ƴ�   
            mbb.position(countryOffset);   
            // �ټ��һ�α�־�ֽڣ���Ϊ���ʱ������ط���Ȼ�����Ǹ��ض���   
            b = mbb.get();   
            if(b == REDIRECT_MODE_2) {   
                loc.setCountry (  readString(readInt3()));   
                mbb.position(countryOffset + 4);   
            } else   
                loc.setCountry (  readString(countryOffset));   
            // ��ȡ�����־   
            loc.setArea(readArea(mbb.position()));   
        } else if(b == REDIRECT_MODE_2) {   
            loc.setCountry ( readString(readInt3()));   
            loc.setArea(readArea(offset + 8));   
        } else {   
            loc.setCountry (  readString(mbb.position() - 1));   
            loc.setArea(readArea(mbb.position()));   
        }   
        return loc;   
    }   
       
    /**  
     * ��offsetƫ�ƿ�ʼ����������ֽڣ�����һ��������  
     * @param offset �����¼����ʼƫ��  
     * @return �������ַ�  
     * @throws IOException  
     */   
    private String readArea(long offset) throws IOException {   
        ipFile.seek(offset);   
        byte b = ipFile.readByte();   
        if(b == REDIRECT_MODE_1 || b == REDIRECT_MODE_2) {   
            long areaOffset = readLong3(offset + 1);   
            if(areaOffset == 0)   
                return Message.unknown_area;   
            else   
                return readString(areaOffset);   
        } else   
            return readString(offset);   
    }   
       
    /**  
     * @param offset �����¼����ʼƫ��  
     * @return �������ַ�  
     */   
    private String readArea(int offset) {   
        mbb.position(offset);   
        byte b = mbb.get();   
        if(b == REDIRECT_MODE_1 || b == REDIRECT_MODE_2) {   
            int areaOffset = readInt3();   
            if(areaOffset == 0)   
                return Message.unknown_area;   
            else   
                return readString(areaOffset);   
        } else   
            return readString(offset);   
    }   
       
    /**  
     * ��offsetƫ�ƴ���ȡһ����0������ַ�  
     * @param offset �ַ���ʼƫ��  
     * @return ��ȡ���ַ����?�ؿ��ַ�  
     */   
    private String readString(long offset) {   
        try {   
            ipFile.seek(offset);   
            int i;   
            for(i = 0, buf[i] = ipFile.readByte(); buf[i] != 0; buf[++i] = ipFile.readByte());   
            if(i != 0)    
                return Util.getString(buf, 0, i, "GBK");   
        } catch (IOException e) {              
            LogFactory.log("",Level.ERROR,e);   
        }   
        return "";   
    }   
       
    /**  
     * ���ڴ�ӳ���ļ���offsetλ�õõ�һ��0��β�ַ�  
     * @param offset �ַ���ʼƫ��  
     * @return ��ȡ���ַ����?�ؿ��ַ�  
     */   
    private String readString(int offset) {   
        try {   
            mbb.position(offset);   
            int i;   
            for(i = 0, buf[i] = mbb.get(); buf[i] != 0; buf[++i] = mbb.get());   
            if(i != 0)    
                return Util.getString(buf, 0, i, "GBK");          
        } catch (IllegalArgumentException e) {   
            LogFactory.log("",Level.ERROR,e);   
        }   
        return "";      
    }   
}
