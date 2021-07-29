package com.learning.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.learning.dao.DaoConnection;
import com.learning.model.Admin;
import com.learning.model.Course;
import com.learning.model.User;
import com.learning.model.UserCourse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="RegisterServlet", urlPatterns="/")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoConnection userDAO; 
    public void init() {
		userDAO = new DaoConnection();
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/home":
				homepage(request, response);
				break;
			case "/register":
				registeruser(request, response);
				break;
			case "/showRegPage":
				displayRegpage(request, response);
				break;
			case "/showLoginpage":
				displayLoginpage(request, response);
				break;
			case "/login":
				login(request, response);
				break;
			case "/course":
				course(request, response);
				break;
			case "/courseadmin":
				courseadmin(request, response);
				break;
			case "/userlist":
				userlist(request, response);
				break;
			case "/userpage":
				userpage(request, response);
				break;
			case "/adminpage":
				adminpage(request, response);
				break;
			case "/delete":
				deleteCourse(request, response);
				break;
			case "/deleteuser":
				deleteUser(request, response);
				break;
//			case "/edit":
//				editUser(request, response);
//				break;
			case "/enroll":
				showEnrollForm(request, response);
				break;
			case "/unenroll":
				unenrollcourse(request, response);
				break;
			default:
				homepage(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void homepage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Home.jsp");
		dispatcher.forward(request, response);
//		response.sendRedirect("/register");
	}
	private void displayRegpage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Register.jsp");
		dispatcher.forward(request, response);
//		response.sendRedirect("/register");
	}
	private void displayLoginpage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Login.jsp");
		dispatcher.forward(request, response);
//		response.sendRedirect("/register");
	}
	private void showEnrollForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		System.out.print(courseid);
		System.out.print(userid);
		userDAO.enrollcourse(userid,courseid);
		//Course enrolled=userDAO.selectCoursebyid(usercourse.getCourseid());
		List<Course> listofenrolledcourse=userDAO.selectCoursebyid();
		System.out.print(listofenrolledcourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/UserPage.jsp");
		request.setAttribute("listcourse", listofenrolledcourse);
		request.setAttribute("userid", userid);
		System.out.print(listofenrolledcourse);
		dispatcher.forward(request, response);
	}
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String emailid= request.getParameter("emailid");
		String password= request.getParameter("password");
		String role = request.getParameter("role");
		System.out.println(emailid);
		System.out.println(password);
		System.out.println(role);
		if(role.equals("admin")) {
			Admin admin=userDAO.checkadmin(emailid,password);
			
			if(admin==null) {
				System.out.println("Admin is null");
				request.setAttribute("alertMsg", "Incorrect password or id");
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/Login.jsp");  
				rd.include(request, response);
				return;
			}
			
			request.setAttribute("adminid", admin.getAdminid());
			request.setAttribute("adminname", admin.getAdminname());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp");
			 dispatcher.forward(request, response);
		}
		else {
			User user=userDAO.validateuser(emailid, password);
			if(user==null) {
				System.out.println("User is null");
				request.setAttribute("alertMsg", "Incorrect password or id");
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/Login.jsp");  
				rd.include(request, response);
				return;			
			}
			request.setAttribute("userid", user.getUserid());
			request.setAttribute("username", user.getUsername());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/UserPage.jsp");
		 dispatcher.forward(request, response);
		}
	}
	private void registeruser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String userid= request.getParameter("userid");
		String username= request.getParameter("username");
		String contact= request.getParameter("contact");
		String emailid= request.getParameter("emailid");
		String password= request.getParameter("password");
		String address= request.getParameter("address");
		System.out.println("Userid"+userid);
		System.out.println("emailid"+emailid);
		System.out.println("contact"+contact);
		System.out.println(address);
		System.out.println(password);
		User newUser = new User(userid, username, contact, emailid, password, address);
		userDAO.insertUser(newUser);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Login.jsp");
//		dispatcher.forward(request, response);
		response.sendRedirect("login");
	}
	private void userpage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> userlist = userDAO.selectAllUsers();
		request.setAttribute("listUser", userlist);
		List<Course> allcourse = userDAO.selectAllCourse();
		request.setAttribute("listcourse", allcourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/UserPage.jsp");
		dispatcher.forward(request, response);
	}
	private void adminpage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		List<Course> listEnrolledCourse = userDAO.selectCourse();
//		request.setAttribute("listUser", listEnrolledCourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp");
		dispatcher.forward(request, response);
	}
	private void course(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		System.out.println(userid);
		List<Course> allcourse = userDAO.selectAllCourse();
		request.setAttribute("userid", userid);
		request.setAttribute("listCourse", allcourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/course.jsp");
		dispatcher.forward(request, response);
	}
	private void courseadmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Course> allcourse = userDAO.selectAllCourse();
		request.setAttribute("listcourse", allcourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/CourseAdmin.jsp");
		dispatcher.forward(request, response);
	}
	private void userlist(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> userlist = userDAO.selectAllUsers();
		request.setAttribute("listUser", userlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Userlist.jsp");
		dispatcher.forward(request, response);
	}
	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException{
		String id=request.getParameter("id");
		System.out.println(id);
		int newid = Integer.parseInt(id);
		System.out.println(newid);
		userDAO.deleteCourse(newid);
		List<Course> allcourse = userDAO.selectAllCourse();
		request.setAttribute("listcourse", allcourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/CourseAdmin.jsp");
		dispatcher.forward(request, response);
	}
	private void unenrollcourse(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException{
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		System.out.print(courseid);
		System.out.print(userid);
		userDAO.unenrollcourse(userid,courseid);
		//Course enrolled=userDAO.selectCoursebyid(usercourse.getCourseid());
		List<Course> listofenrolledcourse=userDAO.selectCoursebyid();
		System.out.print(listofenrolledcourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/UserPage.jsp");
		request.setAttribute("listcourse", listofenrolledcourse);
		request.setAttribute("userid", userid);
		System.out.print(listofenrolledcourse);
		dispatcher.forward(request, response);
	}
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException{
		String id=request.getParameter("id");
		System.out.println(id);
		int newid = Integer.parseInt(id);
		System.out.println(newid);
		userDAO.deleteUser(newid);
		List<User> userlist = userDAO.selectAllUsers();
		request.setAttribute("listUser", userlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Userlist.jsp");
		dispatcher.forward(request, response);
	}
}