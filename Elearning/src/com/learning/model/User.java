package com.learning.model;

public class User {
	public User(String userid2, String username, String contact, String emailid, String address) {
		super();
		this.userid = userid2;
		this.username = username;
		this.contact = contact;
		this.emailid = emailid;
		this.address = address;
	}
	
	public User(String userid, String username, String contact, String emailid, String password, String address) {
		super();
		this.userid = userid;
		this.username = username;
		this.contact = contact;
		this.emailid = emailid;
		this.password = password;
		this.address = address;
	}

	private String userid;
	private String username;
	private String contact;
	private String emailid;
	private String password;
	private String address;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
