<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Favourite courses</title>
</head>
<body>
	<table>
		<c:forEach items="${courses}" var="object">
			<tr>
				<td><a href = "view-courses-user/${object.getId()}">${object.getCourseName()}</a></td>
			</tr>

		</c:forEach>
	</table>
	<a href = "./">Back to home page</a>
</body>
</html>