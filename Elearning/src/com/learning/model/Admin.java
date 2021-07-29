package com.learning.model;

public class Admin {
	private String adminid;
	private String adminname;
	private String contact;
	private String emailid;
	private String password;
	private String address;
	public String getAdminid() {
		return adminid;
	}
	
	public Admin() {
		super();
	}

	public Admin(String adminid, String adminname, String contact, String emailid, String password, String address) {
		super();
		this.adminid = adminid;
		this.adminname = adminname;
		this.contact = contact;
		this.emailid = emailid;
		this.password = password;
		this.address = address;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
