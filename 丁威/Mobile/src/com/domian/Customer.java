package com.domian;

public class Customer {	
	private String username;
	private String dname;
	private String idNumber;
	private String mobile;
	private String birth;
	private String pwd;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}

	public Customer(String username, String dname, String idNumber,
			String mobile, String birth, String pwd) {
		super();
		this.username = username;
		this.dname = dname;
		this.idNumber = idNumber;
		this.mobile = mobile;
		this.birth = birth;
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	
	
	
}
