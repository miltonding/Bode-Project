package com.dao.iface;

import java.util.List;
import java.util.Map;

import com.domian.Customer;

public interface RegisterDaoIface {
	
	//��ѯ�˺��Ƿ����
	public int countUser(String sql,String username);
	
	//��ѯT_Mobile��Is_sale
	public Map<String,Object> isSale(String sql,String mobile);
	
	//ע��ɹ�,д�����ݿ� CUSTOMER_SEQ
	public int saveMsg(Customer customer,String sql);
	
}
