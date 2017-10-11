package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.exception.AppException;

public class AdminMenuList extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7318612740776987232L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		return Path.ADMIN_CABINET;
	}

}
