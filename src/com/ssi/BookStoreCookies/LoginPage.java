package com.ssi.BookStoreCookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// BOOK STORE COOKIES TASK
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String utype = request.getParameter("utype");
		try {
			if (utype.equals("admin")) {
				if (name.equals("admin") && password.equals("abc")) {
					response.sendRedirect("adminPage.jsp");
				} else {
					out.println("INVALID ADMIN CREDENTIALS");
				}
			} else {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");
				String sql = "SELECT uname FROM USERS where uname=? AND password=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					String choice = request.getParameter("save");
					if (choice != null) {
						Cookie c1 = new Cookie("id", name);
						Cookie c2 = new Cookie("pw", password);
						c1.setMaxAge(60 * 60 * 24 * 7);
						c2.setMaxAge(60 * 60 * 24 * 7);
						response.addCookie(c1);
						response.addCookie(c2);
					}
					RequestDispatcher rd = request.getRequestDispatcher("userPage.jsp");
					rd.forward(request, response);
				} else {
					out.println("INVALID USER CREDENTIALS");
				}
				con.close();
			}
		} catch (Exception e) {
			out.println(e);
		}
	}
}
