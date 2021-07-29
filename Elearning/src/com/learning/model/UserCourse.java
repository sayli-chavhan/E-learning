package com.learning.model;

public class UserCourse {
	private int userid;
	private int courseid;
	
	public UserCourse(int user_id, int course_id) {
		super();
		this.userid = user_id;
		this.courseid = course_id;
	}
	public UserCourse() {
		super();
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
}
