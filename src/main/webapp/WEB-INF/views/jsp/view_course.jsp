<%@page import="com.courses.model.JoinedStudentLesson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${course.getCourseName()}</title>
</head>
<body>
	Lessons by this course:
	<c:if test="${!loggedUser.isStudent()}">
		<table>
			<c:forEach items="${course.getLessons()}" var="lesson">
				<tr>
					<td><a href="${course.getId()}/viewlesson/${lesson.getId()}">${lesson}</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${loggedUser.isStudent()}">
		<table>
			<c:forEach items="${course.getLessons()}" var="lesson">
				<tr>
					<td><a href="${course.getId()}/viewlesson/${lesson.getId()}">${lesson}</a></td>
					<td><c:forEach items="${lessons}" var="lessonN">     
					<c:if test = "${lesson.getTitle().equals(lessonN.getLesson().getTitle())}">${lessonN.isPassed()}</c:if>
					</c:forEach></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${!loggedUser.isStudent()}">
		<form action="${course.getId()}/create-lesson" method="get">
			<button type="submit">Create new lesson</button>
		</form>
	</c:if>

	<c:if test="${loggedUser.isStudent()}">
		<a href="${course.getId()}/add-to-favourite">Add to favourite
			courses!</a>
	</c:if>

	<c:if test="${!loggedUser.isStudent()}">
		<a href="${course.getId()}/delete">Delete course</a>
	</c:if>

	<form method="get" action="../view-courses-user/">
		<button type="submit">View courses!</button>
	</form>

</body>
</html>