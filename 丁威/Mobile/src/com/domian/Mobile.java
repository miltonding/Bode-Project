package com.domian;

//手机号码
public class Mobile {
	//归属地
	private String tel_add;
	//预存话费
	private double acc_init_amount;
	//号码开头
	private String begin;
	//号码类型
	private String type;
	//包含数字
	private String containNumber;
	//号码
	private String tel_numb;
	public Mobile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mobile(String tel_add, double acc_init_amount, String begin,
			String type, String containNumber) {
		super();
		this.tel_add = tel_add;
		this.acc_init_amount = acc_init_amount;
		this.begin = begin;
		this.type = type;
		this.containNumber = containNumber;
	}
	public Mobile( String tel_numb, String tel_add,double acc_init_amount) {
		super();
		this.tel_numb = tel_numb;
		this.acc_init_amount = acc_init_amount;
		this.tel_add = tel_add;
	}
	public String getTel_add() {
		return tel_add;
	}
	public void setTel_add(String tel_add) {
		this.tel_add = tel_add;
	}
	public double getAcc_init_amount() {
		return acc_init_amount;
	}
	public void setAcc_init_amount(double acc_init_amount) {
		this.acc_init_amount = acc_init_amount;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContainNumber() {
		return containNumber;
	}
	public void setContainNumber(String containNumber) {
		this.containNumber = containNumber;
	}
	public String getTel_numb() {
		return tel_numb;
	}
	public void setTel_numb(String tel_numb) {
		this.tel_numb = tel_numb;
	}

	
	
	
	
	
}
