<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New lesson for course ${course.getCourseName()}</title>
</head>
<body>

	<springForm:form action = "newlesson" method = "post" commandName="lesson">
		<table>
			<tr>
				<td>Enter title of the lesson:</td>
				<td><springForm:input path="title" /></td>
				<td><springForm:errors path="title" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Enter the order of the lesson:</td>
				<td><springForm:input path="lessonOrder" /></td>
				<td><springForm:errors path="lessonOrder" cssClass="error" /></td>
			</tr>
			</table>
			<button type = "submit">Submit</button>
	</springForm:form>

		
		<br/>
		<br/>
		
		
		
</body>
</html>