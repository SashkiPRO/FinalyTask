package ua.nure.botsula.st4.bean;



public class StudentOrderRequestBean extends CourseRequestBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7019962856794086703L;
public StudentOrderRequestBean() {
	super();
}
	private boolean signed;
	public boolean isSigned() {
		return signed;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (signed ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentOrderRequestBean other = (StudentOrderRequestBean) obj;
		if (signed != other.signed)
			return false;
		return true;
	}
	public void setSigned(boolean signed) {
		this.signed = signed;
	}
	
	
}
