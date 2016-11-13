package com.service.iface;

import java.util.List;
import java.util.Map;

import com.domian.OnlineRechargeConditions;
import com.domian.Preferential;

public interface OnlineRechargeServiceIface {

	public List<Preferential> QueryAllPreferential();
	
	public String QueryNameByTel( String tel_numb);
	
	//根据充值金额，查找充值优惠名,优惠编号
	public Map<String, Object> QueryPreName(double tel_charge);
	
	public int onlineRecharge(OnlineRechargeConditions conditions);

	//根据tel_numb,查余额
	public int QueryAccount_balance(String tel_numb);
}
