package com.dao.iface;

import java.util.List;

import com.domian.RechargeInfor;

public interface RechargeInforDaoIface {
	//ʹ�üٷ�ҳ
	public List<RechargeInfor> rechargeInfor(String sql,String tel_numb,int startindex,int pageSize);
	//��������
	public int countRechargeInfor(String sql,String tel_numb);
}
