package it.emanuelebondattidev.WebfluxLearning.modules.pokemon;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Table("pokemon")
@Data
@ToString
public class PokemonEntity {

	@Id
	private Integer id;
	
	@Size( min = 2, max=30 )
	private String name;
	
	@Size( min = 2, max=30 )
	private String type;
	
	@Size( min = 0, max = 100 )
	private String description;
	
}
