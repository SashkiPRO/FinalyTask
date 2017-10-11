package ua.nure.botsula.st4.bean;

import ua.nure.botsula.st4.entity.Entity;

public class JournalBean extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3975589822269319918L;

	public JournalBean() {
		super();
	}

	public String getStudentFisrtName() {
		return studentFisrtName;
	}

	public void setStudentFisrtName(String sundentFisrtName) {
		this.studentFisrtName = sundentFisrtName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getMark() {
		return mark;
	}

	@Override
	public String toString() {
		return "JournalBean [sundentFisrtName=" + studentFisrtName + ", studentLastName=" + studentLastName
				+ ", courseId=" + courseId + ", studentId=" + studentId + ", mark=" + mark + "]";
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	private String studentFisrtName;
	private String studentLastName;
	private int courseId;
	private int studentId;
	private int mark;

}
