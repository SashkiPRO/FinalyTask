package ua.nure.botsula.st4.command;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.botsula.st4.Role;
import ua.nure.botsula.st4.bean.*;
import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.User;

import java.util.*;
import org.apache.log4j.Logger;
import ua.nure.botsula.st4.exception.*;

public class AboutCoursesCommand extends Command {

	private static final long serialVersionUID = 1752248585483162336L;
	private static final Logger LOG = Logger.getLogger(AboutCoursesCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
		LOG.debug("Command starts");
		LOG.debug("Command finished");
		List<CourseRequestBean> courseList = new ArrayList<CourseRequestBean>();
		List<StudentOrderRequestBean> courseListStudent = new ArrayList<StudentOrderRequestBean>();
		List<User> teacher = DBManager.getInstance().findUserGroupByRoleId(Role.TEACHER.ordinal());
		request.setAttribute("teacher_list", teacher);
		List<String> themes = new ArrayList<String>();

		User user = new User();
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		String sortCommand = request.getParameter("sort");
		String teacherId = request.getParameter("id");
		String theme = request.getParameter("theme");
		System.out.println(sortCommand);
		try {
			if (user != null && user.getRoleid() == Role.STUDENT.ordinal()) {
				System.out.println("dsadjaskdaskjdhaskjdhkasjhdkasj");
				courseListStudent = DBManager.getInstance().findStudentAllOrder(user.getId());
				for (int i = 0; i < courseListStudent.size(); i++) {
					if (!themes.contains(courseListStudent.get(i).getCourse_theme())) {
						themes.add(courseListStudent.get(i).getCourse_theme());
					}
				}
				request.setAttribute("theme", themes);
				if (teacherId != null) {
					List<StudentOrderRequestBean> teachersList = new ArrayList<>();
					int id = Integer.parseInt(teacherId);
					for (int i = 0; i < courseListStudent.size(); i++) {
						if (courseListStudent.get(i).getTeacher_id() == id) {
							teachersList.add(courseListStudent.get(i));
						}
					}
					System.err.println(teachersList);
					courseListStudent = teachersList;

				}
				if (theme != null) {
					List<StudentOrderRequestBean> themeList = new ArrayList<>();
					for (int i = 0; i < courseListStudent.size(); i++) {
						if (courseListStudent.get(i).getCourse_theme().equals(theme)) {
							themeList.add(courseListStudent.get(i));
						}
					}
					courseListStudent = themeList;
				}

				request.setAttribute("course_list", courseListStudent);

				if (sortCommand != null) {
					if (sortCommand.equalsIgnoreCase("count")) {
						// sort menu by category
						Collections.sort(courseListStudent, new Comparator<StudentOrderRequestBean>() {
							public int compare(StudentOrderRequestBean o1, StudentOrderRequestBean o2) {
								return (int) (o2.getStudentCount() - o1.getStudentCount());
							}
						});
					} else if (sortCommand.equalsIgnoreCase("nameA")) {
						// sort menu by category
						Collections.sort(courseListStudent, new Comparator<StudentOrderRequestBean>() {
							public int compare(StudentOrderRequestBean o1, StudentOrderRequestBean o2) {
								return o1.getCourse_name().compareTo(o2.getCourse_name());
							}
						});
					} else if (sortCommand.equalsIgnoreCase("nameZ")) {
						// sort menu by category
						Collections.sort(courseListStudent, new Comparator<StudentOrderRequestBean>() {
							public int compare(StudentOrderRequestBean o1, StudentOrderRequestBean o2) {
								return o2.getCourse_name().compareTo(o1.getCourse_name());
							}
						});
					} else if (sortCommand.equalsIgnoreCase("duration")) {
						// sort menu by category
						Collections.sort(courseListStudent, new Comparator<StudentOrderRequestBean>() {
							public int compare(StudentOrderRequestBean o1, StudentOrderRequestBean o2) {
								return (int) (o1.getDuration() - o2.getDuration());
							}
						});
					}

				}
			} else {
				courseList = DBManager.getInstance().findAllCoursesBean();
				for (int i = 0; i < courseList.size(); i++) {
					if (!themes.contains(courseList.get(i).getCourse_theme())) {
						themes.add(courseList.get(i).getCourse_theme());
					}
				}
				request.setAttribute("theme", themes);
				if (teacherId != null) {
					List<CourseRequestBean> teachersList = new ArrayList<>();
					int id = Integer.parseInt(teacherId);
					for (int i = 0; i < courseList.size(); i++) {
						if (courseList.get(i).getTeacher_id() == id) {
							teachersList.add(courseList.get(i));
						}
					}

					courseList = teachersList;

				}
				if (theme != null) {
					List<CourseRequestBean> themeList = new ArrayList<>();
					for (int i = 0; i < courseList.size(); i++) {
						if (courseList.get(i).getCourse_theme().equals(theme)) {
							themeList.add(courseList.get(i));
						}
					}
					courseList = themeList;
				}
				request.setAttribute("course_list", courseList);
				
				if (sortCommand != null) {
					if (sortCommand.equalsIgnoreCase("count")) {
						// sort menu by category
						Collections.sort(courseList, new Comparator<CourseRequestBean>() {
							public int compare(CourseRequestBean o1, CourseRequestBean o2) {
								return (int) (o2.getStudentCount() - o1.getStudentCount());
							}
						});
					} else if (sortCommand.equalsIgnoreCase("nameA")) {
						// sort menu by category
						Collections.sort(courseList, new Comparator<CourseRequestBean>() {
							public int compare(CourseRequestBean o1, CourseRequestBean o2) {
								return o1.getCourse_name().compareTo(o2.getCourse_name());
							}
						});
					} else if (sortCommand.equalsIgnoreCase("nameZ")) {
						// sort menu by category
						Collections.sort(courseList, new Comparator<CourseRequestBean>() {
							public int compare(CourseRequestBean o1, CourseRequestBean o2) {
								return o2.getCourse_name().compareTo(o1.getCourse_name());
							}
						});
					} else if (sortCommand.equalsIgnoreCase("duration")) {
						// sort menu by category
						Collections.sort(courseList, new Comparator<CourseRequestBean>() {
							public int compare(CourseRequestBean o1, CourseRequestBean o2) {
								return (int) (o1.getDuration() - o2.getDuration());
							}
						});
					}
				}

				System.out.println(courseList);
			}

		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(themes);
		return ua.nure.botsula.st4.Path.PAGE_ABOUT_COURSES;
	}
}
