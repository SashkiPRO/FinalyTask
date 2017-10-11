package ua.nure.botsula.st4.command;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.Course;
import ua.nure.botsula.st4.entity.CourseState;
import ua.nure.botsula.st4.exception.AppException;
import ua.nure.botsula.st4.util.DateUtils;

public class AddCourseCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5220172786589643105L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		Course newCourse = new Course();
		 request.setCharacterEncoding("UTF8");
		newCourse.setName(request.getParameter("name"));
		newCourse.setTheme(request.getParameter("theme"));
		newCourse.setStartDate(DateUtils.getDateFromString(request.getParameter("start_date"), DateUtils.DATE_FORMAT));
		newCourse.setFinishDate(DateUtils.getDateFromString(request.getParameter("finish_date"), DateUtils.DATE_FORMAT));
		newCourse.setState(CourseState.RECRUITED.ordinal());
		newCourse.setCountStudent(0);
		DBManager.getInstance().insertCourseIntoDataBase(newCourse);
		request.setAttribute("type", "courses");
	
		return "/controller?command=course_list&type=courses";
	}

}
