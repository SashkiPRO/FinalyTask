package ua.nure.botsula.st4.command;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import ua.nure.botsula.st4.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;

public class AllTeacherCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3402055329298646624L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		List <User> teacher = new ArrayList<User>();
		teacher=DBManager.getInstance().findUserGroupByRoleId(ua.nure.botsula.st4.Role.TEACHER.ordinal());
		request.setAttribute("teacher_list", teacher);
		request.setAttribute("type", "teachers");
		request.setAttribute("cat", "teachers_all");
		return Path.ADMIN_CABINET;
		
	}

}
