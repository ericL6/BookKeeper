<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/login_style.css">
<meta charset="ISO-8859-1">
<title>BookKeeper</title>
</head>
<body>
	<h1>BookKeeper</h1>

	<form method="post" action="LoginServlet">
		<div class="center">
			<h2>Login</h2>
			<c:if test="${not empty requestScope.errorMessage}">
				<div class="errorMessage">${requestScope.errorMessage}</div>
			</c:if>

			<div class="user_input">
				<div class="username_field">
					Username: <input name="username" type="text" minlength="6" maxlength="30" required />
				</div>
				<br>
				<div class="password_field">
					Password: <input name="password" type="password" minlength="8" maxlength="20" required />
				</div>
				<br>
				<div class="login_type">
					User: <input name="user_or_admin" type="radio" value="0" required />
					Admin: <input name="user_or_admin" type="radio" value="1" />
				</div>
				
				<br> <input type="submit" value="Login" class="submit" />
				<div class="signup">
					<a href="UserRegisterForm.jsp">Create account</a>
				</div>
				
			</div>
		</div>
	</form>
</body>
</html>