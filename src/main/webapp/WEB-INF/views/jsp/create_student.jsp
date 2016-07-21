<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form method="POST" action="CheckStudent"
		modelAttribute="student">

		<form:label path="username">Enter your user-name</form:label>
		<form:input id="username" name="username" path="username" />
		<br />
		<br />
		<form:label path="password">Please enter your password</form:label>
		<form:password path="password" id="password" name="password" />
		<br />
		<br />

		<form:label path="firstName">Enter your first name: </form:label>
		<form:input id="firstName" path="firstName" name="firstName" />
		<br />
		<br />

		<form:label path="secondName">Enter your second name: </form:label>
		<form:input id="secondName" path="secondName" name="secondName" />
		<br />
		<br />

		<form:label path="index">Enter your index: </form:label>
		<form:input id="index" path="index" name="index" />
		<br />
		<br />



		<input type="submit" value="Submit">
	</form:form>
</body>
</html>