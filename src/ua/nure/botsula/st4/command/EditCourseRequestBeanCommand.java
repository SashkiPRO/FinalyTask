package ua.nure.botsula.st4.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.Course;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;

public class EditCourseRequestBeanCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 429688542953183276L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		request.setAttribute("edit", true);
		List<Course> courses = DBManager.getInstance().findAllCourses();
		int id=Integer.parseInt(request.getParameter("id"));
		User teacher = DBManager.getInstance().findUserById(id);
		Course unitCourse;
		int courseId= Integer.parseInt(request.getParameter("course_id"));
		unitCourse = DBManager.getInstance().findCourseById(courseId);
		request.setAttribute("course", unitCourse);
		request.setAttribute("teacher", teacher);
		
		courses.remove(unitCourse);
		request.setAttribute("course_list", courses);
		return Path.ADMIN_CABINET;
		
		
	}

}
