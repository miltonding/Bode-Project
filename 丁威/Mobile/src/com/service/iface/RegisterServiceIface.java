package com.service.iface;

import com.domian.Customer;

public interface RegisterServiceIface {
	
	
	public boolean countUser(String username);
	
	public String isSale(String mobile);
	
	public boolean saveMsg(Customer customer);
}
