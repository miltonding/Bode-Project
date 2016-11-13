package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.iface.RechargeInforDaoIface;
import com.db.DbConnectionMgr;
import com.domian.Package;
import com.domian.RechargeInfor;

public class RechargeInforDao implements RechargeInforDaoIface{

	public List<RechargeInfor> rechargeInfor(String sql,String tel_numb,int startindex,int pageSize) {
		List<RechargeInfor> list=new ArrayList<RechargeInfor>();
		Statement st =null;
		ResultSet rs=null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="SELECT  Recharge_time,recharge_money,recharge_type_name,Card_id,Bank_card_numb,discount_amount FROM t_recharge_infor i JOIN  t_recharge_type t ON i.recharge_type_id=t.recharge_type_id WHERE i.tel_numb="+tel_numb;
			st= c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=st.executeQuery(sql);
	
			//定位
			rs.absolute(startindex);
			for (int i = 0; i <pageSize; i++) {
				
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getInt(4));
				//System.out.println(rs.getInt(5));
				//System.out.println();
				list.add(new RechargeInfor(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)));
				//解决结果集耗尽问题，同时指针也下移
				if(rs.next()==false){
					break;
					
				}
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( rs,st, c);
			
		}
		return list;
	}
	
	public int countRechargeInfor(String sql, String tel_numb) {
		int result=0;
		Statement st =null;
		ResultSet rs=null;
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="SELECT  COUNT(1) FROM t_recharge_infor i JOIN  t_recharge_type t ON i.recharge_type_id=t.recharge_type_id WHERE i.tel_numb="+tel_numb;
			st= c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=st.executeQuery(sql);
			
			if(rs.next()){
				result=rs.getInt(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( rs,st, c);
			
		}
		return result;
	}

}
