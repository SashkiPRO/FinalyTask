package ua.nure.botsula.st4.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.Course;
import ua.nure.botsula.st4.entity.CourseState;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;

public class StudentsCoursesListCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3186580116482225335L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String courseState=request.getParameter("state");
		HttpSession session = request.getSession();
		User student =(User) session.getAttribute("user");
		List <Course> courses = new ArrayList<Course>();
		switch (courseState) {
		case "nostarted": 
			courses = DBManager.getInstance().findCoursesByStudentIdAndState(student.getId(), CourseState.RECRUITED.ordinal());
			break;
		case "during": 
			courses = DBManager.getInstance().findCoursesByStudentIdAndState(student.getId(), CourseState.DURING.ordinal());
			break;
		case "finished": 
			courses = DBManager.getInstance().findCoursesByStudentIdAndState(student.getId(), CourseState.FINISHED.ordinal());
			break;
		default:courses = DBManager.getInstance().findCoursesByStudentIdAndState(student.getId(), CourseState.RECRUITED.ordinal());
			break;
		}
		
		request.setAttribute("type", "courses");
		request.setAttribute("courses_list", courses);
		return Path.STUDENT_CABINET;
	}

}
