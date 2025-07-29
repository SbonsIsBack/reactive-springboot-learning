package it.emanuelebondattidev.WebfluxLearning.modules.pokemon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import it.emanuelebondattidev.WebfluxLearning.modules.pokemon.response.BasePokemonData;
import it.emanuelebondattidev.WebfluxLearning.modules.pokemon.response.PokePaginationResponse;
import it.emanuelebondattidev.WebfluxLearning.modules.pokemon.response.PokemonData;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PokemonService {

	private ParameterizedTypeReference<PokePaginationResponse<BasePokemonData>> baseDataRef =
		    new ParameterizedTypeReference<>() {};
		    
		    
	@Value("${controller.pokemon.remote.domain:}")
	private String pokeApiDomain;
	
	WebClient webClient = WebClient.create();
	
	
	public Flux<PokemonData> getPokemonData(int offset,int limit) {
		
//		System.out.println( "Offset : " + offset );
//		System.out.println( "Limit : " + limit );
		final int simultaneousRequestsNumber = 5;
			    
		String baseDataUrl = "%s/v2/pokemon/".formatted( pokeApiDomain );
		
				
				return webClient.get()
//				        .uri( baseDataUrl + "?offset={offset}&limit={limit}", offset, limit)
				        .uri( baseDataUrl )
				        .retrieve()
				        .bodyToMono( baseDataRef )
				        .flatMapMany( resp -> Flux.fromIterable(resp.getResults()) )
				        .flatMap(result -> webClient.get()
				            .uri(result.getUrl())
				            .retrieve()
				            .bodyToMono(PokemonData.class)
				            .onErrorResume( err -> {
				            	System.out.println(err);
				            	return Mono.empty();
				            			} )
				            , 
				            simultaneousRequestsNumber
				        );
				
				
	}
	
	
}
