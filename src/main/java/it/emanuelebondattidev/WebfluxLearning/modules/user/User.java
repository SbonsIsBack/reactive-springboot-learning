package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.io.Serializable;
import java.util.UUID;

import it.emanuelebondattidev.WebfluxLearning.utils.UUIDUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1557757378440383762L;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	@NonNull
	private UUID id = UUIDUtils.generate();
}
