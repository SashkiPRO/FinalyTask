package ua.nure.botsula.st4.command;

import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.Course;

import ua.nure.botsula.st4.exception.AppException;
import ua.nure.botsula.st4.exception.DBException;


public class CourseListCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 625793218072200760L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		List <Course> b = new ArrayList<Course>();
		try {
			b=DBManager.getInstance().findAllCourses();
			
			
			
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	request.setAttribute("course_list", b);
		return Path.ADMIN_CABINET;
	}

}
