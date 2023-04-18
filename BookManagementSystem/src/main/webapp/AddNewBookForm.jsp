<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/add_book_style.css">
<meta charset="ISO-8859-1">
<title>Add new Book</title>
</head>
<body>
	<div class="center">
	<h2>Add new book: </h2>
		<div class="add_book_result">
			<c:if test="${not empty requestScope.wasBookAdded}">
				<p>${requestScope.wasBookAdded}</p>
			</c:if>
		</div>
		<form method="post" action="AddBookServlet">
			<label>
				Title: <input type="text" name="title" maxlength="100" required/>
			</label>
			<br>
			<label>
				Author last name: <input type="text" name="author_last_name" maxlength="50" required/>
			</label>
			<label>
				, first name: <input type="text" name="author_first_name" maxlength="50" required/>
			</label>
			<br>
			<label>
				Genre: <input type="text" name="genre" maxlength="50" required/>
			</label>
			<br>
			<button type="submit" class="submit_form">submit</button>
		</form>
		<div class="go_back_button">
			<form method="post" action="AdminHomePage.jsp">
				<button type="submit">Back to homepage</button>
			</form>
		</div>
	</div>
	
</body>
</html>