package it.emanuelebondattidev.WebfluxLearning.modules.user.exceptions.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import it.emanuelebondattidev.WebfluxLearning.modules.user.exceptions.MailAlreadyInUseException;
import reactor.core.publisher.Mono;

@RestControllerAdvice(basePackages = "it.emanuelebondattidev.WebfluxLearning.modules.user")
public class UserExceptionsAdvice {

	@ExceptionHandler( MailAlreadyInUseException.class )
	public Mono<ResponseEntity<String>> handleMailAlreadyInUseException( MailAlreadyInUseException ex ) {
		return Mono.just( ResponseEntity.status( HttpStatus.CONFLICT ).body( ex.getMessage() ) );
		
	}
}
