<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Main page</title>
<link rel="stylesheet" type="text/css" href="styles/reset.css" />
<link rel="stylesheet" type="text/css" href="styles/styles.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/sc.js"></script>
</head>
<body>

	<div class="wrapper" align="center">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<div class="context">
			<%@ include file="/WEB-INF/jspf/navigation.jspf"%>
			<c:if test="${empty course_list}">
				<p id="course_empty">List of courses are empty!</p>
			</c:if>
			<c:if test="${not empty course_list}">
				<div class="course_list">
<ul id="unit">

					<c:forEach items="${course_list}" var="item">

						<c:if test="${not empty user&& user.roleid==2}">
						
							<li>
								


									<h1>${item.course_name}</h1>
									<p>		<span><fmt:message key="course_name"/>: </span>${item.course_theme}</p>
										<p>	<span><fmt:message key="teacher"/>: </span>${item.teacherFirstName}
											${item.teacherLastName}</p>
									<p>		<span><fmt:message key="number_of_students"/>: </span>${item.studentCount}</p>
										<p>	<span><fmt:message key="course_start_date"/>: </span>${item.startCourse}</p>
										<p>	<span><fmt:message key="course_finish_date"/>: </span>${item.finishCourse}</p>
											<p>	<span><fmt:message key="course_duration"/>: </span>${item.duration} days</p>
										<p>	<span><fmt:message key="status_name"/>: </span>${item.state}</p>
									
										<c:if test="${item.state=='RECRUITED'}">
										<c:if test="${item.signed==true }"><input id="signed_button" type="button" disabled="disabled" value="<fmt:message key="Signed_button"/>"/></c:if>
										<c:if test="${item.signed==false }">
										<form action="controller?command=sign_course&id=${item.id}" method="post">
										<input type="submit" value="registr">
										</form>
										</c:if>
										
									
										
										</c:if>
									
								
							</li>
						
						</c:if>
						<c:if test="${not empty user&& user.roleid==1}">
						
							<li>
								
									
									<h1>${item.course_name}</h1>
									<p>		<span><fmt:message key="course_theme"/>: </span>${item.course_theme}</p>
										<p>	<span><fmt:message key="teacher"/>: </span>${item.teacherFirstName}
											${item.teacherLastName}</p>
									<p>		<span><fmt:message key="number_of_students"/>: </span>${item.studentCount}</p>
										<p>	<span><fmt:message key="course_start_date"/>: </span>${item.startCourse}</p>
										<p>	<span><fmt:message key="course_finish_date"/>: </span>${item.finishCourse}</p>
											<p><span><fmt:message key="course_duration"/>: </span>${item.duration} days</p>
										<p>	<span><fmt:message key="status_name"/>: </span>${item.state}</p>
										
										
									
								
							</li>
						
						</c:if>
						<c:if test="${not empty user&& user.roleid==0}">
						
							<li>
								
									
								
										<h1>${item.course_name}</h1>
									<p>		<span><fmt:message key="course_theme"/>: </span>${item.course_theme}</p>
										<p>	<span><fmt:message key="teacher"/>: </span>${item.teacherFirstName}
											${item.teacherLastName}</p>
									<p>		<span><fmt:message key="number_of_students"/>: </span>${item.studentCount}</p>
										<p>	<span><fmt:message key="course_start_date"/>: </span>${item.startCourse}</p>
										<p>	<span><fmt:message key="course_finish_date"/>: </span>${item.finishCourse}</p>
											<p><span><fmt:message key="course_duration"/>: </span>${item.duration} days</p>
										<p>	<span><fmt:message key="status_name"/>: </span>${item.state}</p>
								
							</li>
						
						</c:if>
							<c:if test="${ empty user}">
						
							<li>
								





									<h1>${item.course_name}</h1>
									<p>		<span><fmt:message key="course_theme"/>: </span>${item.course_theme}</p>
										<p>	<span><fmt:message key="teacher"/>: </span>${item.teacherFirstName}
											${item.teacherLastName}</p>
									<p>		<span><fmt:message key="number_of_students"/>: </span>${item.studentCount}</p>
										<p>	<span><fmt:message key="course_start_date"/>: </span>${item.startCourse}</p>
										<p>	<span><fmt:message key="course_finish_date"/>: </span>${item.finishCourse}</p>
											<p>	<span><fmt:message key="course_duration"/>: </span>${item.duration} days</p>
										<p>	<span><fmt:message key="status_name"/>: </span>${item.state}</p>
										<c:if test="${item.state=='RECRUITED'}">
										<p style="color: red">Please, sign to registration!</p>
										</c:if>
										
									
								
							</li>
						
						</c:if>
						
					</c:forEach>
</ul>
				</div>
			
			<div>
				<div class="sorting">
					<ul>

						<li><a id="select-sort"><fmt:message key="sorting_button"/></a>
							<ul id="sorting-list">
							<li><a href="controller?command=about_courses">	<fmt:message key="sorting_none"/></a></li>
								<li><a href="controller?command=about_courses&sort=nameA"><fmt:message key="sorting_by_name_a_z"/></a></li>
								<li><a href="controller?command=about_courses&sort=nameZ"><fmt:message key="sorting_by_name_z_a"/></a></li>
								<li><a href="controller?command=about_courses&sort=count"><fmt:message key="sorting_by_student_count"/></a></li>
								<li><a href="controller?command=about_courses&sort=duration"><fmt:message key="sorting_by_long"/></a></li>
							




							</ul></li>
							<li style="padding-top: 5px;"><a id="select-theme"><fmt:message key="course_theme"/></a>
							<ul id="theme_list">
							
								<c:forEach items="${theme}" var="item">
								<li><a href="controller?command=about_courses&theme=${item}">${item}</a></li>
								
								</c:forEach>



							</ul></li>
							<li style="padding-top: 5px;"><a id="select-teacher"><fmt:message key="teacher"/></a>
							<ul id="teacher_list_choise">
							
							<c:forEach items="${teacher_list }" var="item">
							<li><a href="controller?command=about_courses&id=${item.id }">${item.fname } ${item.lname }</a></li>
					
							</c:forEach>
							
							




							</ul></li>
					</ul>
				</div>
			
					<ul>

						
					</ul>
				</div>
				
				
				
				
			</c:if>

		</div>



		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>