package com.service;

import java.util.List;
import java.util.Map;

import com.dao.OnlineRechargeDao;
import com.dao.iface.OnlineRechargeDaoIface;
import com.domian.OnlineRechargeConditions;
import com.domian.Preferential;
import com.service.iface.OnlineRechargeServiceIface;

public class OnlineRechargeService implements OnlineRechargeServiceIface{

	private OnlineRechargeDaoIface onlineRechargeDao=null;
	
	
	public OnlineRechargeService() {
		super();
		onlineRechargeDao=new OnlineRechargeDao();
	}


	public List<Preferential> QueryAllPreferential() {
		String sql="SELECT t.tel_charge,t.discount_amount ,(t.tel_charge+t.discount_amount) sum_amount,t.preferential_name  FROM t_preferential_infor t WHERE t.is_available=1 AND SYSDATE BETWEEN t.start_time AND t.endtime";
		return onlineRechargeDao.QueryAllPreferential(sql);
	}


	public String QueryNameByTel(String tel_numb) {
		String sql="SELECT customer_name FROM t_customer t WHERE  t.tel_numb=?";
		return onlineRechargeDao.QueryNameByTel(sql, tel_numb);
	}


	public Map<String, Object> QueryPreName(double tel_charge) {
		String sql="SELECT  preferential_name, preferential_id FROM t_perferential t WHERE tel_charge=?";
		return onlineRechargeDao.QueryPreName(sql, tel_charge);
	}


	public int onlineRecharge(OnlineRechargeConditions conditions) {
		String sql="{call pro_onlineRecharge(?,?,?,?,?,?)}";
		return onlineRechargeDao.onlineRecharge(sql, conditions);
	}


	public int QueryAccount_balance(String tel_numb) {
		String sql="SELECT account_balance FROM t_account  WHERE tel_numb=?";
		return onlineRechargeDao.QueryAccount_balance(sql, tel_numb);
	}

	
}
