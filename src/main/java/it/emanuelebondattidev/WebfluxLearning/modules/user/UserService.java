package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import it.emanuelebondattidev.WebfluxLearning.modules.user.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

	private final IUserRepository userRepo;
	
	
	public Mono<User> createUser( Mono<CreateUserRequest> req ) {
		return userRepo.createUser( req.map( this::toDTO ) );
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
