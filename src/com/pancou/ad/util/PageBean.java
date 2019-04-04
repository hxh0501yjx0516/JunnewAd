package com.pancou.ad.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** �ֲ����,���ȶ�maxResult��ֵ  */
@Entity
public class PageBean {

	@Id
	@GeneratedValue
	private static final long serialVersionUID = 1L;
	/** �ܼ�¼�� */
	private int maxResult;
	/** ÿҳ��ʾ��¼�� */
	private int pagesize;
	/** ��ҳ�� */
	private int pagecount;
	/** ��ǰҳ�� */
	private int currentpage;
	/** ��ǰ��¼���� */
	private int firstIndex;
	/** ��ǰҳ��ʾ����*/
	private int everyPage;

	/** ���췽��;ֻ���� �ܼ�¼��;ʹ��Ĭ��ÿҳ��ʾ��10��;Ĭ�ϵ�ǰҳΪ��1ҳ */
	public PageBean(int maxResult) {
		this(maxResult, 10, null);
	}

	/** ���췽��;ֻ�����ܼ�¼��/��ǰҳ��;ʹ��Ĭ��ÿҳ��ʾ�� 10��; */
	public PageBean(int maxResult, int currentpage) {
		this(maxResult, 10, currentpage);
	}

	/** ���췽��;����:�ܼ�¼��/ÿҳ��ʾ��/��ǰҳ�� */
	public PageBean(int maxResult, int pagesize, Object currentpage) {
		this.maxResult = maxResult;
		this.pagesize = pagesize;
		setPagecount();
		setCurrentpage(currentpage);
		setIndex();
		setPagecount();
		setEveryPage();
	}

	/** Ĭ�Ϲ��췽�� */
	public PageBean() {
	}

	public int getMaxResult() {
		return maxResult;
	}

	/** ��������¼��;��Dao����дSQL����; */
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getPagesize() {
		return pagesize;
	}

	/** ����ÿҳ��ʾ��;Ĭ��Ϊ10��/ҳ; */
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagecount() {
		return pagecount;
	}

	/** ������ҳ��;��ҳ��= �ܼ�¼��%ÿҳ��ʾ��Ϊ��?�ܼ�¼��%ÿҳ��ʾ��: �ܼ�¼��%ÿҳ��ʾ��+1; */
	public void setPagecount() {
		pagecount = (maxResult % pagesize == 0 ? maxResult / pagesize
				: maxResult / pagesize + 1);
	}

	public int getCurrentpage() {
		return currentpage;
	}

	/** ���õ�ǰҳ��;���ǰҳ��<=0����ǰҳΪNull��ǰҳ��>��ҳ��,��ǰҳ��=1; */
	public void setCurrentpage(Object currentpage) {

		this.currentpage = currentpage == null
				|| Integer.parseInt(currentpage.toString()) <= 0
				|| Integer.parseInt(currentpage.toString()) > getPagecount() ? 1
				: Integer.parseInt(currentpage.toString());
	}

	public int getIndex() {
		return firstIndex;
	}

	/** ���õ�ǰ��¼������ */
	public void setIndex() {
		this.firstIndex = (currentpage - 1) * pagesize;
	}

	public int getEveryPage() {
		return everyPage;
	}

	public void setEveryPage() {
		if (pagecount > currentpage || maxResult % pagesize ==0){
			everyPage = pagesize;
		} else {
			everyPage =  maxResult % pagesize;
			
		}
	}

}

