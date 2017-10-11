package ua.nure.botsula.st4.exception;

/**
 * An exception that provides information on an application error.
 * 
 * @author M.Pinchuk
 * 
 */
public class AppException extends Exception {
	
	private static final long serialVersionUID = -3712898036835855807L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

}
