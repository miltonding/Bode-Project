package com.domian;

//��ֵ��¼
public class RechargeInfor {

	//��ֵ����
	private String recharge_time;
	//��ֵ���
	private int recharge_money;
	//��ֵ��ʽ
	private String recharge_type_name;
	//��ֵ�����
	private int card_id;
	//���п�����
	private int  Bank_card_numb;
	//�Żݽ��
	private int discount_amount;
	public RechargeInfor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RechargeInfor(String recharge_time, int recharge_money,
			String recharge_type_name, int card_id, int bank_card_numb,
			int discount_amount) {
		super();
		this.recharge_time = recharge_time;
		this.recharge_money = recharge_money;
		this.recharge_type_name = recharge_type_name;
		this.card_id = card_id;
		Bank_card_numb = bank_card_numb;
		this.discount_amount = discount_amount;
	}
	public String getRecharge_time() {
		return recharge_time;
	}
	public void setRecharge_time(String recharge_time) {
		this.recharge_time = recharge_time;
	}
	public int getRecharge_money() {
		return recharge_money;
	}
	public void setRecharge_money(int recharge_money) {
		this.recharge_money = recharge_money;
	}
	public String getRecharge_type_name() {
		return recharge_type_name;
	}
	public void setRecharge_type_name(String recharge_type_name) {
		this.recharge_type_name = recharge_type_name;
	}
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public int getBank_card_numb() {
		return Bank_card_numb;
	}
	public void setBank_card_numb(int bank_card_numb) {
		Bank_card_numb = bank_card_numb;
	}
	public int getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(int discount_amount) {
		this.discount_amount = discount_amount;
	}
	
	
}
