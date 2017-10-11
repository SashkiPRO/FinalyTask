<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table>
<c:forEach items="${sessionScope.result}" var="item">
<tr>
    <td>${item.getCourse_name()}</td>
    <td>${item.getCourse_theme()}</td>
    <td>${item.getTeacherFirstName()}</td>
    <td>${item.getTeacherLastName()}</td>
     <td>${item.getStudentCount()}</td>
    </tr>
</c:forEach>
</table>
</body>

</html>