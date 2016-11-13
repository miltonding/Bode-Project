package com.domian;

//此类用来接受购买号码，选择套餐的输入条件
public class PayMobile {
	private String tel_numb;
	
	private double init_account ;
	
	private int pp_id;

	public PayMobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PayMobile(String tel_numb, double init_account, int pp_id) {
		super();
		this.tel_numb = tel_numb;
		this.init_account = init_account;
		this.pp_id = pp_id;
	}

	public String getTel_numb() {
		return tel_numb;
	}

	public void setTel_numb(String tel_numb) {
		this.tel_numb = tel_numb;
	}

	public double getInit_account() {
		return init_account;
	}

	public void setInit_account(double init_account) {
		this.init_account = init_account;
	}

	public int getPp_id() {
		return pp_id;
	}

	public void setPp_id(int pp_id) {
		this.pp_id = pp_id;
	}
	
	
}
