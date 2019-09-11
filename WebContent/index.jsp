
<%
	String s1 = "", s2 = "";
	Cookie ck[] = request.getCookies();
	if (ck != null)
		for (Cookie c : ck) {
			String name = c.getName();
			if (name.equals("id")) {
				s1 = c.getValue();
			} else if (name.equals("pw")) {
				s2 = c.getValue();
			}
		}
%>

<!DOCTYPE html>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

table, th, td {
	border: 1px solid black;
}

td, th {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<form action="LoginPage" method="post">
		<table style="width: 40%">
			<tr>
				<th>USERID</th>
				<td><input type="text" name="name" value="<%=s1%>" /></td>
			</tr>
			<tr>
				<th>PASSWORD</th>
				<td><input type="password" name="password" value="<%=s2%>" /></td>
			</tr>
			<tr>
				<th>RememberMe<input type="checkbox" name="save" value="yes"
					checked="checked" />
				</td>
			</tr>
			<tr>
				<th>USER TYPE</th>
				<td><input type="radio" name="utype" value="admin" checked>
					Admin</td>
			</tr>
			<tr>
				<th></th>
				<td><input type="radio" name="utype" value="user">User</td>
			</tr>
			<tr>
				<th>
				<td><input type="submit" style="color: blue;"Login">
				</th>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
