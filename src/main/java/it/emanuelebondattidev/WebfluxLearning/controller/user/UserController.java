package it.emanuelebondattidev.WebfluxLearning.controller.user;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.emanuelebondattidev.WebfluxLearning.controller.user.request.CreateUserRequest;
import it.emanuelebondattidev.WebfluxLearning.model.User;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@GetMapping
	public Flux<User> getAll() {
		System.out.println("getAll");
		return Flux.fromIterable( Arrays.<User>asList() );
		
	}
	
	@PostMapping
	public Mono<User> createUser( @Valid @RequestBody CreateUserRequest request ) {
		System.out.println("createUser");
		return Mono.justOrEmpty( Optional.ofNullable( new User( request.getFirstName(), request.getLastName() ) ) );
		
	}
	
	

	
}
