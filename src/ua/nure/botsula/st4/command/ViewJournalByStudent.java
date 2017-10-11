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

public class ViewJournalByStudent extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542131245610657789L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int courseId=Integer.parseInt(request.getParameter("id"));
		System.err.println(courseId);
		Course course = DBManager.getInstance().findCourseById(courseId);
		List<JournalBean> journal;
	journal=DBManager.getInstance().findJournalByCourseId(courseId);
	System.out.println(journal);
	System.out.println(course);
		request.setAttribute("type", "courses");
		if(journal.size()>0){
			request.setAttribute("journal", journal);
		}else{
			request.setAttribute("no_students", true);
		}

		request.setAttribute("find_course", course);
			return Path.STUDENT_CABINET;
	}

}
