<%-- <%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
					<button type="submit" formaction="create-course">Create
						new course</button>
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
	<a href="user">View user</a>
	<a href="view-favourites">View favourite courses</a>
</body>
</html>

 --%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>

</div>
<div align="center">
		Welcome ${sessionScope.loggedUser.firstName}
		<form method="get" action="view-courses">
			<c:choose>
				<c:when test="${!loggedUser.isStudent()}">
					<button type="submit" formaction="create-course">Create
						new course</button>
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
	<a href="user">View user</a>
	<a href="view-favourites">View favourite courses</a>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
 