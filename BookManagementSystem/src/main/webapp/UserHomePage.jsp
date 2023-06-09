<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<br>
		<div class="forms">
			<form method="post" action="RetrieveAllBookServlet">
				<button type="submit">View Books</button>
			</form>
			<form method="post" action="BookCheckOutForm.jsp">
				<button type="submit">Check Out Books</button>
			</form>
			<form method="post" action="GetUserBorrowedBooksServlet">
				<button type="submit">Return Books</button>
			</form>
			<form method="post" action="LogoutServlet">
				<button type="submit">Logout</button>
			</form>
		</div>
	</div>
</body>
</html>