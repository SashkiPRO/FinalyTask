package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.exception.AppException;

public class AddCourseToTeacher extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7034755719628211567L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
int course_id=Integer.parseInt(request.getParameter("course_id"));
int teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
DBManager.getInstance().insertCourseToTeacherSign(teacher_id,course_id);
		return "/controller?command=teacher_list&type=teachers&cat=teachers_course";
	}

}
