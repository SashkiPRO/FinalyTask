package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.exception.AppException;


public class LogoutCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2919304503802187065L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

	//	LOG.debug("Command finished");
		return Path.PAGE_INDEX;
	}

}
