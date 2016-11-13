package com.service.iface;

import java.util.List;

import com.domian.RechargeInfor;

public interface RechargeInforServiceIface {
	public List<RechargeInfor> rechargeInfor(String tel_numb,int goalPage,int pageSize);
	
	//计算结果数
	public int countRechargeInfor(String tel_numb);
}
