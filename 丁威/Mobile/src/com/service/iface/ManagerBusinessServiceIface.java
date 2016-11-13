package com.service.iface;

public interface ManagerBusinessServiceIface {

	public int QueryBusinessId(String business_name);
	
	public boolean addBusiness( String tel_numb,int business_id);
	
	public boolean closeBusiness( String tel_numb,int business_id);
}
