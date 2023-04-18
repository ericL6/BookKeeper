<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/user_home_style.css">

<meta charset="ISO-8859-1">
<title></title>
</head>
	
<body>
	<div class="container">
		<h1>Welcome ${sessionScope.user}</h1>
		
		<div class="forms">
			<form method="post" action="RetrieveAllBookServlet">
				<button type="submit">View Books</button>
			</form>
			<form method="post" action="RetrieveAllNonAdminUserServlet">
				<button type="submit">View/Remove Users</button>
			</form>
			<form method="post" action="AddNewBookForm.jsp">
				<button type="submit">Add book</button>
			</form>
			<form method="post" action="RemoveBookForm.jsp">
				<button type="submit">Remove book</button>
			</form>
			<form method="post" action="AddOrRemoveAdminForm.jsp">
				<button type="submit">Add/Remove admin</button>
			</form>
			<form method="post" action="LogoutServlet">
				<button type="submit">Logout</button>
			</form>
		</div>
	</div>
</body>
</html>