package com.pancou.ad.listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.util.Http;
import com.pancou.ad.vo.InterfaceBeiqing;
import com.pancou.ad.vo.ViewReadyBox;

/**
 * 每日1：00，更新昨日北青数据入库
 * 
 * @author Orliu
 * 
 */
public class BeiqingListener implements ServletContextListener {
	// private final Logger log = Logger.getLogger(getClass());
	private Timer timer = null;
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 1);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		timer = new Timer(true);
		timer.schedule(new BeiqingTask(), cal.getTime(), PERIOD_DAY);

	}

	class BeiqingTask extends TimerTask {
		private PlatDao dao = new PlatDao();
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		private String interAddr = "http://sync.7676.com/cpsync/sync.php?dbid=087";

		@Override
		public void run() {
			copyToDb();

		}

		public void copyToDb() {
			// 昨天一天数据
			Calendar cal = Calendar.getInstance();

			String btime = "";
			String etime = "";
			String actionFlag = "";

			cal.add(Calendar.DATE, -1);
			btime = sdf.format(cal.getTime());
			cal.add(Calendar.DATE, 1);
			etime = sdf.format(cal.getTime());

			interAddr += "&btime=" + btime + "&etime=" + etime + "&action="
					+ actionFlag;

			// 取得数据，入库
			byte[] result = Http.requestServer(interAddr, null,
					Http.REQUEST_POST, Http.ENCODING_UTF8, null);
			String jsonString = new String(result);
			List<InterfaceBeiqing> list = InterfaceBeiqing
					.parseJson(jsonString);

			for (InterfaceBeiqing bq : list) {
				if (!"*_--_*".equals(bq.getGunion())) {
					ViewReadyBox readyBox = (ViewReadyBox) dao.findById(
							ViewReadyBox.class,
							Integer.parseInt(bq.getGunion()));
					if (readyBox != null) {
						bq.setWebMasterName(readyBox.getWebMasterName());
					} else {
						bq.setWebMasterName("");
					}
				}

				dao.save(bq);
			}

			// log.debug("北青入库" + list.size() + "条");

		}

	}

}
