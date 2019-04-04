package com.pancou.ad.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;

/**
 * ��ҳ������
 * 
 * @author tmb
 * @version 1.00
 */
@Entity
public class PagingHandle  {

	/**
	 * ��÷�ҳ�Ĳ���
	 * 
	 * @param page ��ǰҳ
	 * @param size ÿҳ����
	 * @param rows ������
	 * @return
	 */
	public final static Map<String, Integer> getPagingParams (int page, int size, int rows) {
		Map<String, Integer> _result = new HashMap<String, Integer> ();
		int first, last, count;
		if (size <= 0) size = 20;
			count = rows / size + ((rows % size) != 0 ? 1 : 0); 
		if (count <= 0) count = 1;
		if (page < 1) page = 1;
		else if (page > count) page = count;
		first = (page - 1) * size;
		last = first + size - 1;
		if (last >= rows)
			last = rows - 1;
		_result.put("page", page);//��ʼҳ
		_result.put("size", size);//ÿҳ��ʾ����
		_result.put("count", count);//��ҳ��
		_result.put("first", first);//��Ŀ��ʼ��
		_result.put("last", last);//��Ŀ������
		_result.put("rows", rows);//���������
		return _result;
	}
	
	/**
	 * ��÷�ҳ���ص�ǰ̨�Ľ��
	 * 
	 * @param pars getPagingParams���ص�Map
	 * @param resultListName ���ص�list����
	 * @param resultList ������ǰ̨����ݼ�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final static Map<String, Object> getResultMap (Map<String, Integer> pars, String resultListName, List resultList) {
		Map<String, Object> _result = new HashMap<String, Object>();
		_result.put("pageNum", pars.get("page"));
		_result.put("totalCount", pars.get("rows"));
		_result.put("numPerPage", pars.get("size"));
		_result.put("pageCount", pars.get("count"));
		_result.put("first", pars.get("first")+1);
		_result.put("last", pars.get("last")+1);
		_result.put(resultListName, resultList);
		return _result;
	}
}
