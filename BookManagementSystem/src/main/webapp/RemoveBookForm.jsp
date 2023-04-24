<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/remove_book_style.css">
<meta charset="ISO-8859-1">
<title>Remove book:</title>
</head>
<body>
	<div class="center">
		<div class="remove_by_id">
			<h2>Remove book by id:</h2>
			<c:if test="${not empty requestScope.wasBookRemoved}">
				<p>${requestScope.wasBookRemoved}</p>
			</c:if>
			<form method="post" action="RemoveBookServlet">
				<input type="hidden" name="remove_book_by" value="book_id" /> <label>
					Book ID: <input type="text" name="book_id" required />
				</label>
				<button type="submit">submit</button>
			</form>
		</div>
		<br>
		<div class="remove_by_info">
			<h2>Remove book by information:</h2>
			<form method="post" action="RemoveBookServlet">
				<input type="hidden" name="remove_book_by" value="book_info" /> 
				<label>
					Title: <input type="text" name="title" maxlength="100" required />
				</label>
				<br> 
				<label> 
					Author last name: <input type="text" name="author_last_name" maxlength="50" required />
				</label> 
				<label> 
					, first name: <input type="text" name="author_first_name" maxlength="50" required />
				</label> 
				<br> 
				<label> 
					Genre: <input type="text" name="genre" maxlength="50" required />
				</label> 
				<br>
				<button type="submit" class="submit_form">submit</button>
			</form>
			<form method="post" action="AdminHomePage.jsp">
				<button type="submit">Back to homepage</button>
			</form>
		</div>

	</div>


</body>
</html>