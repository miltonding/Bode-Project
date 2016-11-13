package com.dao.iface;

import java.util.List;
import java.util.Map;

import com.domian.Customer;

public interface RegisterDaoIface {
	
	//查询账号是否存在
	public int countUser(String sql,String username);
	
	//查询T_Mobile的Is_sale
	public Map<String,Object> isSale(String sql,String mobile);
	
	//注册成功,写入数据库 CUSTOMER_SEQ
	public int saveMsg(Customer customer,String sql);
	
}
