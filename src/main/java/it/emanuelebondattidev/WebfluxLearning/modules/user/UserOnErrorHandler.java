package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.util.function.Function;

import org.springframework.dao.DuplicateKeyException;

import it.emanuelebondattidev.WebfluxLearning.modules.user.exceptions.MailAlreadyInUseException;

public class UserOnErrorHandler {

	public static Function<Throwable,Throwable> mapExceptions() {
		return throwable -> {
			
			if (throwable instanceof DuplicateKeyException) {
                return new MailAlreadyInUseException("");
            }
            return throwable;
		};
	}
}
