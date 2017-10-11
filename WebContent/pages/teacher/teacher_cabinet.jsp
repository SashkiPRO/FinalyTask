<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Main page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/reset.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sc.js"></script>

</head>
<body>

<div class="wrapper" align="center">
<%@ include file="/WEB-INF/jspf/header.jspf"%>

<div class="context">
<div class="admin_menu">
<ul>
<c:choose>

<c:when test="${type=='info'||param.type=='info' }">
<li><a id="teacher_info" class="active" href="controller?command=user_info&type=info"><fmt:message key="link_info"/></a></li>
<li><a href="controller?command=teacher_courses"><fmt:message key="navigation_jspf.courses"/></a></li>

							<div class="info">
<h5>User info</h5>
<table class="user_info">
<tr><td><h3><fmt:message key="name"/></h3></td><td>${sessionScope.user.fname }</td></tr>
<tr><td><h3><fmt:message key="last_name"/></h3></td><td>${sessionScope.user.lname }</td></tr>
<tr><td><h3><fmt:message key="login"/></h3></td><td>${sessionScope.user.login }</td></tr>
<tr><td><h3>Email</h3></td><td>${sessionScope.user.email }</td></tr>
</table>

</div>
</c:when>
<c:when test="${type=='courses'||param.type=='courses' }">
<li><a id="teacher_info" href="controller?command=user_info&type=info"><fmt:message key="link_info"/></a></li>
<li><a class="active" href="controller?command=teacher_courses"><fmt:message key="navigation_jspf.courses"/></a></li>
<div class="info">

<c:if test="${not empty course_list }">

<c:if test="${empty journal }">
<ul class="teachers_courses_list">
<c:forEach items="${course_list }" var="item">
<li><a href="controller?command=journal_show&id=${item.id}">${item.name } (${item.theme})</a></li>
</c:forEach>


</ul>
</c:if>



</c:if>
<c:if test="${no_students==true }">
<h1><fmt:message key="no_student_on_course"/></h1>

</c:if>
<c:if test="${not empty journal&&edit!=true}">
<h1 class="course_name"><fmt:message key="journal_for_course"/> <span>${find_course.name }</span></h1>
<table class="journal">
<tr>
<td><h2><fmt:message key="name"/></h2></td><td><h2><fmt:message key="last_name"/></h2></td><td><h2><fmt:message key="mark"/></h2></td>
</tr>


<c:forEach items="${journal }" var="item">
<tr>
<td>${item.studentFisrtName }</td><td>${item.studentLastName }</td><td>${item.mark}</td>
</tr>
	
</c:forEach>
</table>
<c:if test="${find_course.getState()==0||find_course.getState()==1}">
<fmt:message key="not_finish_course"/>
</c:if>
<c:if test="${find_course.getState()==2}">
<a href="controller?command=edit_unit&edit=journal&course_id=${find_course.id }" class="edit_journal"><fmt:message key="manage_button.edit"/></a>
</c:if>
</c:if>

<c:if test="${edit==true }">
<form action="controller?command=update_journal&course_id=${course.id}" method="post">
<table class="journal">
<tr>
<td><h2><fmt:message key="name"/></h2></td><td><h2><fmt:message key="last_name"/></h2></td><td><h2><fmt:message key="mark"/></h2></td>
</tr>


<c:forEach items="${edit_journal}" var="item">
<tr>
<td>${item.studentFisrtName }</td><td>${item.studentLastName }</td><td><input class="mark_input" type="text" name="${item.studentId }" value="${item.mark }"/></td>
</tr>
	
</c:forEach>
</table>
<input type="submit" value="<fmt:message key="manage_button.update"/>">

</form>
</c:if>


</div>
</c:when>
<c:otherwise>
<li><a class="active" id="teacher_info" href="controller?command=user_info&type=info"><fmt:message key="link_info"/></a></li>
<li><a href="controller?command=teacher_courses"><fmt:message key="navigation_jspf.courses"/></a></li>

</c:otherwise>


</c:choose>
</ul>




 </div>


</div>



<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
</body>
</html>