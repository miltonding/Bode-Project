package com.domian;

import java.util.Date;

public class Business {

	//业务编号
	private int business_id;
	//套餐名称
	private String business_name;
	//业务收费
	private int business_charge;
	//开始时间
	private String start_time;
	//结束时间
	private String end_time;
	//可操作
	private int is_optional;
	//基础，赠送
	private int is_largess;
	//区分是否可操作
	private int status;
	public Business() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Business(int business_id, String business_name, int business_charge,
			String start_time, String end_time, int is_optional, int is_largess) {
		super();
		this.business_id = business_id;
		this.business_name = business_name;
		this.business_charge = business_charge;
		this.start_time = start_time;
		this.end_time = end_time;
		this.is_optional = is_optional;
		this.is_largess = is_largess;
	}
	public Business(String business_name, int business_charge,
			String start_time, String end_time, int is_optional, int is_largess) {
		super();
		this.business_name = business_name;
		this.business_charge = business_charge;
		this.start_time = start_time;
		this.end_time = end_time;
		this.is_optional = is_optional;
		this.is_largess = is_largess;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Business(int business_id, String business_name, int business_charge,
			String start_time, String end_time, int is_optional,
			int is_largess, int status) {
		super();
		this.business_id = business_id;
		this.business_name = business_name;
		this.business_charge = business_charge;
		this.start_time = start_time;
		this.end_time = end_time;
		this.is_optional = is_optional;
		this.is_largess = is_largess;
		this.status = status;
	}
	public int getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
	}
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public int getBusiness_charge() {
		return business_charge;
	}
	public void setBusiness_charge(int business_charge) {
		this.business_charge = business_charge;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public int getIs_optional() {
		return is_optional;
	}
	public void setIs_optional(int is_optional) {
		this.is_optional = is_optional;
	}
	public int getIs_largess() {
		return is_largess;
	}
	public void setIs_largess(int is_largess) {
		this.is_largess = is_largess;
	}
	
	
}
