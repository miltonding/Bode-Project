package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.dao.iface.AllPackageDaoIface;
import com.db.DbConnectionMgr;
import com.domian.Package;

public class AllPackageDao implements AllPackageDaoIface{
	public List<Package> selectAllBusiness(String sql) {
		List<Package> list=new ArrayList<Package>();
		
		Statement st =null;
		ResultSet rs=null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="SELECT * FROM T_ALLBUSINESS";
			st= c.createStatement();
			rs=st.executeQuery(sql);
	
			while(rs.next()){
				int pp_id=rs.getInt(1);
				String pp_name= rs.getString(2);
				double pp_fee=rs.getDouble(3);
				String freeBusines=rs.getString(4);
				list.add(new Package(pp_id,pp_name,pp_fee,freeBusines));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( rs,st, c);
			
		}
		return list;
	}
	public static void main(String[] args) {
		AllPackageDao a=new AllPackageDao();
		String sql="SELECT * FROM T_ALLBUSINESS";
		a.selectAllBusiness(sql);
	}
}
