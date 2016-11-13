package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.dao.iface.LoginDaoIface;
import com.db.DbConnectionMgr;
import com.domian.Customer;

public class LoginDao implements LoginDaoIface{

	//���ú���
	public Map<String,Object> login(String sql, Customer customer) {
		 Map<String,Object> map=new HashMap<String, Object>();
		int result=0;
		String dname=null;
		//��ȡ�˺�����
		String username=customer.getUsername();
		String pwd=customer.getPwd();
		
		CallableStatement cs=null;
		
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="{?=call fn_customer_login(?,?,?)}";
			cs= c.prepareCall(sql);
			
			cs.setString(2, username);
			cs.setString(3, pwd);
			
			cs.registerOutParameter(1, Types.NUMERIC);
			cs.registerOutParameter(4, Types.VARCHAR);
			
			cs.execute();
			//���
			result=cs.getInt(1);
			dname=cs.getString(4);
			map.put("result", result);
			map.put("dname", dname);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( cs, c);
			
		}
		return map;
	}

	//ͨ���˺ţ�����롣���
	public Map<String, Object> findAllByID(String sql, String username) {
		 Map<String,Object> map=new HashMap<String, Object>();
			String mobile=null;
			double balance=0; 
			CallableStatement cs=null;
			
			Connection c=DbConnectionMgr.getConnection();
			try {
				//String sql="{call pro_findAllBYID(?,?,?)}";
				cs=c.prepareCall(sql);
				
				cs.setString(1, username);
				
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.NUMERIC);
				
				cs.execute();
				//���
				mobile=cs.getString(2);
				balance=cs.getDouble(3);
				
				map.put("mobile", mobile);
				map.put("balance", balance);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnectionMgr.closeAll( cs, c);
				
			}
			return map;
	}
	
	//��Ԫ����
	public static void main(String[] args) {
		LoginDao l=new LoginDao();
		String sql="{call pro_findAllBYID(?,?,?)}";
		l.findAllByID(sql, "1");
	}
}
