package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dao.iface.ManagerPackageDaoIface;
import com.db.DbConnectionMgr;

public class ManagerPackageDao implements ManagerPackageDaoIface{


	public int changePackage(String sql, int pp_id, String tel_numb) {
		int result=0;
		Connection c = DbConnectionMgr.getConnection();
		PreparedStatement ps = null;
		try {

			//String sql="UPDATE t_mobile_package SET pp_id=?,start_time=SYSDATE WHERE TEL_NUMB=? AND pp_id!=0";
			ps=c.prepareStatement(sql);
			
			ps.setInt(1, pp_id);
			ps.setString(2, tel_numb);
			
			result=ps.executeUpdate();
			System.out.println("change ........");
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



}
