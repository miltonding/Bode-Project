package com.domian;

//�������߳�ֵ����������
public class OnlineRechargeConditions {
	
	//��ֵ����
	private String recharge_type_name;
	//�ֻ�����
	private String tel_numb;
	//�Żݱ��
	private int preferential_id;
	//ʵ�ʵ���
	private double sum_amount;
	//��ֵ���
	private int tel_charge;
	public OnlineRechargeConditions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OnlineRechargeConditions(String recharge_type_name, String tel_numb,
			int preferential_id, double sum_amount, int tel_charge) {
		super();
		this.recharge_type_name = recharge_type_name;
		this.tel_numb = tel_numb;
		this.preferential_id = preferential_id;
		this.sum_amount = sum_amount;
		this.tel_charge = tel_charge;
	}
	public String getRecharge_type_name() {
		return recharge_type_name;
	}
	public void setRecharge_type_name(String recharge_type_name) {
		this.recharge_type_name = recharge_type_name;
	}
	public String getTel_numb() {
		return tel_numb;
	}
	public void setTel_numb(String tel_numb) {
		this.tel_numb = tel_numb;
	}
	public int getPreferential_id() {
		return preferential_id;
	}
	public void setPreferential_id(int preferential_id) {
		this.preferential_id = preferential_id;
	}
	public double getSum_amount() {
		return sum_amount;
	}
	public void setSum_amount(double sum_amount) {
		this.sum_amount = sum_amount;
	}
	public int getTel_charge() {
		return tel_charge;
	}
	public void setTel_charge(int tel_charge) {
		this.tel_charge = tel_charge;
	}
	
	
}
