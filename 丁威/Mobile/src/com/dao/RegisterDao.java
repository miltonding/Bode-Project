package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.iface.RegisterDaoIface;
import com.db.DbConnectionMgr;
import com.domian.Customer;

public class RegisterDao implements RegisterDaoIface{
	
	//查询账号是否存在
	public int countUser(String sql,String username) {
		int result=0;
		//String sql="SELECT COUNT(1) FROM t_customer WHERE Customer_username=?";
		PreparedStatement ps =null;
		ResultSet rs =null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			ps= c.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			rs.next();
			
			result=rs.getInt(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(rs, ps, c);
			
		}
		
		return result;
		
	}

	//查询T_Mobile的Is_sale,count
	public Map<String,Object> isSale(String sql, String mobile) {
		Map<String,Object> map=new HashMap<String, Object>();
		//String sql="SELECT Is_sale FROM T_Mobile WHERE Tel_numb=?";
		PreparedStatement ps =null;
		ResultSet rs =null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			ps= c.prepareStatement(sql);
			ps.setString(1, mobile);
			rs=ps.executeQuery();
			rs.next();
			String issale=rs.getString(1);
			map.put("issale", issale);
			
			//未注册过
			String sql2="SELECT COUNT(*) FROM t_customer WHERE Tel_numb=?";
			ps= c.prepareStatement(sql2);
			ps.setString(1, mobile);
			rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			map.put("count", count);
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(rs, ps, c);
			
		}
		
		return map;
	}
	
	//注册成功,写入数据库 CUSTOMER_SEQ
	public int saveMsg(Customer customer,String sql) {
		int result=0;
		
		String username=customer.getUsername();
		String dname=customer.getDname();
		String idNumber=customer.getIdNumber();
		String mobile=customer.getMobile();
		String birth=customer.getBirth();
		String pwd=customer.getPwd();
		
		CallableStatement cs=null;
		
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="{call pro_addCustomer(?,?,?,?,?,?,?)}";
			cs= c.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, dname);
			cs.setString(3, idNumber);
			cs.setString(4, mobile);
			cs.setString(5, birth);
			cs.setString(6, pwd);
			
			cs.registerOutParameter(7, Types.NUMERIC);
			
			cs.execute();
			//存储过程的结果
			result=cs.getInt(7);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( cs, c);
			
		}
		System.out.println(result);
		return result;
	}

	
	
	//注册成功,写入数据库 CUSTOMER_SEQ
	public int saveMsgTwo(String sql) {
		int result=0;
		

		
		
		Connection c=DbConnectionMgr.getConnection();
		PreparedStatement ps=null;
		try {
			//String sql="INSERT INTO t_customer VALUES(CUSTOMER_SEQ.NEXTVAL,?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?)";
			ps = c.prepareStatement(sql);
			ps.setString(1, "123");
			ps.setString(2, "jack");
			ps.setString(3, "320582199508241719");
			ps.setString(4, "13862203010");
			ps.setString(5, "2016-05-20");
			ps.setString(6, "123");
			
			result=ps.executeUpdate();
			System.out.println(result+"....................................");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( ps, c);
			
		}
		System.out.println(result);
		return result;
	}
	
	public static void main(String[] args) {
		RegisterDao r=new RegisterDao();
		String sql="INSERT INTO t_customer VALUES(CUSTOMER_SEQ.NEXTVAL,?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?)";
		r.saveMsgTwo(sql);
	}
	
}
