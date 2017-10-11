package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.Role;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;



/**
 * Login command.
 * 
 * @author D.Kolesnikov
 * 
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	//private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, AppException {
		//LOG.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from a request
		DBManager manager = DBManager.getInstance();
		String login = request.getParameter("login");
	//	LOG.trace("Request parameter: loging --> " + login);
System.out.println(login);
		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}

		User user = manager.findUserByLogin(login);
		//LOG.trace("Found in DB: user --> " + user);

		if (user == null || !password.equals(user.getPassword())) {
			throw new AppException("Cannot find user with such login/password");
		}

		Role userRole = Role.getRole(user);
		System.out.println(user.getRoleid());
	//	LOG.trace("userRole --> " + userRole);
		
		String forward = Path.PAGE_ABOUT_COURSES;

		if (userRole == Role.ADMIN) {
			forward = "/controller?command=user_info&type=admin_info";
		}
if (userRole == Role.TEACHER) {
			forward = "/controller?command=user_info&type=info";
		}
		if (userRole == Role.STUDENT) {
			forward = "/controller?command=user_info&type=student_info";
		}
		
		session.setAttribute("user", user);
		//LOG.trace("Set the session attribute: user --> " + user);

		session.setAttribute("userRole", userRole);
		//LOG.trace("Set the session attribute: userRole --> " + userRole);

	//	LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

		//LOG.debug("Command finished");
		return forward;
	}

}
