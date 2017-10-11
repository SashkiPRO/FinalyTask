package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.exception.AppException;

public class MainPageCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 501863198294830231L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
	
		return Path.PAGE_INDEX;
	}

}
