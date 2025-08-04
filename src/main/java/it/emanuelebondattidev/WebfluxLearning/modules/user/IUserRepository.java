package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;

public interface IUserRepository extends ReactiveCrudRepository<UserEntity, UUID>, CustomUserRepository{
	
	Mono<User> findByEmail( String email );

}
