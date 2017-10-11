<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Main page</title>
<link rel="stylesheet" type="text/css" href="styles/reset.css" />
<link rel="stylesheet" type="text/css" href="styles/styles.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/sc.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-validate.min.js"></script>



</head>
<body>
	<div class="wrapper" align="center">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<div class="context">
			<%@ include file="/WEB-INF/jspf/navigation.jspf"%>

<c:out value="${OK}"></c:out>
			<div class="registration_block">
				<h1>Registration</h1>
				<form action="controller?command=registration_student" id="registration_form" method="post">
					<table id="autenth_table">
						<tr>
							<td>First name</td>
							<td><span>*</span></td>
							<td><input type="text" name="fname" /></td>
						</tr>
						<tr>
							<td>Last name</td>
							<td><span>*</span></td>
							<td><input type="text" name="lname" /></td>
						</tr>
						<tr>
							<td>login</td>
							<td><span>*</span></td>
							<td><input type="text" name="login" /></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><span>*</span></td>
							<td><input type="text" name="password" id="password" /></td>
						</tr>
						<tr>
							<td>Confirm</td>
							<td><span>*</span></td>
							<td><input type="text" name="cpassword" /></td>
						</tr>
						<tr>
							<td>Email</td>
							<td><span>*</span></td>
							<td><input type="text" name="email" /></td>
						</tr>
					</table>
					<input type="submit" value="Send" id="reg_button">
				</form>


			</div>
		</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>

	

</body>
</html>