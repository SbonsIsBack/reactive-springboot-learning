package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1557757378440383762L;
	
	private String firstName;
	private String lastName;
	private String email;
	private UUID id;
}
