package com.pancou.ad.util.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Entity;
import org.apache.log4j.PropertyConfigurator;


@Entity
public class Logger {
	
	private final static String DEBUG 	= ", DEBUG, ";
	private final static String INFO 	= ", INFO, ";
	private final static String WARN 	= ", WARN, ";
	private final static String ERROR 	= ", ERROR, ";
	private final static String OUT 	= ", OUTPUT, ";
	
	public final static String DEFAULT = "default";
	public final static String SOFTPHONE = "softphone";
	
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private static Map<String, org.apache.log4j.Logger> loggerMap = new HashMap<String, org.apache.log4j.Logger> ();
	private static org.apache.log4j.Logger _defaultLogger = null;;
	
	static {
		loggerMap.put("default", org.apache.log4j.Logger.getLogger("com.suplus.oms"));
		loggerMap.put("softphone", org.apache.log4j.Logger.getLogger("softphone"));
		_defaultLogger = loggerMap.get(DEFAULT);
	}
	//private static org.apache.log4j.Logger _errorLogger = org.apache.log4j.Logger.getLogger("stderr");
	
	private static String getSuffix () {
		boolean isThisStackTrace = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		for (StackTraceElement e : ste) {
			// �ж��Ƿ���Logger��
			boolean b = e.toString().startsWith("com.suplus.oms.log.bo.Logger");
			if (b)
				isThisStackTrace=true;
			// �ж��Ƿ�isThisStackTraceΪtrue����ʼ�Ѳ���Logger��
			if (isThisStackTrace && !b) 
				return ", "+e;
		}
		for (StackTraceElement e : ste) {
			_defaultLogger.debug(getPrefix(" [ERR LINE] : ")+e);
		}
		return ", @[ERROR CLASS INFO]";
	}
	
	private static String getPrefix (String level) {
		return sdf.format(new Date())+level;
	}
	
	public static void debug (Object message) {
		debug(DEFAULT, message);
	}
	
	public static void info (Object message) {
		info(DEFAULT, message);
	}
	
	public static void out (Object message) {
		out(DEFAULT, message);
	}
	
	public static void warn (Object message) {
		warn(DEFAULT, message);
	}
	
	public static void error (Object message) {
		error(DEFAULT, message);
	}
	
	public static void debug (String name, Object message) {
		loggerMap.get(name).debug(getPrefix(DEBUG)+message+getSuffix());
	}
	
	public static void out (String name, Object message) {
		loggerMap.get(name).info(getPrefix(OUT)+message+getSuffix());
	}
	
	public static void info (String name, Object message) {
		loggerMap.get(name).info(getPrefix(INFO)+message+getSuffix());
	}
	
	public static void warn (String name, Object message) {
		loggerMap.get(name).warn(getPrefix(WARN)+message+getSuffix());
	}
	
	public static void error (String name, Object message) {
		loggerMap.get(name).error(getPrefix(ERROR)+message+getSuffix());
	}
	
	@SuppressWarnings("unused")
	private static void printException (Throwable t) {
		t.printStackTrace();
//		StackTraceElement[] ste = t.getStackTrace();
//		_errorLogger.error(getPrefix(",'EXCEPTION-MSG','")+t.getLocalizedMessage()+"'");
//		for (StackTraceElement e : ste) {
//			String str = e.toString();
//			if (str.startsWith("com.suplus"))
//				_errorLogger.error(getPrefix(",'EXCEPTION-STK','")+e+"'");
//		}
	}
	
	public static void error (String message, Throwable t) {
		error(DEFAULT, message, t);
		// printException(t);
	}
	
	public static void error (Object message, Throwable t) {
		error(DEFAULT, message, t);
		// printException(t);
	}
	
	public static void error (String name, Object message, Throwable t) {
		loggerMap.get(name).error(getPrefix(ERROR)+message+getSuffix(), t);
		// printException(t);
	}
	
	private static Properties properties = new Properties();
	
	public static void initialize () throws FileNotFoundException, IOException {
		properties.load(Logger.class.getResourceAsStream("/log4j.properties"));
		PropertyConfigurator.configure(properties);
	}
	
	public static void changeProperty (String name, String value) {
		properties.setProperty(name, value);
		PropertyConfigurator.configure(properties);
	}
	public static void removeProperty (String name) {
		properties.remove(name);
		PropertyConfigurator.configure(properties);
	}
}
