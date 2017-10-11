<%--=========================================================================== 
JSTL core tag library.
===========================================================================--%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--=========================================================================== 
JSTL functions tag library.
===========================================================================--%> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%--=========================================================================== 
 JSTL fmt tag library.
===========================================================================--%> 

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="lastName" required="true"%>
<%@ attribute name="mark" required="true"%>

		<tr style=" background-color: red;  ">
			<td style="text-align: left;" width="100px" align="center">${name}</td>
			<td style="text-align: left;" width="100px" align="center">${lastName}</td>
			<td style="text-align: left;" width="100px" align="center">${mark}</td>
		</tr>