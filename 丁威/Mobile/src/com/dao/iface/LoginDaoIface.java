package com.dao.iface;

import java.util.Map;

import com.domian.Customer;


public interface LoginDaoIface {

	//��֤��¼
	public Map<String,Object> login(String sql,Customer customer);
	
	//ͨ���˺�,�����������롣���
	public Map<String,Object> findAllByID(String sql,String username); 
}
