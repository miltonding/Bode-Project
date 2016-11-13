package com.service;

import java.util.Map;

import com.dao.LoginDao;
import com.dao.iface.LoginDaoIface;
import com.domian.Customer;
import com.service.iface.LoginServiceIface;

public class LoginService implements LoginServiceIface{

	private LoginDaoIface loginDao=null;
	
	
	public LoginService() {
		super();
		loginDao=new LoginDao();
	}


	public String login(Customer customer) {
		String backname="";
		String sql="{?=call fn_customer_login(?,?,?)}";
		//调用DAO
		Map<String, Object> map=loginDao.login(sql, customer);
		int result=(Integer)map.get("result");
		System.out.println(result);
		String dname=(String)map.get("dname");
		//验证成功
		if(result==1){
			//返回姓名
			backname=dname;
		}
		
		return backname;
			
	}
	
	public Map<String, Object> findAllByID(String username) {
		String sql="{call pro_findAllBYID(?,?,?)}";
		return loginDao.findAllByID(sql, username);
	}
	
	
	public static void main(String[] args) {
		LoginService l=new LoginService();
		System.out.println(l.login(new Customer("2","12")));
	
	}



}
