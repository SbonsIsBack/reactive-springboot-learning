package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.emanuelebondattidev.WebfluxLearning.modules.user.exceptions.MailAlreadyInUseException;
import it.emanuelebondattidev.WebfluxLearning.modules.user.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

	private final IUserRepository userRepo;
	
	
	public Mono<User> createUser( Mono<CreateUserRequest> req ) {
//		return req.map(  this::toDTO )
//				 .flatMap(user ->
//		            userRepo.findByEmail(user.getEmail() )
//		                .flatMap( existingUser -> Mono.<User>error(new MailAlreadyInUseException( existingUser.getEmail() ) ) )
//		                .switchIfEmpty( userRepo.createUser( Mono.just( user ) ) )
//		        );
		return req.mapNotNull( this::toDTO )
				.flatMap( user -> userRepo.createUser( Mono.just( user ) ) )
				.onErrorMap( UserOnErrorHandler.mapExceptions() );
				
				
		
	}
	
	public Flux<User> getUsers( int limit ){
		return userRepo.findAll()
				.map( this::toDTO )
				.take( limit );
	}
	
	public Mono<User> getUser( UUID id ) {
		return userRepo.findById( id )
				.map( this::toDTO );
	}
	
	
	private User toDTO( CreateUserRequest from ) {
		
		User to = new User();
		
		if( from != null ) {
			BeanUtils.copyProperties( from, to );
		}
		
		return to;
	}
	
	private User toDTO( UserEntity from ) {
		
		User to = new User();
		
		if( from != null ) {
			BeanUtils.copyProperties( from, to );
		}
		
		return to;
	}
}
