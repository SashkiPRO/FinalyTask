package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.exception.AppException;

public class DeleteTeacher extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2761885584785727196L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
	
		int teacherId=Integer.parseInt(request.getParameter("id"));
	DBManager.getInstance().deleteTeacher(teacherId);
	return "controller?command=all_teachers";
		
	}

}
