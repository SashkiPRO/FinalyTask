package ua.nure.botsula.st4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;

public class UpdateTeacherCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3677745704820499619L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int teacher_id= Integer.parseInt(request.getParameter("id"));
		User teacher = new User();
		teacher.setId(teacher_id);
		teacher.setFname((String)request.getParameter("first_name"));
		teacher.setLname((String)request.getParameter("last_name"));
		teacher.setEmail((String)request.getParameter("email"));
		teacher.setLogin((String)request.getParameter("login"));
		teacher.setPassword((String)request.getParameter("password"));

	DBManager.getInstance().updateUser(teacher, teacher_id);
		
		return "/controller?command=all_teachers";
	}

}
