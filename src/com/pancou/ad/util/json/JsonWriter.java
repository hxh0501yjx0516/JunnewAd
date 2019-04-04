package com.pancou.ad.util.json;

import java.beans.BeanInfo;
import java.beans.Introspector;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import java.text.CharacterIterator;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.*;
import javax.persistence.Entity;





@Entity
public class JsonWriter {

    private StringBuffer buf = new StringBuffer();

    public String write(Object object) {
        buf.setLength(0);
        value(object);
        return buf.toString();
    }

    public String write(long n) {
        return write(new Long(n));
    }

    public Object write(double d) {
        return write(new Double(d));
    }

    public String write(char c) {
        return write(new Character(c));
    }

    public String write(boolean b) {
        return write(Boolean.valueOf(b));
    }

    @SuppressWarnings("unchecked")
	private void value(Object object) {
        if (object == null){
        	add("''");
        }
        
       // else if (object instanceof Class) string(object);
        
        else if (object instanceof Boolean) string(object);
        else if (object instanceof Number) string(object);
        else if (object instanceof String) string(object);
        else if (object instanceof Character) string(object);
        else if (object instanceof Map) map((Map) object);
        else if (object instanceof Set) set((Set) object);
        else if (object instanceof List) list(object);
        else if (object instanceof Date)date(object);
        else if (object.getClass().isArray()) array(object);
        //else if (object instanceof Iterable) array(((Iterable) object).iterator());
        else bean(object);
    }

    private void bean(Object object) {
        add("{");
        @SuppressWarnings("unused")
		BeanInfo info;
        try {
            info = Introspector.getBeanInfo(object.getClass());
            /*
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (int i = 0; i < props.length; ++i) {
                PropertyDescriptor prop = props[i];
                String name = prop.getName();
                Method accessor = prop.getReadMethod();
                Object value = accessor.invoke(object, (Object[])null);
                add(name, value);
                if (i < props.length - 1) add(',');
            }*/
            Field[] ff = object.getClass().getDeclaredFields();
            for (int i = 0; i < ff.length; ++i) {
                //add(',');
                Field field = ff[i];
                field.setAccessible(true);
                add(field.getName(), field.get(object));
                if (i < ff.length - 1) add(',');
            }
            
        } catch (Exception e) {/**/ }
        add("}");
    }

    private void add(String name, Object value) {
        add('\'');
        add(name);
        add("\':");
        //add(":");
        value(value);
    }

    @SuppressWarnings({ "unchecked" })
	private void map(Map map) {
        add("\n{\n");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            value(key);
            add(":");
            value(map.get(key));
            if (it.hasNext()) add(",");
        }
        add("\n}\n");
    }

    @SuppressWarnings({ "unchecked", "unused" })
	private void array(Iterator it) {
        add("[");
        while (it.hasNext()) {
            value(it.next());
            if (it.hasNext()) add(",");
        }
        add("]");
    }

    private void array(Object object) {
        add("[");
        int length = Array.getLength(object);
        for (int i = 0; i < length; ++i) {
            value(Array.get(object, i));
            if (i < length - 1) add(',');
        }
        add("]");
    }
    @SuppressWarnings("unchecked")
	private void list(Object object) {
        add("[");
        int length = ((List)object).size();
        for (int i = 0; i < length; ++i) {
            value(((List)object).get(i));
            if (i < length - 1){ 
            	add(',');
            	add('\n');
            }
        }
        add("]");
    }
    @SuppressWarnings("unchecked")
    private void set(Object object) {
        add("[");
        Iterator ite = ((Set)object).iterator();
        while(ite.hasNext()) {
            value(ite.next());
            if (ite.hasNext()){ 
            	add(',');
            	add('\n');
            }
        }
        add("]");
    }

    @SuppressWarnings("unused")
	private void bool(boolean b) {
        add(b ? "true" : "false");
    }

    private void string(Object obj) {
        add('\'');
        CharacterIterator it = new StringCharacterIterator( obj.toString());
        for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
            if (c == '"') add("\\\"");
            else if (c == '\\') add("\\\\");
            else if (c == '/') add("\\/");
            else if (c == '\b') add("\\b");
            else if (c == '\f') add("\\f");
            else if (c == '\n') add("\\n");
            else if (c == '\r') add("\\r");
            else if (c == '\t') add("\\t");
            else if (c == '\'') add("\\'");
            else if (Character.isISOControl(c)) {
                unicode(c);
            } else {
                add(c);
            }
        }
        add('\'');
    }
    
    private void date(Object o){
    	Date d = (Date)o;
    	SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //��ʽ����ǰϵͳ����
    	String date = dateFm.format(d);

    	
    	string(date);
    }

    private void add(Object obj) {
    	
        buf.append(obj);
    }

    private void add(char c) {
        buf.append(c);
    }

    static char[] hex = "0123456789ABCDEF".toCharArray();

    private void unicode(char c) {
        add("\\u");
        int n = c;
        for (int i = 0; i < 4; ++i) {
            int digit = (n & 0xf000) >> 12;
            add(hex[digit]);
            n <<= 4;
        }
    }
    
    @SuppressWarnings("unchecked")
	public static void main(String args[]){
    	Map m = new HashMap();
    	m.put("aa", "aaa");
    	JsonWriter j = new JsonWriter();
    	String json = j.write(m);
    	//j.add("prjid","1360");
    	//j.add(d);
    	
    	System.out.println(json);
    }
}