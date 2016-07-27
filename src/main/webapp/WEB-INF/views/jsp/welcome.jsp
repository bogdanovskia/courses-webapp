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
		<form method="get" action="view-courses">
			<c:choose>
				<c:when test="${!loggedUser.isStudent()}">
					<button type="submit" formaction="create-course">Create new
						course</button>
				</c:when>
				<c:otherwise>
					<button type="submit">Enroll to course</button>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
	<form method="get" action="view-courses-user/">
		<c:choose>
			<c:when test="${!loggedUser.isStudent()}">
				<button type="submit">View all your courses</button>
			</c:when>
			<c:otherwise>
				<button type="submit">View all your enrolled courses</button>
			</c:otherwise>
		</c:choose>
	</form>
	<div align="right">
		${sessionScope.loggedUser.username}
		<form method="get" action="logout">
			<button type="submit">Log Out</button>
		</form>
	</div>
</body>
</html>