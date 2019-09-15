package com.ssi.BookStoreCookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@WebServlet("/Subjectwise")
public class Subjectwise extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root",
					"root");
			String sql = "SELECT distinct b_subject from booksentry";
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			out.println("<html>");
			out.println("<html><body>");
			out.println("<h3>Select The Desired Subject</h3>");
			out.println("<hr>");
			while (rs.next()) {
				String sub = rs.getString(1);
				out.println("<a href=BooksList?subject=" + sub + ">");
				out.println(sub);
				out.println("</a><br>");
			}
			out.println("<hr>");
			out.println("<a href=AddCart>View-Cart</a><br>");
			out.println("<a href=userPage.jsp>USER-Page</a>");
			out.println("</body></html>");

		} catch (Exception e) {
			out.println(e);
		}
	}
}
