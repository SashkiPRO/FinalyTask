package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.exception.AppException;

public class StudentsCoursesKindCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8491166277372228457L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		request.setAttribute("type", "courses");
		return Path.STUDENT_CABINET;
	}

}
