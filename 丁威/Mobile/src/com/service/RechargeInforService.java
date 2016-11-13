package com.service;

import java.util.List;

import com.dao.RechargeInforDao;
import com.dao.iface.RechargeInforDaoIface;
import com.domian.RechargeInfor;
import com.service.iface.RechargeInforServiceIface;

public class RechargeInforService implements RechargeInforServiceIface{

	private RechargeInforDaoIface rechargeInforDao=null;
	
	public RechargeInforService() {
		super();
		// TODO Auto-generated constructor stub
		rechargeInforDao=new RechargeInforDao();
	}

	public List<RechargeInfor> rechargeInfor(String tel_numb,int goalPage,int pageSize) {
		String sql="SELECT  Recharge_time,recharge_money,recharge_type_name,Card_id,Bank_card_numb,discount_amount FROM t_recharge_infor i JOIN  t_recharge_type t ON i.recharge_type_id=t.recharge_type_id WHERE i.tel_numb="+tel_numb;
		//º∆À„startindex
		int startindex=(goalPage-1)*pageSize+1;
		return rechargeInforDao.rechargeInfor(sql, tel_numb, startindex, pageSize);
	}


	public int countRechargeInfor(String tel_numb) {
		String sql="SELECT  COUNT(1) FROM t_recharge_infor i JOIN  t_recharge_type t ON i.recharge_type_id=t.recharge_type_id WHERE i.tel_numb="+tel_numb;
		return rechargeInforDao.countRechargeInfor(sql, tel_numb);
	}
}
