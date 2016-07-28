<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update teacher</title>
<style type="text/css">
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<body>

	<springForm:form method="POST" commandName="user"
		action="">
		<table>
			<tr>
				<td>Username:</td>
				<td><springForm:input path="username" /></td>
				<td><springForm:errors path="username" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><springForm:password path="password" /></td>
				<td><springForm:errors path="password" cssClass="error" /></td>
			</tr>
				<tr>
				<td>First name:</td>
				<td><springForm:input path="firstName" /></td>
				<td><springForm:errors path="firstName" cssClass="error" /></td>
			</tr>
				<tr>
				<td>Second name:</td>
				<td><springForm:input path="secondName" /></td>
				<td><springForm:errors path="secondName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><springForm:input path="email" /></td>
				<td><springForm:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><springForm:input path="age" /></td>
				<td><springForm:errors path="age" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><springForm:select path="gender">
						<springForm:option value="" label="Select Gender" />
						<springForm:option value="MALE" label="Male" />
						<springForm:option value="FEMALE" label="Female" />
					</springForm:select></td>
				<td><springForm:errors path="gender" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Birthday:</td>
				<td><springForm:input path="birthday" placeholder="MM/dd/yyyy" /></td>
				<td><springForm:errors path="birthday" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td colspan="3"><input type="submit" value="Save teacher"></td>
			</tr>
		</table>

	</springForm:form>

</body>
</html>