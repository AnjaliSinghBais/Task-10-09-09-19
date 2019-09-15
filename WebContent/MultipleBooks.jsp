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


<%@ page import="java.sql.*"%>

<html>
<body>
	<table>
		<tr>
			<th>BOOKCODE</th>
			<th>BOOKNAME</th>
			<th>SUBJECT</th>
			<th>PRICE</th>
			<th>AUTHOR</th>
			<th>ADD TO CART</th>
		</tr>


		<%
			String languages = " ";
			String[] lang = request.getParameterValues("Choice");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bookstore", "root", "root");
			for (int i = 0; i < lang.length; i++) {
				PreparedStatement ps = con
						.prepareStatement("select * from booksentry where b_subject=('" + lang[i] + "')");
				ResultSet rs1 = ps.executeQuery();
				while (rs1.next()) {
		%>

		<tr>
			<td><%=rs1.getString(1)%></td>
			<td><%=rs1.getString(2)%></td>
			<td><%=rs1.getString(3)%></td>
			<td><%=rs1.getString(4)%></td>
			<td><%=rs1.getString(5)%></td>
			<td><a href="AddCart?choice=<%=rs1.getString(1)%>">Add-Cart</a></td>
		</tr>


		<%
			}
			}
		%>
	</table>
</body>
</html>