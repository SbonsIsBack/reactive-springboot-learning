package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.emanuelebondattidev.WebfluxLearning.modules.user.request.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@GetMapping
	public Flux<User> getAll( 
			@RequestParam(defaultValue = "0") int offset ,
			@RequestParam(defaultValue = "10") int limit 
			) {
		
		
		System.out.println("getAll");
		System.out.println( "offset : " + offset );
		
		
		return service.getUsers( limit );
		
	}
	
	@PostMapping
//	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono< ResponseEntity<User> > createUser( @Valid @RequestBody Mono<CreateUserRequest> request ) {
		System.out.println("createUser");
		
		
		return service.createUser( request )
				.map( x -> {
					
					HttpStatus status = x != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
					
					return ResponseEntity
							.status(status)
							.location( URI.create( "/users" + x.getId() ) )
							.build();
					
				});
		
	}
	
	
	@GetMapping("/{userId}")
	public Mono< User > getUser( @PathVariable UUID userId ) {
		System.out.println( "getUser" );
		
		return service.getUser( userId );
	}
	
	

	
}
