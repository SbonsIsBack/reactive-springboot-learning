package it.emanuelebondattidev.WebfluxLearning.modules.pokemon.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BasePokemonData {

	private String name;
	@JsonIgnore
	private String url;
	
}
