<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/register_style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>BookKeeper</h1>
	
	<form method="post" action="UserAdminRegisterServlet">
		<div class="center">
			<h2>Create user account</h2>
			<input name="admin" type="hidden" value="0"/>
			<c:if test="${not empty requestScope.errorMessage}">
				<div class="errorMessage">${requestScope.errorMessage}</div>
			</c:if>
			<c:if test="${not empty requestScope.successMessage}">
				<div class="errorMessage">${requestScope.successMessage}</div>
			</c:if>
			<div class="user_input">
				<div class="username_field">
					Username: <input name="username" type="text" minlength="6" maxlength="30" required/>
				</div>
				<br>
				<div class="password_field">
					Password: <input name="password" type="password" minlength="8" maxlength="20" required/>
				</div>
				<br>
				<div class="confirm_pass_field">
					Confirm password: <input name="password_confirm" type="password" minlength="8" maxlength="20" required/>
				</div>
				<br>
				<input type="submit" value="Submit" />
				<div class="login_link">
					<a href="Index.jsp">Login</a>
				</div>
				<br>
			</div>		
		</div>
	</form>
</body>
</html>