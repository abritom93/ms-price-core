package cl.price.core.config.exceptions;

public class GenericException extends RuntimeException{
	
	private static final long serialVersionUID = -9079454849611061074L;

	public GenericException() {
		super();
	}

	public GenericException(final String message) {
		super(message);
	}

}
