package com.dao.iface;

import java.util.List;

import com.domian.Business;
import com.domian.Package;

public interface QueryBusinessDaoIface {
	//基础/赠送业务
	public  List<Business> queryFreeBusiness(String sql,String tel_numb);
	//未开通的收费业务
	public  List<Business> queryNotOpenChargeBusiness(String sql,String tel_numb);
	//开通的收费业务
	public  List<Business> queryOpenChargeBusiness(String sql,String tel_numb);
	//查询当前套餐
	public List<Package> queryOpenPackage(String sql,String tel_numb);
	//查询未开通套餐
	public List<Package> queryNotOpenPackage(String sql,String tel_numb);
}
