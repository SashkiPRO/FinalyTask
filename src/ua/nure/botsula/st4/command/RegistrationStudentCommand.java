package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.Role;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;

public class RegistrationStudentCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5004989891973601559L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
	User u=new User();
	 request.setCharacterEncoding("UTF8");
u.setFname(request.getParameter("fname"));
u.setLname(request.getParameter("lname"));
u.setLogin(request.getParameter("login"));
u.setPassword(request.getParameter("password"));
u.setEmail(request.getParameter("email"));
u.setRoleid(Role.STUDENT.ordinal());
	System.out.println(u.getFname());
	 DBManager.getInstance().insertUserIntoDatabase(u);
		return "/controller?command=adout_courses";
	}

}
