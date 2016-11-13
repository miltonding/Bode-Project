package com.dao.iface;

import java.util.Map;

import com.domian.Customer;


public interface LoginDaoIface {

	//验证登录
	public Map<String,Object> login(String sql,Customer customer);
	
	//通过账号,查姓名，号码。余额
	public Map<String,Object> findAllByID(String sql,String username); 
}
