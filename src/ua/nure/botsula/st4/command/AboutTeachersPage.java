package ua.nure.botsula.st4.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.Role;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;

public class AboutTeachersPage extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5463236914795510444L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		// TODO Auto-generated method stub
		List <User> teachers = new ArrayList<User>();
		teachers=DBManager.getInstance().findUserGroupByRoleId(Role.TEACHER.ordinal());
		request.setAttribute("teachers_list", teachers);
		return Path.PAGE_ABOUT_TEACHER;
	}

}
