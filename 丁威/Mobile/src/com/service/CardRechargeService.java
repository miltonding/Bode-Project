package com.service;

import com.dao.CardRechargeDao;
import com.dao.iface.CardRechargeDaoIface;
import com.domian.CardRechargeConditions;
import com.service.iface.CardRechargeServiceIface;

public class CardRechargeService implements CardRechargeServiceIface{

	private CardRechargeDaoIface cardRechargeDao=null;
	public CardRechargeService() {
		super();
		// TODO Auto-generated constructor stub
		cardRechargeDao=new CardRechargeDao();
	}
	public boolean cardRecharge(CardRechargeConditions conditions) {
		boolean result=false;
		String sql="{call pro_cardPay(?,?,?,?)}";
		int i = cardRechargeDao.cardRecharge(sql, conditions);
		if(i==1){
			result=true;	
		}
		return result;
	}

	
}
