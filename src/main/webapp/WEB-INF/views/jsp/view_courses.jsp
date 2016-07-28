<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View courses</title>
</head>
<body>

    <table>
        <c:forEach items="${courses}" var="object" >
            <tr>
               <td><a id = "link" href = "${object.getId()}">${object}</a></td>
            </tr>

        </c:forEach>
    </table>
	
	
	<form method = "get" action="../welcome">
		<button type = "submit">Go back to home page</button>
	</form>

    <hr/>


</body>
</html>