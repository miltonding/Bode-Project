package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleTypes;

import com.dao.iface.SelectMobileDaoIface;
import com.db.DbConnectionMgr;
import com.domian.Mobile;

public class SelectMobileDao implements SelectMobileDaoIface{

	public List<Mobile> SelectMobile(String sql,Mobile m,int startindex,int endindex) {
		List<Mobile> mobileList=new ArrayList<Mobile>();
		//String sql="{call pack_selMobile.pro_dele_emp(?,?,?,?,?,?,?)}";
		CallableStatement cs =null;
		ResultSet rs =null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			cs = c.prepareCall(sql);
			cs.setString(1, m.getTel_add());
			cs.setDouble(2, m.getAcc_init_amount());
			cs.setString(3, m.getBegin());
			cs.setString(4, m.getType());
			cs.setString(5, m.getContainNumber());
			cs.setInt(6, startindex);
			cs.setInt(7, endindex);
			
			cs.registerOutParameter(8, OracleTypes.CURSOR);
			cs.execute();
				rs=(ResultSet)cs.getObject(8);
				//结果集存在
				if(rs!=null){
					while(rs.next()){
						String tel_numb=rs.getString(1);
						String tel_add=rs.getString(2);
						double acc_init_amout=rs.getDouble(3);
						mobileList.add(new Mobile(tel_numb,tel_add,acc_init_amout));
					
					}
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(rs, cs, c);
			
		}
		
		return mobileList;
	}

	

	public int ALLSelectMobile(String sql, Mobile m) {
		int result=0;
		//String sql="{call pack_selMobile.pro_count_mobile(?,?,?,?,?,?)}";
		CallableStatement cs =null;
		ResultSet rs =null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			cs = c.prepareCall(sql);
			cs.setString(1, m.getTel_add());
			cs.setDouble(2, m.getAcc_init_amount());
			cs.setString(3, m.getBegin());
			cs.setString(4, m.getType());
			cs.setString(5, m.getContainNumber());
			
			cs.registerOutParameter(6, Types.NUMERIC);
			cs.execute();
			
			result=cs.getInt(6);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(rs, cs, c);
			
		}
		
		return result;
		
	}
	
	
	public static void main(String[] args) {
		SelectMobileDao s=new SelectMobileDao();
		String sql="{call pack_selMobile.pro_count_mobile(?,?,?,?,?,?)}";
		System.out.println(s.ALLSelectMobile(sql, new Mobile("苏州",100,"13","4G","5")));
	}
}
