package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.exception.AppException;

public class AddTeacherMenu extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3122794515459541830L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		request.setAttribute("teacher_reg", true);
		return "controller?command=all_teachers";
	}

}
