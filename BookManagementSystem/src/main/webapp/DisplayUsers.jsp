<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/display_user_style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="center">
		<c:if test="${not empty requestScope.userRemoved}">
			<p>${requestScope.userRemoved}</p>
		</c:if>
		<c:choose>
			<c:when test="${not empty sessionScope.userlist}">
				<div class="display">
					<table>
						<tr>
							<th>username: </th>
						</tr>
						<c:forEach items="${sessionScope.userlist}" var="user" varStatus="loop">
							<tr>
								<td>${user}</td>
								<td>
									<form method="post" action="RemoveUserServlet">
										<input type="hidden" name="username" value="${user}"/>
										<button type="submit">Remove user</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<p>No results found</p>
			</c:otherwise>
		</c:choose>
		<form method="post" action="AdminHomePage.jsp">
			<button type="submit" class="go_back_button">Back to homepage</button>
		</form>
	</div>
</body>
</html>