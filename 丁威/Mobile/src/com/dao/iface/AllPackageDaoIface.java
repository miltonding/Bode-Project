package com.dao.iface;

import java.util.List;

import com.domian.Package;

public interface AllPackageDaoIface {

	//查询所有套餐及赠送业务
	public List<Package> selectAllBusiness(String sql);
}
