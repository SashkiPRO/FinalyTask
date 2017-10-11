package ua.nure.botsula.st4.bean;

import java.sql.Date;

import ua.nure.botsula.st4.entity.Entity;

public class CourseRequestBean extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8523502083151250147L;

	public CourseRequestBean() {
		super();
	}

	private String course_name;
	private String course_theme;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course_name == null) ? 0 : course_name.hashCode());
		result = prime * result + ((course_theme == null) ? 0 : course_theme.hashCode());
		result = prime * result + (int) (duration ^ (duration >>> 32));
		result = prime * result + ((finishCourse == null) ? 0 : finishCourse.hashCode());
		result = prime * result + ((startCourse == null) ? 0 : startCourse.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + studentCount;
		result = prime * result + ((teacherFirstName == null) ? 0 : teacherFirstName.hashCode());
		result = prime * result + ((teacherLastName == null) ? 0 : teacherLastName.hashCode());
		result = prime * result + teacher_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseRequestBean other = (CourseRequestBean) obj;
		if (course_name == null) {
			if (other.course_name != null)
				return false;
		} else if (!course_name.equals(other.course_name))
			return false;
		if (course_theme == null) {
			if (other.course_theme != null)
				return false;
		} else if (!course_theme.equals(other.course_theme))
			return false;
		if (duration != other.duration)
			return false;
		if (finishCourse == null) {
			if (other.finishCourse != null)
				return false;
		} else if (!finishCourse.equals(other.finishCourse))
			return false;
		if (startCourse == null) {
			if (other.startCourse != null)
				return false;
		} else if (!startCourse.equals(other.startCourse))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (studentCount != other.studentCount)
			return false;
		if (teacherFirstName == null) {
			if (other.teacherFirstName != null)
				return false;
		} else if (!teacherFirstName.equals(other.teacherFirstName))
			return false;
		if (teacherLastName == null) {
			if (other.teacherLastName != null)
				return false;
		} else if (!teacherLastName.equals(other.teacherLastName))
			return false;
		if (teacher_id != other.teacher_id)
			return false;
		return true;
	}

	private String teacherFirstName;
	private String teacherLastName;
	private int teacher_id;
	private int studentCount;
	private Date startCourse;
	private Date finishCourse;
	private long duration;
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStartCourse() {
		return startCourse;
	}

	public void setStartCourse(Date startCourse) {
		this.startCourse = startCourse;
	}

	public Date getFinishCourse() {
		return finishCourse;
	}

	public void setFinishCourse(Date finishCourse) {
		this.finishCourse = finishCourse;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_theme() {
		return course_theme;
	}

	public void setCourse_theme(String course_theme) {
		this.course_theme = course_theme;
	}

	public String getTeacherFirstName() {
		return teacherFirstName;
	}

	public void setTeacherFirstName(String teacherFistName) {
		this.teacherFirstName = teacherFistName;
	}

	public String getTeacherLastName() {
		return teacherLastName;
	}

	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public int getStudentCount() {
		return studentCount;
	}

	@Override
	public String toString() {
		return "CourseRequestBean [course_name=" + course_name + ", course_theme=" + course_theme + ", teacherFistName="
				+ teacherFirstName + ", teacherLastName=" + teacherLastName + ", teacher_id=" + teacher_id
				+ ", studentCount=" + studentCount + "]";
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

}
