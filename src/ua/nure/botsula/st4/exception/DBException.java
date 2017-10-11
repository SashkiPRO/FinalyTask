package ua.nure.botsula.st4.exception;

/**
 * An exception that provides information on a database access error.
 * 
 * @author M.Pinchuk
 * 
 */
public class DBException extends AppException {

	private static final long serialVersionUID = 3393046720797622194L;

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

}
