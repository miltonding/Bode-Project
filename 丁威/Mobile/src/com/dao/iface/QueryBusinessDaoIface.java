package com.dao.iface;

import java.util.List;

import com.domian.Business;
import com.domian.Package;

public interface QueryBusinessDaoIface {
	//����/����ҵ��
	public  List<Business> queryFreeBusiness(String sql,String tel_numb);
	//δ��ͨ���շ�ҵ��
	public  List<Business> queryNotOpenChargeBusiness(String sql,String tel_numb);
	//��ͨ���շ�ҵ��
	public  List<Business> queryOpenChargeBusiness(String sql,String tel_numb);
	//��ѯ��ǰ�ײ�
	public List<Package> queryOpenPackage(String sql,String tel_numb);
	//��ѯδ��ͨ�ײ�
	public List<Package> queryNotOpenPackage(String sql,String tel_numb);
}
