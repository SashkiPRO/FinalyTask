package ua.nure.botsula.st4.entity;

public class User extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lname;
	private String fname;
	private String login;
	private String password;
	private int roleid;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pasword) {
		this.password = pasword;
	}

	@Override
	public String toString() {
		return "User [lname=" + lname + ", fname=" + fname + ", login=" + login + ", pasword=" + password + ", roleid="
				+ roleid + "]";
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

}
