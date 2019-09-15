package com.ssi.BookStoreCookies;

import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/AddCart")
public class AddCart extends HttpServlet {

	private static final long serialVersionUID = 1L;
	List<String> arr = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String code = request.getParameter("choice");
			arr.add(code);
			session.setAttribute("book", arr);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");
			for (int i = 0; i < arr.size(); i++) {
				if (session.getAttribute("book") != null) {

					String sql = "SELECT * from booksentry where id=" + arr.get(i);

					PreparedStatement ps = con.prepareStatement(sql);

					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						String bcode = rs.getString(1);
						String title = rs.getString(2);
						String price = rs.getString(3);
						String author = rs.getString(4);
						String subject = rs.getString(5);
						out.println("<html>");
						out.println("<body>");
						out.println("<h3>Book-Details</h3>");
						out.println("<table border=2 cellpadding=15 colspan=10>");
						out.println("<tr>");
						out.println("<td>BCode</td>");
						out.println("<td>" + bcode + "</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Title</td>");
						out.println("<td>" + title + "</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Author</td>");
						out.println("<td>" + author + "</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Subject</td>");
						out.println("<td>" + subject + "</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Price</td>");
						out.println("<td>" + price + "</td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("</body>");
						out.println("</html>");
					}

				}
				out.println("<a href=UserOrder?code=" + code + ">Order</a><br>");
				
			}

			out.println("<a href=AddCart>View-Cart</a><br>");
			out.println("<a href=SearchList.jsp>Show-books</a><br>");
			out.println("<a href=userPage.jsp>User-Page</a><br>");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
