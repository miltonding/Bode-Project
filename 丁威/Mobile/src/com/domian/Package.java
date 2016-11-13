package com.domian;

import java.util.List;

public class Package {
	private int pp_id;
	private String pp_name;
	private double pp_fee;
	//ิ๙หอาตฮ๑
	private String freeBussiness;
	public Package() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Package(int pp_id, String pp_name, double pp_fee,
			String freeBussiness) {
		super();
		this.pp_id = pp_id;
		this.pp_name = pp_name;
		this.pp_fee = pp_fee;
		this.freeBussiness = freeBussiness;
	}
	public Package(String pp_name, double pp_fee, String freeBussiness) {
		super();
		this.pp_name = pp_name;
		this.pp_fee = pp_fee;
		this.freeBussiness = freeBussiness;
	}
	public int getPp_id() {
		return pp_id;
	}
	public void setPp_id(int pp_id) {
		this.pp_id = pp_id;
	}
	public String getPp_name() {
		return pp_name;
	}
	public void setPp_name(String pp_name) {
		this.pp_name = pp_name;
	}
	public double getPp_fee() {
		return pp_fee;
	}
	public void setPp_fee(double pp_fee) {
		this.pp_fee = pp_fee;
	}
	public String getFreeBussiness() {
		return freeBussiness;
	}
	public void setFreeBussiness(String freeBussiness) {
		this.freeBussiness = freeBussiness;
	}
	
	
	
}
