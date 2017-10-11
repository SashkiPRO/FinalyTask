package ua.nure.botsula.st4.entity;

import java.sql.Date;

public class Course extends Entity {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String theme;
	private Date startDate;
	private Date finishDate;
	private int stateId;
	private int countStudent;
	public int getId() {
		return id;
	}
	public int getCountStudent() {
		return countStudent;
	}
	public void setCountStudent(int countStudent) {
		this.countStudent = countStudent;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getState(){
		return stateId;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setState(int state_id){
		this.stateId=state_id;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", theme=" + theme + ", theacher_id="
				+ ", startDate=" + startDate + ", finishDate=" + finishDate + ", state_id=" + stateId
				+ ", countStudent=" + countStudent + "]";
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

}
