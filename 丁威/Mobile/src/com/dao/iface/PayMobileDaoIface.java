package com.dao.iface;

import java.util.List;

import com.domian.PayMobile;

public interface PayMobileDaoIface {
	//����v_mobile,init_balance,pp_id��ִ�д洢����
	public int payMobile(PayMobile p,String sql);
}
