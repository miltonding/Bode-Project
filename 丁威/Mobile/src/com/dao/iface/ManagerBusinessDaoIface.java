package com.dao.iface;

public interface ManagerBusinessDaoIface {
	
	//����ҵ�����Ʋ�ѯҵ����
	public int QueryBusinessId(String sql,String business_name);
	//����ҵ��
	public int addBusiness(String sql,String tel_numb,int business_id);
	//�ر�ҵ��
	public int closeBusiness(String sql,String tel_numb,int business_id);;

}
