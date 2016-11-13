package com.service.iface;

import java.util.Map;
import com.domian.Customer;

public interface LoginServiceIface {
	//·µ»ØÐÕÃû
	public String login(Customer customer);
	
	public  Map<String, Object> findAllByID(String username);
}

