<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<div align="center">
		Welcome ${sessionScope.loggedUser.firstName}
		<form method="get" action="ViewCourses">
			<button type="submit">Enroll to course</button>
			<c:if test="${!loggedUser.isStudent()}">
				<button type="submit" formaction="CreateCourse">Create new
					course</button>
			</c:if>
		</form>
	</div>
	<form method="get" action="ViewCoursesByUser">
		<button type="submit">View all your enrolled courses</button>
	</form>
	<div align="right">
		${sessionScope.loggedUser.username}
		<form method="get" action="logout">
			<button type="submit">Log Out</button>
		</form>
	</div>
</body>
</html>