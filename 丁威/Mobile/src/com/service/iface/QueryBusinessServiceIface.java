package com.service.iface;

import java.util.List;

import com.domian.Business;
import com.domian.Package;

public interface QueryBusinessServiceIface {

	public List<Business> queryFreeBusiness(String tel_numb);
	//��ѯδ��ͨ���շ��ײ�
	public List<Business> queryNotOpenChargeBusiness(String tel_numb);
	//��ѯ��ͨ���շ��ײ�
	public List<Business> queryOpenChargeBusiness(String tel_numb);
	
	public List<Package> queryOpenPackage(String tel_numb);
	
	public List<Package> queryNotOpenPackage(String tel_numb);
}
