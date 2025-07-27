package it.emanuelebondattidev.WebfluxLearning.controller.user;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.emanuelebondattidev.WebfluxLearning.controller.user.request.CreateUserRequest;
import it.emanuelebondattidev.WebfluxLearning.model.User;
import it.emanuelebondattidev.WebfluxLearning.utils.UUIDUtils;
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
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<ResponseEntity<User>> createUser( @Valid @RequestBody Mono<CreateUserRequest> request ) {
		System.out.println("createUser");
		
		
		
		return request.flatMap( x -> {
			
			//is it efficient to unpack content and repack it inside a new mono?
			//maybe using .map() instead would've been better? will find out.
			
			//from documentation:
			//.flatMap() mentions about unpacking, converting, and repacking inside a different Mono.
			//.map() only talks about changing value of the existing Mono. Maybe could be better.
			
			User user = new User( 
					x.getFirstName(), 
					x.getLastName(), 
					x.getEmail(), 
					UUIDUtils.generate() 
					);
			
			//is badRequest the right one?
			HttpStatus returnStatus = user != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
			
			return Mono.justOrEmpty( ResponseEntity.status( returnStatus ).body( user ) );
			
		});
		
		//is better a single map/flatMap handling object mapping -> saving -> returning
		// or multiple map/flatMap : one for operation?
		//maybe with multiple ones is it more clear where the code ends and why.
		//I'll leave a single one for now
		
	}
	
	

	
}
