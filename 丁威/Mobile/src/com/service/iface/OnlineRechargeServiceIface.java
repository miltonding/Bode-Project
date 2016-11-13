package com.service.iface;

import java.util.List;
import java.util.Map;

import com.domian.OnlineRechargeConditions;
import com.domian.Preferential;

public interface OnlineRechargeServiceIface {

	public List<Preferential> QueryAllPreferential();
	
	public String QueryNameByTel( String tel_numb);
	
	//���ݳ�ֵ�����ҳ�ֵ�Ż���,�Żݱ��
	public Map<String, Object> QueryPreName(double tel_charge);
	
	public int onlineRecharge(OnlineRechargeConditions conditions);

	//����tel_numb,�����
	public int QueryAccount_balance(String tel_numb);
}
