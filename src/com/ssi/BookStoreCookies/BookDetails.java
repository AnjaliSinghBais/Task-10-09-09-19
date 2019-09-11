package com.ssi.BookStoreCookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookDetails")
public class BookDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			String bid = request.getParameter("id");
			Cookie chk = new Cookie("bid", "1");
			chk.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(chk);

			String s = "";
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");
			String sql = "SELECT * from booksentry where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(bid));
			ResultSet rs = ps.executeQuery();
			out.println("<html>");
			out.println("<html><body>");
			out.println("<h3>Book-Details</h3>");
			out.println("<hr>");
			while (rs.next()) {
				String bcode = rs.getString(1);
				String title = rs.getString(2);
				String subject = rs.getString(3);
				int Price = rs.getInt(4);
				String author = rs.getString(5);
				out.println("<table border=2>");
				out.println("<tr>");
				out.println("<td>BCode</td>");
				out.println("<td>" + bcode + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Title</td>");
				out.println("<td>" + title + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>subject</td>");
				out.println("<td>" + subject + "</td>");
				out.println("</tr>");

				Cookie ch[] = request.getCookies();
				if (ch != null)
					for (Cookie c : ch) {
						String sub = c.getName();
						if (sub.equals("bid")) {
							String s1 = c.getValue();
							int m = Integer.parseInt(s1);
							m = m + 1;
							c.setValue(String.valueOf(m));
							response.addCookie(c);
						}

					}
				if (ch != null)
					for (Cookie c : ch) {
						String sub = c.getName();
						if (sub.equals("bid")) {
							String s1 = c.getValue();
							int x = Integer.parseInt(s1);

							if (x > 5 || x < 10) {
								out.println("<tr>");
								out.println("<td>price</td>");
								out.println("<td>" + (Price * 0.01 + Price) + "</td>");
								out.println("</tr>");
								out.println("<tr>");
							} else if (x > 10) {

								out.println("<tr>");
								out.println("<td>price</td>");
								out.println("<td>" + (Price * 0.02 + Price) + "</td>");
								out.println("</tr>");
								out.println("<tr>");

							} else if (x < 5) {
								out.println("<tr>");
								out.println("<td>price</td>");
								out.println("<td>" + Price + "</td>");
								out.println("</tr>");
								out.println("<tr>");
							}
						}
					}

				out.println("<tr>");
				out.println("<td>author</td>");
				out.println("<td>" + author + "</td>");
				out.println("</tr>");
				out.println("</table>");

				out.println("<hr>");
				out.println("<a href=CartManager?code=" + bid + ">Add-To-Cart</a><br>");
				out.println("<a href=SubjectPageServlet>Subject-Page</a><br>");
				out.println("<a href=buyerpage.jsp>Buyer-Page</a><br>");
				out.println("</body></html>");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
