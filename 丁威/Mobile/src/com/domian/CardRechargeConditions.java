package com.domian;

public class CardRechargeConditions {

	private String tel_numb;
	
	private int card_id;
	
	private String card_pwd;

	public CardRechargeConditions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardRechargeConditions(String tel_numb, int card_id, String card_pwd) {
		super();
		this.tel_numb = tel_numb;
		this.card_id = card_id;
		this.card_pwd = card_pwd;
	}

	public String getTel_numb() {
		return tel_numb;
	}

	public void setTel_numb(String tel_numb) {
		this.tel_numb = tel_numb;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public String getCard_pwd() {
		return card_pwd;
	}

	public void setCard_pwd(String card_pwd) {
		this.card_pwd = card_pwd;
	}
	
	
}
