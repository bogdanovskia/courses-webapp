<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New lesson for course ${course.getCourseName()}</title>
</head>
<body>
	<form:form action="NewLesson" method="post" modelAttribute="lesson">
		<form:label path="title">Enter the title of the lesson:</form:label>
		<form:input id="title" name="title" path="title" />
		
		<br/>
		<br/>
		
		
		
	</form:form>
</body>
</html>