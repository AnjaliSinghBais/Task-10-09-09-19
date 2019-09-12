<%@ page import= "java.sql.*" %>
<html>
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

<h2>BOOKS IN THE BOOKSTORE.....SELECT THE REQUIRED SUBJECT</h2>
<body>
<form action="MultipleBooks.jsp" method="get">
<select multiple name="Choice">
<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bookstore", "root", "root");
	PreparedStatement ps = con.prepareStatement("select distinct b_subject from booksentry");
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
%>

  <option value=<%=rs.getString(1) %>><%=rs.getString(1) %></option>
  
<%}%>
</select>

<input type="submit" value="SEARCH"> 
</form>
</body>
</html>