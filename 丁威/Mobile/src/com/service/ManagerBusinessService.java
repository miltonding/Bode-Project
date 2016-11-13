package com.service;

import com.dao.ManagerBusinessDao;
import com.dao.iface.ManagerBusinessDaoIface;
import com.service.iface.ManagerBusinessServiceIface;

public class ManagerBusinessService implements ManagerBusinessServiceIface{

	private ManagerBusinessDaoIface managerBusinessDao=null;
	
	
	public ManagerBusinessService() {
		super();
		managerBusinessDao=new ManagerBusinessDao();
	}


	public int QueryBusinessId(String business_name) {
		String sql=" SELECT f.business_id FROM t_business_fee f WHERE f.business_name=?";
		return managerBusinessDao.QueryBusinessId(sql, business_name);
	}

	public boolean addBusiness(String tel_numb, int business_id) {
		boolean flag=false;
		String sql="{call pro_openbusiness(?,?,?)}";
		int result=managerBusinessDao.addBusiness(sql, tel_numb, business_id);
		
		if(result==1){
			flag=true;
		}
		
		System.out.println(flag);
		return flag;
	}
	


	public boolean closeBusiness(String tel_numb, int business_id) {
		boolean flag=false;
		String sql="{call pro_closebusiness(?,?,?)}";
		int result=managerBusinessDao.closeBusiness(sql, tel_numb, business_id);
		
		if(result==1){
			flag=true;
		}
		
		System.out.println(flag);
		return flag;
	}
	
	//µ¥Ôª²âÊÔ
	public static void main(String[] args) {
		
		ManagerBusinessService m=new ManagerBusinessService();
		m.closeBusiness("13862203000", 1025);
	}


}
