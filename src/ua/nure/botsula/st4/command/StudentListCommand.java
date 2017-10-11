package ua.nure.botsula.st4.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.Role;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;

public class StudentListCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6286785849400452121L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		List<User> students = DBManager.getInstance().findUserGroupByRoleId(Role.STUDENT.ordinal()) ;
		List<User> bunned= DBManager.getInstance().findUserGroupByRoleId(Role.BUNNED.ordinal());
		request.setAttribute("type", "students");
		request.setAttribute("students", students);
		request.setAttribute("bunned", bunned);
		return Path.ADMIN_CABINET;
	}

}
