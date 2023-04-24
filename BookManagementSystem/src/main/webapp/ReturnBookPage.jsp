<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/return_book_style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="center">
		<h2>${sessionScope.user}'sbooks:</h2>
		<c:if test="${not empty requestScope.bookReturnStatus}">
			<p>${requestScope.bookReturnStatus}</p>
		</c:if>
		<c:choose>
			<c:when test="${not empty sessionScope.borrowBooks}">
				<table border="1">
					<tr>
						<th>book ID</th>
						<th>Title</th>
						<th>Author Last Name</th>
						<th>Author First Name</th>
						<th>Genre</th>
					</tr>
					<c:forEach items="${sessionScope.borrowBooks}" var="book"
						varStatus="loop">
						<tr>
							<td>${book.getId()}</td>
							<td>${book.getTitle()}</td>
							<td>${book.getAuthorLastName()}</td>
							<td>${book.getAuthorFirstName()}</td>
							<td>${book.getGenre()}</td>
							<td>
								<form method="post" action="ReturnBookServlet">
									<input type="hidden" name="book_id"value="${book.getId()}"/>
									<button type="submit">Return</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p>No books borrowed</p>
			</c:otherwise>
		</c:choose>
		<br>
		<form method="post" action="UserHomePage.jsp">
			<button type="submit" class="go_back_button">Back to
				homepage</button>
		</form>
	</div>
</body>
</html>