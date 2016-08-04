<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>Username</td>
			<td>${user.getUsername() }</td>
		</tr>
		<tr>
			<td>First name</td>
			<td>${user.getFirstName() }</td>
		</tr>
		<tr>
			<td>Second name</td>
			<td>${user.getSecondName() }</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${user.getEmail() }</td>
		</tr>
		<tr>
			<td>Gender</td>
			<td>${user.getGender() }</td>
		</tr>
		<tr>
			<td>Date of birth</td>
			<td>${user.getBirthday() }</td>
		</tr>
	</table>
	<a href = "user/update">Update user</a>
	<a href = "./">Back to home page</a>
	<a href = "user/delete">Delete user</a>
</body>
</html>