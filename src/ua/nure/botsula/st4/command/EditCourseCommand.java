package ua.nure.botsula.st4.command;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.Course;
import ua.nure.botsula.st4.exception.AppException;

public class EditCourseCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1723530470298854414L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

request.setAttribute("edit", true);
int id = Integer.parseInt(request.getParameter("id"));
System.out.println(id);
Course course;
course=	DBManager.getInstance().findCourseById(id);
request.setAttribute("course_ed", course);
		return Path.ADMIN_CABINET;
	}

}
