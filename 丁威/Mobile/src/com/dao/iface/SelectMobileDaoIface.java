package com.dao.iface;

import java.util.List;

import com.domian.Mobile;

public interface SelectMobileDaoIface {
	
	//ѡ�Ų�ѯ
	public List<Mobile> SelectMobile(String sql,Mobile m,int startindex,int endindex);
	
	//��¼����
	public int ALLSelectMobile(String sql,Mobile m);
}
