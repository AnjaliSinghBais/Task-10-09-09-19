package com.ssi.BookStoreCookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/BooksList")
public class BooksList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String subject = request.getParameter("subject");
		PrintWriter out = response.getWriter();
		try {

			Cookie chk = new Cookie("cid", subject);
			chk.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(chk);
			String s = "";
			Cookie ch[] = request.getCookies();
			if (ch != null)
				for (Cookie c : ch) {
					String sub = c.getName();
					if (sub.equals("cid")) {
						s = c.getValue();
					}
				}

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");
			String sql = "SELECT id,b_name from booksentry where b_subject=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, subject);
			ResultSet rs = ps.executeQuery();
			out.println("<html>");
			out.println("<html><body>");
			out.println("<h3>Select The Desired Title</h3>");
			out.println("<hr>");
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				out.println("<a href=BookDetails?id=" + id + ">");
				out.println(name);
				out.println("</a><br>");
			}
			out.println("<marquee><h4>Attractive Offers On" + s + "Books</h4></marquee>");
			out.println("<hr>");
			out.println("<a href=Subjectwise>Subject-Page</a>");
			out.println("<a href=userPage.jsp>User-Page</a>");
			out.println("</body></html>");

		} catch (Exception e) {
			out.println(e);
		}
	}

}
