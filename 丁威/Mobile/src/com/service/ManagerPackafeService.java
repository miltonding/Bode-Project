package com.service;

import com.dao.ManagerPackageDao;
import com.dao.iface.ManagerPackageDaoIface;
import com.service.iface.ManagerPackageServiceIface;

public class ManagerPackafeService implements ManagerPackageServiceIface{

	private ManagerPackageDaoIface managerPackageDao=null;
	public ManagerPackafeService() {
		super();
		managerPackageDao=new ManagerPackageDao();
	}

	public boolean changePackage(int pp_id, String tel_numb) {
		boolean result=false;
		//¸üÐÂ
		String sql="UPDATE t_mobile_package SET pp_id=?,start_time=SYSDATE WHERE TEL_NUMB=? AND pp_id!=0";
		int i=managerPackageDao.changePackage(sql, pp_id, tel_numb);
		if(i==1){
			result=true;
			
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		ManagerPackafeService m=new ManagerPackafeService();
		m.changePackage(1001, "13862203000");
	}

}
