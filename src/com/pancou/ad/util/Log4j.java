package com.pancou.ad.util;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

@SuppressWarnings("serial")
public class Log4j extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		String propertiesFile = this.getInitParameter("Log4jConfig");
		File file = new File(propertiesFile);
		if (file.exists() && file.isFile()) {
			PropertyConfigurator.configure(propertiesFile);
		}
	}
}
