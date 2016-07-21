<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>

	 <h3 style="color:red">${message}</h3>

	<form:form id = "loginForm" method = "post" action="login" modelAttribute="loginBean">
		<form:label path="username">Enter your user-name</form:label>
        <form:input id = "username" name = "username" path="username" /> <br/> <br/>
        <form:label path="username">Please enter your password</form:label>
        <form:password path="password" id = "password" name = "password" /> <br/> <br/>
		
		<button type="submit">Submit</button>
	</form:form>
</body>
</html>