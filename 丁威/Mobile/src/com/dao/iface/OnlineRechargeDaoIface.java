package com.dao.iface;

import java.util.List;
import java.util.Map;

import com.domian.OnlineRechargeConditions;
import com.domian.Preferential;

public interface OnlineRechargeDaoIface {

	//��ѯ�����Żݻ
	public List<Preferential> QueryAllPreferential(String sql);
	
	//���ݺ����ͻ�����
	public String QueryNameByTel(String sql,String tel_numb);
	
	//���ݳ�ֵ�����ҳ�ֵ�Ż���,�Żݱ��
	public Map<String, Object> QueryPreName (String sql,double tel_charge);
	
	//���֧��
	public  int onlineRecharge(String sql,OnlineRechargeConditions conditions);
	
	//����tel_numb,�����
	public int QueryAccount_balance (String sql,String tel_numb);
}
