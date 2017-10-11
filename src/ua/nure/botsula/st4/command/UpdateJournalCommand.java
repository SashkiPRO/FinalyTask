package ua.nure.botsula.st4.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.bean.JournalBean;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.exception.AppException;

public class UpdateJournalCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3255380485173374203L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int courseId = Integer.parseInt(request.getParameter("course_id"));
		List <JournalBean> journal = DBManager.getInstance().findJournalByCourseId(courseId);
		List<JournalBean> updateJournal = new ArrayList<JournalBean>();
		 request.setCharacterEncoding("UTF8");
		int newMark;
		for(JournalBean j : journal){
			JournalBean journalUnit= new JournalBean();
			journalUnit.setCourseId(courseId);
			journalUnit.setStudentFisrtName(j.getStudentFisrtName());
			journalUnit.setStudentLastName(j.getStudentLastName());
			journalUnit.setStudentId(j.getStudentId());
			newMark=Integer.parseInt(request.getParameter(String.valueOf(j.getStudentId())));
			journalUnit.setMark(newMark);
			updateJournal.add(journalUnit);
		}
		DBManager.getInstance().updateCoursesJournal(updateJournal);
		System.out.println(updateJournal);
		StringBuilder path =new StringBuilder();
		path.append("/controller?command=journal_show&id=");
		path.append(String.valueOf(courseId));
		
		return path.toString();
	}

}
