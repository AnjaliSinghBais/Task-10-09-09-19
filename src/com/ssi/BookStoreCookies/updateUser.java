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

@WebServlet("/updateUser")
public class updateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();

			String uname = request.getParameter("uname");
			String password = request.getParameter("password");
			int mobile = Integer.parseInt(request.getParameter("phone"));
			String email = request.getParameter("email");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");
			String user_Name = "";
			Cookie ck[] = request.getCookies();
			if (ck != null)
				for (Cookie c : ck) {
					String name = c.getName();
					if (name.equals("id")) {
						user_Name = c.getValue();
					}
				}

			String sql = "update users set  uname=?,password=?,mobile=?,email=? where uname=?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, uname);
			ps.setString(2, password);
			ps.setInt(3, mobile);
			ps.setString(4, email);
			ps.setString(5, user_Name);
			int x = ps.executeUpdate();
			if (x > 0) {
				out.println("<html>");
				out.println("<body>");
				out.println("<h2>......RECORD UPDATED....<h2>");
				out.println("</body>");
				out.println("</html>");
			} else
				out.println("NOT UPDATED");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
