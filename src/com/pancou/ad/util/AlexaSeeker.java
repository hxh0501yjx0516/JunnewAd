package com.pancou.ad.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.persistence.Entity;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Administrator
 */
@Entity
public class AlexaSeeker {
	public static final String baseURL = "http://data.alexa.com/data?cli=10&dat=snba&url=";

	/** Creates a new instance of AlexaSeeker */
	public AlexaSeeker() {
	}

	/**
	 * getValues(String node1,String attrib,String
	 * webadd)��node1ΪXML�Ľڵ㣬attribΪ�ڵ������,webaddΪҪ���Ե���ַ
	 * ������ֻ�ܵõ�SD�ڵ��ڵ��ӽڵ����Ե�ֵ�����е�node,attrib����ֵ��������SD�ڵ���Ķ�����
	 * 
	 * @param node1
	 *            XML�Ľڵ�,������SD�ڵ���Ķ���
	 * @param attrib
	 *            �ڵ������
	 * @param webadd
	 *            ΪҪ���Ե���ַ
	 * @return ����Alexaֵ
	 */
	public String getValues(String node1, String attrib, String vwebadd) {
		String sd = "SD";
		String totalURL = ""; // ����������ܵ���ַ
		URL url = null;
		URLConnection conn = null;
		Document doc = null; // ���XML�ļ��Ķ���
		DocumentBuilderFactory factory; // ���幤��API��ʹӦ�ó����ܹ��� XML �ĵ���ȡ��� DOM
										// �������Ľ�����
		DocumentBuilder docBuilder = null; // ���� API�� ʹ��� XML �ĵ���ȡ DOM �ĵ�ʵ��
		Element root;
		String webadd = vwebadd.trim();
		if(webadd.indexOf("http://") == -1 && webadd.indexOf("www.") == -1){
			webadd = "http://www."+webadd;
		}else if(webadd.indexOf("http://") == -1 && webadd.indexOf("www.") != -1){
			webadd = "http://"+webadd;
		}else if(webadd.indexOf("http://") != -1 && webadd.indexOf("www.") == -1){
			//��http:// ,û��www.
			String pruffex = webadd.substring(0,7);
			String suffex = webadd.substring(7);
			webadd = pruffex+"www."+suffex;
		}
		totalURL = baseURL + webadd;
		try {
			url = new URL(totalURL);
			conn = url.openConnection();
			conn.connect();

			factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false); // ָ���ɴ˴�����ɵĽ���������֤���������ĵ���
			try {
				docBuilder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException ex) {
				ex.printStackTrace();
			}
			try {
				doc = docBuilder.parse(conn.getInputStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (SAXException ex) {
				ex.printStackTrace();
			}
			System.out.println("parse successfull");
			root = doc.getDocumentElement(); // ����������ֱ�ӷ����ĵ����ĵ�Ԫ�ص��ӽڵ㡣�õ�XML�ĵ��ĸ�ڵ�
			NodeList allchildnodes = root.getChildNodes(); // �õ��ӽڵ�
			for (int i = 0; i < allchildnodes.getLength(); i++) { // ѭ�������ӽڵ�

				Node eachno = allchildnodes.item(i); // �õ�ÿһ���ڵ�
				if (eachno.getNodeType() == Node.ELEMENT_NODE) {
					if (eachno.getNodeName() == sd) { // ���SD����ڵ㣬SD�ڵ�����һ���ڵ����԰�������ֵ
						NodeList sbnodes = eachno.getChildNodes();// �õ�SD�ڵ�������ӽڵ����sbnodes��
						for (int j = 0; j < sbnodes.getLength(); j++) {// ѭ���ж�SD���ӽڵ�
							Node sbnode = sbnodes.item(j);
							if (sbnode.getNodeName() == node1) { // ����ӽڵ��Ǵ���ĵ�һ������Ļ�
								String value = sbnode.getAttributes()
										.getNamedItem(attrib).getNodeValue();
								System.out.println(sbnode.getAttributes()
										.getNamedItem(attrib).getNodeValue());
								return value;
							}
						}

					}
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AlexaSeeker a = new AlexaSeeker();
		System.out.println(a.getValues("POPULARITY", "TEXT","j2mehome.com"));
//		String address = "http://j2mehome.com";
//		int a = address.indexOf("://");
//		System.out.println(address.substring(a));
	}

}
