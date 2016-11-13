package com.service.iface;

import java.util.List;

import com.domian.Business;
import com.domian.Package;

public interface QueryBusinessServiceIface {

	public List<Business> queryFreeBusiness(String tel_numb);
	//查询未开通的收费套餐
	public List<Business> queryNotOpenChargeBusiness(String tel_numb);
	//查询开通的收费套餐
	public List<Business> queryOpenChargeBusiness(String tel_numb);
	
	public List<Package> queryOpenPackage(String tel_numb);
	
	public List<Package> queryNotOpenPackage(String tel_numb);
}
