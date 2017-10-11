package ua.nure.botsula.st4.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.bean.JournalBean;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.Course;
import ua.nure.botsula.st4.exception.AppException;

public class EditUnitCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7517608880986784634L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String command = request.getParameter("edit");
		int courseId=Integer.parseInt(request.getParameter("course_id"));
		List <JournalBean> journal = DBManager.getInstance().findJournalByCourseId(courseId);
		Course course = DBManager.getInstance().findCourseById(courseId);
		request.setAttribute("edit", true);
		request.setAttribute("edit_journal", journal);
		request.setAttribute("type", "courses");
		request.setAttribute("course", course);
		System.out.println(course);
		System.out.println(command);
	return	Path.TEACHER_CABINET;
		
		
	}

}
