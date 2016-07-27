<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create course</title>
<style type="text/css">
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<body>
	<springForm:form id="createCourseFormId" action="create-course"
		commandName="course" method="POST">
		<table>
			<tr>
				<td>Course name:</td>
				<td><springForm:input path="courseName" /></td>
				<td><springForm:errors path="courseName" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Save course"></td>
			</tr>
		</table>
	</springForm:form>
</body>
</html>