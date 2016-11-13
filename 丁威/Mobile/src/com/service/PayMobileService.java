package com.service;

import java.util.List;

import com.dao.PayMobileDao;
import com.dao.iface.PayMobileDaoIface;
import com.domian.PayMobile;
import com.service.iface.PayMobileServiceIface;

public class PayMobileService implements PayMobileServiceIface {

	private PayMobileDaoIface payMobileDao=null;
	
	
	public PayMobileService() {
		super();
		payMobileDao=new PayMobileDao();
	}


	public boolean payMobile(PayMobile p) {
		boolean result=false;
		String sql="{call pro_payMobile(?,?,?,?)}";
		int i=payMobileDao.payMobile(p, sql);
		
		if(i>0){
			result=true;
		}
		
		return result;
	}

}
