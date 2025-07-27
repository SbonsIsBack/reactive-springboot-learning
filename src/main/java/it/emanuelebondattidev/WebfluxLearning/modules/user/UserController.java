package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.net.URI;
import java.util.Arrays;
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
import it.emanuelebondattidev.WebfluxLearning.utils.UUIDUtils;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@GetMapping
	public Flux<User> getAll( 
			@RequestParam(defaultValue = "0") int offset ,
			@RequestParam(defaultValue = "10") int limit 
			) {
		
		
		System.out.println("getAll");
		System.out.println( "offset : " + offset );
		
		return Flux.fromIterable( Arrays.<User>asList(
				
				new User( "Nome1", "Cognome1", "mail@gmail.com", UUIDUtils.generate() ),
				new User( "Nome2", "Cognome2", "mail2@gmail.com", UUIDUtils.generate() ),
				new User( "Nome3", "Cognome3", "mail3@gmail.com", UUIDUtils.generate() ),
				new User( "Nome4", "Cognome4", "mail4@gmail.com", UUIDUtils.generate() ),
				new User( "Nome5", "Cognome5", "mail5@gmail.com", UUIDUtils.generate() ),
				new User( "Nome6", "Cognome6", "mail6@gmail.com", UUIDUtils.generate() ),
				new User( "Nome7", "Cognome7", "mail7@gmail.com", UUIDUtils.generate() ),
				new User( "Nome8", "Cognome8", "mail8@gmail.com", UUIDUtils.generate() ),
				new User( "Nome9", "Cognome9", "mail9@gmail.com", UUIDUtils.generate() ),
				new User( "Nome10", "Cognome10", "mail10@gmail.com", UUIDUtils.generate() ),
				new User( "Nome11", "Cognome11", "mail11@gmail.com", UUIDUtils.generate() ),
				new User( "Nome12", "Cognome12", "mail12@gmail.com", UUIDUtils.generate() )
				
				) ).take( limit );
		
	}
	
	@PostMapping
//	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono< ResponseEntity<User> > createUser( @Valid @RequestBody Mono<CreateUserRequest> request ) {
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
			
			return Mono.justOrEmpty( 
					ResponseEntity
					.status( returnStatus )
					.location( URI.create( "/users/" + user.getId() ) )
					.body( user ) 
					);
			
		});
		
		//is better a single map/flatMap handling object mapping -> saving -> returning
		// or multiple map/flatMap : one for operation?
		//maybe with multiple ones is it more clear where the code ends and why.
		//I'll leave a single one for now
		
	}
	
	
	@GetMapping("/{userId}")
	public Mono< User > getUser( @PathVariable UUID userId ) {
		System.out.println( "getUser" );
		
		return Mono.justOrEmpty( new User( "Emanuele", "Bondatti", "bondattiemanuele@gmail.com", UUIDUtils.generate() ) );
		
	}
	
	

	
}
