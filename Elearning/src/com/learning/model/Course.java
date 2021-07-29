package com.learning.model;

public class Course {
	private String courseid;
	private String coursename;
	private String coursedesc;
	private String coursefee;
	public String getCourseid() {
		return courseid;
	}
	
	public Course() {
		super();
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getCoursedesc() {
		return coursedesc;
	}
	public void setCoursedesc(String coursedesc) {
		this.coursedesc = coursedesc;
	}
	public String getCoursefee() {
		return coursefee;
	}
	public void setCoursefee(String coursefee) {
		this.coursefee = coursefee;
	}
	public Course(String courseid, String coursename, String coursedesc, String coursefee) {
		super();
		this.courseid = courseid;
		this.coursename = coursename;
		this.coursedesc = coursedesc;
		this.coursefee = coursefee;
	}
}
