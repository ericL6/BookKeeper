<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/display_book_style.css">
<meta charset="ISO-8859-1">
<title>View Books</title>
</head>
<body>
	<div class="center">
		<div class="book_search_options">
			<form method="post" action="BookSearchByServlet">
				<input type="hidden" name="search_type" value="book_id" /> <label>
					Search by book ID: <input name="book_id" type="text" required />
				</label>
				<button type="submit">Search</button>
			</form>
			<br>
			<form method="post" action="BookSearchByServlet">
				<input type="hidden" name="search_type" value="title" /> <label>
					Search by title: <input name="title" type="text" maxlength="100"
					required />
				</label>
				<button type="submit">Search</button>
			</form>
			<br>
			<form method="post" action="BookSearchByServlet">
				<input type="hidden" name="search_type" value="author" /> <label>
					Search by author last name: <input name="author_last_name"
					type="text" maxlength="50" required />
				</label> <label> , first name: <input name="author_first_name"
					type="text" maxlength="50" required />
				</label>
				<button type="submit">Search</button>
			</form>
			<br>
			<form method="post" action="BookSearchByServlet">
				<input type="hidden" name="search_type" value="genre" /> <label>
					Search by genre: <input name="genre" type="text" maxlength="50"
					required />
				</label>
				<button type="submit">Search</button>
			</form>
			<br>
			<div class="display_all_button">
				<form method="post" action="RetrieveAllBookServlet"
					style="display: inline;">
					<input type="hidden" name="search_type" value="getAllBook" />
					<button type="submit">View all books</button>
				</form>
			</div>
			<div class="go_back_button">
				<c:choose>
					<c:when test="${sessionScope.admin}">
						<form method="post" action="AdminHomePage.jsp"
							style="display: inline;">
							<button type="submit">Back to homepage</button>
						</form>
					</c:when>
					<c:otherwise>
						<form method="post" action="UserHomePage.jsp"
							style="display: inline;">
							<button type="submit">Back to homepage</button>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="display">
			<p>Search results:</p>
			<c:choose>
				<c:when test="${not empty sessionScope.SearchResult}">
					<table border="1">
						<tr>
							<th>book ID</th>
							<th>Title</th>
							<th>Author Last Name</th>
							<th>Author First Name</th>
							<th>Genre</th>
						</tr>
						<c:forEach items="${sessionScope.SearchResult}" var="book"
							varStatus="loop">
							<tr>
								<td>${book.getId()}</td>
								<td>${book.getTitle()}</td>
								<td>${book.getAuthorLastName()}</td>
								<td>${book.getAuthorFirstName()}</td>
								<td>${book.getGenre()}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<p class="no_result">No results found</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>