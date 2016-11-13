package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.iface.QueryBusinessDaoIface;
import com.db.DbConnectionMgr;
import com.domian.Business;
import com.domian.Package;

public class QueryBusineeDao implements QueryBusinessDaoIface{

	public List<Business> queryFreeBusiness(String sql,String tel_numb) {
		List<Business> list=new ArrayList<Business>();
		
		Statement st =null;
		ResultSet rs=null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="SELECT business_name,business_charge,start_time,end_time,is_optional,is_largess  FROM t_freebusiness f WHERE tel_numb="+tel_numb;
			st= c.createStatement();
			rs=st.executeQuery(sql);
	
			while(rs.next()){
				String business_name=rs.getString(1);
				int business_charge=rs.getInt(2);
				String start_time=rs.getString(3);
				String end_time=rs.getString(4);
				int is_optional=rs.getInt(5);
				int is_largess=rs.getInt(6);
				//System.out.println(business_name+"  ,"+business_charge+"  ,"+start_time+"  ,"+end_time+"  ,"+is_optional+"  ,"+is_largess);
				list.add(new Business(business_name,business_charge,start_time,end_time,is_optional,is_largess));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( rs,st, c);
			
		}
		return list;
	}

	
	
	public List<Business> queryNotOpenChargeBusiness(String sql,String tel_numb) {
		List<Business> list=new ArrayList<Business>();
		
		Statement st =null;
		ResultSet rs=null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="SELECT f.business_name,f.business_charge,f.effective_time,f.end_time,f.is_optional,f.is_largess FROM t_business_fee f WHERE business_charge!=0 AND f.business_id NOT IN( SELECT business_id FROM t_mobile_package m WHERE  m.business_id!=0 AND  m.status=1 AND  m.tel_numb="+tel_numb+")";

			st= c.createStatement();
			rs=st.executeQuery(sql);
	
			while(rs.next()){
				String business_name=rs.getString(1);
				int business_charge=rs.getInt(2);
				String start_time=rs.getString(3);
				String end_time=rs.getString(4);
				int is_optional=rs.getInt(5);
				int is_largess=rs.getInt(6);
				//System.out.println(business_name+"  ,"+business_charge+"  ,"+start_time+"  ,"+end_time+"  ,"+is_optional+"  ,"+is_largess);
				list.add(new Business(business_name,business_charge,start_time,end_time,is_optional,is_largess));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( rs,st, c);
			
		}
		return list;
	}



	public List<Business> queryOpenChargeBusiness(String sql, String tel_numb) {
		List<Business> list=new ArrayList<Business>();
		
		Statement st =null;
		ResultSet rs=null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="SELECT f.business_name,f.business_charge,f.effective_time,f.end_time,f.is_optional,f.is_largess FROM t_business_fee f WHERE business_charge!=0 AND f.business_id  IN( SELECT business_id FROM t_mobile_package m WHERE  m.business_id!=0 AND  m.status=1 AND  m.tel_numb="+tel_numb+")";

			st= c.createStatement();
			rs=st.executeQuery(sql);
	
			while(rs.next()){
				int business_id=rs.getInt(1);
				String business_name=rs.getString(2);
				int business_charge=rs.getInt(3);
				String start_time=rs.getString(4);
				start_time=start_time.substring(0, 10);
				String end_time=rs.getString(5);
				if(end_time!=null){
					end_time=end_time.substring(0, 10);
					
				}
				int is_optional=rs.getInt(6);
				int is_largess=rs.getInt(7);
				int status=rs.getInt(8);
				//System.out.println("已开通的付费业务");
				//System.out.println(business_id+"  ,"+business_name+"  ,"+business_charge+"  ,"+start_time+"  ,"+end_time+"  ,"+is_optional+"  ,"+is_largess+",业务状态"+status);
				list.add(new Business(business_id,business_name,business_charge,start_time,end_time,is_optional,is_largess,status));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( rs,st, c);
			
		}
		return list;
	}


	//已开套餐
	public List<Package> queryOpenPackage(String sql, String tel_numb) {
		List<Package> list=new ArrayList<Package>();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			ps= c.prepareStatement(sql);
			ps.setString(1, tel_numb);
			
			rs=ps.executeQuery();
			while(rs.next()){
				int pp_id=rs.getInt(1);
				String pp_name=rs.getString(2);
				double pp_fee=rs.getDouble(3);
				String business_name=rs.getString(4);
				if(business_name!=null){
					//System.out.println("已开套餐");
					//System.out.println(pp_id+"  ,"+pp_name+"   ,"+pp_fee+"   ,"+business_name);
					list.add(new Package(pp_id,pp_name,pp_fee,business_name));
				}
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	public List<Package> queryNotOpenPackage(String sql, String tel_numb) {
		List<Package> list=new ArrayList<Package>();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			ps= c.prepareStatement(sql);
			ps.setString(1, tel_numb);
			
			rs=ps.executeQuery();
			while(rs.next()){
				int pp_id=rs.getInt(1);
				String pp_name=rs.getString(2);
				double pp_fee=rs.getDouble(3);
				String business_name=rs.getString(4);
				if(business_name!=null){
					//System.out.println("未开套餐");
					//System.out.println(pp_id+"  ,"+pp_name+"   ,"+pp_fee+"   ,"+business_name);
					list.add(new Package(pp_id,pp_name,pp_fee,business_name));
				}
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	
	

}
