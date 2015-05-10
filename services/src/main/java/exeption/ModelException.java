package exeption;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended exception class to work with business logic
 */


public class ModelException extends Exception {

	private static final long serialVersionUID = -3541079648901723384L;

	public ModelException() {
	}

	public ModelException(String message) {
		super(message);
	}

	public ModelException(Throwable cause) {
		super(cause);
	}

	public ModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModelException(String message, Throwable cause,
						  boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
