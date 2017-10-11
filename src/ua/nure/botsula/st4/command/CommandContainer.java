package ua.nure.botsula.st4.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * Holder for all commands.<br/>
 * 
 * @author M.Pinchuk
 * 
 */
public class CommandContainer {
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		commands.put("about_courses", new AboutCoursesCommand());
		commands.put("registration_student", new RegistrationStudentCommand());
		commands.put("registration", new RegistrationPageCommand());
		commands.put("login_command", new LoginCommand());
		commands.put("admin_menu", new AdminMenuList());
		commands.put("teacher_list", new TeachersListCommand());
		commands.put("course_list", new CourseListCommand());
		commands.put("edit_course", new EditCourseCommand());
		commands.put("logout_command", new LogoutCommand());
		commands.put("update_course", new UpdateCourseCommand());
		commands.put("delete_course", new DleteCourseCommand());
		commands.put("add_course", new AddCourseCommand());
		commands.put("all_teachers", new AllTeacherCommand());
		commands.put("add_course_to_teacher", new  AddCourseToTeacher());
		commands.put("edit_course_request", new EditCourseRequestBeanCommand());
		commands.put("update_teachers_course", new UpdateCourseSign());
		commands.put("delete_teacher_sign", new DeleteTeacherSignCourse());
		commands.put("edit_teacher_menu", new EditTeacherCommand());
		commands.put("update_teacher", new UpdateTeacherCommand());
		commands.put("delete_teacher", new  DeleteTeacher());
		commands.put("register_teacher_menu", new AddTeacherMenu());
		commands.put("add_teacher", new AddTeacherCommand());
		commands.put("user_info", new UserInfoCommand());
		commands.put("students_list", new StudentListCommand());
		commands.put("block_student", new BlockedStudentCommand());
		commands.put("unblock_student", new UnblockStudentCommand());
		commands.put("main_page", new MainPageCommand());
		commands.put("sign_course", new SignedToCourse());
		commands.put("teacher_courses", new TeacherCoursesList());
		commands.put("journal_show", new JournalShowCommand());
		commands.put("edit_unit", new EditUnitCommand());
		commands.put("update_journal", new UpdateJournalCommand());
		commands.put("students_courses_kind", new StudentsCoursesKindCommand());
		commands.put("students_courses_list", new StudentsCoursesListCommand());
		commands.put("view_journal_by_student", new ViewJournalByStudent());
		commands.put("about_teachers", new  AboutTeachersPage());
		/*// common commands
		commands.put("login", new LoginCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("view_settings", new ViewSettingsCommand());

		// client commands
		commands.put("list_rooms_and_rates", new ListRoomsCommand());
		commands.put("about_hotel", new AboutHotelCommand());
		commands.put("contacts", new ContactsCommand());
		commands.put("booking_room_request", new BookingRoomRequestCommand());
		commands.put("booking_request", new BookingRequestCommand());
		commands.put("client_profile", new ClientProfileCommand());
		commands.put("delete_booking_request",
				new DeleteBookingRequestCommand());
		commands.put("pay", new PaymentCommand());
		commands.put("booked_booking_request", new BookedBookingRequest());
		

		// admin commands
		commands.put("admin_profile", new AdminProfileCommand());
		commands.put("list_sent_booking_requests", new ListSentRequestsCommand());
		commands.put("process_request", new ProcessRequestCommand());
		commands.put("set_room_in_request", new SetRoomInRequestCommand());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
*/	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}
}
