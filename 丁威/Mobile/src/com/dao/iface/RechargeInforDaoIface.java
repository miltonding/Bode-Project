package com.dao.iface;

import java.util.List;

import com.domian.RechargeInfor;

public interface RechargeInforDaoIface {
	//使用假分页
	public List<RechargeInfor> rechargeInfor(String sql,String tel_numb,int startindex,int pageSize);
	//计算结果数
	public int countRechargeInfor(String sql,String tel_numb);
}
