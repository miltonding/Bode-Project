package com.service;

import java.util.List;

import com.dao.SelectMobileDao;
import com.dao.iface.SelectMobileDaoIface;
import com.domian.Mobile;
import com.service.iface.SelectMobileServiceIface;

public class SelectMobileService implements SelectMobileServiceIface{

	private SelectMobileDaoIface selectMobileDao=null;
	
	public SelectMobileService() {
		super();
		// TODO Auto-generated constructor stub
		selectMobileDao=new SelectMobileDao();
	}

	public List<Mobile> SelectMobile(Mobile m,int currenPage,int pageSize) {
		String sql="{call pack_selMobile.pro_dele_emp(?,?,?,?,?,?,?,?)}";
		//计算，开始索引，结束索引
		int startindex=(currenPage-1)*pageSize+1;
		int endindex=startindex+pageSize;
		//System.out.println("开始索引"+startindex);
		//System.out.println("结束索引"+endindex);
		
		
		/*//拼接LIKE关键字
		String containNumber=m.getContainNumber();
		char[] begin=containNumber.toCharArray();
		int length=containNumber.length();
		System.out.println("长度为"+length);
		StringBuffer keyword=new StringBuffer();
		for (int i = 0; i <length; i++) {
			keyword.append("%");
			keyword.append(begin[i]);
			
		}
		keyword.append("%");
		System.out.println(keyword.toString());
		m.setContainNumber(keyword.toString());*/
		return selectMobileDao.SelectMobile(sql, m,startindex,endindex);
	}

	public int ALLSelectMobile(Mobile m) {
		String sql="{call pack_selMobile.pro_count_mobile(?,?,?,?,?,?)}";
		return selectMobileDao.ALLSelectMobile(sql, m);
	}
}
