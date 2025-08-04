package it.emanuelebondattidev.WebfluxLearning.modules.user.exceptions;

public class MailAlreadyInUseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MailAlreadyInUseException( String email ) {
		super( "Mail %s is already in use".formatted( email ) );
	}

}
