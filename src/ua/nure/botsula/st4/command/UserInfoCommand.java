package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.Role;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;

public class UserInfoCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1763283871046991234L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user.getRoleid()==Role.ADMIN.ordinal()){
			return Path.ADMIN_CABINET;
		}else if(user.getRoleid()==Role.TEACHER.ordinal()){
			return Path.TEACHER_CABINET;
		}else{
			return Path.STUDENT_CABINET;
		}
		
	
	}

}
