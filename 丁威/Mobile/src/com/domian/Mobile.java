package com.domian;

//�ֻ�����
public class Mobile {
	//������
	private String tel_add;
	//Ԥ�滰��
	private double acc_init_amount;
	//���뿪ͷ
	private String begin;
	//��������
	private String type;
	//��������
	private String containNumber;
	//����
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
