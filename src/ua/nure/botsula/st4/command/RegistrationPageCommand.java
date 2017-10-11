package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.exception.AppException;

public class RegistrationPageCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1348402842119130484L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		return Path.PAGE_REGISTRATION;
	}

}
