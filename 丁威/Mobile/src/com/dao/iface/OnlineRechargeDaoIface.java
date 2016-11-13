package com.dao.iface;

import java.util.List;
import java.util.Map;

import com.domian.OnlineRechargeConditions;
import com.domian.Preferential;

public interface OnlineRechargeDaoIface {

	//查询所有优惠活动
	public List<Preferential> QueryAllPreferential(String sql);
	
	//根据号码查客户姓名
	public String QueryNameByTel(String sql,String tel_numb);
	
	//根据充值金额，查找充值优惠名,优惠编号
	public Map<String, Object> QueryPreName (String sql,double tel_charge);
	
	//最后支付
	public  int onlineRecharge(String sql,OnlineRechargeConditions conditions);
	
	//根据tel_numb,查余额
	public int QueryAccount_balance (String sql,String tel_numb);
}
