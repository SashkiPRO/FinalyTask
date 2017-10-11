package ua.nure.botsula.st4.db;

import javax.naming.NamingException;


import javax.sql.DataSource;

import ua.nure.botsula.st4.exception.DBException;
import ua.nure.botsula.st4.exception.Messages;
import ua.nure.botsula.st4.util.DateUtils;
import ua.nure.botsula.st4.SortingType;
import ua.nure.botsula.st4.bean.CourseRequestBean;
import ua.nure.botsula.st4.bean.JournalBean;
import ua.nure.botsula.st4.bean.StudentOrderRequestBean;
import ua.nure.botsula.st4.bean.TeacherRequestBean;
import ua.nure.botsula.st4.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

public final class DBManager {
	private static DBManager instance;
	private DataSource ds;

	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// ST4DB - the name of data source
			ds = (DataSource) envContext.lookup("jdbc/facultet_db");
			// LOG.trace("Data source ==> " + ds);
		} catch (NamingException ex) {
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private static final String SQL_FIND_ALL_COURSE_BEAN = "SELECT c.id,c.name,c.theme, c.start_date,c.finish_date, c.student_count, c.status_id, t.teacher_id, u.fname, u.lname FROM courses c INNER JOIN teachers_course t ON t.course_id=c.id INNER JOIN users u ON u.id=t.teacher_id";
	private static final String SQL_FIND_ALL_COURSES = "SELECT * FROM courses";
	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
	private static final String SQL_FIND_USERS_BY_ROLE_ID = "SELECT * FROM users WHERE role_id=?";
	private static final String SQL_INSERT_STUDENT = "INSERT INTO users(fname,lname,login,password,role_id,email) VALUES(?,?,?,?,?,?)";
	private static final String SQL_UPDATE_COURSE = "UPDATE courses SET name=?, theme=?, start_date=?, finish_date=?, status_id=? WHERE id=?";
	private static final String SQL_FIND_COURSE_BY_ID = "SELECT * FROM courses WHERE id=?";
	private static final String SQL_DELETE_SIGN_COURSE_BY_COURSE_ID = "DELETE FROM sign_course WHERE course_id = ?";
	private static final String SQL_DELETE_TEACHER_COURSE_BY_COURSE_ID = "DELETE FROM teachers_course WHERE course_id = ?";
	private static final String SQL_DELETE_JOURNAL_BY_COURSE_ID = "DELETE FROM journals WHERE course_id=?";
	private static final String SQL_DELETE_COURSE_BY_ID = "DELETE FROM courses WHERE id=?";
	private static final String SQL_INSERT_COURSE = "INSERT INTO courses (name,theme, start_date, finish_date, status_id, student_count) VALUES(?,?,?,?,?,?)";
	private static final String SQL_FIND_TEACERS_COURSES = "SELECT t.id, t.fname, t.lname,t.email, c.name, d.course_id FROM users t INNER JOIN teachers_course d ON d.teacher_id=t.id INNER JOIN courses c ON c.id=d.course_id";
	private static final String SQL_INSERT_COURSE_TEACHER_SIGN = "INSERT INTO teachers_course (teacher_id, course_id) VALUES(?,?)";
	private static final String SQL_DELETE_COURSE_TEACHER_SIGN = "DELETE FROM teachers_course WHERE course_id=? AND teacher_id=?";
	private static final String SQL_UPDATE_COURSE_TEACHER_SIGN = "UPDATE teachers_course SET course_id=? WHERE course_id=? AND teacher_id=?";
	private static final String SQL_UPDATE_USER = "UPDATE users SET fname=?, lname=?, login=?, password=?, email=? WHERE id=?";
	private static final String SQL_DELETE_TEACHER = "DELETE FROM users WHERE id=?";
	private static final String SQL_DELETE_ALL_TEACHER_COURSES = "DELETE FROM teachers_course WHERE teacher_id=?";
	private static final String SQL_UPDATE_USER_ROLE = "UPDATE users SET role_id=? WHERE id=?";
	private static final String SQL_FIND_STUDENT_SIGN = "SELECT *FROM sign_course WHERE user_id=? AND course_id=?";
	private static final String SQL_SIGN_TO_COURSE = "INSERT INTO sign_course SET user_id=?, course_id=?";
	private static final String SQL_UPDATE_STUDENT_COUNT = "UPDATE courses SET student_count=? WHERE id=?";
	private static final String SQL_FIND_COURSE_BY_TEACHER_ID = "SELECT  c.id, c.name, c.theme, c.start_date, c.finish_date, c.status_id, c.student_count FROM courses c INNER JOIN teachers_course t ON c.id=t.course_id AND t.teacher_id=?";
	private static final String SQL_INSERT_COURSE_JOURNAL = "INSERT INTO journals (course_id, user_id, mark) VALUES(?, ?, 0)";
	private static final String SQL_FIND_ALL_FREE_COURSES = "SELECT courses.id, courses.name, courses.theme, courses.start_date, courses.finish_date, courses.status_id, courses.student_count FROM courses WHERE courses.id NOT IN (SELECT teachers_course.course_id FROM teachers_course)";
	private static final String SQL_FIND_JOURNAL = "SELECT s.fname, s.lname, s.id, c.name, j.course_id, j.mark FROM users s INNER JOIN journals j ON j.user_id=s.id INNER JOIN courses c ON c.id=j.course_id AND c.id=?";
	private static final String SQL_UPDATE_JOURNAL = "UPDATE journals SET mark=? WHERE course_id=? AND user_id=?";
private static final String SQL_FIND_COURSE_STUDENTS_AND_STATUS = "SELECT * FROM courses c INNER JOIN sign_course s ON c.id=s.course_id WHERE s.user_id=? AND c.status_id=?";
private static final String SQL_UPDATE_COURSE_STATE = "UPDATE courses SET status_id=? WHERE id=?";	
/**
	 * Returns a DB connection from the Pool Connections. Before using this
	 * method you must configure the Date Source and the Connections Pool in
	 * your WEB_APP_ROOT/META-INF/context.xml file.
	 * 
	 * @return DB connection.
	 */
	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
			// LOG.trace("getConnection ==> " + con);
		} catch (SQLException ex) {
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}
	// // //////////////////////////////////////////////////////////s
	// // Methods to obtain beans
	// // //////////////////////////////////////////////////////////

	/**
	 * Returns a user with the given login.
	 * 
	 * @param login
	 *            User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public User findUserByLogin(String login) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			// LOG.trace("findUserByLogin ==> " + login);
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = extractUser(rs);
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	public List<User> findUserGroupByRoleId(int roleId) throws DBException {
		List<User> userList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			// LOG.trace("findUserByLogin ==> " + login);
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USERS_BY_ROLE_ID);
			pstmt.setInt(1, roleId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				userList.add(extractUser(rs));
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return userList;
	}

	public List<JournalBean> findJournalByCourseId(int courseId) throws DBException {
		List<JournalBean> journal = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			// LOG.trace("findUserByLogin ==> " + login);
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_JOURNAL);
			pstmt.setInt(1, courseId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				journal.add(extractJournalBean(rs));
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return journal;
	}

	public List<Course> findAllTeachersCoursesById(int teacherId) throws DBException {
		List<Course> courseList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			// LOG.trace("findUserByLogin ==> " + login);
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_COURSE_BY_TEACHER_ID);
			pstmt.setInt(1, teacherId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				courseList.add(extractCourse(rs));
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return courseList;

	}

	public void updateStudentCount(int courseId) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		Course course;

		// System.out.println(newCount);
		try {

			con = getConnection();
			course = findCourseById(courseId);
			int oldCount = course.getCountStudent();
			System.out.println(oldCount + "oldCount");

			pstmt = con.prepareStatement(SQL_UPDATE_STUDENT_COUNT);
			int i = 1;

			pstmt.setInt(i++, ++oldCount);
			pstmt.setInt(i++, courseId);
			pstmt.executeUpdate();
			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}
	}

	public void updateCourseState(int courseStateId, int courseId) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		Course course = new Course();

		// System.out.println(newCount);
		try {

			con = getConnection();
			course = findCourseById(courseId);

			pstmt = con.prepareStatement(SQL_UPDATE_COURSE_STATE);
			int i = 1;

			pstmt.setInt(i++, courseStateId);
			pstmt.setInt(i++, courseId);
			pstmt.executeUpdate();
			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}
	}

	public void changeUserRole(int userId, int roleId) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_USER_ROLE);
			int i = 1;

			pstmt.setInt(i++, roleId);
			pstmt.setInt(i++, userId);
			pstmt.executeUpdate();
			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}
	}

	public User findUserById(int id) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			// LOG.trace("findUserByLogin ==> " + login);
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = extractUser(rs);
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	public Course findCourseById(int id) throws DBException {
		Course course = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_COURSE_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				course = extractCourse(rs);
				System.out.println("EXTRA");

			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return course;
	}

	public List<CourseRequestBean> findAllCoursesBean() throws DBException {
		List<CourseRequestBean> courseList = new ArrayList<CourseRequestBean>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt=con.prepareStatement(SQL_FIND_ALL_COURSE_BEAN);
		
			rs=pstmt.executeQuery();
			while (rs.next()) {
				courseList.add(extractCourseRequestBean(rs));
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return courseList;
	}

	public List<Course> findAllCourses() throws DBException {
		List<Course> courseList = new ArrayList<Course>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_COURSES);
			while (rs.next()) {
				courseList.add(extractCourse(rs));
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return courseList;
	}

	public List<Course> findAllFreeCourses() throws DBException {
		List<Course> courseList = new ArrayList<Course>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_FREE_COURSES);
			while (rs.next()) {
				courseList.add(extractCourse(rs));
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return courseList;
	}

	public List<StudentOrderRequestBean> findStudentAllOrder(int studentId) throws DBException {
		List<StudentOrderRequestBean> courseList = new ArrayList<StudentOrderRequestBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
		pstmt=con.prepareStatement(SQL_FIND_ALL_COURSE_BEAN);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				courseList.add(extractRequstBean(rs, studentId));
				
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return courseList;
	}

	public void updateUser(User user, int id) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		User newUser = user;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_USER);
			int i = 1;
			pstmt.setString(i++, newUser.getFname());
			pstmt.setString(i++, newUser.getLname());
			pstmt.setString(i++, newUser.getLogin());
			pstmt.setString(i++, newUser.getPassword());
			pstmt.setString(i++, newUser.getEmail());
			pstmt.setInt(i++, newUser.getId());
			pstmt.executeUpdate();
			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}
	}

	public void updateCourse(Course newCourse, int courseId) throws DBException {
		// LOG.trace("Update Room Busy State ==> " + conditionId);
		PreparedStatement pstmt = null;
		Connection con = null;
		Course course = newCourse;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_COURSE);
			int i = 1;
			pstmt.setString(i++, course.getName());
			pstmt.setString(i++, course.getTheme());
			pstmt.setDate(i++, course.getStartDate());
			pstmt.setDate(i++, course.getFinishDate());
			pstmt.setInt(i++, course.getState());
			pstmt.setInt(i++, courseId);
			pstmt.executeUpdate();
			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}
	}

	public void deleteTeacher(int teacherId) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			deleteAllTeacherSignCourse(teacherId);
			pstmt = con.prepareStatement(SQL_DELETE_TEACHER);
			int i = 1;
			pstmt.setInt(i++, teacherId);
			pstmt.executeUpdate();

			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}

	}

	public void updateTeacherSignCourse(int teacherId, int courseId, int newCourseId) throws DBException {
		// LOG.trace("Update Room Busy State ==> " + conditionId);
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_COURSE_TEACHER_SIGN);
			int i = 1;
			pstmt.setInt(i++, newCourseId);
			pstmt.setInt(i++, courseId);
			pstmt.setInt(i++, teacherId);
			pstmt.executeUpdate();

			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}
	}

	public void updateCoursesJournal(List<JournalBean> newJournal) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			int count;
			con = getConnection();
			for (int i = 0; i < newJournal.size(); i++) {
				count = 1;
				pstmt = con.prepareStatement(SQL_UPDATE_JOURNAL);
				pstmt.setInt(count++, newJournal.get(i).getMark());
				pstmt.setInt(count++, newJournal.get(i).getCourseId());
				pstmt.setInt(count++, newJournal.get(i).getStudentId());
				pstmt.executeUpdate();
			}

			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}

	}
public List<Course> findCoursesByStudentIdAndState(int studentId, int stateId) throws DBException{
	List<Course> courseList = new ArrayList<Course>();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection con = null;

	try {
		con = getConnection();
		pstmt = con.prepareStatement(SQL_FIND_COURSE_STUDENTS_AND_STATUS);
		int i =1;
		pstmt.setInt(i++, studentId);
		pstmt.setInt(i++, stateId);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			courseList.add(extractCourse(rs));
		}
		commit(con);
	} catch (SQLException ex) {
		rollback(con);
		// LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMS, ex);
		throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
	} finally {
		close(con, pstmt, rs);
	}
	return courseList;
}
	public void deleteTeacherSignCourse(int teacherId, int courseId) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_COURSE_TEACHER_SIGN);
			int i = 1;
			pstmt.setInt(i++, courseId);
			pstmt.setInt(i++, teacherId);
			pstmt.executeUpdate();

			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}
	}

	public void deleteAllTeacherSignCourse(int teacherId) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_ALL_TEACHER_COURSES);
			int i = 1;
			pstmt.setInt(i++, teacherId);
			pstmt.executeUpdate();

			commit(con);
			// LOG.trace("Room, with id= " + roomId + " turned in requestState="
			// + conditionId);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_UPDATE_ROOM_CONDITION_STATE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPADATE_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}
	}

	public void insertUserIntoDatabase(User user) throws DBException {
		User u = user;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_STUDENT);

			int i = 1;
			pstmt.setString(i++, u.getFname());
			pstmt.setString(i++, u.getLname());
			pstmt.setString(i++, u.getLogin());
			pstmt.setString(i++, u.getPassword());
			pstmt.setInt(i++, u.getRoleid());
			pstmt.setString(i++, u.getEmail());
			System.out.println("OK");
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error("Cannot insert a booking request ", ex);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public void insertIntoDataBaseJournal(int courseId, int studentId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_COURSE_JOURNAL);

			int i = 1;

			pstmt.setInt(i++, courseId);
			pstmt.setInt(i++, studentId);
			pstmt.executeUpdate();

			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error("Cannot insert a booking request ", ex);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public void insertIntoDatabaseSignCourse(int studentId, int courseId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_SIGN_TO_COURSE);

			int i = 1;
			pstmt.setInt(i++, studentId);
			pstmt.setInt(i++, courseId);
			updateStudentCount(courseId);
			insertIntoDataBaseJournal(courseId, studentId);
			pstmt.executeUpdate();

			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error("Cannot insert a booking request ", ex);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public void deleteSignByCourseId(int course_id) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_SIGN_COURSE_BY_COURSE_ID);
			pstmt.setInt(1, course_id);
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_BOOKING_REQUESTS_BY_USER_ID,
			// ex);
			throw new DBException(Messages.ERR_CANNOT_DELETE_SIGN_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}

	}

	public void deleteTeacherCourseByCourseId(int course_id) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_TEACHER_COURSE_BY_COURSE_ID);
			pstmt.setInt(1, course_id);
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_BOOKING_REQUESTS_BY_USER_ID,
			// ex);
			throw new DBException(Messages.ERR_CANNOT_DELETE_SIGN_COURSE, ex);
		} finally {
			close(con, pstmt, null);
		}

	}

	public void deleteJournalByCourseId(int course_id) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_JOURNAL_BY_COURSE_ID);
			pstmt.setInt(1, course_id);
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_BOOKING_REQUESTS_BY_USER_ID,
			// ex);
			throw new DBException(Messages.ERR_CANNOT_DELETE_JOURNAL, ex);
		} finally {
			close(con, pstmt, null);
		}

	}

	public void deleteCourseById(int course_id) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			deleteSignByCourseId(course_id);
			deleteJournalByCourseId(course_id);
			deleteTeacherCourseByCourseId(course_id);
			pstmt = con.prepareStatement(SQL_DELETE_COURSE_BY_ID);
			pstmt.setInt(1, course_id);
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_BOOKING_REQUESTS_BY_USER_ID,
			// ex);
			throw new DBException(Messages.ERR_CANNOT_DELETE_COURSE_BY_ID, ex);
		} finally {
			close(con, pstmt, null);
		}

	}

	public List<TeacherRequestBean> findAllTeachersCourses() throws DBException {
		List<TeacherRequestBean> teacherList = new ArrayList<TeacherRequestBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_TEACERS_COURSES);
			while (rs.next()) {
				teacherList.add(extractTacherRequestBean(rs));
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return teacherList;
	}

	public void insertCourseToTeacherSign(int teacher_id, int course_id) throws DBException {
		int c_id = course_id;
		int t_id = teacher_id;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_COURSE_TEACHER_SIGN);

			int i = 1;

			pstmt.setInt(i++, t_id);
			pstmt.setInt(i++, c_id);
			System.out.println("OK");
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error("Cannot insert a booking request ", ex);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public boolean studentSignedOnCourse(int studentId, int courseId) throws DBException {
		int studId = studentId;
		int courId = courseId;
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_STUDENT_SIGN);
			int i = 1;
			pstmt.setInt(i++, studId);
			pstmt.setInt(i++, courId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			close(pstmt);
			close(con);
		}
		return false;

	}

	public void insertCourseIntoDataBase(Course course) throws DBException {
		Course c = course;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_COURSE);

			int i = 1;
			pstmt.setString(i++, c.getName());
			pstmt.setString(i++, c.getTheme());
			pstmt.setDate(i++, c.getStartDate());
			pstmt.setDate(i++, c.getFinishDate());
			pstmt.setInt(i++, c.getState());
			pstmt.setInt(i++, c.getCountStudent());
			System.out.println("OK");
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			// LOG.error("Cannot insert a booking request ", ex);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	/**
	 * 
	 * Closes a connection.
	 * 
	 * @param con
	 *            Connection to be closed.
	 */
	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				// LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	/**
	 * Closes a statement object.
	 */
	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				// LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	/**
	 * Closes a result set object.
	 */
	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				// LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	/**
	 * Closes resources.
	 */
	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	/**
	 * Commit the given connection.
	 * 
	 * @param con
	 *            Connection to be commited.
	 */
	private void commit(Connection con) {
		if (con != null) {
			try {
				con.commit();
			} catch (SQLException ex) {
				// LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	/**
	 * Rollbacks a connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked.
	 */
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				// LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	private User extractUser(ResultSet rs) throws SQLException {
		// LOG.trace("Try to extract user:");
		User user = new User();
		user.setId((rs.getInt(Fields.ENTITY_ID)));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setFname(rs.getString(Fields.USER_FIRST_NAME));
		user.setLname(rs.getString(Fields.USER_LAST_NAME));
		user.setRoleid(rs.getInt(Fields.USER_ROLE_ID));
		user.setEmail(rs.getString(Fields.USER_EMAIL));
		return user;
	}

	private Course extractCourse(ResultSet rs) throws SQLException {
		Course course = new Course();
		course.setId(rs.getInt(Fields.ENTITY_ID));
		course.setName(rs.getString(Fields.COURSE_NAME));
		course.setTheme(rs.getString(Fields.COURSE_THEME));
		course.setStartDate(rs.getDate(Fields.START_DATE));
		course.setFinishDate(rs.getDate(Fields.FINISH_DATE));

		course.setState(rs.getInt(Fields.STATUS_ID));
		course.setCountStudent(rs.getInt(Fields.STUDENT_COUNT));
		return course;
	}

	private CourseRequestBean extractCourseRequestBean(ResultSet rs) throws SQLException {
		CourseRequestBean course = new CourseRequestBean();
		course.setId(rs.getInt(Fields.ENTITY_ID));
		course.setCourse_name(rs.getString(Fields.COURSE_NAME));
		course.setCourse_theme(rs.getString(Fields.COURSE_THEME));
		course.setStartCourse(rs.getDate(Fields.START_DATE));
		course.setFinishCourse(rs.getDate(Fields.FINISH_DATE));
		course.setTeacher_id(rs.getInt(Fields.TEACHER_ID));
		course.setTeacherFirstName(rs.getString(Fields.USER_FIRST_NAME));
		course.setTeacherLastName(rs.getString(Fields.USER_LAST_NAME));
		course.setState(CourseState.getCourseState(new Integer(rs.getString(Fields.STATUS_ID))).toString());
		course.setDuration(
				DateUtils.fullDaysBetweenDates(rs.getDate(Fields.START_DATE), rs.getDate(Fields.FINISH_DATE)));
course.setStudentCount(rs.getInt(Fields.STUDENT_COUNT));
		return course;
	}

	private StudentOrderRequestBean extractRequstBean(ResultSet rs, int studentId) throws SQLException, DBException {
		StudentOrderRequestBean course = new StudentOrderRequestBean();
		course.setId(rs.getInt(Fields.ENTITY_ID));
		course.setCourse_name(rs.getString(Fields.COURSE_NAME));
		course.setCourse_theme(rs.getString(Fields.COURSE_THEME));
		course.setStartCourse(rs.getDate(Fields.START_DATE));
		course.setFinishCourse(rs.getDate(Fields.FINISH_DATE));
		course.setTeacher_id(rs.getInt(Fields.TEACHER_ID));
		course.setTeacherFirstName(rs.getString(Fields.USER_FIRST_NAME));
		course.setTeacherLastName(rs.getString(Fields.USER_LAST_NAME));
		course.setState(CourseState.getCourseState(new Integer(rs.getString(Fields.STATUS_ID))).toString());
		course.setDuration(
				DateUtils.fullDaysBetweenDates(rs.getDate(Fields.START_DATE), rs.getDate(Fields.FINISH_DATE)));
		course.setSigned(studentSignedOnCourse(studentId, course.getId()));
		course.setStudentCount(rs.getInt(Fields.STUDENT_COUNT));
		return course;
	}

	private TeacherRequestBean extractTacherRequestBean(ResultSet rs) throws SQLException {
		TeacherRequestBean teacher = new TeacherRequestBean();
		teacher.setId(rs.getInt(Fields.ENTITY_ID));
		teacher.setCourseName(rs.getString(Fields.COURSE_NAME));
		teacher.setEmail(rs.getString(Fields.USER_EMAIL));
		teacher.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
		teacher.setLastName(rs.getString(Fields.USER_LAST_NAME));
		teacher.setCourseId(rs.getInt(Fields.COURSE_ID));

		return teacher;

	}

	private JournalBean extractJournalBean(ResultSet rs) throws SQLException {
		JournalBean journal = new JournalBean();
		// journal.setId(rs.getInt(Fields.ENTITY_ID));
		journal.setCourseId(rs.getInt(Fields.COURSE_ID));
		journal.setStudentFisrtName(rs.getString(Fields.USER_FIRST_NAME));
		journal.setStudentLastName(rs.getString(Fields.USER_LAST_NAME));
		journal.setStudentId(rs.getInt(Fields.ENTITY_ID));
		journal.setMark(rs.getInt(Fields.MARK));

		return journal;

	}
}
