package it.emanuelebondattidev.WebfluxLearning.modules.user.request;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateUserRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8031743873973707357L;
	
	
	@NotBlank( message = "First name cannot be empty" )
	@Size( min = 2, max = 70, message = "First name must be in 2-70 characters range." )
	private String firstName;
	
	@NotBlank( message = "Last name cannot be empty" )
	@Size( min = 2, max = 70, message = "Last name must be in 2-70 characters range." )
	private String lastName;
	
	@NotBlank( message = "Email cannot be empty" )
	@Email( message = "Must provide a valid email" )
	private String email;
	
	@NotBlank( message = "Password cannot be empty" )
	@Size( min = 9, max = 18, message = "Password must be in 9-18 characters range." )
	private String password;
	
	
	
}
