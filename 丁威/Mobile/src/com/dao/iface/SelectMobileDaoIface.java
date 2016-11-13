package com.dao.iface;

import java.util.List;

import com.domian.Mobile;

public interface SelectMobileDaoIface {
	
	//选号查询
	public List<Mobile> SelectMobile(String sql,Mobile m,int startindex,int endindex);
	
	//记录总数
	public int ALLSelectMobile(String sql,Mobile m);
}
