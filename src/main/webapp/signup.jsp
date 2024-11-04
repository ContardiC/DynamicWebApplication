<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DWA - SignUp</title>
</head>
<body>
<form method="post" action="SignUpServlet">
	<input type="text" name="first-name" placeholder="First name"/>
	<input type="text" name="last-name" placeholder="Last name"/>
	<input type="email" name="email" placeholder="Email"/>
	<input type="password" name="password" placeholder="Password"/>
	<input type="submit" value="SignUp"/>
</form>
</body>
</html>