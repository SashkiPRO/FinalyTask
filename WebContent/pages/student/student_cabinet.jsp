<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
     <%@taglib prefix="ex" uri="/WEB-INF/SubstrDescriptor.tld"%>
<title>Student page</title>
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

<c:when test="${type=='student_info'||param.type=='student_info' }">
<li><a id="teacher_info" class="active" href="controller?command=user_info&type=student_info"><fmt:message key="link_info"/></a></li>
<li><a href="controller?command=students_courses_kind"><fmt:message key="navigation_jspf.courses"/></a></li>

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
<li><a id="teacher_info" href="controller?command=user_info&type=student_info"><fmt:message key="link_info"/></a></li>
<li><a class="active" href="controller?command=students_courses_kind"><fmt:message key="navigation_jspf.courses"/></a></li>
<div class="info">
<c:if test="${empty courses_list &&empty param.type &&empty journal}">
<ul class="teachers_courses_list">
<li><a href="controller?command=students_courses_list&state=nostarted"><fmt:message key="no_started_courses"/></a></li>
<li><a href="controller?command=students_courses_list&state=during"><fmt:message key="in_process"/></a></li>
<li><a href="controller?command=students_courses_list&state=finished"><fmt:message key="finished_courses"/></a></li>

</ul>
</c:if>


<c:if test="${param.state=='nostarted' }">


<table class="student_courses">
									<tr id="courses_list_title">
										<td><fmt:message key="course_name"/></td>
										<td><fmt:message key="course_theme"/></td>
									
										<td><fmt:message key="course_start_date"/></td>
										<td><fmt:message key="course_finish_date"/></td>
									
								
									</tr>
									<c:forEach items="${courses_list}" var="item">
										<tr>
											<td>${item.name }</td>
											<td>${item.theme }</td>
											<td>${item.startDate }</td>
											<td>${item.finishDate }</td>
										

											
											<td> </td>
											
										
										</tr>
										<tr>


										</tr>
									</c:forEach>
								</table>
</c:if>
<c:if test="${param.state=='during' }">
<c:if test="${empty courses_list }">
You don't have courses this type!
</c:if>


<table class="student_courses">
									<tr id="courses_list_title">
										<td><fmt:message key="course_name"/></td>
										<td><fmt:message key="course_theme"/></td>
									
										<td><fmt:message key="course_start_date"/></td>
										<td><fmt:message key="course_finish_date"/></td>
									<td><fmt:message key="days_to_finish"/></td>
								
									</tr>
									<c:forEach items="${courses_list}" var="item">
										<tr>
											<td>${item.name }</td>
											<td>${item.theme }</td>
											<td>${item.startDate }</td>
											<td>${item.finishDate }</td>
											<td><ex:Date message="${item.finishDate }" /></td>

											
											<td> </td>
											
										
										</tr>
										<tr>


										</tr>
									</c:forEach>
								</table>


</c:if>
<c:if test="${param.state=='finished' && empty journal}">
		<table id="list_courses">
									<tr id="courses_list_title">
										<td><fmt:message key="course_name"/></td>
										<td><fmt:message key="course_theme"/></td>
									
										<td><fmt:message key="course_start_date"/></td>
										<td><fmt:message key="course_finish_date"/></td>
										<td><fmt:message key="journal"/></td>
									
										





									</tr>
									<c:forEach items="${courses_list}" var="item">
										<tr>
											<td>${item.name }</td>
											<td>${item.theme }</td>
											<td>${item.startDate }</td>
											<td>${item.finishDate }</td>
								<td><a class="open_button" href="controller?command=view_journal_by_student&id=${item.id}"><fmt:message key="open"/></a></td>
										
										</tr>
										<tr>

										

										</tr>
									</c:forEach>
								</table>
</c:if>
<c:if test="${not empty journal }">
<h1 class="course_name"><fmt:message key="journal_for_course"/> <span>${find_course.name }</span></h1>
<table class="journal">
<tr>
<td><h2><fmt:message key="name"/></h2></td><td><h2><fmt:message key="last_name"/></h2></td><td><h2><fmt:message key="mark"/></h2></td>
</tr>


<c:forEach items="${journal }" var="item">

<c:if test="${item.studentId==sessionScope.user.id}">

<my:StudentMark name="${item.studentFisrtName}"
						lastName="${item.studentLastName }" 
						mark="${item.mark }"/>


</c:if>
<c:if test="${item.studentId!=sessionScope.user.id}">
<tr>
<td>${item.studentFisrtName }</td><td>${item.studentLastName }</td><td>${item.mark}</td>
</tr>
</c:if>

	
</c:forEach>
</table>
</c:if>



</div>
</c:when>
<c:otherwise>
<li><a class="active" id="teacher_info" href="controller?command=user_info&type=student_info"><fmt:message key="link_info"/></a></li>
<li><a href="controller?command=students_courses_kind"><fmt:message key="navigation_jspf.courses"/></a></li>

</c:otherwise>


</c:choose>
</ul>




 </div>



</div>



<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
</body>
</html>