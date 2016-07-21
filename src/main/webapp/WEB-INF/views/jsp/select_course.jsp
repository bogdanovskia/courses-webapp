<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select course</title>
</head>
<body>
	<form method="POST" action="EnrollToCourse">
    Select one of our courses: <br/>

    <table>
        <c:forEach items="${courses}" var="object">
            <tr>
                <td><input type="radio" name="course" value="${object.courseName}">${object}</td>
            </tr>

        </c:forEach>
    </table>



    <hr/>
    <button value = "submit">Submit</button>


</form>
</body>
</html>