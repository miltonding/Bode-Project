package com.dao.iface;

public interface ManagerBusinessDaoIface {
	
	//根据业务名称查询业务编号
	public int QueryBusinessId(String sql,String business_name);
	//增加业务
	public int addBusiness(String sql,String tel_numb,int business_id);
	//关闭业务
	public int closeBusiness(String sql,String tel_numb,int business_id);;

}
