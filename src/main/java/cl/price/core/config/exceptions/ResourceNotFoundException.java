package cl.price.core.config.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -9079454849611061074L;

	public ResourceNotFoundException(final String message) {
		super(message);
	}

}
