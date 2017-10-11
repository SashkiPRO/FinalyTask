package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.exception.AppException;

public class UpdateCourseSign extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3538429968151377174L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		// TODO Auto-generated method stub
		int teacherId= Integer.parseInt(request.getParameter("teacher_id"));
		int courseId= Integer.parseInt(request.getParameter("course_id"));
		int newCourseId= Integer.parseInt(request.getParameter("newcourse_id"));
		DBManager.getInstance().updateTeacherSignCourse(teacherId, courseId, newCourseId);
		return "/controller?command=teacher_list&type=teachers&cat=teachers_course";
	}

}
