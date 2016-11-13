package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.iface.OnlineRechargeDaoIface;
import com.db.DbConnectionMgr;
import com.domian.OnlineRechargeConditions;
import com.domian.Preferential;

public class OnlineRechargeDao implements OnlineRechargeDaoIface {
	
	public List<Preferential> QueryAllPreferential(String sql) {
		List< Preferential> onlineList=new ArrayList<Preferential>();
		//String sql="SELECT t.tel_charge,t.discount_amount ,(t.tel_charge+t.discount_amount) sum_amount,t.preferential_name  FROM t_preferential_infor t WHERE t.is_available=1 AND SYSDATE BETWEEN t.start_time AND t.endtime";
		ResultSet rs=null;
		Statement st = null;
		Connection c = DbConnectionMgr.getConnection();
		try {
			st=c.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()){
				int  tel_change=rs.getInt(1);
				int dis_amount=rs.getInt(2);
				int sum_amount=rs.getInt(3);
				String preferential_name=rs.getString(4);
				System.out.println(tel_change+",  "+dis_amount+",  "+sum_amount+",  "+preferential_name);
				
				Preferential o=new Preferential(tel_change,dis_amount,sum_amount,preferential_name);
				onlineList.add(o);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(rs, st, c);
			
		}
		
		
		return onlineList;
	}
	
	//根据号码查客户姓名,查询t_customer
	public String QueryNameByTel(String sql, String tel_numb) {
		String customer_name=""; 
		//String sql="SELECT customer_name FROM t_customer t WHERE  t.tel_numb=?";
		ResultSet rs=null;
		PreparedStatement ps = null;
		Connection c = DbConnectionMgr.getConnection();
		try {
			ps= c.prepareStatement(sql);
			ps.setString(1, tel_numb);
			
			rs=ps.executeQuery();
			if(rs.next()){
			customer_name=rs.getString("customer_name");
			}
			System.out.println(customer_name);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(rs, ps, c);
			
		}
		
		
		return customer_name;
	}

	//根据充值金额，查找充值优惠名 ，优惠编号
	public Map<String, Object> QueryPreName(String sql, double tel_charge) {
		//String sql="SELECT  preferential_name, preferential_id FROM t_perferential t WHERE tel_charge=?";
		Map<String, Object> map=new HashMap<String, Object>();
		ResultSet rs=null;
		PreparedStatement ps = null;
		Connection c = DbConnectionMgr.getConnection();
		try {
			ps= c.prepareStatement(sql);
			ps.setDouble(1, tel_charge);
			
			rs=ps.executeQuery();
			if(rs.next()){
			String preferential_name=rs.getString("preferential_name")+"元";
			int perferential_id=rs.getInt("preferential_id");
			map.put("preferential_name", preferential_name);
			map.put("preferential_id", perferential_id);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(rs, ps, c);
			
		}
		
		
		return map;
	}
	


	public int onlineRecharge(String sql,OnlineRechargeConditions conditions) {
		int result=0;
		
		CallableStatement cs=null;
		
		Connection c=DbConnectionMgr.getConnection();
		try {
			//String sql="{call pro_onlineRecharge(?,?,?,?,?,?)}";
			cs= c.prepareCall(sql);
			cs.setString(1, conditions.getRecharge_type_name());
			cs.setString(2, conditions.getTel_numb());
			cs.setInt(3, conditions.getPreferential_id());
			cs.setDouble(4, conditions.getSum_amount());
			cs.setInt(5, conditions.getTel_charge());
			
			cs.registerOutParameter(6, Types.NUMERIC);
			
			cs.execute();
			//存储过程的结果
			result=cs.getInt(6);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll( cs, c);
			
		}
		
		System.out.println(result);	
		return result;
	}

	
	
	//根据tel_numb,查余额
	public int QueryAccount_balance(String sql,String tel_numb) {
		int account_balance=0;
		ResultSet rs=null;
		PreparedStatement ps = null;
		Connection c = DbConnectionMgr.getConnection();
		//String sql="SELECT account_balance FROM t_account  WHERE tel_numb=?";
		try {
			ps= c.prepareStatement(sql);
			ps.setString(1, tel_numb);
			
			rs=ps.executeQuery();
			
			if(rs.next()){
				account_balance=rs.getInt(1);	
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnectionMgr.closeAll(rs, ps, c);
			
		}
		return account_balance;
	}
	
	//单元测试
	public static void main(String[] args) {
		OnlineRechargeDao o=new OnlineRechargeDao();
		String sql="SELECT account_balance FROM t_account  WHERE tel_numb=?";
		o.QueryAccount_balance(sql, "13806224773");
	}
}
