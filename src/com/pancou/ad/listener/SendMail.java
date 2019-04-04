package com.pancou.ad.listener;
import javax.persistence.Entity;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

@Entity
public class SendMail {
	// ���������
	private String host = "smtpcom.263xmail.com";
	// �������������û���
	private String username = "spadmin@pancou.com";

	private String password = "spadmin123456";

	public SendMail() {
	}

	/** */
	/**
	 * �˶δ�������������ͨ�����ʼ�
	 */
	public void send(String from, String to, String title, String content) throws Exception {
		try {
			// ��Ҫʹ��SimpleEmail,�������������
			HtmlEmail email = new HtmlEmail();
			String[] mailToArray = null;        
			// �����Ƿ��ͷ����������֣���163�����£�
			email.setHostName(host);
			// ���뼯������
			email.setCharset("gbk");
			// �ʼ��������б�
			mailToArray = to.split(",|;|��|��|:|��");
			for(int i = 0; i < mailToArray.length; i++){				
				// �ж��������Ƿ�Ϊ��
				if(mailToArray[i] != null && !"".equals(mailToArray[i])){
					// ��Ϊ��ʱ�������ʼ������˵�ַ
					email.addTo(mailToArray[i]);
				}
				
			}
			// �����˵�����
			email.setFrom(username, username);
			// �����Ҫ��֤��Ϣ�Ļ���������֤���û���-���롣�ֱ�Ϊ���������ʼ��������ϵ�ע����ƺ�����
			email.setAuthentication(username, password);
			email.setSubject(title);
			// Ҫ���͵���Ϣ
			email.setMsg(content);
			// ����
			email.send();
//			System.out.println("send ok!");
			
		} catch (EmailException ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}

	
	public static void main(String[] args) {
		String mailFrom = "spadmin@pancou.com";
		String mailTo = "764962971@qq.com";
		String title = "����";

		SendMail sendmail = new SendMail();
		System.out.println("d1rresdsf12");
		try {
			sendmail.send(mailFrom, mailTo, title,"����");
		} catch (Exception ex) {
		}
	}

}
