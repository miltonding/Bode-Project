package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.dao.iface.ManagerBusinessDaoIface;
import com.db.DbConnectionMgr;

public class ManagerBusinessDao implements ManagerBusinessDaoIface {

	public int QueryBusinessId(String sql, String business_name) {
		int result=0;
		
		PreparedStatement ps =null;
		ResultSet rs=null;
		
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql=" SELECT f.business_id FROM t_business_fee f WHERE f.business_name=?";
			ps= c.prepareStatement(sql);
			
			ps.setString(1, business_name);
			
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getInt(1);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(rs,ps, c);
			
		}
		
		System.out.println(result);	
		return result;
	}

	
	
	public int addBusiness(String sql, String tel_numb,int business_id) {
		int result=0;
		
		CallableStatement cs=null;
		
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="{call pro_openbusiness(?,?,?)}";
			cs= c.prepareCall(sql);
			cs.setInt(1, business_id);
			cs.setString(2, tel_numb);
			
			cs.registerOutParameter(3, Types.NUMERIC);
			
			cs.execute();
			//存储过程的结果
			result=cs.getInt(3);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( cs, c);
			
		}
		
		System.out.println(result);	
		return result;
	}



	public int closeBusiness(String sql, String tel_numb, int business_id) {
		int result=0;
		
		CallableStatement cs=null;
		
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="{call pro_closebusiness(?,?,?)}";
			cs= c.prepareCall(sql);
			cs.setInt(1, business_id);
			cs.setString(2, tel_numb);
			
			cs.registerOutParameter(3, Types.NUMERIC);
			
			cs.execute();
			//存储过程的结果
			result=cs.getInt(3);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( cs, c);
			
		}
		
		System.out.println(result);	
		return result;
	}

}
