package exception;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended exception class to database access
 */

public class DataAccessException extends Exception {

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1026848613205784955L;

}
