package com.dao.iface;

import java.util.List;

import com.domian.PayMobile;

public interface PayMobileDaoIface {
	//根据v_mobile,init_balance,pp_id来执行存储过程
	public int payMobile(PayMobile p,String sql);
}
