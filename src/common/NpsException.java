package common;

/**
 * Description:	Nps specific exception to allow errors to be thrown rather
 * 			than continually checked by status codes. Allows catching of this error
 * 			so that other exceptions are not accidently logged and swallowed.
 * 
 * 			Extends Exception so it is checked i.e. for recoverable conditions, 
 * 			using a try-catch on the exception explicitly.
 * 			If it is unchecked, it should extend RuntimeException i.e. for 
 * 			unrecoverable condition, like programming errors, with no need for try-catch.
 * 
 * @author alison.j.king
 *
 */
public class NpsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private Status status;
	private int statusCode;
	private String message;

	public NpsException(Status status, String message) {
		super(message);
		this.status = status;
		this.statusCode = status.getCode();
		this.message = message;
	}
	
	/**
	 * Constructor. Initialise from a status code integer and an explanatory
	 * message. Retains the argument statusCode even if this does not match a
	 * know Status code enumeration, so original code can still be used for
	 * debugging.
	 */
	public NpsException(int statusCode, String message) {
		super(message);
		this.status = Status.fromCode(statusCode);
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public Status getStatus() {
		return status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return "NpsException:(" + status + "): " + message;
	}	
}
