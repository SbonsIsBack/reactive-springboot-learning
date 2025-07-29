package it.emanuelebondattidev.WebfluxLearning.modules.pokemon.response;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PokePaginationResponse<T> {
	
	private int count;
	private String next;
	private String previous;
	public List<T> results;
	
}
