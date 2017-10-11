package ua.nure.botsula.st4.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.Role;
import ua.nure.botsula.st4.bean.TeacherRequestBean;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.Course;
import ua.nure.botsula.st4.entity.User;
import ua.nure.botsula.st4.exception.AppException;

public class TeachersListCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6609222266164179194L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		List<TeacherRequestBean> teacher=new ArrayList<>();
		List<User> allTeacher;
		List<Course> courses;
		teacher = DBManager.getInstance().findAllTeachersCourses();
		allTeacher=DBManager.getInstance().findUserGroupByRoleId(Role.TEACHER.ordinal());
		courses=DBManager.getInstance().findAllFreeCourses();
		
		
		
	request.setAttribute("teachers_list", teacher);

	request.setAttribute("all_teacher_list", allTeacher);
	request.setAttribute("all_courses", courses);
		return Path.ADMIN_CABINET;
	}

}
