<%@ page import="java.sql.*"%>

<html>
<head>
<h2>UPDATE YOUR DETAILS</h2>
</head>

<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>


<%
	String user_Name = "";
	Cookie ck[] = request.getCookies();
	if (ck != null)
		for (Cookie c : ck) {
			String name = c.getName();
			if (name.equals("id")) {
				user_Name = c.getValue();
			}
		}
	Class.forName("com.mysql.jdbc.Driver");
	out.print("session");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bookstore", "root", "root");
	PreparedStatement ps = con.prepareStatement("select uname,password,mobile,email from users where uname=?");
	ps.setString(1, user_Name);
	out.print("session");
	ResultSet rs = ps.executeQuery();
	out.print("session");
	while (rs.next()) {
%>
<body>
	<form action="updateUser" method="get">
		<table>
			<tr>

				<th>USER_NAME</th>
				<th>PASSWORD</th>
				<th>PHONE</th>
				<th>EMAIL</th>
			</tr>
			<tr>

				<td><input type="uname" size="10" placeholder="New Name"
					name="uname"><%=rs.getString(1)%></td>
				<td><input type="password" size="10" placeholder="New Password"
					name="password"><%=rs.getString(2)%></td>
				<td><input type="phone" size="10" placeholder=" New Mobileno"
					name="phone"><%=rs.getString(3)%></td>
				<td><input type="email" size="10" placeholder="New Email"
					name="email"><%=rs.getString(4)%></td>


			</tr>

		</table>
		<input type="submit" value="UPDATE">
	</form>
	<%
		}
	%>














</body>
</html>