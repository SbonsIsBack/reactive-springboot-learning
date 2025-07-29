package it.emanuelebondattidev.WebfluxLearning.modules.pokemon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.emanuelebondattidev.WebfluxLearning.modules.pokemon.response.PokemonData;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
public class PokemonController {

	
	private final PokemonService pokeService;
	
	
	@GetMapping
	public Flux<PokemonData> getAll( 
			@RequestParam(defaultValue = "0",name = "offset") int offset,
			@RequestParam(defaultValue = "50",name="limit") int limit
			) {
		
		return pokeService.getPokemonData(offset, limit);
				
		
		
	}
	
	
}
