package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.dao.iface.PayMobileDaoIface;
import com.db.DbConnectionMgr;
import com.domian.PayMobile;

public class PayMobileDao implements PayMobileDaoIface{

	//根据v_mobile,init_balance,pp_id来执行存储过程
	//使用PayMobile

	public int payMobile(PayMobile p,String sql) {
		int result=0;
		Connection c=DbConnectionMgr.getConnection();
		CallableStatement cs=null;
		try {
			 cs = c.prepareCall(sql);
			 cs.setString(1, p.getTel_numb());
			 cs.setDouble(2, p.getInit_account());
			 cs.setInt(3, p.getPp_id());
			 
			 cs.registerOutParameter(4, Types.NUMERIC);
			 cs.execute();
			 
			 result=cs.getInt(4);
			 System.out.println(result);
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(cs, c);
			
		}
		
		return result;
	}
	public static void main(String[] args) {
		PayMobileDao p=new PayMobileDao();
		String sql="{call pro_payMobile(?,?,?,?)}";
		p.payMobile(new PayMobile("13862203000",100.0,1001), sql);
	}

}
