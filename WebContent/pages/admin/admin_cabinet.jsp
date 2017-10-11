<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="ua.nure.botsula.st4.entity.CourseState"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Main page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/reset.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/admin_style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/sc.js"></script>

</head>
<body>

	<div class="wrapper" align="center">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		
<%@ include file="/pages/admin/content.jspf"%>


		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>