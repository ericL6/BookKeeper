<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/add_remove_admin_style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="center">
		<div class="create_admin_field">
			<h2>Create new admin: </h2>
			<c:if test="${not empty requestScope.errorMessage}">
				<div class="alert">${requestScope.errorMessage}</div>
			</c:if>
			<c:if test="${not empty requestScope.successMessage}">
				<div class="alert">${requestScope.successMessage}</div>
			</c:if>
			<form method="post" action="UserAdminRegisterServlet">
				<input name="admin" type="hidden" value="1"/>
				<table>
					<tr>
						<td>Username:</td>
						<td><input name="username" type="text" minlength="6" maxlength="30" required/></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input name="password" type="password" minlength="8" maxlength="20" required/></td>
					</tr>
					<tr>
						<td>Confirm password:</td>
						<td><input name="password_confirm" type="password" minlength="8" maxlength="20" required/></td>
					</tr>
					<tr>
						<td><input type="submit" value="Submit" class="submit_form"/></td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="remove_admin_field">
			<c:if test="${not empty requestScope.wasAdminRemoved}">
				<p>${requestScope.wasAdminRemoved}</p>
			</c:if>
			<h2>Remove admin: </h2>
			<form method="post" action="RemoveAdminServlet">
				<label>
					Username: <input type="text" name="username" maxlength="30" required>
				</label>
				<button type="submit">submit</button>
			</form>
			
			<form method="post" action="AdminHomePage.jsp">
				<button type="submit">Back to homepage</button>
			</form>
		</div>
	</div>
</body>
</html>