package com.pancou.ad.listener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import javax.persistence.Entity;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Entity
public class ListenerLimit implements ServletContextListener{
	private Timer timer = null;
	
	/**
	 *  �����ʼ��
	 */
	public void contextInitialized(ServletContextEvent sce) {
		try {
			// ���ʼ��
			timer = new Timer(true);
			
			// ��øü���ִ��ʱ��
			// �趨����ƻ� 10����ִ��һ��
			timer.schedule(new MyTask(), 1000, 1000 * 60 * 10);

		} catch (Exception e) {
			// ȡ��ƻ�
			timer.cancel();
			e.printStackTrace();
		}
	}

	/**
	 * ȡ��timer
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		timer.cancel();
	}

	/**
	 * ����Servlet���������ڻ�
	 * 
	 * @param pEvent
	 *  Servlet�����¼�
	 */
	@Entity
	class MyTask extends java.util.TimerTask {

		public MyTask() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void run() {
			HashMap<Object,Object> hashCount = new HashMap<Object,Object>();//sid,����
			HashMap<Object,Object> hash = new HashMap<Object,Object>();//sid,����
			ArrayList alist = new ArrayList();
			SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
			// ���ϵͳ��ǰʱ��
			String now = sd.format(new Date());
			// �ʼ�����
			String title = null;
			int cnt = 0;
			int limit = 0;
			int editflag = 0;
			String mailTo = null;
			// ���sp_service���еı�����������
			
			try {
				// ȡ��moflow���е���������
				//alist = spmomt.getMoCount(now);
			} catch (Exception e) {
				System.out.println("�ñ?���ڣ�");
				e.printStackTrace();
			}
			for (int j = 0; j < alist.size(); j++) {				
				Object[] moCount = (Object[]) alist.get(j);
				// ���moflow���е�sid��sp_service���ж�Ӧsid�����ж��Ƿ��Ѿ������ʼ���
				if (hash.containsKey(moCount[0])){
				
				}
			}	
		}
	}
}
