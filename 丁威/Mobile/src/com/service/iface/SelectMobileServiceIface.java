package com.service.iface;

import java.util.List;

import com.domian.Mobile;

public interface SelectMobileServiceIface {
	
	public List<Mobile> SelectMobile(Mobile m,int currenPage,int pageSize );
	
	//��¼����
	public int ALLSelectMobile(Mobile m);
}
