package it.emanuelebondattidev.WebfluxLearning.modules.user;

import reactor.core.publisher.Mono;

public interface CustomUserRepository {

	public Mono<User> createUser( Mono<User> dto );
	
}
