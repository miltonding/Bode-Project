package com.service;

import java.util.Map;

import com.dao.RegisterDao;
import com.dao.iface.RegisterDaoIface;
import com.domian.Customer;
import com.service.iface.RegisterServiceIface;

public class RegisterService implements RegisterServiceIface{
	private RegisterDaoIface registerDao=null;
	
	
	public RegisterService() {
		super();
		registerDao=new RegisterDao();
	}


	public boolean countUser(String username){
		boolean result=false;
		String sql="SELECT COUNT(1) FROM t_customer WHERE Customer_username=?";
		//调用DAO
		int count=registerDao.countUser(sql, username);
		
		if(count==0){
			result=true;	
		}
		return result;
	}
	
	
	public String isSale(String mobile){
		String result=null;
		String sql="SELECT Is_sale FROM T_Mobile WHERE Tel_numb=?";
		//调用DAO
		Map<String, Object> map=registerDao.isSale(sql, mobile);
		String issale=(String)map.get("issale");
		System.out.println(issale);
		int count=(Integer)map.get("count");
		//已出售
		if(issale.equals("1")){
			//未注册
			if(count==0){
				result="success";
			}else{
				result="registered";
			}	
		}
		//未出售的
		else{
			result="notsale";
			
		}
		return result;
		
		
	}
	
	//存入数据库 
	public boolean saveMsg(Customer customer){
		boolean result=false;
		//调用存储过程
		String sql="{call pro_addCustomer(?,?,?,?,?,?,?)}";
		int i=registerDao.saveMsg(customer, sql);
		//更新成功
		if(i>0){
			result=true;
			
		}
		return result;
		
	}

	
	//单元测试
	public static void main(String[] args) {
		RegisterService r=new RegisterService();
		Customer customer=new Customer("12478914","jack","320582199508241719","13762204720","1900-01-01","123");
		//(CUSTOMER_SEQ.NEXTVAL,'1535','5234652','320582199508241719','13762204720',TO_DATE('1900-01-01','yyyy-mm-dd'),'123');
		System.out.println(r.saveMsg(customer));
	}
}
