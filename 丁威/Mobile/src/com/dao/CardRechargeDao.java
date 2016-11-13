package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.dao.iface.CardRechargeDaoIface;
import com.db.DbConnectionMgr;
import com.domian.CardRechargeConditions;
import com.domian.OnlineRechargeConditions;

public class CardRechargeDao implements CardRechargeDaoIface{

	public int cardRecharge(String sql, CardRechargeConditions conditons) {
			int result=0;
			
			CallableStatement cs=null;
			
			Connection c=DbConnectionMgr.getConnection();
			try {
				//String sql="{call pro_cardPay(?,?,?,?)}";
				cs= c.prepareCall(sql);
				cs.setString(1, conditons.getTel_numb());
				cs.setInt(2, conditons.getCard_id());
				cs.setString(3,conditons.getCard_pwd());
				
				cs.registerOutParameter(4, Types.NUMERIC);
				
				cs.execute();
				//存储过程的结果
				result=cs.getInt(4);
				
				
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




