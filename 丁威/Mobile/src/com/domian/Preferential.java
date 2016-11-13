package com.domian;

//在线充值查询的接受类
public class Preferential {

	//充值金额
	private int tel_charge;
	//实际优惠
	private int dis_amount ;
	//实际到账
	private int  sum_amount;
	//实际优惠套餐名
	private String preferential_name;
	public Preferential() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Preferential(int tel_charge, int dis_amount, int sum_amount,
			String preferential_name) {
		super();
		this.tel_charge = tel_charge;
		this.dis_amount = dis_amount;
		this.sum_amount = sum_amount;
		this.preferential_name = preferential_name;
	}
	public int getTel_charge() {
		return tel_charge;
	}
	public void setTel_charge(int tel_charge) {
		this.tel_charge = tel_charge;
	}
	public int getDis_amount() {
		return dis_amount;
	}
	public void setDis_amount(int dis_amount) {
		this.dis_amount = dis_amount;
	}
	public int getSum_amount() {
		return sum_amount;
	}
	public void setSum_amount(int sum_amount) {
		this.sum_amount = sum_amount;
	}
	public String getPreferential_name() {
		return preferential_name;
	}
	public void setPreferential_name(String preferential_name) {
		this.preferential_name = preferential_name;
	}
	
	
}
