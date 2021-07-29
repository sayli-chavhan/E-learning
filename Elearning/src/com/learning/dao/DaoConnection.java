package com.learning.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.learning.model.Admin;
import com.learning.model.Course;
import com.learning.model.User;
import com.learning.model.UserCourse;
import com.learning.utils.JDBCUtils;

public class DaoConnection {
	   
  	private static final String INSERT_USERS_SQL = "INSERT INTO user" + "  (userid, username, contact, emailid, password, address) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select userid,username,contact,emailid,address from user where userid =?";
	private static final String VALIDATE_USER= "select * from user where emailid=? AND password=?";
	private static final String ENROLL_COURSE= "INSERT INTO user_course" + " (userid,courseid) values " + "(?, ?);";
	private static final String UNENROLL_COURSE= "DELETE FROM user_course WHERE userid=? and courseid=?";
	private static final String CHECK_ADMIN= "select * from admin where emailid=? AND password=?";
	private static final String SELECT_ALL_USERS = "select * from user";
	private static final String SELECT_USER = "select emailid, password from user";
	private static final String DELETE_COURSE_SQL = "delete from course where courseid=?;";
	private static final String DELETE_USER = "delete from user where userid=?;";
	private static final String SELECT_ENROLLED_COURSE = "select * from course where courseid in (select courseid from user_course)";
	private static final String SELECT_ALL_COURSE = "select * from course";
	public DaoConnection() {
	}
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getUserid());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getContact());
			preparedStatement.setString(4, user.getEmailid());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getAddress());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
	}

	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String userid = rs.getString("userid");
				String username = rs.getString("username");
				String contact = rs.getString("contact");
				String emailid = rs.getString("emailid");
//				String password = rs.getString("password");
				String address = rs.getString("address");
				user = new User(userid, username, contact, emailid, address);
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return user;
	}
	public User validateuser(String emailid, String password) {
		User user=null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_USER);) {
			preparedStatement.setString(1, emailid);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.isBeforeFirst() )
			{
				rs.next();
				String userid = rs.getString("userid");
				String username = rs.getString("username");
				String contact = rs.getString("contact");
				String email_id = rs.getString("emailid");
				String pass_word = rs.getString("password");
				String address = rs.getString("address");
				user=new User(userid, username, contact, email_id, pass_word, address);
			}
			
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return user;
	}
	public Admin checkadmin(String emailid, String password) {
		Admin admin = null;
		System.out.println(emailid);
		System.out.println(password);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ADMIN);) {
			preparedStatement.setString(1, emailid);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.isBeforeFirst() )
			{
				rs.next();
				String adminid = rs.getString("adminid");
				String adminname = rs.getString("adminname");
				String contact = rs.getString("contact");
				String email_id = rs.getString("emailid");
				String pass_word = rs.getString("password");
				String address = rs.getString("address");
				admin=new Admin(adminid, adminname, contact, email_id, pass_word, address);
				
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		//System.out.println(admin.getAdminid());
		return admin;
	}
	public List<User> selectAllUsers() {

		List<User> users = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String userid = rs.getString("userid");
				String username = rs.getString("username");
				String contact = rs.getString("contact");
				String emailid = rs.getString("emailid");
				String address = rs.getString("address");
				users.add(new User(userid, username, contact,emailid, address));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return users;
	}
	
	public boolean deleteCourse(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_COURSE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	public List<Course> selectCoursebyid() {
		List<Course> enrolledcourse = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ENROLLED_COURSE);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String course_id = rs.getString("courseid");
				String coursename = rs.getString("coursename");
				String coursedesc = rs.getString("coursedesc");
				String coursefee = rs.getString("coursefee");
				enrolledcourse.add(new Course(course_id, coursename, coursedesc, coursefee));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return enrolledcourse;
	}
	public List<Course> selectAllCourse() {
		List<Course> allcourse = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSE);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String courseid = rs.getString("courseid");
				String coursename = rs.getString("coursename");
				String coursedesc = rs.getString("coursedesc");
				String coursefee = rs.getString("coursefee");
				allcourse.add(new Course(courseid, coursename, coursedesc, coursefee));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return allcourse;
	}
	public void enrollcourse(int userid,int courseid) {
		//UserCourse usercourse=null;
		System.out.println(userid);
		System.out.println(courseid);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ENROLL_COURSE);) {
			preparedStatement.setInt(1, userid);
			preparedStatement.setInt(2, courseid);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		//System.out.println(admin.getAdminid());
		//return usercourse;
	}
	public void unenrollcourse(int userid,int courseid) {
		//UserCourse usercourse=null;
		System.out.println(userid);
		System.out.println(courseid);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UNENROLL_COURSE);) {
			preparedStatement.setInt(1, userid);
			preparedStatement.setInt(2, courseid);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		//System.out.println(admin.getAdminid());
		//return usercourse;
	}
	}

