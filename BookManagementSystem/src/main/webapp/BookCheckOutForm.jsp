<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/book_search_style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<div class="center">
		<div class="search_by_id">
			<c:if test="${not empty requestScope.errorMessage}">
				<p class="error_message">${requestScope.errorMessage}</p>
			</c:if>
			<c:if test="${not empty requestScope.successMessage}">
				<p>${requestScope.successMessage}</p>
			</c:if>
			<h3>Check out by book ID:</h3>
			<form method="post" action="BookCheckOutServlet">
				<input type="hidden" name="check_out_type" value="book_id" /> <label>Checkout
					Book by ID: <input name="book_id" type="text" required />
				</label>
				<button type="submit">submit</button>
			</form>
		</div>

		<div class="search_by_info">
			<h3>Check out by book information:</h3>
			<form method="post" action="BookCheckOutServlet">
				<input type="hidden" name="check_out_type" value="book_info" /> <label>
					Title: <input name="title" type="text" maxlength="100" required />
				</label> <br> <label> Author last name: <input
					name="author_last_name" type="text" maxlength="50" required />
				</label> <label> , first name: <input name="author_first_name"
					type="text" maxlength="50" required />
				</label> <br> <label> Search by genre: <input name="genre"
					type="text" maxlength="50" required />
				</label> <br>
				<button type="submit">submit</button>
			</form>
			<br>
			<form method="post" action="UserHomePage.jsp">
				<button type="submit">Back to homepage</button>
			</form>
		</div>
	</div>

</body>
</html>