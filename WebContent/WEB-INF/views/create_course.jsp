<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create course</title>
</head>
<body>
	<form:form id="createCourseFormId" action="CheckCourse" modelAttribute="course">
		<form:label path="courseName">Enter name of the course:</form:label>
		<form:input id="courseName" name="courseName" path="courseName" />
		<br />
		<br />
		<button type="submit">Submit</button>
	</form:form>
</body>
</html>